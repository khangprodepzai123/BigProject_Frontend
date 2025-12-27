package com.example.a65131433_bigproject.api;

import com.example.a65131433_bigproject.models.HoaDonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HoaDonApi {
    @GET("api/hoadon/me")
    Call<HoaDonResponse> getMyHoaDon(@Header("Authorization") String token);
}

