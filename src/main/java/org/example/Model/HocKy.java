package org.example.Model;

public class HocKy {
    private int hocKy;
    private String namHoc;

    public HocKy() {
    }

    public HocKy(int hocKy, String namHoc) {
        this.hocKy = hocKy;
        this.namHoc = namHoc;
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
    @Override
    public String toString() {
        if (hocKy == 0 && "ALL".equals(namHoc)) {
            return "Tất cả các kỳ";
        }
        return "Kỳ: " + hocKy + " Năm: " + namHoc;
    }
}
