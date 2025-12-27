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

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvGoToSignup;
    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initViewModel();
        setupClickListener();
        observeViewModel();
    }

    private void initView() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoToSignup = findViewById(R.id.tvGoToSignup);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
    }

    private void setupClickListener() {
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (!username.isEmpty() && !password.isEmpty()) {
                viewModel.login(username, password);
            } else {
                Toast.makeText(this, "Vui lòng nhập tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        tvGoToSignup.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            finish();
        });
    }

    private void observeViewModel() {
        viewModel.isLoading.observe(this, isLoading -> {
            btnLogin.setEnabled(!isLoading);
            btnLogin.setText(isLoading ? "Đang đăng nhập..." : "Đăng nhập");
        });

        viewModel.authResponse.observe(this, response -> {
            if (response != null && response.isSuccess()) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
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