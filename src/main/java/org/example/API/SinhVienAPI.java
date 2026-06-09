package org.example.API;

import com.google.gson.Gson;
import org.example.Model.SinhVien;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SinhVienAPI {
    public static final String baseUrl = "http://26.108.243.246:8080/api/student";
    public static String getById(String maSv) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/" + maSv)) // Ví dụ tạo ra: .../api/student/CT9B230128
                    .GET()
                    .build();
            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(SinhVien sv) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(sv);

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(baseUrl + "/update"))
                            .header("Content-Type", "application/json")
                            .PUT(HttpRequest.BodyPublishers.ofString(json))
                            .build();

            client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
