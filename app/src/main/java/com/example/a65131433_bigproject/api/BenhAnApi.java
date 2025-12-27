package com.example.a65131433_bigproject.api;

import com.example.a65131433_bigproject.models.BenhAnResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BenhAnApi {
    @GET("api/benhan/me")
    Call<BenhAnResponse> getMyBenhAn(@Header("Authorization") String token);
}

