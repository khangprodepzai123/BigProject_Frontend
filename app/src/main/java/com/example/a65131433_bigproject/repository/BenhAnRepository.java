package com.example.a65131433_bigproject.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.api.RetrofitClient;
import com.example.a65131433_bigproject.models.BenhAnResponse;
import com.example.a65131433_bigproject.models.ToaThuocHienTaiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BenhAnRepository {
    
    public MutableLiveData<BenhAnResponse> getMyBenhAn(String token) {
        MutableLiveData<BenhAnResponse> responseLiveData = new MutableLiveData<>();

        RetrofitClient.getBenhAnApi().getMyBenhAn(token).enqueue(new Callback<BenhAnResponse>() {
            @Override
            public void onResponse(Call<BenhAnResponse> call, Response<BenhAnResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    responseLiveData.setValue(response.body());
                } else {
                    BenhAnResponse errorResponse = new BenhAnResponse();
                    errorResponse.setSuccess(false);
                    errorResponse.setMessage("Lỗi: " + response.code());
                    responseLiveData.setValue(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<BenhAnResponse> call, Throwable t) {
                BenhAnResponse errorResponse = new BenhAnResponse();
                errorResponse.setSuccess(false);
                errorResponse.setMessage("Lỗi kết nối: " + t.getMessage());
                responseLiveData.setValue(errorResponse);
            }
        });

        return responseLiveData;
    }

    public MutableLiveData<ToaThuocHienTaiResponse> getToaThuocHienTai(String token) {
        MutableLiveData<ToaThuocHienTaiResponse> responseLiveData = new MutableLiveData<>();

        RetrofitClient.getBenhAnApi().getToaThuocHienTai(token).enqueue(new Callback<ToaThuocHienTaiResponse>() {
            @Override
            public void onResponse(Call<ToaThuocHienTaiResponse> call, Response<ToaThuocHienTaiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    responseLiveData.setValue(response.body());
                } else {
                    ToaThuocHienTaiResponse errorResponse = new ToaThuocHienTaiResponse();
                    errorResponse.setSuccess(false);
                    errorResponse.setMessage("Lỗi: " + response.code());
                    responseLiveData.setValue(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<ToaThuocHienTaiResponse> call, Throwable t) {
                ToaThuocHienTaiResponse errorResponse = new ToaThuocHienTaiResponse();
                errorResponse.setSuccess(false);
                errorResponse.setMessage("Lỗi kết nối: " + t.getMessage());
                responseLiveData.setValue(errorResponse);
            }
        });

        return responseLiveData;
    }
}

