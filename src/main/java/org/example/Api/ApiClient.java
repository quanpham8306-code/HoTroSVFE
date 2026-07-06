package org.example.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Config.AppSession;
import org.example.Util.ApiEndpoint;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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

    public String put(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + AppSession.getToken())
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("PUT STATUS = " + response.statusCode());

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

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RuntimeException("DELETE API failed: " + response.body());
            }

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("DELETE API failed: " + endpoint, e);
        }
    }

    public String delete(String endpoint, Object body) {
        try {
            String json = objectMapper.writeValueAsString(body);
            System.out.println(json);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ApiEndpoint.BASE_URL + endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + AppSession.getToken())
                    .method("DELETE", HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("DELETE STATUS = " + response.statusCode());
            System.out.println("DELETE BODY = " + response.body());

//            if (response.statusCode() < 200 || response.statusCode() >= 300) {
//                throw new RuntimeException("DELETE API failed: " + response.body());
//            }

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
    public boolean importExcel(File file, String endpoint, String nameFile) {
        try {
            String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();

            URL url = new URL(ApiEndpoint.BASE_URL + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            conn.setRequestProperty(
                    "Content-Type",
                    "multipart/form-data; boundary=" + boundary
            );

            conn.setRequestProperty("Authorization", "Bearer " + AppSession.getToken());

            try (OutputStream os = conn.getOutputStream();
                 PrintWriter writer = new PrintWriter(
                         new OutputStreamWriter(os, StandardCharsets.UTF_8), true)) {

                writer.append("--").append(boundary).append("\r\n");

                writer.append("Content-Disposition: form-data; name=\"")
                        .append(nameFile)
                        .append("\"; filename=\"")
                        .append(file.getName())
                        .append("\"\r\n");

                writer.append("Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet\r\n\r\n");
                writer.flush();

                Files.copy(file.toPath(), os);
                os.flush();

                writer.append("\r\n");
                writer.append("--").append(boundary).append("--").append("\r\n");
                writer.flush();
            }

            int status = conn.getResponseCode();

            InputStream is = status >= 200 && status < 300
                    ? conn.getInputStream()
                    : conn.getErrorStream();

            String response = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(response);

            return status >= 200 && status < 300;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public String delete(String endpoint, Object body) {
//        try {
//            String json = objectMapper.writeValueAsString(body);
//
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(ApiEndpoint.BASE_URL + endpoint))
//                    .header("Content-Type", "application/json")
//                    .header("Authorization", "Bearer " + AppSession.getToken())
//                    .method("DELETE", HttpRequest.BodyPublishers.ofString(json))
//                    .build();
//
//            HttpResponse<String> response =
//                    client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            System.out.println("DELETE STATUS = " + response.statusCode());
//            System.out.println("DELETE BODY = " + response.body());
//
//            return response.body();
//
//        } catch (Exception e) {
//            throw new RuntimeException("DELETE API failed: " + endpoint, e);
//        }
//    }
}
