package org.example.Service.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.LopHocPhan;
import org.example.Util.ApiEndpoint;
import org.example.Util.UrlUtil;
import java.util.List;

public class AdminClassService {
    private final ApiClient apiClient =
            new ApiClient();

    public List<LopHocPhan> getAll() {
        String response = apiClient.get(ApiEndpoint.ADMIN_CLASS);

        return ApiResponseHandler.readData(
                response,
                new TypeReference<List<LopHocPhan>>() {}
        );
    }

    public boolean add(LopHocPhan lhp) {
        String response = apiClient.post(ApiEndpoint.ADMIN_CLASS, lhp);
        return ApiResponseHandler.isOk(response);
    }

    public boolean update(LopHocPhan lhp) {
        String response = apiClient.put(
                UrlUtil.build(
                        ApiEndpoint.ADMIN_CLASS_BY_MALOP + lhp.getMaLopHP()
                        ),
                lhp
        );
        return ApiResponseHandler.isOk(response);
    }

    public boolean delete(String maLop) {
        String response = apiClient.delete(
                UrlUtil.build(
                        ApiEndpoint.ADMIN_CLASS_BY_MALOP + maLop
                        )
                );
        return ApiResponseHandler.isOk(response);
    }
}
