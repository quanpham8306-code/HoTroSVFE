package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class ThoiKhoaBieu {

    private int ky;
    private String namHoc;
    private List<LopHocPhan> lopHocPhanDTOList = new ArrayList<>();

    public ThoiKhoaBieu() {
    }

    public int getKy() {
        return ky;
    }

    public void setKy(int ky) {
        this.ky = ky;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public List<LopHocPhan> getLopHocPhanDTOList() {
        return lopHocPhanDTOList;
    }

    public void setLopHocPhanDTOList(List<LopHocPhan> lopHocPhanDTOList) {
        this.lopHocPhanDTOList = lopHocPhanDTOList;
    }

    public ThoiKhoaBieu(int ky, String namHoc, List<LopHocPhan> lopHocPhanDTOList) {
        this.ky = ky;
        this.namHoc = namHoc;
        this.lopHocPhanDTOList = lopHocPhanDTOList;
    }
}
