package org.example.Service.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Model.MonHoc;
import org.example.Util.ApiEndpoint;
import org.example.Util.UrlUtil;

import java.util.List;

public class AdminSubjectService {
    private final org.example.Api.ApiClient apiClient =
            new org.example.Api.ApiClient();

    public List<MonHoc> getAll() {

        String response =
                apiClient.get(
                        ApiEndpoint.ADMIN_SUBJECT
                );

        return org.example.Api.ApiResponseHandler.readData(
                response,
                new TypeReference<List<MonHoc>>() {}
        );
    }

    public boolean add(MonHoc mh) {

        String response =
                apiClient.post(
                        ApiEndpoint.ADMIN_SUBJECT,
                        mh
                );

        return org.example.Api.ApiResponseHandler.isOk(response);
    }

    public boolean update(MonHoc mh) {

        String response =
                apiClient.put(
                        UrlUtil.build(
                                ApiEndpoint.ADMIN_SUBJECT_BY_ID,
                                mh.getMaMon()
                        ),
                        mh
                );

        return org.example.Api.ApiResponseHandler.isOk(response);
    }

    public boolean delete(String maMon) {

        String response =
                apiClient.delete(
                        UrlUtil.build(
                                ApiEndpoint.ADMIN_SUBJECT_BY_ID,
                                maMon
                        )
                );
        return org.example.Api.ApiResponseHandler.isOk(response);
    }
}
