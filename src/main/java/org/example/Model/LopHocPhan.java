package org.example.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class LopHocPhan {

    private String maLopHP;

    private String tenMonHoc;

    private String giangVien;

    private String phongHoc;

    private int thu;

    private LocalTime gioBatDau;

    private LocalTime gioKetThuc;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    private Integer siSoToiDa;

    private int hocKy;

    private String namHoc;

    private String nganh;

    private String khoa;

    public LopHocPhan() {
    }

    public LopHocPhan(String maLopHP, String tenMonHoc, String giangVien, String phongHoc, int thu, LocalTime gioBatDau, LocalDate ngayBatDau, LocalTime gioKetThuc, LocalDate ngayKetThuc, Integer siSoToiDa, int hocKy, String namHoc, String nganh, String khoa) {
        this.maLopHP = maLopHP;
        this.tenMonHoc = tenMonHoc;
        this.giangVien = giangVien;
        this.phongHoc = phongHoc;
        this.thu = thu;
        this.gioBatDau = gioBatDau;
        this.ngayBatDau = ngayBatDau;
        this.gioKetThuc = gioKetThuc;
        this.ngayKetThuc = ngayKetThuc;
        this.siSoToiDa = siSoToiDa;
        this.hocKy = hocKy;
        this.namHoc = namHoc;
        this.nganh = nganh;
        this.khoa = khoa;
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

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
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

    public int getHocKy() {
        return hocKy;
    }

    public void setHocKy(int hocKy) {
        this.hocKy = hocKy;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getKhoangNgayHoc() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ngayBatDau.format(formatter) + " - " + ngayKetThuc.format(formatter);
    }

    public String getKhoangThoiGian() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return gioBatDau.format(formatter) + " - " + gioKetThuc.format(formatter);
    }
}
