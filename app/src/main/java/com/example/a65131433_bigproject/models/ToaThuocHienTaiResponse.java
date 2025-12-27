package com.example.a65131433_bigproject.models;

import java.util.List;

public class ToaThuocHienTaiResponse {
    private boolean success;
    private String message;
    private ToaThuocHienTaiData data;

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

    public ToaThuocHienTaiData getData() {
        return data;
    }

    public void setData(ToaThuocHienTaiData data) {
        this.data = data;
    }

    public static class ToaThuocHienTaiData {
        private String maBenhAn;
        private String maKham;
        private String ngayKham;
        private String ngayLuu;
        private String bacSi;
        private String chuanDoan;
        private List<ThuocData> toaThuoc;

        public String getMaBenhAn() {
            return maBenhAn;
        }

        public void setMaBenhAn(String maBenhAn) {
            this.maBenhAn = maBenhAn;
        }

        public String getMaKham() {
            return maKham;
        }

        public void setMaKham(String maKham) {
            this.maKham = maKham;
        }

        public String getNgayKham() {
            return ngayKham;
        }

        public void setNgayKham(String ngayKham) {
            this.ngayKham = ngayKham;
        }

        public String getNgayLuu() {
            return ngayLuu;
        }

        public void setNgayLuu(String ngayLuu) {
            this.ngayLuu = ngayLuu;
        }

        public String getBacSi() {
            return bacSi;
        }

        public void setBacSi(String bacSi) {
            this.bacSi = bacSi;
        }

        public String getChuanDoan() {
            return chuanDoan;
        }

        public void setChuanDoan(String chuanDoan) {
            this.chuanDoan = chuanDoan;
        }

        public List<ThuocData> getToaThuoc() {
            return toaThuoc;
        }

        public void setToaThuoc(List<ThuocData> toaThuoc) {
            this.toaThuoc = toaThuoc;
        }

        public static class ThuocData {
            private String maThuoc;
            private String tenThuoc;
            private int soLuong;
            private String lieuDung;
            private String cachDung;
            private int soLanUongMoiNgay;

            public String getMaThuoc() {
                return maThuoc;
            }

            public void setMaThuoc(String maThuoc) {
                this.maThuoc = maThuoc;
            }

            public String getTenThuoc() {
                return tenThuoc;
            }

            public void setTenThuoc(String tenThuoc) {
                this.tenThuoc = tenThuoc;
            }

            public int getSoLuong() {
                return soLuong;
            }

            public void setSoLuong(int soLuong) {
                this.soLuong = soLuong;
            }

            public String getLieuDung() {
                return lieuDung;
            }

            public void setLieuDung(String lieuDung) {
                this.lieuDung = lieuDung;
            }

            public String getCachDung() {
                return cachDung;
            }

            public void setCachDung(String cachDung) {
                this.cachDung = cachDung;
            }

            public int getSoLanUongMoiNgay() {
                return soLanUongMoiNgay;
            }

            public void setSoLanUongMoiNgay(int soLanUongMoiNgay) {
                this.soLanUongMoiNgay = soLanUongMoiNgay;
            }
        }
    }
}

