package org.example.Service.Student;

import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.SinhVien;
import org.example.Util.ApiEndpoint;

public class SinhVienService {

    private final ApiClient apiClient = new ApiClient();

    public SinhVien getMyInfo() {
        String response = apiClient.get(ApiEndpoint.STUDENT_ME);
        return ApiResponseHandler.readData(response, SinhVien.class);
    }
    public int getKhoa()
    {
        String response = apiClient.get(ApiEndpoint.STUDENT_ME);
        return ApiResponseHandler.readData(response, SinhVien.class).getNamNhapHoc();
    }
}
