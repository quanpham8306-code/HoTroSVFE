package org.example.Model;

import java.util.List;

public class TongDiem {
    private double GPA;
    private double tongTin, tongMon;
    private String xepLoai;
    private List<Diem> listDiem;

    public TongDiem() {
    }

    public TongDiem(double GPA, double tongTin, double tongMon, String xepLoai, List<Diem> listDiem) {
        this.GPA = GPA;
        this.tongTin = tongTin;
        this.tongMon = tongMon;
        this.xepLoai = xepLoai;
        this.listDiem = listDiem;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public double getTongTin() {
        return tongTin;
    }

    public void setTongTin(double tongTin) {
        this.tongTin = tongTin;
    }

    public double getTongMon() {
        return tongMon;
    }

    public void setTongMon(double tongMon) {
        this.tongMon = tongMon;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }

    public List<Diem> getListDiem() {
        return listDiem;
    }

    public void setListDiem(List<Diem> listDiem) {
        this.listDiem = listDiem;
    }
}
