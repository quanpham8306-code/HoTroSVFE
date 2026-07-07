package org.example.Service.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.scene.control.Button;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.BangDiem;
import org.example.Model.DangKyLopRequest;
import org.example.Model.LopHocPhan;
import org.example.Model.SinhVien;
import org.example.Util.ApiEndpoint;
import org.example.Util.ExcelUtil;

import java.util.List;

public class AdminRegisteredClassService {
    private final ApiClient apiClient = new ApiClient();

    public List<SinhVien> getByMaLopHP(String maLopHp) {
        String response = apiClient.get(
                ApiEndpoint.ADMIN_STUDENT_IN_CLASS + maLopHp
        );
        if (!ApiResponseHandler.isOk(response)) {
            return List.of();
        }
        return ApiResponseHandler.readData(response, new TypeReference<List<SinhVien>>() {});
    }

    public String addStudentToClass(String maSv, String maLopHP) {
        String response = apiClient.post(
                ApiEndpoint.ADMIN_ADD_STUDENT_TO_CLASS,
                new DangKyLopRequest(maSv, maLopHP)
        );

        if (ApiResponseHandler.isOk(response)) {
            return "SUCCESS";
        }

        return ApiResponseHandler.getMessage(response);
    }

    public String removeStudentFromClass(String maSv, String maLopHP) {
        String url =  ApiEndpoint.ADMIN_REMOVE_STUDENT_FROM_CLASS + maSv + "/" + maLopHP;
        String response = apiClient.delete(url
        );
        if (ApiResponseHandler.isOk(response)) {
            return "SUCCESS";
        }

        return ApiResponseHandler.getMessage(response);
    }
    public void handleImportStudentToClass(Button button,String maLop, Runnable reloadAction) {
        ExcelUtil.handleImportExcel(
                button,
                ApiEndpoint.ADMIN_STUDENT_TO_CLASS_IMPORT + maLop,
                "danhSachSinhVienVaoLop",
                reloadAction
        );
    }
}
