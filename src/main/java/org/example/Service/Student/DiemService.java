package org.example.Service.Student;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.BangDiem;
import org.example.Model.HocKy;
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
    public TongDiem getMySummaryByKy(int ky, String nam) {
        String url = ApiEndpoint.STUDENT_SCORE_SUMMARY_BY_KY + ky+"/"+nam;
        String response = apiClient.get(url);
        return ApiResponseHandler.readData(response, TongDiem.class);
    }
    public List<BangDiem> getMyScoresByKy(int hocKy,String nam)
    {
        String url = ApiEndpoint.STUDENT_SCORE_ME_BY_KY + hocKy+"/"+nam;
        String response = apiClient.get(url);
        if (!ApiResponseHandler.isOk(response)) {
            return List.of();
        }
        return ApiResponseHandler.readData(response, new TypeReference<List<BangDiem>>() {});
    }
}
