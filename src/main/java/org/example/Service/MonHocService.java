package org.example.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.LopHocPhan;
import org.example.Model.MonHoc;
import org.example.Util.ApiEndpoint;

import java.util.List;

public class MonHocService {
    private final ApiClient apiClient = new ApiClient();

    public List<MonHoc> getMonHocByKhoaAndNganh(String khoa) {
        String response = apiClient.get(ApiEndpoint.STUDENT_PICKED_SUBJECT + khoa);
        if (!ApiResponseHandler.isOk(response)) {
            return List.of();
        }
        return ApiResponseHandler.readData(response, new TypeReference<List<MonHoc>>() {});
    }
}
