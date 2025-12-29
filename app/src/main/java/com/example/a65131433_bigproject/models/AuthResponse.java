// ===== models/AuthResponse.java =====
package com.example.a65131433_bigproject.models;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("success")
    private boolean success;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("data")
    private AuthResponseData data;

    public AuthResponse() {
        // Default constructor
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message != null ? message : "";
    }

    public AuthResponseData getData() {
        return data;
    }

    public static class AuthResponseData {
        @SerializedName("maTk")
        private String maTk;
        
        @SerializedName("tenDangNhap")
        private String tenDangNhap;
        
        @SerializedName("maBn")
        private String maBn;
        
        @SerializedName("hoTen")
        private String hoTen;
        
        @SerializedName("diemTichLuy")
        private Integer diemTichLuy;
        
        @SerializedName("token")
        private String token;

        public String getMaTk() {
            return maTk != null ? maTk : "";
        }

        public String getTenDangNhap() {
            return tenDangNhap != null ? tenDangNhap : "";
        }

        public String getMaBn() {
            return maBn;
        }

        public String getHoTen() {
            return hoTen;
        }

        public Integer getDiemTichLuy() {
            return diemTichLuy != null ? diemTichLuy : 0;
        }

        public String getToken() {
            return token != null ? token : "";
        }
    }
}