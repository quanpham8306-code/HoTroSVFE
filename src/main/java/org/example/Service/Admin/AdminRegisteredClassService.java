package org.example.Service.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.DangKyLopRequest;
import org.example.Model.LopHocPhan;
import org.example.Model.ResponeObject;
import org.example.Model.SinhVien;
import org.example.Util.ApiEndpoint;
import org.example.Util.ExcelImportUtil;

import java.util.List;

public class AdminRegisteredClassService {
    private final ApiClient apiClient = new ApiClient();

    public List<LopHocPhan> getByMaSv(String maSv) {
        String response = apiClient.get(
                ApiEndpoint.ADMIN_REGISTERED_CLASS_BY_STUDENT + maSv
        );

        List<LopHocPhan> list = ApiResponseHandler.readData(
                response,
                new TypeReference<List<LopHocPhan>>() {}
        );

        return list != null ? list : List.of();
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

    public boolean removeStudentFromClass(String maSv, String maLopHP) {
        String response = apiClient.post(
                ApiEndpoint.ADMIN_REMOVE_STUDENT_FROM_CLASS,
                new DangKyLopRequest(maSv, maLopHP)
        );

        return ApiResponseHandler.isOk(response);
    }
    public void handleImportStudentToClass(Button button,String maLop, Runnable reloadAction) {
        ExcelImportUtil.handleImportExcel(
                button,
                ApiEndpoint.ADMIN_STUDENT_TO_CLASS_IMPORT + maLop,
                "danhSachSinhVienVaoLop",
                reloadAction
        );
    }
}
