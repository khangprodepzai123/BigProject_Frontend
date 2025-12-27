package com.example.a65131433_bigproject.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.models.BenhAnResponse;
import com.example.a65131433_bigproject.utils.SharedPrefManager;
import com.example.a65131433_bigproject.viewmodel.BenhAnViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BenhAnActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvEmpty;
    private BenhAnViewModel viewModel;
    private SharedPrefManager prefManager;
    private BenhAnAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benh_an);

        initView();
        initViewModel();
        loadBenhAn();
        observeViewModel();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerViewBenhAn);
        tvEmpty = findViewById(R.id.tvEmpty);
        prefManager = SharedPrefManager.getInstance(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BenhAnAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(BenhAnViewModel.class);
    }

    private void loadBenhAn() {
        String token = prefManager.getToken();
        if (token.isEmpty()) {
            Toast.makeText(this, "Vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        viewModel.getMyBenhAn(token);
    }

    private void observeViewModel() {
        viewModel.isLoading.observe(this, isLoading -> {
            // Có thể thêm progress bar nếu cần
        });

        viewModel.benhAnResponse.observe(this, response -> {
            if (response != null && response.isSuccess()) {
                List<BenhAnResponse.BenhAnData> data = response.getData();
                if (data != null && !data.isEmpty()) {
                    adapter.updateData(data);
                    recyclerView.setVisibility(android.view.View.VISIBLE);
                    tvEmpty.setVisibility(android.view.View.GONE);
                } else {
                    recyclerView.setVisibility(android.view.View.GONE);
                    tvEmpty.setVisibility(android.view.View.VISIBLE);
                    tvEmpty.setText("Chưa có dữ liệu bệnh án");
                }
            }
        });

        viewModel.errorMessage.observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(android.view.View.GONE);
                tvEmpty.setVisibility(android.view.View.VISIBLE);
                tvEmpty.setText("Chưa có dữ liệu bệnh án");
            }
        });
    }
}

