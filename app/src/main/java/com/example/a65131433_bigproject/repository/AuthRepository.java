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
                    android.util.Log.d("AuthRepository", "Signup response: success=" + authResponse.isSuccess() + ", message=" + authResponse.getMessage());
                    
                    if (authResponse.isSuccess()) {
                        // Lưu token & user info
                        AuthResponse.AuthResponseData data = authResponse.getData();
                        if (data != null && data.getToken() != null) {
                            prefManager.saveToken("Bearer " + data.getToken());
                            prefManager.saveMaTk(data.getMaTk());
                            prefManager.saveUsername(data.getTenDangNhap());
                            prefManager.saveMaBn(data.getMaBn());
                            prefManager.saveHoTen(data.getHoTen());
                            prefManager.saveDiemTichLuy(data.getDiemTichLuy());
                            android.util.Log.d("AuthRepository", "Đã lưu token và thông tin user");
                        } else {
                            android.util.Log.e("AuthRepository", "Data hoặc Token null!");
                        }
                    }
                    responseLiveData.setValue(authResponse);
                } else {
                    // Parse error response
                    try {
                        if (response.errorBody() != null) {
                            String errorBody = response.errorBody().string();
                            android.util.Log.e("AuthRepository", "Signup error: " + response.code() + " - " + errorBody);
                            
                            // Try to parse as AuthResponse
                            com.google.gson.Gson gson = new com.google.gson.Gson();
                            AuthResponse errorResponse = gson.fromJson(errorBody, AuthResponse.class);
                            if (errorResponse != null) {
                                responseLiveData.setValue(errorResponse);
                            } else {
                                AuthResponse emptyResponse = new AuthResponse();
                                responseLiveData.setValue(emptyResponse);
                            }
                        } else {
                            android.util.Log.e("AuthRepository", "Signup error: " + response.code() + " - No error body");
                            AuthResponse emptyResponse = new AuthResponse();
                            responseLiveData.setValue(emptyResponse);
                        }
                    } catch (Exception e) {
                        android.util.Log.e("AuthRepository", "Error parsing error response: " + e.getMessage());
                        AuthResponse emptyResponse = new AuthResponse();
                        responseLiveData.setValue(emptyResponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                android.util.Log.e("AuthRepository", "Signup network error: " + t.getMessage());
                t.printStackTrace();
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