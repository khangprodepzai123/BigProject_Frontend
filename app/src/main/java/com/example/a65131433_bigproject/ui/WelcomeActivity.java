package com.example.a65131433_bigproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.utils.SharedPrefManager;
import com.example.a65131433_bigproject.viewmodel.AuthViewModel;

public class WelcomeActivity extends AppCompatActivity {
    private TextView tvWelcome, tvUsername, tvMaBn, tvHoTen, tvDiemTichLuy, tvMaTk;
    private Button btnLogout, btnDangKyKham, btnBenhAn, btnHoaDon, btnToaThuocHienTai;
    private AuthViewModel viewModel;
    private SharedPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
        initViewModel();
        loadUserInfo();
        setupClickListener();
    }

    private void initView() {
        tvWelcome = findViewById(R.id.tvWelcome);
        tvUsername = findViewById(R.id.tvUsername);
        tvMaBn = findViewById(R.id.tvMaBn);
        tvHoTen = findViewById(R.id.tvHoTen);
        tvDiemTichLuy = findViewById(R.id.tvDiemTichLuy);
        tvMaTk = findViewById(R.id.tvMaTk);
        btnLogout = findViewById(R.id.btnLogout);
        btnDangKyKham = findViewById(R.id.btnDangKyKham);
        btnBenhAn = findViewById(R.id.btnBenhAn);
        btnHoaDon = findViewById(R.id.btnHoaDon);
        btnToaThuocHienTai = findViewById(R.id.btnToaThuocHienTai);

        prefManager = SharedPrefManager.getInstance(this);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
    }

    private void loadUserInfo() {
        String username = prefManager.getUsername();
        String maTk = prefManager.getMaTk();
        String maBn = prefManager.getMaBn();
        String hoTen = prefManager.getHoTen();
        int diem = prefManager.getDiemTichLuy();

        tvWelcome.setText("Xin chào, " + username + "!");
        tvUsername.setText("Tên đăng nhập: " + username);
        tvMaTk.setText("Mã tài khoản: " + maTk);
        tvMaBn.setText("Mã BN: " + (maBn == null || maBn.isEmpty() ? "Chưa liên kết" : maBn));
        tvHoTen.setText("Họ tên: " + (hoTen == null || hoTen.isEmpty() ? "Chưa cập nhật" : hoTen));
        tvDiemTichLuy.setText("Điểm tích lũy: " + diem);
    }

    private void setupClickListener() {
        btnDangKyKham.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, RegisterKhamActivity.class));
        });

        btnBenhAn.setOnClickListener(v -> {
            String maBn = prefManager.getMaBn();
            if (maBn == null || maBn.isEmpty()) {
                Toast.makeText(this, "Tài khoản chưa liên kết với bệnh nhân", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(WelcomeActivity.this, BenhAnActivity.class));
            }
        });

        btnHoaDon.setOnClickListener(v -> {
            String maBn = prefManager.getMaBn();
            if (maBn == null || maBn.isEmpty()) {
                Toast.makeText(this, "Tài khoản chưa liên kết với bệnh nhân", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(WelcomeActivity.this, HoaDonActivity.class));
            }
        });

        btnToaThuocHienTai.setOnClickListener(v -> {
            String maBn = prefManager.getMaBn();
            if (maBn == null || maBn.isEmpty()) {
                Toast.makeText(this, "Tài khoản chưa liên kết với bệnh nhân", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(WelcomeActivity.this, ToaThuocHienTaiActivity.class));
            }
        });

        btnLogout.setOnClickListener(v -> {
            viewModel.logout();
            Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Kiểm tra token có hợp lệ không
        if (!viewModel.isLoggedIn()) {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            finish();
        }
    }
}