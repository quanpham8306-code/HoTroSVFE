package org.example.Service.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;
import org.example.Api.ApiResponseHandler;
import org.example.Model.MonHoc;
import org.example.Model.SinhVien;
import org.example.Util.ApiEndpoint;
import org.example.Util.UrlUtil;

import java.util.List;

public class AdminSubjectService {
    private final org.example.Api.ApiClient apiClient =
            new org.example.Api.ApiClient();

//    public List<MonHoc> getAll() {
//        try {
//            String response = apiClient.get(ApiEndpoint.ADMIN_SUBJECT);
//
//            System.out.println("RAW JSON:" + response);
//
//            ObjectMapper mapper = new ObjectMapper();
//
//            return mapper.readValue(
//                    response,
//                    new TypeReference<List<MonHoc>>() {}
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//            return List.of();
//        }
//    }

    public List<MonHoc> getAll() {
        try {
            String response = apiClient.get(ApiEndpoint.ADMIN_SUBJECT);

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(
                    response,
                    new TypeReference<List<MonHoc>>() {}
            );

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }



    public boolean add(MonHoc mh) {
        String response = apiClient.post(ApiEndpoint.ADMIN_SUBJECT, mh);

        if (!ApiResponseHandler.isOk(response)) {
            showError(ApiResponseHandler.getMessage(response));
            return false;
        }

        return true;
    }

    public boolean update(MonHoc mh) {
        String response = apiClient.put(
                ApiEndpoint.ADMIN_SUBJECT_BY_MAMON + mh.getMaMon(),
                mh
        );

        if (!ApiResponseHandler.isOk(response)) {
            showError(ApiResponseHandler.getMessage(response));
            return false;
        }

        return true;
    }

    public boolean delete(String maMon) {
        String response = apiClient.delete(
                ApiEndpoint.ADMIN_SUBJECT_BY_MAMON + maMon
        );

        if (!ApiResponseHandler.isOk(response)) {
            showError(ApiResponseHandler.getMessage(response));
            return false;
        }

        return true;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
