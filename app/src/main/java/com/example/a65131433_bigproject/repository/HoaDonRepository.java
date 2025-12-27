package com.example.a65131433_bigproject.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.api.RetrofitClient;
import com.example.a65131433_bigproject.models.HoaDonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoaDonRepository {
    
    public MutableLiveData<HoaDonResponse> getMyHoaDon(String token) {
        MutableLiveData<HoaDonResponse> responseLiveData = new MutableLiveData<>();

        RetrofitClient.getHoaDonApi().getMyHoaDon(token).enqueue(new Callback<HoaDonResponse>() {
            @Override
            public void onResponse(Call<HoaDonResponse> call, Response<HoaDonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    responseLiveData.setValue(response.body());
                } else {
                    HoaDonResponse errorResponse = new HoaDonResponse();
                    errorResponse.setSuccess(false);
                    errorResponse.setMessage("Lỗi: " + response.code());
                    responseLiveData.setValue(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<HoaDonResponse> call, Throwable t) {
                HoaDonResponse errorResponse = new HoaDonResponse();
                errorResponse.setSuccess(false);
                errorResponse.setMessage("Lỗi kết nối: " + t.getMessage());
                responseLiveData.setValue(errorResponse);
            }
        });

        return responseLiveData;
    }
}

