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
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ToaThuocHienTaiViewModel.class);
    }

    private void loadToaThuoc() {
        String token = prefManager.getToken();
        if (token != null && !token.isEmpty()) {
            viewModel.getToaThuocHienTai("Bearer " + token);
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
            if (response != null && response.isSuccess()) {
                ToaThuocHienTaiResponse.ToaThuocHienTaiData data = response.getData();
                if (data != null && data.getToaThuoc() != null && !data.getToaThuoc().isEmpty()) {
                    // Hiển thị thông tin
                    tvNgayKham.setText("Ngày khám: " + (data.getNgayKham() != null ? data.getNgayKham() : "N/A"));
                    tvBacSi.setText("Bác sĩ: " + (data.getBacSi() != null ? data.getBacSi() : "N/A"));
                    tvChuanDoan.setText("Chẩn đoán: " + (data.getChuanDoan() != null ? data.getChuanDoan() : "N/A"));
                    
                    // Hiển thị danh sách thuốc
                    adapter.updateList(data.getToaThuoc());
                    recyclerView.setVisibility(android.view.View.VISIBLE);
                    tvEmpty.setVisibility(android.view.View.GONE);
                } else {
                    tvEmpty.setText("Chưa có toa thuốc nào");
                    recyclerView.setVisibility(android.view.View.GONE);
                    tvEmpty.setVisibility(android.view.View.VISIBLE);
                }
            } else {
                tvEmpty.setText("Chưa có toa thuốc nào");
                recyclerView.setVisibility(android.view.View.GONE);
                tvEmpty.setVisibility(android.view.View.VISIBLE);
            }
        });
    }
}

