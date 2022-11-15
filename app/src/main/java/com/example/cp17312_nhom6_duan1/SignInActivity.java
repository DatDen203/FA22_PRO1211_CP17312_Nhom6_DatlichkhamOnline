package com.example.cp17312_nhom6_duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.cp17312_nhom6_duan1.admin.AdminActivity;
import com.example.cp17312_nhom6_duan1.dao.AccountDAO;
import com.example.cp17312_nhom6_duan1.doctor.DoctorActivity;
import com.example.cp17312_nhom6_duan1.dto.AccountDTO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {
    TextInputEditText edtUsername;
    TextInputEditText edtPassword;
    TextInputLayout tilUsername;
    TextInputLayout tilPassword;
    MaterialButton btnSignIn;
    AccountDAO accountDAO;
    MaterialCheckBox chkRememberPass;

    SharedPreferences sharedPreferences;
    ArrayList<AccountDTO> listAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtUsername = (TextInputEditText) findViewById(R.id.edt_username);
        edtPassword = (TextInputEditText) findViewById(R.id.edt_password);
        tilUsername = (TextInputLayout) findViewById(R.id.til_username);
        tilPassword = (TextInputLayout) findViewById(R.id.til_password);
        btnSignIn = (MaterialButton) findViewById(R.id.btn_sign_in);
        chkRememberPass = (MaterialCheckBox) findViewById(R.id.chk_remember_pass);
        accountDAO = new AccountDAO(this);
        listAcc = accountDAO.getAll();

        sharedPreferences = getSharedPreferences("getIdUser", MODE_PRIVATE);

        findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUsername.getText().toString().trim();
                String passWord = edtPassword.getText().toString().trim();
                String checkRole = sharedPreferences.getString("role", "");
                boolean checkLogin = accountDAO.checkLogin(userName, passWord);
                String fullname = sharedPreferences.getString("fullname", "");
                if (userName.isEmpty() || passWord.isEmpty()) {
                    tilUsername.setError("Vui lòng không để trống");
                    tilPassword.setError("Vui lòng không để trống");
                    ErrorAnimaton(tilUsername);
                    ErrorAnimaton2(tilPassword,100);
                } else {
                    tilUsername.setError("");
                    tilPassword.setError("");
                    if (checkLogin== true) {
                        tilUsername.setError("");
                        tilPassword.setError("");
                        if (checkRole.equalsIgnoreCase("Admin")) {
                            Toast.makeText(SignInActivity.this, "Man admin", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInActivity.this, AdminActivity.class);
                            startActivity(intent);
                        } else if (checkRole.equalsIgnoreCase("User")) {
                            Toast.makeText(SignInActivity.this, "Man user", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            intent.putExtra("fullname", fullname);
                            startActivity(intent);
                        } else if (checkRole.equalsIgnoreCase("Doctor")) {
                            Toast.makeText(SignInActivity.this, "Man doctor", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInActivity.this, DoctorActivity.class);
                            intent.putExtra("fullname", fullname);
                            startActivity(intent);
                        }
                    }else{
                        ErrorAnimaton2(tilPassword,100);
                        tilPassword.setError("Incorrect password");
                    }
                }
            }
        });
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