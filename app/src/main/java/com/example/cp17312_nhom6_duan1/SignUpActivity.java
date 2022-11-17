package com.example.cp17312_nhom6_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
import com.example.cp17312_nhom6_duan1.dto.AccountDTO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {
    TextInputEditText edtUsername;
    TextInputEditText edtPassword;
    TextInputEditText edtFullname;
    TextInputLayout tilUsername;
    TextInputLayout tilPassword;
    TextInputLayout tilFullname;
    TextInputLayout tilPhoneNumber;

    EditText edtPhoneNumber;

    FirebaseAuth mAuth;
    AccountDAO accountDAO;
    ArrayList<AccountDTO> listAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        accountDAO = new AccountDAO(this);
        listAccount = accountDAO.getAll();
        edtUsername = (TextInputEditText) findViewById(R.id.edt_username);
        edtPassword = (TextInputEditText) findViewById(R.id.edt_password);
        edtFullname = (TextInputEditText) findViewById(R.id.edt_fullname);
        tilUsername = (TextInputLayout) findViewById(R.id.til_username);
        tilPassword = (TextInputLayout) findViewById(R.id.til_password);
        tilFullname = (TextInputLayout) findViewById(R.id.til_fullname);
        tilPhoneNumber = (TextInputLayout) findViewById(R.id.til_phone_number);
        edtPhoneNumber = findViewById(R.id.edt_phone_number);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                finish();
            }
        });

        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                finish();
            }
        });

        findViewById(R.id.btn_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String fullName = edtFullname.getText().toString().trim();
                String phoneNumber = edtPhoneNumber.getText().toString().trim();
                onClickVerifyPhoneNumber(phoneNumber);

            }
        });
    }

    private void onClickVerifyPhoneNumber(String strPhoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+84" + strPhoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(SignUpActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);
                                goToEnterOtpActivity(strPhoneNumber, verificationId);
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
//                            goToMainActivity(user.getPhoneNumber());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(SignUpActivity.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goToEnterOtpActivity(String strPhoneNumber, String verificationId) {
        Intent intent = new Intent(this, EnterOTPActivity.class);
        intent.putExtra("username", edtUsername.getText().toString().trim());
        intent.putExtra("password", edtPassword.getText().toString().trim());
        intent.putExtra("fullname", edtFullname.getText().toString().trim());
        intent.putExtra("phone_number", strPhoneNumber);
        intent.putExtra("verification_id", verificationId);
        startActivity(intent);
    }
    // check lỗi
    public boolean checkErrorSignUp() {
        if (tilFullname.getEditText().getText().toString().trim().isEmpty() ||
                tilUsername.getEditText().getText().toString().trim().isEmpty() ||
                tilPassword.getEditText().getText().toString().trim().isEmpty() ||
                tilPhoneNumber.getEditText().getText().toString().trim().isEmpty()|| checkUserName()==false) {
            if (tilFullname.getEditText().getText().toString().trim().isEmpty()) {
                tilFullname.setError("FullName can't isEmpty");
                ErrorAnimaton2(tilFullname, 0);
            } else {
                tilFullname.setError("");
            }
            if (tilUsername.getEditText().getText().toString().trim().isEmpty()) {
                tilUsername.setError("UserName can't isEmpty");
                ErrorAnimaton2(tilUsername, 50);
            }
            else if(checkUserName()==false){
                tilUsername.setError("This account has already existed");
                ErrorAnimaton2(tilUsername, 50);
            }
            else {
                tilUsername.setError("");
            }
            if (tilPassword.getEditText().getText().toString().trim().isEmpty()) {
                tilPassword.setError("Password can't isEmpty");
                ErrorAnimaton2(tilPassword, 60);
            } else if (tilPassword.getEditText().getText().toString().trim().length() < 8) {
                tilPassword.setError("Password must be at least 8 characters");
                ErrorAnimaton2(tilPassword, 60);
            } else {
                tilPassword.setError("");
            }
            if (tilPhoneNumber.getEditText().getText().toString().trim().isEmpty()) {
                tilPhoneNumber.setError("PhoneNumber can't isEmpty");
                ErrorAnimaton2(tilPhoneNumber, 70);
            } else if (!tilPhoneNumber.getEditText().getText().toString().trim().matches("^[0-9]{10}$")) {
                tilPhoneNumber.setError("Incorrect phone number");
                ErrorAnimaton2(tilPhoneNumber, 70);
            } else {
                tilPhoneNumber.setError("");
            }
            return false;
        } else {
            return true;
        }
    }
    public boolean checkUserName(){
        boolean a = false;
        for(int i=0;i<listAccount.size();i++){
            if(listAccount.get(i).getUserName().equals(tilUsername.getEditText().getText().toString().trim())){
                a= true;
                break;
            }else{
                a= false;
            }
        }
        return a;
    }
    public void ErrorAnimaton(View view){
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.annimation_arror);
        animatorSet.setTarget(view);
        animatorSet.start();

    }
    public void ErrorAnimaton2(View view,long delay ){
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.annimation_arror);
        animatorSet.setTarget(view);
        animatorSet.setStartDelay(delay);
        animatorSet.start();

    }
}