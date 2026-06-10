package org.example.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.BangDiem;
import org.example.Model.TongDiem;
import org.example.Util.ApiEndpoint;

import java.util.List;

public class DiemService {

    private final ApiClient apiClient = new ApiClient();

    public List<BangDiem> getMyScores() {
        String response = apiClient.get(ApiEndpoint.STUDENT_SCORE_ME);
        if (!ApiResponseHandler.isOk(response)) {
            return List.of();
        }
        return ApiResponseHandler.readData(response, new TypeReference<List<BangDiem>>() {});
    }

    public TongDiem getMySummary() {
        String response = apiClient.get(ApiEndpoint.STUDENT_SCORE_SUMMARY);
        return ApiResponseHandler.readData(response, TongDiem.class);
    }
}
