package com.example.a65131433_bigproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.viewmodel.AuthViewModel;

public class SignupActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etPasswordConfirm;
    private Button btnSignup;
    private TextView tvGoToLogin;
    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();
        initViewModel();
        setupClickListener();
        observeViewModel();
    }

    private void initView() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        btnSignup = findViewById(R.id.btnSignup);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
    }

    private void setupClickListener() {
        btnSignup.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String passwordConfirm = etPasswordConfirm.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(passwordConfirm)) {
                Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            viewModel.signup(username, password);
        });

        tvGoToLogin.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void observeViewModel() {
        viewModel.isLoading.observe(this, isLoading -> {
            btnSignup.setEnabled(!isLoading);
            btnSignup.setText(isLoading ? "Đang đăng ký..." : "Đăng ký");
        });

        viewModel.authResponse.observe(this, response -> {
            if (response != null && response.isSuccess()) {
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this, WelcomeActivity.class));
                finish();
            }
        });

        viewModel.errorMessage.observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}