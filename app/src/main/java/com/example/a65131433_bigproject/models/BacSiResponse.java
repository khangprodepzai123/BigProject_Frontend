package com.example.a65131433_bigproject.models;

import java.util.List;

public class BacSiResponse {
    private boolean success;
    private String message;
    private List<BacSiData> data;

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

    public List<BacSiData> getData() {
        return data;
    }

    public void setData(List<BacSiData> data) {
        this.data = data;
    }

    public static class BacSiData {
        private String maBs;
        private String hoTenBs;
        private String trinhDoHocVan;
        private String chuyenKhoa;
        private int tuoi;
        private int kinhNghiem;
        private String chungChiHanhNghe;
        private int soLuotKham;

        public String getMaBs() {
            return maBs;
        }

        public void setMaBs(String maBs) {
            this.maBs = maBs;
        }

        public String getHoTenBs() {
            return hoTenBs;
        }

        public void setHoTenBs(String hoTenBs) {
            this.hoTenBs = hoTenBs;
        }

        public String getTrinhDoHocVan() {
            return trinhDoHocVan;
        }

        public void setTrinhDoHocVan(String trinhDoHocVan) {
            this.trinhDoHocVan = trinhDoHocVan;
        }

        public String getChuyenKhoa() {
            return chuyenKhoa;
        }

        public void setChuyenKhoa(String chuyenKhoa) {
            this.chuyenKhoa = chuyenKhoa;
        }

        public int getTuoi() {
            return tuoi;
        }

        public void setTuoi(int tuoi) {
            this.tuoi = tuoi;
        }

        public int getKinhNghiem() {
            return kinhNghiem;
        }

        public void setKinhNghiem(int kinhNghiem) {
            this.kinhNghiem = kinhNghiem;
        }

        public String getChungChiHanhNghe() {
            return chungChiHanhNghe;
        }

        public void setChungChiHanhNghe(String chungChiHanhNghe) {
            this.chungChiHanhNghe = chungChiHanhNghe;
        }

        public int getSoLuotKham() {
            return soLuotKham;
        }

        public void setSoLuotKham(int soLuotKham) {
            this.soLuotKham = soLuotKham;
        }
    }
}

