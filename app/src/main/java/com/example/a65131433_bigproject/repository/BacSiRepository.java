package com.example.a65131433_bigproject.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.api.RetrofitClient;
import com.example.a65131433_bigproject.models.BacSiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BacSiRepository {
    
    public MutableLiveData<BacSiResponse> getAllBacSi() {
        MutableLiveData<BacSiResponse> responseLiveData = new MutableLiveData<>();

        android.util.Log.d("BacSiRepository", "Calling API to get all doctors");

        RetrofitClient.getBacSiApi().getAllBacSi().enqueue(new Callback<BacSiResponse>() {
            @Override
            public void onResponse(Call<BacSiResponse> call, Response<BacSiResponse> response) {
                android.util.Log.d("BacSiRepository", "Response code: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    android.util.Log.d("BacSiRepository", "Response success: " + response.body().isSuccess());
                    if (response.body().getData() != null) {
                        android.util.Log.d("BacSiRepository", "Data size: " + response.body().getData().size());
                    }
                    responseLiveData.setValue(response.body());
                } else {
                    android.util.Log.e("BacSiRepository", "Response error: " + response.code());
                    BacSiResponse errorResponse = new BacSiResponse();
                    errorResponse.setSuccess(false);
                    String errorMsg = "Lỗi: " + response.code();
                    if (response.errorBody() != null) {
                        try {
                            errorMsg = response.errorBody().string();
                        } catch (Exception e) {
                            android.util.Log.e("BacSiRepository", "Error reading error body", e);
                        }
                    }
                    errorResponse.setMessage(errorMsg);
                    responseLiveData.setValue(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<BacSiResponse> call, Throwable t) {
                android.util.Log.e("BacSiRepository", "Network error", t);
                BacSiResponse errorResponse = new BacSiResponse();
                errorResponse.setSuccess(false);
                errorResponse.setMessage("Lỗi kết nối: " + (t.getMessage() != null ? t.getMessage() : "Unknown error"));
                responseLiveData.setValue(errorResponse);
            }
        });

        return responseLiveData;
    }
}

