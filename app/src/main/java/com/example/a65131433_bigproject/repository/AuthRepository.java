package com.example.a65131433_bigproject.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.api.RetrofitClient;
import com.example.a65131433_bigproject.models.AuthResponse;
import com.example.a65131433_bigproject.models.LoginRequest;
import com.example.a65131433_bigproject.models.SignupRequest;
import com.example.a65131433_bigproject.utils.SharedPrefManager;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private SharedPrefManager prefManager;

    public AuthRepository(Context context) {
        this.prefManager = SharedPrefManager.getInstance(context);
    }

    // ===== SIGNUP =====
    public MutableLiveData<AuthResponse> signup(String username, String password) {
        MutableLiveData<AuthResponse> responseLiveData = new MutableLiveData<>();

        SignupRequest request = new SignupRequest(username, password);
        RetrofitClient.getAuthApi().signup(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AuthResponse authResponse = response.body();
                    if (authResponse.isSuccess()) {
                        // Lưu token & user info
                        AuthResponse.AuthResponseData data = authResponse.getData();
                        prefManager.saveToken("Bearer " + data.getToken());
                        prefManager.saveMaTk(data.getMaTk());
                        prefManager.saveUsername(data.getTenDangNhap());
                        prefManager.saveMaBn(data.getMaBn());
                        prefManager.saveHoTen(data.getHoTen());
                        prefManager.saveDiemTichLuy(data.getDiemTichLuy());
                    }
                    responseLiveData.setValue(authResponse);
                } else {
                    responseLiveData.setValue(new AuthResponse());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                responseLiveData.setValue(null);
            }
        });

        return responseLiveData;
    }

    // ===== LOGIN =====
    public MutableLiveData<AuthResponse> login(String username, String password) {
        MutableLiveData<AuthResponse> responseLiveData = new MutableLiveData<>();

        LoginRequest request = new LoginRequest(username, password);
        RetrofitClient.getAuthApi().login(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AuthResponse authResponse = response.body();
                    if (authResponse.isSuccess()) {
                        // Lưu token & user info
                        AuthResponse.AuthResponseData data = authResponse.getData();
                        prefManager.saveToken("Bearer " + data.getToken());
                        prefManager.saveMaTk(data.getMaTk());
                        prefManager.saveUsername(data.getTenDangNhap());
                        prefManager.saveMaBn(data.getMaBn());
                        prefManager.saveHoTen(data.getHoTen());
                        prefManager.saveDiemTichLuy(data.getDiemTichLuy());
                    }
                    responseLiveData.setValue(authResponse);
                } else {
                    responseLiveData.setValue(new AuthResponse());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                responseLiveData.setValue(null);
            }
        });

        return responseLiveData;
    }

    // ===== GET ME =====
    public MutableLiveData<AuthResponse> getMe(String token) {
        MutableLiveData<AuthResponse> responseLiveData = new MutableLiveData<>();

        RetrofitClient.getAuthApi().getMe(token).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    responseLiveData.setValue(response.body());
                } else {
                    responseLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                responseLiveData.setValue(null);
            }
        });

        return responseLiveData;
    }

    // ===== LOGOUT =====
    public void logout() {
        prefManager.logout();
    }

    // ===== CHECK LOGIN =====
    public boolean isLoggedIn() {
        return prefManager.isLoggedIn();
    }

    public String getToken() {
        return prefManager.getToken();
    }
}