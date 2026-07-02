package org.example.Service.Admin;

import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.ChangePws;
import org.example.Util.ApiEndpoint;

public class AccountService {
    ApiClient apiClient = new ApiClient();
    public String resetPws() {
        String response = apiClient.put(
                ApiEndpoint.ADMIN_RESET_PASSWORD
        );

        if (ApiResponseHandler.isOk(response)) {
            return "SUCCESS";
        }
        return ApiResponseHandler.getMessage(response);
    }
}
