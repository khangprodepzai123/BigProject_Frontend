package com.example.a65131433_bigproject.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.models.HoaDonResponse;
import com.example.a65131433_bigproject.repository.HoaDonRepository;

public class HoaDonViewModel extends AndroidViewModel {
    private HoaDonRepository repository;
    public MutableLiveData<HoaDonResponse> hoaDonResponse = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public HoaDonViewModel(Application application) {
        super(application);
        this.repository = new HoaDonRepository();
    }

    public void getMyHoaDon(String token) {
        isLoading.setValue(true);
        repository.getMyHoaDon(token).observeForever(response -> {
            isLoading.setValue(false);
            if (response != null) {
                if (response.isSuccess()) {
                    hoaDonResponse.setValue(response);
                } else {
                    errorMessage.setValue(response.getMessage());
                }
            } else {
                errorMessage.setValue("Lỗi kết nối mạng");
            }
        });
    }
}

