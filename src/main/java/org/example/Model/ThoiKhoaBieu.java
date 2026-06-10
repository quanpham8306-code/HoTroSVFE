package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class ThoiKhoaBieu {
    private String loaiLich;

    private List<LopHocPhan> lopHocPhanList = new ArrayList<>();

    public ThoiKhoaBieu() {
    }

    public ThoiKhoaBieu(String loaiLich, List<LopHocPhan> lopHocPhanList) {
        this.loaiLich = loaiLich;
        this.lopHocPhanList = lopHocPhanList;
    }

    public String getLoaiLich() {
        return loaiLich;
    }

    public void setLoaiLich(String loaiLich) {
        this.loaiLich = loaiLich;
    }

    public List<LopHocPhan> getLopHocPhanList() {
        return lopHocPhanList;
    }

    public void setLopHocPhanList(List<LopHocPhan> lopHocPhanList) {
        this.lopHocPhanList = lopHocPhanList;
    }
}
