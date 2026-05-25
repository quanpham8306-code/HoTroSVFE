package org.example.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TaiKhoanAPI {
    public static final String baseUrl = "http://localhost:8080/api/account";
    public static String logIn(String username, String password) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String json =
                    """
                    {
                        "username":"%s",
                        "password":"%s"
                    }      
                    """.formatted(username, password);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/login"))
                    .header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
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
