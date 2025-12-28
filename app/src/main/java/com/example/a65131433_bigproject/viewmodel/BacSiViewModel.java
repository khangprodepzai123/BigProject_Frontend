package com.example.a65131433_bigproject.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.models.BacSiResponse;
import com.example.a65131433_bigproject.repository.BacSiRepository;

public class BacSiViewModel extends AndroidViewModel {
    private BacSiRepository repository;
    public MutableLiveData<BacSiResponse> bacSiResponse = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public BacSiViewModel(Application application) {
        super(application);
        this.repository = new BacSiRepository();
    }

    public void getAllBacSi() {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        
        repository.getAllBacSi().observeForever(response -> {
            isLoading.setValue(false);
            if (response != null) {
                if (response.isSuccess()) {
                    bacSiResponse.setValue(response);
                    errorMessage.setValue(null);
                } else {
                    String errorMsg = response.getMessage() != null ? response.getMessage() : "Lỗi không xác định";
                    errorMessage.setValue(errorMsg);
                    bacSiResponse.setValue(null);
                }
            } else {
                errorMessage.setValue("Lỗi kết nối mạng");
                bacSiResponse.setValue(null);
            }
        });
    }
}

