package com.example.a65131433_bigproject.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.a65131433_bigproject.models.AuthResponse;
import com.example.a65131433_bigproject.repository.AuthRepository;

public class AuthViewModel extends AndroidViewModel {
    private AuthRepository repository;
    public MutableLiveData<AuthResponse> authResponse = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public AuthViewModel(Application application) {
        super(application);
        this.repository = new AuthRepository(application.getApplicationContext());
    }

    // ===== SIGNUP =====
    public void signup(String username, String password) {
        isLoading.setValue(true);
        repository.signup(username, password).observeForever(response -> {
            isLoading.setValue(false);
            if (response != null) {
                if (response.isSuccess()) {
                    authResponse.setValue(response);
                } else {
                    errorMessage.setValue(response.getMessage());
                }
            } else {
                errorMessage.setValue("Lỗi kết nối mạng");
            }
        });
    }

    // ===== LOGIN =====
    public void login(String username, String password) {
        isLoading.setValue(true);
        repository.login(username, password).observeForever(response -> {
            isLoading.setValue(false);
            if (response != null) {
                if (response.isSuccess()) {
                    authResponse.setValue(response);
                } else {
                    errorMessage.setValue(response.getMessage());
                }
            } else {
                errorMessage.setValue("Lỗi kết nối mạng");
            }
        });
    }

    // ===== GET ME =====
    public void getMe(String token) {
        repository.getMe(token).observeForever(response -> {
            if (response != null) {
                authResponse.setValue(response);
            }
        });
    }

    // ===== LOGOUT =====
    public void logout() {
        repository.logout();
    }

    // ===== CHECK LOGIN =====
    public boolean isLoggedIn() {
        return repository.isLoggedIn();
    }

    public String getToken() {
        return repository.getToken();
    }
}