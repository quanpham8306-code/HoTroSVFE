package org.example.Service.Student;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.LopHocPhan;
import org.example.Util.ApiEndpoint;

import java.util.List;

public class LopHocPhanService {

    private final ApiClient apiClient = new ApiClient();

    public List<LopHocPhan> getPickedClass(String khoa, String mon) {
        String url = ApiEndpoint.STUDENT_PICKED_CLASS + khoa+"/"+mon;
        System.out.println(url);
        String response = apiClient.get(url);
        if (!ApiResponseHandler.isOk(response)) {
            return List.of();
        }
        return ApiResponseHandler.readData(response, new TypeReference<List<LopHocPhan>>() {});
    }
}
