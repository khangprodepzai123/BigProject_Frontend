package com.example.a65131433_bigproject.models;

import java.math.BigDecimal;
import java.util.List;

public class HoaDonResponse {
    private boolean success;
    private String message;
    private List<HoaDonData> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<HoaDonData> getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<HoaDonData> data) {
        this.data = data;
    }

    public static class HoaDonData {
        private String maHd;
        private String maKham;
        private String ngayLap;
        private BigDecimal thanhTien;
        private Integer diemTichLuySuDung;
        private String bacSi;
        private List<ChiTietHoaDonData> chiTiet;

        public String getMaHd() {
            return maHd;
        }

        public String getMaKham() {
            return maKham;
        }

        public String getNgayLap() {
            return ngayLap;
        }

        public BigDecimal getThanhTien() {
            return thanhTien;
        }

        public Integer getDiemTichLuySuDung() {
            return diemTichLuySuDung;
        }

        public String getBacSi() {
            return bacSi;
        }

        public List<ChiTietHoaDonData> getChiTiet() {
            return chiTiet;
        }

        public static class ChiTietHoaDonData {
            private String maThuoc;
            private String tenThuoc;
            private int soLuong;
            private BigDecimal donGia;
            private BigDecimal thanhTien;

            public String getMaThuoc() {
                return maThuoc;
            }

            public String getTenThuoc() {
                return tenThuoc;
            }

            public int getSoLuong() {
                return soLuong;
            }

            public BigDecimal getDonGia() {
                return donGia;
            }

            public BigDecimal getThanhTien() {
                return thanhTien;
            }
        }
    }
}

