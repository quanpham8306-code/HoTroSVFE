package org.example.Model;

import java.sql.Time;

public class LopHocPhan {
    private int thuHoc;
    private Time gioBatDau;
    private Time gioiKetThuc;
    private String phongHoc;
    private MonHoc monHoc;

    public LopHocPhan() {
    }

    public LopHocPhan(int thuHoc, Time gioBatDau, Time gioiKetThuc, String phongHoc, MonHoc monHoc) {
        this.thuHoc = thuHoc;
        this.gioBatDau = gioBatDau;
        this.gioiKetThuc = gioiKetThuc;
        this.phongHoc = phongHoc;
        this.monHoc = monHoc;
    }

    public int getThuHoc() {
        return thuHoc;
    }

    public void setThuHoc(int thuHoc) {
        this.thuHoc = thuHoc;
    }

    public Time getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(Time gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public Time getGioiKetThuc() {
        return gioiKetThuc;
    }

    public void setGioiKetThuc(Time gioiKetThuc) {
        this.gioiKetThuc = gioiKetThuc;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }
}
