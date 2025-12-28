package com.example.a65131433_bigproject.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.api.RetrofitClient;
import com.example.a65131433_bigproject.models.DiemTichLuyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiemTichLuyRepository {
    
    public MutableLiveData<DiemTichLuyResponse> getMyDiemTichLuy(String token) {
        MutableLiveData<DiemTichLuyResponse> responseLiveData = new MutableLiveData<>();

        android.util.Log.d("DiemTichLuyRepository", "Calling API with token: " + (token != null ? token.substring(0, Math.min(20, token.length())) + "..." : "null"));

        RetrofitClient.getDiemTichLuyApi().getMyDiemTichLuy(token).enqueue(new Callback<DiemTichLuyResponse>() {
            @Override
            public void onResponse(Call<DiemTichLuyResponse> call, Response<DiemTichLuyResponse> response) {
                android.util.Log.d("DiemTichLuyRepository", "Response code: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    android.util.Log.d("DiemTichLuyRepository", "Response success: " + response.body().isSuccess());
                    android.util.Log.d("DiemTichLuyRepository", "Response message: " + response.body().getMessage());
                    if (response.body().getData() != null) {
                        android.util.Log.d("DiemTichLuyRepository", "DiemTichLuy: " + response.body().getData().getDiemTichLuy());
                    }
                    responseLiveData.setValue(response.body());
                } else {
                    android.util.Log.e("DiemTichLuyRepository", "Response error: " + response.code());
                    DiemTichLuyResponse errorResponse = new DiemTichLuyResponse();
                    errorResponse.setSuccess(false);
                    String errorMsg = "Lỗi: " + response.code();
                    if (response.errorBody() != null) {
                        try {
                            errorMsg = response.errorBody().string();
                        } catch (Exception e) {
                            android.util.Log.e("DiemTichLuyRepository", "Error reading error body", e);
                        }
                    }
                    errorResponse.setMessage(errorMsg);
                    responseLiveData.setValue(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<DiemTichLuyResponse> call, Throwable t) {
                android.util.Log.e("DiemTichLuyRepository", "Network error", t);
                DiemTichLuyResponse errorResponse = new DiemTichLuyResponse();
                errorResponse.setSuccess(false);
                errorResponse.setMessage("Lỗi kết nối: " + (t.getMessage() != null ? t.getMessage() : "Unknown error"));
                responseLiveData.setValue(errorResponse);
            }
        });

        return responseLiveData;
    }
}

