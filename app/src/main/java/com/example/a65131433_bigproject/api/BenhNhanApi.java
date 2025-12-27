package com.example.a65131433_bigproject.api;

import com.example.a65131433_bigproject.models.RegisterKhamRequest;
import com.example.a65131433_bigproject.models.RegisterKhamResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BenhNhanApi {
    @POST("api/benhnhan/register")
    Call<RegisterKhamResponse> registerKham(@Header("Authorization") String token, @Body RegisterKhamRequest request);
}

