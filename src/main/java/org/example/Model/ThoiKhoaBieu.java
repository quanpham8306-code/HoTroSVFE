package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class ThoiKhoaBieu {

    private int ky;
    private int namHoc;
    private List<LopHocPhan> lopHocPhanDTOList = new ArrayList<>();

    public ThoiKhoaBieu() {
    }

    public ThoiKhoaBieu(int ky, int namHoc, List<LopHocPhan> lopHocPhanDTOList) {
        this.ky = ky;
        this.namHoc = namHoc;
        this.lopHocPhanDTOList = lopHocPhanDTOList;
    }

    public int getKy() {
        return ky;
    }

    public void setKy(int ky) {
        this.ky = ky;
    }

    public int getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(int namHoc) {
        this.namHoc = namHoc;
    }

    public List<LopHocPhan> getLopHocPhanDTOList() {
        return lopHocPhanDTOList;
    }

    public void setLopHocPhanDTOList(List<LopHocPhan> lopHocPhanDTOList) {
        this.lopHocPhanDTOList = lopHocPhanDTOList;
    }
}
