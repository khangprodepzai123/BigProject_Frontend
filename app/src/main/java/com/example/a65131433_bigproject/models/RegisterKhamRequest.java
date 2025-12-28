package com.example.a65131433_bigproject.models;

import java.util.Date;

public class RegisterKhamRequest {
    // Thông tin bệnh nhân
    private String hoTenBn;
    private String sdt;
    private String ngaySinh; // Format: "yyyy-MM-dd"
    private String gt; // Giới tính
    private String doiTuong; // Đối tượng
    private String diaChi;
    private String bhyt; // Bảo hiểm y tế

    // Thông tin đăng ký khám
    private String lyDoKham;
    private String thoiGianHenKham; // Format: "yyyy-MM-dd HH:mm" (ngày giờ hẹn khám)

    // Constructors
    public RegisterKhamRequest() {
    }

    public RegisterKhamRequest(String hoTenBn, String sdt, String ngaySinh, String gt, 
                              String doiTuong, String diaChi, String bhyt, 
                              String lyDoKham, String thoiGianHenKham) {
        this.hoTenBn = hoTenBn;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.gt = gt;
        this.doiTuong = doiTuong;
        this.diaChi = diaChi;
        this.bhyt = bhyt;
        this.lyDoKham = lyDoKham;
        this.thoiGianHenKham = thoiGianHenKham;
    }

    // Getters and Setters
    public String getHoTenBn() {
        return hoTenBn;
    }

    public void setHoTenBn(String hoTenBn) {
        this.hoTenBn = hoTenBn;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getBhyt() {
        return bhyt;
    }

    public void setBhyt(String bhyt) {
        this.bhyt = bhyt;
    }

    public String getLyDoKham() {
        return lyDoKham;
    }

    public void setLyDoKham(String lyDoKham) {
        this.lyDoKham = lyDoKham;
    }

    public String getThoiGianHenKham() {
        return thoiGianHenKham;
    }

    public void setThoiGianHenKham(String thoiGianHenKham) {
        this.thoiGianHenKham = thoiGianHenKham;
    }
}



