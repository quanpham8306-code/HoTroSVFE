package org.example.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class LopHocPhan {

    private String maLopHP;

    private String tenMonHoc;

    private String giangVien;

    private String phongHoc;

    private String thu;

    private LocalTime gioBatDau;

    private LocalTime gioKetThuc;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    private Integer siSoToiDa;

    private String hocKy;

    private String namHoc;

    public LopHocPhan() {
    }

    public LopHocPhan(String maLopHP, String tenMonHoc, String giangVien, String phongHoc, String thu, LocalTime gioBatDau, LocalTime gioKetThuc, LocalDate ngayBatDau, LocalDate ngayKetThuc, Integer siSoToiDa, String hocKy, String namHoc) {
        this.maLopHP = maLopHP;
        this.tenMonHoc = tenMonHoc;
        this.giangVien = giangVien;
        this.phongHoc = phongHoc;
        this.thu = thu;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.siSoToiDa = siSoToiDa;
        this.hocKy = hocKy;
        this.namHoc = namHoc;
    }

    public String getMaLopHP() {
        return maLopHP;
    }

    public void setMaLopHP(String maLopHP) {
        this.maLopHP = maLopHP;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(String giangVien) {
        this.giangVien = giangVien;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public LocalTime getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(LocalTime gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public LocalTime getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(LocalTime gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Integer getSiSoToiDa() {
        return siSoToiDa;
    }

    public void setSiSoToiDa(Integer siSoToiDa) {
        this.siSoToiDa = siSoToiDa;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }
}
