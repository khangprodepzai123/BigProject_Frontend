package com.example.a65131433_bigproject.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.api.RetrofitClient;
import com.example.a65131433_bigproject.models.RegisterKhamRequest;
import com.example.a65131433_bigproject.models.RegisterKhamResponse;
import com.example.a65131433_bigproject.utils.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BenhNhanRepository {
    private SharedPrefManager prefManager;
    
    public BenhNhanRepository(android.content.Context context) {
        this.prefManager = SharedPrefManager.getInstance(context);
    }
    
    public MutableLiveData<RegisterKhamResponse> registerKham(RegisterKhamRequest request) {
        MutableLiveData<RegisterKhamResponse> responseLiveData = new MutableLiveData<>();

        String token = prefManager.getToken();
        String authToken = token.isEmpty() ? "" : "Bearer " + token;

        RetrofitClient.getBenhNhanApi().registerKham(authToken, request).enqueue(new Callback<RegisterKhamResponse>() {
            @Override
            public void onResponse(Call<RegisterKhamResponse> call, Response<RegisterKhamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    responseLiveData.setValue(response.body());
                } else {
                    RegisterKhamResponse errorResponse = new RegisterKhamResponse();
                    errorResponse.setSuccess(false);
                    errorResponse.setMessage("Lỗi: " + response.code());
                    responseLiveData.setValue(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<RegisterKhamResponse> call, Throwable t) {
                RegisterKhamResponse errorResponse = new RegisterKhamResponse();
                errorResponse.setSuccess(false);
                errorResponse.setMessage("Lỗi kết nối: " + t.getMessage());
                responseLiveData.setValue(errorResponse);
            }
        });

        return responseLiveData;
    }
}

