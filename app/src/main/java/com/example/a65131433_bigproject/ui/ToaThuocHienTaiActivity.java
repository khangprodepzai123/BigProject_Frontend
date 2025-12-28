package com.example.a65131433_bigproject.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.models.ToaThuocHienTaiResponse;
import com.example.a65131433_bigproject.utils.SharedPrefManager;
import com.example.a65131433_bigproject.viewmodel.ToaThuocHienTaiViewModel;

import java.util.ArrayList;

public class ToaThuocHienTaiActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvEmpty, tvNgayKham, tvBacSi, tvChuanDoan;
    private ToaThuocHienTaiViewModel viewModel;
    private SharedPrefManager prefManager;
    private ToaThuocHienTaiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toa_thuoc_hien_tai);

        initView();
        initViewModel();
        loadToaThuoc();
        observeViewModel();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerViewToaThuoc);
        tvEmpty = findViewById(R.id.tvEmpty);
        tvNgayKham = findViewById(R.id.tvNgayKham);
        tvBacSi = findViewById(R.id.tvBacSi);
        tvChuanDoan = findViewById(R.id.tvChuanDoan);
        prefManager = SharedPrefManager.getInstance(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ToaThuocHienTaiAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        
        // Nút quay lại
        android.widget.Button btnQuayLai = findViewById(R.id.btnQuayLai);
        btnQuayLai.setOnClickListener(v -> {
            android.util.Log.d("ToaThuocHienTai", "Quay lại button clicked");
            finish();
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ToaThuocHienTaiViewModel.class);
    }

    private void loadToaThuoc() {
        String token = prefManager.getToken();
        android.util.Log.d("ToaThuocHienTai", "Token from SharedPref: " + (token != null ? "exists" : "null"));
        if (token != null && !token.isEmpty()) {
            // Token đã có "Bearer " prefix từ AuthRepository (xem AuthRepository.java dòng 37, 72)
            // Không cần thêm "Bearer " nữa, dùng trực tiếp như BenhAnActivity và HoaDonActivity
            android.util.Log.d("ToaThuocHienTai", "Calling API with token: " + token.substring(0, Math.min(30, token.length())) + "...");
            viewModel.getToaThuocHienTai(token);
        } else {
            Toast.makeText(this, "Vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void observeViewModel() {
        viewModel.isLoading.observe(this, isLoading -> {
            // Có thể thêm ProgressBar nếu cần
        });

        viewModel.errorMessage.observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.toaThuocResponse.observe(this, response -> {
            android.util.Log.d("ToaThuocHienTai", "Response received: " + (response != null ? "not null" : "null"));
            if (response != null) {
                android.util.Log.d("ToaThuocHienTai", "Response success: " + response.isSuccess());
                android.util.Log.d("ToaThuocHienTai", "Response message: " + response.getMessage());
            }
            
            if (response != null && response.isSuccess()) {
                ToaThuocHienTaiResponse.ToaThuocHienTaiData data = response.getData();
                android.util.Log.d("ToaThuocHienTai", "Data: " + (data != null ? "not null" : "null"));
                if (data != null) {
                    android.util.Log.d("ToaThuocHienTai", "ToaThuoc: " + (data.getToaThuoc() != null ? "not null, size=" + data.getToaThuoc().size() : "null"));
                }
                
                if (data != null && data.getToaThuoc() != null && !data.getToaThuoc().isEmpty()) {
                    android.util.Log.d("ToaThuocHienTai", "Displaying toa thuoc with " + data.getToaThuoc().size() + " items");
                    // Hiển thị thông tin
                    tvNgayKham.setText("Ngày khám: " + (data.getNgayKham() != null ? data.getNgayKham() : "N/A"));
                    tvBacSi.setText("Bác sĩ: " + (data.getBacSi() != null ? data.getBacSi() : "N/A"));
                    tvChuanDoan.setText("Chẩn đoán: " + (data.getChuanDoan() != null ? data.getChuanDoan() : "N/A"));
                    
                    // Hiển thị danh sách thuốc
                    adapter.updateList(data.getToaThuoc());
                    recyclerView.setVisibility(android.view.View.VISIBLE);
                    tvEmpty.setVisibility(android.view.View.GONE);
                } else {
                    android.util.Log.d("ToaThuocHienTai", "No toa thuoc data or empty list");
                    tvEmpty.setText("Chưa có toa thuốc nào");
                    recyclerView.setVisibility(android.view.View.GONE);
                    tvEmpty.setVisibility(android.view.View.VISIBLE);
                }
            } else {
                android.util.Log.d("ToaThuocHienTai", "Response is null or not success");
                String message = response != null ? response.getMessage() : "Không có dữ liệu";
                tvEmpty.setText(message);
                recyclerView.setVisibility(android.view.View.GONE);
                tvEmpty.setVisibility(android.view.View.VISIBLE);
            }
        });
    }
}

