package org.example.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.ThoiKhoaBieu;
import org.example.Util.ApiEndpoint;

import java.util.List;

public class ThoiKhoaBieuService {

    private final ApiClient apiClient = new ApiClient();

    public List<ThoiKhoaBieu> getMySchedule() {
        String response = apiClient.get(ApiEndpoint.STUDENT_SCHEDULE_ME);
        if (!ApiResponseHandler.isOk(response)) {
            return List.of();
        }
        return ApiResponseHandler.readData(response, new TypeReference<List<ThoiKhoaBieu>>() {});
    }
}
