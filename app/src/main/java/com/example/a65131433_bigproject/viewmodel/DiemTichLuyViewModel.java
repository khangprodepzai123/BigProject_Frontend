package com.example.a65131433_bigproject.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.models.DiemTichLuyResponse;
import com.example.a65131433_bigproject.repository.DiemTichLuyRepository;

public class DiemTichLuyViewModel extends AndroidViewModel {
    private DiemTichLuyRepository repository;
    public MutableLiveData<DiemTichLuyResponse> diemTichLuyResponse = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public DiemTichLuyViewModel(Application application) {
        super(application);
        this.repository = new DiemTichLuyRepository();
    }

    public void getMyDiemTichLuy(String token) {
        isLoading.setValue(true);
        errorMessage.setValue(null); // Clear previous error
        
        repository.getMyDiemTichLuy(token).observeForever(response -> {
            isLoading.setValue(false);
            if (response != null) {
                if (response.isSuccess()) {
                    diemTichLuyResponse.setValue(response);
                    errorMessage.setValue(null); // Clear error on success
                } else {
                    String errorMsg = response.getMessage() != null ? response.getMessage() : "Lỗi không xác định";
                    errorMessage.setValue(errorMsg);
                    diemTichLuyResponse.setValue(null); // Clear response on error
                }
            } else {
                errorMessage.setValue("Lỗi kết nối mạng");
                diemTichLuyResponse.setValue(null);
            }
        });
    }
}

