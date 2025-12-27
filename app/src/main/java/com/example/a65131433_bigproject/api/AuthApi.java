// ===== api/AuthApi.java =====
package com.example.a65131433_bigproject.api;

import com.example.a65131433_bigproject.models.AuthResponse;
import com.example.a65131433_bigproject.models.LoginRequest;
import com.example.a65131433_bigproject.models.SignupRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface AuthApi {
    @POST("api/auth/signup")
    Call<AuthResponse> signup(@Body SignupRequest request);

    @POST("api/auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);

    @GET("api/auth/me")
    Call<AuthResponse> getMe(@Header("Authorization") String token);
}