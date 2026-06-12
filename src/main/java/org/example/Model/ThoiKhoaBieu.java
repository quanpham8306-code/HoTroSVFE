package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class ThoiKhoaBieu {
    private String loaiLich;

    private int ky;

    private List<LopHocPhan> lopHocPhanList = new ArrayList<>();

    public ThoiKhoaBieu() {
    }

    public ThoiKhoaBieu(String loaiLich, int ky, List<LopHocPhan> lopHocPhanList) {
        this.loaiLich = loaiLich;
        this.ky = ky;
        this.lopHocPhanList = lopHocPhanList;
    }

    public String getLoaiLich() {
        return loaiLich;
    }

    public void setLoaiLich(String loaiLich) {
        this.loaiLich = loaiLich;
    }

    public int getKy() {
        return ky;
    }

    public void setKy(int ky) {
        this.ky = ky;
    }

    public List<LopHocPhan> getLopHocPhanList() {
        return lopHocPhanList;
    }

    public void setLopHocPhanList(List<LopHocPhan> lopHocPhanList) {
        this.lopHocPhanList = lopHocPhanList;
    }
}
