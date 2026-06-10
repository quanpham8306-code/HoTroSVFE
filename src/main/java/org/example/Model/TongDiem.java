package org.example.Model;

public class TongDiem {
    private double gpa;
    private int tongTin;
    private int tongMon;
    private String xepLoai;

    public TongDiem() {
    }

    public TongDiem(double gpa, int tongTin, int tongMon, String xepLoai) {
        this.gpa = gpa;
        this.tongTin = tongTin;
        this.tongMon = tongMon;
        this.xepLoai = xepLoai;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getTongTin() {
        return tongTin;
    }

    public void setTongTin(int tongTin) {
        this.tongTin = tongTin;
    }

    public int getTongMon() {
        return tongMon;
    }

    public void setTongMon(int tongMon) {
        this.tongMon = tongMon;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }
}
