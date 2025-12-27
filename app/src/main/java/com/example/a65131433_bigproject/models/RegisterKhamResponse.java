package com.example.a65131433_bigproject.models;

public class RegisterKhamResponse {
    private boolean success;
    private String message;
    private RegisterKhamData data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegisterKhamData getData() {
        return data;
    }

    public void setData(RegisterKhamData data) {
        this.data = data;
    }

    public static class RegisterKhamData {
        private String maBn;
        private String maKham;
        private String hoTenBn;
        private String thoiGianHenKham;

        public String getMaBn() {
            return maBn;
        }

        public String getMaKham() {
            return maKham;
        }

        public String getHoTenBn() {
            return hoTenBn;
        }

        public String getThoiGianHenKham() {
            return thoiGianHenKham;
        }
    }
}

