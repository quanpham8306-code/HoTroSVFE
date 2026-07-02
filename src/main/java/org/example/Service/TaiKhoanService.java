package org.example.Service;

import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Config.AppSession;
import org.example.Model.ChangePws;
import org.example.Util.ApiEndpoint;

import java.util.Map;

public class TaiKhoanService {

    private final ApiClient apiClient = new ApiClient();

    public boolean login(String username, String password) {
        String response = apiClient.post(ApiEndpoint.LOGIN, Map.of(
                "username", username,
                "password", password
        ));

        if (!ApiResponseHandler.isOk(response)) {
            return false;
        }

        LoginResponse loginResponse = ApiResponseHandler.readData(response, LoginResponse.class);
        AppSession.setToken(loginResponse.getToken());
        AppSession.setUsername(username);
        AppSession.setRole(loginResponse.getRole());
        return true;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        String response = apiClient.put(ApiEndpoint.CHANGE_PASSWORD, Map.of(
                "oldPassword", oldPassword,
                "newPassword", newPassword
        ));
        return ApiResponseHandler.isOk(response);
    }

    public static class LoginResponse {
        private String role;

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getRole() {return  role;}
    }

    public String changePws(String oldPws,  String newPws ) {
        String response = apiClient.post(
                ApiEndpoint.COMMON_CHANGE_PWS,
                new ChangePws(oldPws,newPws)
        );

        if (ApiResponseHandler.isOk(response)) {
            return "SUCCESS";
        }
        return ApiResponseHandler.getMessage(response);
    }
}
