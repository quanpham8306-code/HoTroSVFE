package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class ThoiKhoaBieu {
    private String loaiLich;

    private int ky;

    private List<LopHocPhan> lopHocPhanDTOList = new ArrayList<>();

    public ThoiKhoaBieu() {
    }

    public ThoiKhoaBieu(String loaiLich, int ky, List<LopHocPhan> lopHocPhanList) {
        this.loaiLich = loaiLich;
        this.ky = ky;
        this.lopHocPhanDTOList = lopHocPhanList;
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
        return lopHocPhanDTOList;
    }

    public void setLopHocPhanDTOList(List<LopHocPhan> lopHocPhanList) {
        this.lopHocPhanDTOList = lopHocPhanList;
    }
}
