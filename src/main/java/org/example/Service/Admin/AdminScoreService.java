package org.example.Service.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Api.ApiResponseHandler;
import org.example.Model.Diem;
import org.example.Util.ApiEndpoint;
import org.example.Util.UrlUtil;

import java.util.List;

public class AdminScoreService {
    private final org.example.Api.ApiClient apiClient =
            new org.example.Api.ApiClient();

    public List<Diem> getAll() {
        String response =
                apiClient.get(ApiEndpoint.ADMIN_SCORE);

        return org.example.Api.ApiResponseHandler.readData(
                response,
                new TypeReference<List<Diem>>() {});
    }

    public boolean add(Diem diem) {

        String response =
                apiClient.post(
                        ApiEndpoint.ADMIN_SCORE
                        + diem.getMaSv()
                                + "/"
                                + diem.getMaLopHP(),
                        diem
                );

        return org.example.Api.ApiResponseHandler.isOk(response);
    }

    public boolean update(Diem diem) {
        String response = apiClient.put(
                ApiEndpoint.ADMIN_SCORE_BY_STUDENT_CLASS
                        + diem.getMaSv()
                        + "/"
                        + diem.getMaLopHP(),
                diem
        );

        return ApiResponseHandler.isOk(response);
    }

    public boolean delete(Diem diem) {
        String response = apiClient.delete(
                ApiEndpoint.ADMIN_SCORE_BY_STUDENT_CLASS
                        + diem.getMaSv()
                        + "/"
                        + diem.getMaLopHP()
        );

        return ApiResponseHandler.isOk(response);
    }
}
