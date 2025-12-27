package com.example.a65131433_bigproject.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a65131433_bigproject.R;
import com.example.a65131433_bigproject.models.RegisterKhamRequest;
import com.example.a65131433_bigproject.viewmodel.RegisterKhamViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterKhamActivity extends AppCompatActivity {
    private EditText etHoTen, etSdt, etDiaChi, etBhyt, etLyDoKham;
    private TextView tvNgaySinh, tvThoiGianHenKham;
    private Spinner spGioiTinh, spDoiTuong;
    private Button btnChonNgaySinh, btnChonThoiGianHen, btnDangKy;
    
    private RegisterKhamViewModel viewModel;
    private Calendar calendarNgaySinh = Calendar.getInstance();
    private Calendar calendarThoiGianHen = Calendar.getInstance();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_kham);

        initView();
        initViewModel();
        setupSpinners();
        setupClickListeners();
        observeViewModel();
    }

    private void initView() {
        etHoTen = findViewById(R.id.etHoTen);
        etSdt = findViewById(R.id.etSdt);
        etDiaChi = findViewById(R.id.etDiaChi);
        etBhyt = findViewById(R.id.etBhyt);
        etLyDoKham = findViewById(R.id.etLyDoKham);
        tvNgaySinh = findViewById(R.id.tvNgaySinh);
        tvThoiGianHenKham = findViewById(R.id.tvThoiGianHenKham);
        spGioiTinh = findViewById(R.id.spGioiTinh);
        spDoiTuong = findViewById(R.id.spDoiTuong);
        btnChonNgaySinh = findViewById(R.id.btnChonNgaySinh);
        btnChonThoiGianHen = findViewById(R.id.btnChonThoiGianHen);
        btnDangKy = findViewById(R.id.btnDangKy);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(RegisterKhamViewModel.class);
    }

    private void setupSpinners() {
        // Spinner Giới tính
        ArrayAdapter<CharSequence> gioiTinhAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.gioi_tinh_array,
            android.R.layout.simple_spinner_item
        );
        gioiTinhAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGioiTinh.setAdapter(gioiTinhAdapter);

        // Spinner Đối tượng
        ArrayAdapter<CharSequence> doiTuongAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.doi_tuong_array,
            android.R.layout.simple_spinner_item
        );
        doiTuongAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDoiTuong.setAdapter(doiTuongAdapter);
    }

    private void setupClickListeners() {
        btnChonNgaySinh.setOnClickListener(v -> showDatePickerDialog(true));
        btnChonThoiGianHen.setOnClickListener(v -> showDateTimePickerDialog());
        
        btnDangKy.setOnClickListener(v -> {
            if (validateInput()) {
                registerKham();
            }
        });
    }

    private void showDatePickerDialog(boolean isNgaySinh) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            this,
            (view, year, month, dayOfMonth) -> {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                
                if (isNgaySinh) {
                    calendarNgaySinh = selectedDate;
                    tvNgaySinh.setText(dateFormat.format(selectedDate.getTime()));
                }
            },
            calendarNgaySinh.get(Calendar.YEAR),
            calendarNgaySinh.get(Calendar.MONTH),
            calendarNgaySinh.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showDateTimePickerDialog() {
        // Chọn ngày trước
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            this,
            (view, year, month, dayOfMonth) -> {
                calendarThoiGianHen.set(year, month, dayOfMonth);
                // Sau khi chọn ngày, chọn giờ
                showTimePickerDialog();
            },
            calendarThoiGianHen.get(Calendar.YEAR),
            calendarThoiGianHen.get(Calendar.MONTH),
            calendarThoiGianHen.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
            this,
            (view, hourOfDay, minute) -> {
                calendarThoiGianHen.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendarThoiGianHen.set(Calendar.MINUTE, minute);
                tvThoiGianHenKham.setText(dateTimeFormat.format(calendarThoiGianHen.getTime()));
            },
            calendarThoiGianHen.get(Calendar.HOUR_OF_DAY),
            calendarThoiGianHen.get(Calendar.MINUTE),
            true
        );
        timePickerDialog.show();
    }

    private boolean validateInput() {
        if (etHoTen.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etSdt.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tvThoiGianHenKham.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn thời gian hẹn khám", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void registerKham() {
        RegisterKhamRequest request = new RegisterKhamRequest();
        request.setHoTenBn(etHoTen.getText().toString().trim());
        request.setSdt(etSdt.getText().toString().trim());
        
        // Ngày sinh - chỉ set nếu không rỗng
        String ngaySinh = tvNgaySinh.getText().toString();
        if (!ngaySinh.isEmpty() && !ngaySinh.equals("Chưa chọn")) {
            request.setNgaySinh(ngaySinh);
        }
        
        // Giới tính - chỉ set nếu không phải default
        String gioiTinh = spGioiTinh.getSelectedItem().toString();
        if (!gioiTinh.equals("-- Chọn giới tính --")) {
            request.setGt(gioiTinh);
        }
        
        // Đối tượng - chỉ set nếu không phải default
        String doiTuong = spDoiTuong.getSelectedItem().toString();
        if (!doiTuong.equals("-- Chọn đối tượng --")) {
            request.setDoiTuong(doiTuong);
        }
        
        // Địa chỉ - chỉ set nếu không rỗng
        String diaChi = etDiaChi.getText().toString().trim();
        if (!diaChi.isEmpty()) {
            request.setDiaChi(diaChi);
        }
        
        // BHYT - chỉ set nếu không rỗng
        String bhyt = etBhyt.getText().toString().trim();
        if (!bhyt.isEmpty()) {
            request.setBhyt(bhyt);
        }
        
        // Lý do khám - chỉ set nếu không rỗng
        String lyDoKham = etLyDoKham.getText().toString().trim();
        if (!lyDoKham.isEmpty()) {
            request.setLyDoKham(lyDoKham);
        }
        
        // Thời gian hẹn khám - bắt buộc
        String thoiGianHen = tvThoiGianHenKham.getText().toString();
        if (!thoiGianHen.isEmpty() && !thoiGianHen.equals("Chưa chọn")) {
            request.setThoiGianHenKham(thoiGianHen);
        }
        
        viewModel.registerKham(request);
    }

    private void observeViewModel() {
        viewModel.isLoading.observe(this, isLoading -> {
            btnDangKy.setEnabled(!isLoading);
            btnDangKy.setText(isLoading ? "Đang đăng ký..." : "Đăng ký khám");
        });

        viewModel.registerResponse.observe(this, response -> {
            if (response != null && response.isSuccess()) {
                Toast.makeText(this, "Đăng ký khám thành công!", Toast.LENGTH_SHORT).show();
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

