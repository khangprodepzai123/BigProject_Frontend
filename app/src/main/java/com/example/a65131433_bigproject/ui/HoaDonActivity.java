package com.example.a65131433_bigproject.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.models.HoaDonResponse;
import com.example.a65131433_bigproject.utils.SharedPrefManager;
import com.example.a65131433_bigproject.viewmodel.HoaDonViewModel;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HoaDonActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvEmpty;
    private HoaDonViewModel viewModel;
    private SharedPrefManager prefManager;
    private HoaDonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        initView();
        initViewModel();
        loadHoaDon();
        observeViewModel();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerViewHoaDon);
        tvEmpty = findViewById(R.id.tvEmpty);
        prefManager = SharedPrefManager.getInstance(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HoaDonAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        
        // Nút quay lại
        android.widget.Button btnQuayLai = findViewById(R.id.btnQuayLai);
        btnQuayLai.setOnClickListener(v -> {
            android.util.Log.d("HoaDon", "Quay lại button clicked");
            finish();
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(HoaDonViewModel.class);
    }

    private void loadHoaDon() {
        String token = prefManager.getToken();
        if (token.isEmpty()) {
            Toast.makeText(this, "Vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        viewModel.getMyHoaDon(token);
    }

    private void observeViewModel() {
        viewModel.isLoading.observe(this, isLoading -> {
            // Có thể thêm progress bar nếu cần
        });

        viewModel.hoaDonResponse.observe(this, response -> {
            if (response != null && response.isSuccess()) {
                List<HoaDonResponse.HoaDonData> data = response.getData();
                if (data != null && !data.isEmpty()) {
                    adapter.updateData(data);
                    recyclerView.setVisibility(android.view.View.VISIBLE);
                    tvEmpty.setVisibility(android.view.View.GONE);
                } else {
                    recyclerView.setVisibility(android.view.View.GONE);
                    tvEmpty.setVisibility(android.view.View.VISIBLE);
                    tvEmpty.setText("Chưa có hóa đơn nào");
                }
            }
        });

        viewModel.errorMessage.observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(android.view.View.GONE);
                tvEmpty.setVisibility(android.view.View.VISIBLE);
                tvEmpty.setText("Chưa có hóa đơn nào");
            }
        });
    }
}

