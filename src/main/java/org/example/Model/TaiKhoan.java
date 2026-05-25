package org.example.Model;

import java.util.Date;

public class TaiKhoan {
    private String username;
    private String password;
    private String role;
    private SinhVien sinhVien;

    public TaiKhoan() {
    }

    public TaiKhoan(String username, String password, String role, SinhVien sinhVien) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.sinhVien = sinhVien;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }
}
