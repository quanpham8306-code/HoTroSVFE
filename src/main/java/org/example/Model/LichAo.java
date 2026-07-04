package org.example.Model;

import java.util.List;

public class LichAo {
    List<LopHocPhan> lopHocPhanDTOList;

    public LichAo() {
    }

    public LichAo(List<LopHocPhan> lopHocPhanDTOList) {
        this.lopHocPhanDTOList = lopHocPhanDTOList;
    }

    public List<LopHocPhan> getLopHocPhanDTOList() {
        return lopHocPhanDTOList;
    }

    public void setLopHocPhanDTOList(List<LopHocPhan> lopHocPhanDTOList) {
        this.lopHocPhanDTOList = lopHocPhanDTOList;
    }
}
