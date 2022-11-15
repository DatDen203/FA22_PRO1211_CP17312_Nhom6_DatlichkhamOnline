package com.example.cp17312_nhom6_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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
                if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || phoneNumber.isEmpty()) {
                    tilUsername.setError("Vui lòng nhập đủ thông tin");
                    tilPassword.setError("Vui lòng nhập đủ thông tin");
                    tilFullname.setError("Vui lòng nhập đủ thông tin");
                    tilPhoneNumber.setError("Vui lòng nhập đủ thông tin");
                } else {
                    onClickVerifyPhoneNumber(phoneNumber);
                }
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
}