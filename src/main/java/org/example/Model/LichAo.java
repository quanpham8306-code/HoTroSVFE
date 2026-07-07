package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class LichAo {
    List<LopHocPhan> lopHocPhanDTOList =  new ArrayList<LopHocPhan>();

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
