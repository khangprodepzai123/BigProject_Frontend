// ===== models/SignupRequest.java =====
package com.example.a65131433_bigproject.models;

import com.google.gson.annotations.SerializedName;

public class SignupRequest {
    @SerializedName("tenDangNhap")
    private String tenDangNhap;
    
    @SerializedName("matKhau")
    private String matKhau;

    public SignupRequest(String tenDangNhap, String matKhau) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }
}