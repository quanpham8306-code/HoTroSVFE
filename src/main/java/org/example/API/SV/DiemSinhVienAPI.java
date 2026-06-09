package org.example.API.SV;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Model.SinhVien;
import org.example.Model.TongDiem;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.example.Model.Token.token;

public class DiemSinhVienAPI {
    private static final String baseUrl="http://26.108.243.246:8080/api/student/score";
    public static SinhVien getMyScore(){
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/me"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();
            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            JsonNode root = mapper.readTree(response.body());
            SinhVien student =
                    mapper.treeToValue(
                            root.get("data"),
                            SinhVien.class
                    );

            return student;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static TongDiem getMyTongDiem() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/summary"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();
            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            JsonNode root = mapper.readTree(response.body());
            TongDiem td =
                    mapper.treeToValue(
                            root.get("data"),
                            TongDiem.class
                    );

            return td;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
