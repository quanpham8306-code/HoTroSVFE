package org.example.Service.Admin;

import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.ChangePws;
import org.example.Util.ApiEndpoint;

public class AccountService {
    ApiClient apiClient = new ApiClient();
    public String resetPws(String username) {
        String url = ApiEndpoint.ADMIN_RESET_PASSWORD+username;
        String response = apiClient.put(url);
        if (ApiResponseHandler.isOk(response)) {
            return "SUCCESS";
        }
        return ApiResponseHandler.getMessage(response);
    }
}
