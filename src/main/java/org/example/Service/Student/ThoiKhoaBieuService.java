package org.example.Service.Student;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.BuoiHoc;
import org.example.Model.Note;
import org.example.Model.ThoiKhoaBieu;
import org.example.Util.ApiEndpoint;
import org.example.Util.ExcelUtil;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

public class ThoiKhoaBieuService {

    private final ApiClient apiClient = new ApiClient();

    public ThoiKhoaBieu getMySchedule() {
        String response = apiClient.get(ApiEndpoint.STUDENT_SCHEDULE_ME);
        return ApiResponseHandler.readData(response, ThoiKhoaBieu.class);
    }
    public ThoiKhoaBieu getScheduleByWeek(LocalDate date) {
        String url = ApiEndpoint.STUDENT_SCHEDULE_BY_WEEK + date.toString();
        System.out.println(url);
        String response = apiClient.get(url);
        return ApiResponseHandler.readData(response, ThoiKhoaBieu.class);
    }
    public List<BuoiHoc> getNearSchedule() {
        String response = apiClient.get(ApiEndpoint.STUDENT_SCHEDULE_NEAR);
        return ApiResponseHandler.readData(response, new TypeReference<List<BuoiHoc>>() {});
    }
}
