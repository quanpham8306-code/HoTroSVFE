package org.example.Service.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Model.Diem;
import org.example.Util.ApiEndpoint;

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
                        ApiEndpoint.ADMIN_SCORE,
                        diem
                );

        return org.example.Api.ApiResponseHandler.isOk(response);
    }

//    public boolean update(String mon,
//                          Diem diem) {
//
//        String response =
//                apiClient.put(
//                        UrlUtil.build(
//                                ApiEndpoint.ADMIN_SCORE_BY_MON,
//                                mon
//                        ),
//                        diem
//                );
//
//        return org.example.Api.ApiResponseHandler.isOk(response);
//    }
//
//    public boolean delete(String mon) {
//
//        String response =
//                apiClient.delete(
//                        UrlUtil.build(
//                                ApiEndpoint.ADMIN_SCORE_BY_MON,
//                                mon
//                        )
//                );
//
//        return org.example.Api.ApiResponseHandler.isOk(response);
//    }
}
