package org.example.Api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ApiResponseHandler {
    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static boolean isOk(String responseBody) {
        try {
            return "ok".equalsIgnoreCase(mapper.readTree(responseBody).path("status").asText());
        } catch (Exception e) {
            return false;
        }
    }

    public static String getMessage(String responseBody) {
        try {
            return mapper.readTree(responseBody).path("message").asText("");
        } catch (Exception e) {
            return "Không đọc được phản hồi từ server";
        }
    }

    public static <T> T readData(String responseBody, Class<T> clazz) {
        try {
            JsonNode root = mapper.readTree(responseBody);
            JsonNode data = root.get("data");

            if (data == null || data.isNull()) {
                return null;
            }

            return mapper.treeToValue(data, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse response data", e);
        }
    }

    public static <T> T readData(String responseBody, TypeReference<T> typeReference) {
        try {
            JsonNode root = mapper.readTree(responseBody);
            JsonNode data = root.get("data");

            if (data == null || data.isNull()) {
                return null;
            }

            return mapper.convertValue(data, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse response data", e);
        }
    }
}
