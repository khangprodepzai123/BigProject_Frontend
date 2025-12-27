package com.example.a65131433_bigproject.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.models.RegisterKhamRequest;
import com.example.a65131433_bigproject.models.RegisterKhamResponse;
import com.example.a65131433_bigproject.repository.BenhNhanRepository;

public class RegisterKhamViewModel extends AndroidViewModel {
    private BenhNhanRepository repository;
    public MutableLiveData<RegisterKhamResponse> registerResponse = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public RegisterKhamViewModel(Application application) {
        super(application);
        this.repository = new BenhNhanRepository(application.getApplicationContext());
    }

    public void registerKham(RegisterKhamRequest request) {
        isLoading.setValue(true);
        repository.registerKham(request).observeForever(response -> {
            isLoading.setValue(false);
            if (response != null) {
                if (response.isSuccess()) {
                    registerResponse.setValue(response);
                } else {
                    errorMessage.setValue(response.getMessage());
                }
            } else {
                errorMessage.setValue("Lỗi kết nối mạng");
            }
        });
    }
}

