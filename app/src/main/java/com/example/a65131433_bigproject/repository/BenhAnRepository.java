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

        android.util.Log.d("BenhAnRepository", "Calling API with token: " + (token != null ? token.substring(0, Math.min(20, token.length())) + "..." : "null"));

        RetrofitClient.getBenhAnApi().getToaThuocHienTai(token).enqueue(new Callback<ToaThuocHienTaiResponse>() {
            @Override
            public void onResponse(Call<ToaThuocHienTaiResponse> call, Response<ToaThuocHienTaiResponse> response) {
                android.util.Log.d("BenhAnRepository", "Response code: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    android.util.Log.d("BenhAnRepository", "Response success: " + response.body().isSuccess());
                    android.util.Log.d("BenhAnRepository", "Response message: " + response.body().getMessage());
                    if (response.body().getData() != null) {
                        android.util.Log.d("BenhAnRepository", "Data not null, toaThuoc size: " + 
                            (response.body().getData().getToaThuoc() != null ? response.body().getData().getToaThuoc().size() : 0));
                    } else {
                        android.util.Log.d("BenhAnRepository", "Data is null");
                    }
                    responseLiveData.setValue(response.body());
                } else {
                    android.util.Log.e("BenhAnRepository", "Response error: " + response.code());
                    ToaThuocHienTaiResponse errorResponse = new ToaThuocHienTaiResponse();
                    errorResponse.setSuccess(false);
                    String errorMsg = "Lỗi: " + response.code();
                    if (response.errorBody() != null) {
                        try {
                            errorMsg = response.errorBody().string();
                        } catch (Exception e) {
                            android.util.Log.e("BenhAnRepository", "Error reading error body", e);
                        }
                    }
                    errorResponse.setMessage(errorMsg);
                    responseLiveData.setValue(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<ToaThuocHienTaiResponse> call, Throwable t) {
                android.util.Log.e("BenhAnRepository", "Network error", t);
                ToaThuocHienTaiResponse errorResponse = new ToaThuocHienTaiResponse();
                errorResponse.setSuccess(false);
                errorResponse.setMessage("Lỗi kết nối: " + (t.getMessage() != null ? t.getMessage() : "Unknown error"));
                responseLiveData.setValue(errorResponse);
            }
        });

        return responseLiveData;
    }
}

