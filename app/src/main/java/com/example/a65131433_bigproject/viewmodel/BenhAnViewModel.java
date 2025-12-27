package com.example.a65131433_bigproject.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.models.BenhAnResponse;
import com.example.a65131433_bigproject.repository.BenhAnRepository;

public class BenhAnViewModel extends AndroidViewModel {
    private BenhAnRepository repository;
    public MutableLiveData<BenhAnResponse> benhAnResponse = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public BenhAnViewModel(Application application) {
        super(application);
        this.repository = new BenhAnRepository();
    }

    public void getMyBenhAn(String token) {
        isLoading.setValue(true);
        repository.getMyBenhAn(token).observeForever(response -> {
            isLoading.setValue(false);
            if (response != null) {
                if (response.isSuccess()) {
                    benhAnResponse.setValue(response);
                } else {
                    errorMessage.setValue(response.getMessage());
                }
            } else {
                errorMessage.setValue("Lỗi kết nối mạng");
            }
        });
    }
}

