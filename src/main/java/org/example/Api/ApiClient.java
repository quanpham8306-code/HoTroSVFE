package org.example.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Config.AppSession;
import org.example.Util.ApiEndpoint;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.example.Util.ApiEndpoint.BASE_URL;

public class ApiClient {

    private final HttpClient client = HttpClient.newHttpClient();
   // private final ObjectMapper objectMapper = new ObjectMapper();

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public String get(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .header("Authorization", "Bearer " + AppSession.getToken())
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("GET API failed: " + endpoint, e);
        }
    }

    public String post(String endpoint, Object body) {
        try {
            String json = objectMapper.writeValueAsString(body);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + AppSession.getToken())
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("POST STATUS = " + response.statusCode());
            System.out.println("POST BODY = " + response.body());
            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("POST API failed: " + endpoint, e);
        }
    }

    public String put(String endpoint, Object body) {
        try {
            String json = objectMapper.writeValueAsString(body);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + AppSession.getToken())
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("PUT STATUS = " + response.statusCode());
            System.out.println("PUT BODY = " + response.body());

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("PUT API failed: " + endpoint, e);
        }
    }

    public String delete(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiEndpoint.BASE_URL + endpoint))
                    .header("Authorization", "Bearer " + AppSession.getToken())
                    .DELETE()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("DELETE STATUS = " + response.statusCode());
            System.out.println("DELETE BODY = " + response.body());

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("DELETE API failed: " + endpoint, e);
        }
    }


    public byte[] downloadFile(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .header("Authorization", "Bearer " + AppSession.getToken())
                    .GET()
                    .build();

            HttpResponse<byte[]> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofByteArray()
            );

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return response.body();
            }

            throw new RuntimeException("Tải file thất bại. Status: " + response.statusCode());

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tải file Excel", e);
        }
    }
}
