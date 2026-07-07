package org.example.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class BuoiHoc {
    private LocalDate ngayHoc;
    private LopHocPhan lopHocPhanDTO;

    public BuoiHoc() {
    }

    public BuoiHoc(LocalDate ngayHoc, LopHocPhan lopHocPhanDTO) {
        this.ngayHoc = ngayHoc;
        this.lopHocPhanDTO = lopHocPhanDTO;
    }

    public LocalDate getNgayHoc() {
        return ngayHoc;
    }

    public void setNgayHoc(LocalDate ngayHoc) {
        this.ngayHoc = ngayHoc;
    }

    public LopHocPhan getLopHocPhanDTO() {
        return lopHocPhanDTO;
    }

    public void setLopHocPhanDTO(LopHocPhan lopHocPhanDTO) {
        this.lopHocPhanDTO = lopHocPhanDTO;
    }
}
