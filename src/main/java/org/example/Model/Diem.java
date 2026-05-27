package org.example.Model;

public class Diem {

    private String Mon;
    private double diemQuaTrinh;
    private double diemCuoiKy;
    private double diemHocPhan;

    public Diem() {
    }

    public Diem(String mon, double diemQuaTrinh, double diemCuoiKy, double diemHocPhan) {
        Mon = mon;
        this.diemQuaTrinh = diemQuaTrinh;
        this.diemCuoiKy = diemCuoiKy;
        this.diemHocPhan = diemHocPhan;
    }

    public String getMon() {
        return Mon;
    }

    public void setMon(String mon) {
        Mon = mon;
    }

    public double getDiemQuaTrinh() {
        return diemQuaTrinh;
    }

    public void setDiemQuaTrinh(double diemQuaTrinh) {
        this.diemQuaTrinh = diemQuaTrinh;
    }

    public double getDiemCuoiKy() {
        return diemCuoiKy;
    }

    public void setDiemCuoiKy(double diemCuoiKy) {
        this.diemCuoiKy = diemCuoiKy;
    }

    public double getDiemHocPhan() {
        return diemHocPhan;
    }

    public void setDiemHocPhan(double diemHocPhan) {
        this.diemHocPhan = diemHocPhan;
    }

}
