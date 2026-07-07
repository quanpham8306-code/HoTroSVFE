package org.example.Service.Student;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.*;
import org.example.Util.AlertUtil;
import org.example.Util.ApiEndpoint;

import java.util.List;

public class LichAoService {
    private final ApiClient apiClient = new ApiClient();

    public boolean isTrungLich(LopHocPhan lopCu, LopHocPhan lopMoi) {
        if (lopCu.getThu() != lopMoi.getThu()) {
            return false;
        }

        boolean trungNgay = !lopMoi.getNgayKetThuc().isBefore(lopCu.getNgayBatDau())
                && !lopMoi.getNgayBatDau().isAfter(lopCu.getNgayKetThuc());

        if (!trungNgay) {
            return false;
        }

        boolean trungGio = lopMoi.getGioBatDau().isBefore(lopCu.getGioKetThuc())
                && lopMoi.getGioKetThuc().isAfter(lopCu.getGioBatDau());

        return trungGio;
    }

    public LopHocPhan timLopBiTrung(List<LopHocPhan> danhSachDaChon, LopHocPhan lopMoi) {
        if (danhSachDaChon == null || danhSachDaChon.isEmpty() || lopMoi == null) {
            return null;
        }

        for (LopHocPhan lopDaChon : danhSachDaChon) {
            if (lopDaChon == null) {
                continue;
            }

            boolean trungThu = lopDaChon.getThu() == lopMoi.getThu();

            boolean trungGio =
                    lopMoi.getGioBatDau().isBefore(lopDaChon.getGioKetThuc())
                            && lopMoi.getGioKetThuc().isAfter(lopDaChon.getGioBatDau());

            boolean trungNgay =
                    !lopMoi.getNgayKetThuc().isBefore(lopDaChon.getNgayBatDau())
                            && !lopMoi.getNgayBatDau().isAfter(lopDaChon.getNgayKetThuc());

            if (trungThu && trungGio && trungNgay) {
                return lopDaChon;
            }
        }

        return null;
    }
    public LichAo getLichAo(){
        String response = apiClient.get(ApiEndpoint.STUDENT_GET_VIRTUAL_SCHEDULE);
        if (!ApiResponseHandler.isOk(response)) {
            return null;
        }
        return ApiResponseHandler.readData(response, LichAo.class);
    }
    public String save(LichAo lichAo){
        String response = apiClient.post(
                ApiEndpoint.STUDENT_UPSERT_VIRTUAL_SCHEDULE,
                lichAo
        );
        if (!ApiResponseHandler.isOk(response)) {
            return "SUCCESS";
        }
        else
            return ApiResponseHandler.getMessage(response);
    }
    public String deleteLichAo(LichAo lichAo){
        String response = apiClient.delete(ApiEndpoint.STUDENT_DELETE_VIRTUAL_SCHEDULE);
        if(ApiResponseHandler.isOk(response)) {
            return "SUCCESS";
        }
        else
            return ApiResponseHandler.getMessage(response);
    }
}
