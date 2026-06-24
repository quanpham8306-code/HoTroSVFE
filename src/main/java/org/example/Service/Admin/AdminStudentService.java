package org.example.Service.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Model.SinhVien;
import org.example.Util.ApiEndpoint;
import org.example.Util.UrlUtil;

import java.util.List;

public class AdminStudentService {
    private final org.example.Api.ApiClient apiClient = new org.example.Api.ApiClient();
//    public List<SinhVien> getAll() {
//
//        try {
//            String response =
//                    apiClient.get(ApiEndpoint.ADMIN_STUDENT);
//
//            System.out.println(response);
//
//            List<SinhVien> list =
//                    org.example.Api.ApiResponseHandler.readData(
//                            response,
//                            new TypeReference<List<SinhVien>>() {}
//                    );
//
//            System.out.println(list);
//
//            return list != null ? list : List.of();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return List.of();
//        }
//    }

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public List<SinhVien> getAll() {
        try {
            String response = apiClient.get(ApiEndpoint.ADMIN_STUDENT);

            System.out.println("RAW JSON: " + response);

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

            return mapper.readValue(
                    response,
                    new TypeReference<List<SinhVien>>() {}
            );

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public boolean add(SinhVien sv) {
        String response =
                apiClient.post(
                        ApiEndpoint.ADMIN_STUDENT,
                        sv
                );

        System.out.println("ADD STUDENT RESPONSE = " + response);
        return org.example.Api.ApiResponseHandler.isOk(response);
    }

    public boolean update(SinhVien sv) {
        String response =
                apiClient.put(
                        UrlUtil.build(
                                ApiEndpoint.ADMIN_STUDENT_BY_MASV + sv.getMaSv()
                        ),
                        sv
                );

        System.out.println("UPDATE STUDENT RESPONSE = " + response);
        return org.example.Api.ApiResponseHandler.isOk(response);
    }

    public boolean delete(String maSv) {

        String response = apiClient.post(
                ApiEndpoint.ADMIN_STUDENT_DELETE_BY_MASV + maSv,
                null
        );

        return true;
    }
}
