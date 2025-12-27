// ===== models/AuthResponse.java =====
package com.example.a65131433_bigproject.models;

public class AuthResponse {
    private boolean success;
    private String message;
    private AuthResponseData data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public AuthResponseData getData() {
        return data;
    }

    public static class AuthResponseData {
        private String maTk;
        private String tenDangNhap;
        private String maBn;
        private String hoTen;
        private Integer diemTichLuy;
        private String token;

        public String getMaTk() {
            return maTk;
        }

        public String getTenDangNhap() {
            return tenDangNhap;
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
            return token;
        }
    }
}