package com.example.a65131433_bigproject.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.models.DiemTichLuyResponse;
import com.example.a65131433_bigproject.utils.SharedPrefManager;
import com.example.a65131433_bigproject.viewmodel.DiemTichLuyViewModel;

public class DiemTichLuyActivity extends AppCompatActivity {
    private TextView tvDiemTichLuy, tvTenMuc, tvMoTa, tvPhanTramGiamGia, tvCoTheSuDung, tvEmpty;
    private DiemTichLuyViewModel viewModel;
    private SharedPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_tich_luy);

        initView();
        initViewModel();
        loadDiemTichLuy();
        observeViewModel();
    }

    private void initView() {
        tvDiemTichLuy = findViewById(R.id.tvDiemTichLuy);
        tvTenMuc = findViewById(R.id.tvTenMuc);
        tvMoTa = findViewById(R.id.tvMoTa);
        tvPhanTramGiamGia = findViewById(R.id.tvPhanTramGiamGia);
        tvCoTheSuDung = findViewById(R.id.tvCoTheSuDung);
        tvEmpty = findViewById(R.id.tvEmpty);
        prefManager = SharedPrefManager.getInstance(this);

        // Nút quay lại
        android.widget.Button btnQuayLai = findViewById(R.id.btnQuayLai);
        btnQuayLai.setOnClickListener(v -> {
            android.util.Log.d("DiemTichLuy", "Quay lại button clicked");
            finish();
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(DiemTichLuyViewModel.class);
    }

    private void loadDiemTichLuy() {
        String token = prefManager.getToken();
        android.util.Log.d("DiemTichLuy", "Token from SharedPref: " + (token != null ? "exists" : "null"));
        if (token != null && !token.isEmpty()) {
            // Token đã có "Bearer " prefix từ SharedPrefManager
            android.util.Log.d("DiemTichLuy", "Calling API with token: " + token.substring(0, Math.min(30, token.length())) + "...");
            viewModel.getMyDiemTichLuy(token);
        } else {
            Toast.makeText(this, "Vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void observeViewModel() {
        viewModel.isLoading.observe(this, isLoading -> {
            if (isLoading != null && isLoading) {
                tvEmpty.setText("Đang tải...");
                tvEmpty.setVisibility(android.view.View.VISIBLE);
            }
        });

        viewModel.errorMessage.observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                tvEmpty.setText(error);
                tvEmpty.setVisibility(android.view.View.VISIBLE);
            }
        });

        viewModel.diemTichLuyResponse.observe(this, response -> {
            android.util.Log.d("DiemTichLuy", "Response received: " + (response != null ? "not null" : "null"));
            if (response != null) {
                android.util.Log.d("DiemTichLuy", "Response success: " + response.isSuccess());
                android.util.Log.d("DiemTichLuy", "Response message: " + response.getMessage());
            }

            if (response != null && response.isSuccess()) {
                DiemTichLuyResponse.DiemTichLuyData data = response.getData();
                android.util.Log.d("DiemTichLuy", "Data: " + (data != null ? "not null" : "null"));

                if (data != null) {
                    // Hiển thị điểm tích lũy
                    int diem = data.getDiemTichLuy();
                    tvDiemTichLuy.setText(String.valueOf(diem));

                    // Hiển thị mức ưu đãi
                    DiemTichLuyResponse.DiemTichLuyData.MucUuDaiData mucUuDai = data.getMucUuDai();
                    if (mucUuDai != null) {
                        tvTenMuc.setText(mucUuDai.getTenMuc() != null ? mucUuDai.getTenMuc() : "Chưa có");
                        tvMoTa.setText(mucUuDai.getMoTa() != null ? mucUuDai.getMoTa() : "Chưa có mô tả");
                    } else {
                        tvTenMuc.setText("Chưa có");
                        tvMoTa.setText("Chưa có mô tả");
                    }

                    // Hiển thị phần trăm giảm giá
                    int phanTramGiamGia = data.getPhanTramGiamGia();
                    tvPhanTramGiamGia.setText(phanTramGiamGia + "%");

                    // Hiển thị trạng thái có thể sử dụng
                    boolean coTheSuDung = data.isCoTheSuDung();
                    if (coTheSuDung) {
                        tvCoTheSuDung.setText("Bạn có thể sử dụng điểm để giảm giá cho lần khám tiếp theo");
                        tvCoTheSuDung.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    } else {
                        tvCoTheSuDung.setText("Bạn chưa có điểm để sử dụng");
                        tvCoTheSuDung.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    }

                    tvEmpty.setVisibility(android.view.View.GONE);
                } else {
                    tvEmpty.setText("Không có dữ liệu");
                    tvEmpty.setVisibility(android.view.View.VISIBLE);
                }
            } else {
                android.util.Log.d("DiemTichLuy", "Response is null or not success");
                String message = response != null ? response.getMessage() : "Không có dữ liệu";
                tvEmpty.setText(message);
                tvEmpty.setVisibility(android.view.View.VISIBLE);
            }
        });
    }
}

