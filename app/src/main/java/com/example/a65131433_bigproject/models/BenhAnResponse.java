package com.example.a65131433_bigproject.models;

import java.util.List;

public class BenhAnResponse {
    private boolean success;
    private String message;
    private List<BenhAnData> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<BenhAnData> getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<BenhAnData> data) {
        this.data = data;
    }

    public static class BenhAnData {
        private String maBenhAn;
        private String maKham;
        private String ngayKham;
        private String ngayLuu;
        private String bacSi;
        private String lyDoKham;
        private String quaTrinhBenhLy;
        private String tienSuBenhNhan;
        private String tienSuGiaDinh;
        private String khamBoPhan;
        private String chuanDoan;
        private String huongXuTri;
        private String loaiKham;
        private String xuTriKham;
        private List<ToaThuocData> toaThuoc;

        public String getMaBenhAn() {
            return maBenhAn;
        }

        public String getMaKham() {
            return maKham;
        }

        public String getNgayKham() {
            return ngayKham;
        }

        public String getNgayLuu() {
            return ngayLuu;
        }

        public String getBacSi() {
            return bacSi;
        }

        public String getLyDoKham() {
            return lyDoKham;
        }

        public String getQuaTrinhBenhLy() {
            return quaTrinhBenhLy;
        }

        public String getTienSuBenhNhan() {
            return tienSuBenhNhan;
        }

        public String getTienSuGiaDinh() {
            return tienSuGiaDinh;
        }

        public String getKhamBoPhan() {
            return khamBoPhan;
        }

        public String getChuanDoan() {
            return chuanDoan;
        }

        public String getHuongXuTri() {
            return huongXuTri;
        }

        public String getLoaiKham() {
            return loaiKham;
        }

        public String getXuTriKham() {
            return xuTriKham;
        }

        public List<ToaThuocData> getToaThuoc() {
            return toaThuoc;
        }

        public static class ToaThuocData {
            private String maThuoc;
            private String tenThuoc;
            private int soLuong;
            private String lieuDung;
            private String cachDung;

            public String getMaThuoc() {
                return maThuoc;
            }

            public String getTenThuoc() {
                return tenThuoc;
            }

            public int getSoLuong() {
                return soLuong;
            }

            public String getLieuDung() {
                return lieuDung;
            }

            public String getCachDung() {
                return cachDung;
            }
        }
    }
}

