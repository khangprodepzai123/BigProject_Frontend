package com.example.a65131433_bigproject.api;

import com.example.a65131433_bigproject.models.DiemTichLuyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface DiemTichLuyApi {
    @GET("api/diemtichluy/me")
    Call<DiemTichLuyResponse> getMyDiemTichLuy(@Header("Authorization") String token);
}

