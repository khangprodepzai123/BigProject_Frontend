package com.example.a65131433_bigproject.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.models.BacSiResponse;
import com.example.a65131433_bigproject.ui.adapter.BacSiAdapter;
import com.example.a65131433_bigproject.viewmodel.BacSiViewModel;

import java.util.ArrayList;

public class DanhSachBacSiActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvEmpty;
    private BacSiViewModel viewModel;
    private BacSiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bac_si);

        initView();
        initViewModel();
        loadDanhSachBacSi();
        observeViewModel();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerViewBacSi);
        tvEmpty = findViewById(R.id.tvEmpty);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BacSiAdapter();
        recyclerView.setAdapter(adapter);

        // Nút quay lại
        android.widget.Button btnQuayLai = findViewById(R.id.btnQuayLai);
        btnQuayLai.setOnClickListener(v -> {
            android.util.Log.d("DanhSachBacSi", "Quay lại button clicked");
            finish();
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(BacSiViewModel.class);
    }

    private void loadDanhSachBacSi() {
        android.util.Log.d("DanhSachBacSi", "Loading danh sach bac si");
        viewModel.getAllBacSi();
    }

    private void observeViewModel() {
        viewModel.isLoading.observe(this, isLoading -> {
            if (isLoading != null && isLoading) {
                tvEmpty.setText("Đang tải...");
                tvEmpty.setVisibility(android.view.View.VISIBLE);
                recyclerView.setVisibility(android.view.View.GONE);
            }
        });

        viewModel.errorMessage.observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                tvEmpty.setText(error);
                tvEmpty.setVisibility(android.view.View.VISIBLE);
                recyclerView.setVisibility(android.view.View.GONE);
            }
        });

        viewModel.bacSiResponse.observe(this, response -> {
            android.util.Log.d("DanhSachBacSi", "Response received: " + (response != null ? "not null" : "null"));
            if (response != null) {
                android.util.Log.d("DanhSachBacSi", "Response success: " + response.isSuccess());
            }

            if (response != null && response.isSuccess()) {
                if (response.getData() != null && !response.getData().isEmpty()) {
                    android.util.Log.d("DanhSachBacSi", "Displaying " + response.getData().size() + " doctors");
                    adapter.updateList(response.getData());
                    recyclerView.setVisibility(android.view.View.VISIBLE);
                    tvEmpty.setVisibility(android.view.View.GONE);
                } else {
                    android.util.Log.d("DanhSachBacSi", "No doctors found");
                    tvEmpty.setText("Chưa có bác sĩ nào");
                    recyclerView.setVisibility(android.view.View.GONE);
                    tvEmpty.setVisibility(android.view.View.VISIBLE);
                }
            } else {
                android.util.Log.d("DanhSachBacSi", "Response is null or not success");
                String message = response != null ? response.getMessage() : "Không có dữ liệu";
                tvEmpty.setText(message);
                recyclerView.setVisibility(android.view.View.GONE);
                tvEmpty.setVisibility(android.view.View.VISIBLE);
            }
        });
    }
}

