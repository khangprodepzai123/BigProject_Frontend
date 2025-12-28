package com.example.a65131433_bigproject.models;

public class DiemTichLuyResponse {
    private boolean success;
    private String message;
    private DiemTichLuyData data;

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

    public DiemTichLuyData getData() {
        return data;
    }

    public void setData(DiemTichLuyData data) {
        this.data = data;
    }

    public static class DiemTichLuyData {
        private int diemTichLuy;
        private MucUuDaiData mucUuDai;
        private boolean coTheSuDung;
        private int phanTramGiamGia;

        public int getDiemTichLuy() {
            return diemTichLuy;
        }

        public void setDiemTichLuy(int diemTichLuy) {
            this.diemTichLuy = diemTichLuy;
        }

        public MucUuDaiData getMucUuDai() {
            return mucUuDai;
        }

        public void setMucUuDai(MucUuDaiData mucUuDai) {
            this.mucUuDai = mucUuDai;
        }

        public boolean isCoTheSuDung() {
            return coTheSuDung;
        }

        public void setCoTheSuDung(boolean coTheSuDung) {
            this.coTheSuDung = coTheSuDung;
        }

        public int getPhanTramGiamGia() {
            return phanTramGiamGia;
        }

        public void setPhanTramGiamGia(int phanTramGiamGia) {
            this.phanTramGiamGia = phanTramGiamGia;
        }

        public static class MucUuDaiData {
            private String maMuc;
            private String tenMuc;
            private String moTa;
            private int diemToiThieu;

            public String getMaMuc() {
                return maMuc;
            }

            public void setMaMuc(String maMuc) {
                this.maMuc = maMuc;
            }

            public String getTenMuc() {
                return tenMuc;
            }

            public void setTenMuc(String tenMuc) {
                this.tenMuc = tenMuc;
            }

            public String getMoTa() {
                return moTa;
            }

            public void setMoTa(String moTa) {
                this.moTa = moTa;
            }

            public int getDiemToiThieu() {
                return diemToiThieu;
            }

            public void setDiemToiThieu(int diemToiThieu) {
                this.diemToiThieu = diemToiThieu;
            }
        }
    }
}

