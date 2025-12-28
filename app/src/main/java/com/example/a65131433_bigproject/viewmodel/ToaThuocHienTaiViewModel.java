package com.example.a65131433_bigproject.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.models.ToaThuocHienTaiResponse;
import com.example.a65131433_bigproject.repository.BenhAnRepository;

public class ToaThuocHienTaiViewModel extends AndroidViewModel {
    private BenhAnRepository repository;
    public MutableLiveData<ToaThuocHienTaiResponse> toaThuocResponse = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public ToaThuocHienTaiViewModel(Application application) {
        super(application);
        this.repository = new BenhAnRepository();
    }

    public void getToaThuocHienTai(String token) {
        isLoading.setValue(true);
        errorMessage.setValue(null); // Clear previous error
        
        repository.getToaThuocHienTai(token).observeForever(response -> {
            isLoading.setValue(false);
            if (response != null) {
                if (response.isSuccess()) {
                    toaThuocResponse.setValue(response);
                    errorMessage.setValue(null); // Clear error on success
                } else {
                    String errorMsg = response.getMessage() != null ? response.getMessage() : "Lỗi không xác định";
                    errorMessage.setValue(errorMsg);
                    toaThuocResponse.setValue(null); // Clear response on error
                }
            } else {
                errorMessage.setValue("Lỗi kết nối mạng");
                toaThuocResponse.setValue(null);
            }
        });
    }
}

