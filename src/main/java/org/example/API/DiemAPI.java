package org.example.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DiemAPI {
    public static final String baseUrl = "http://localhost:8080/api/score";
    public static String getByIdSvIdlop(int idSv,int idlop) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/get/" + idSv + "/" + idlop))
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
}
