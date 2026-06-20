package org.example.Service.Student;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.stage.FileChooser;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.BuoiHoc;
import org.example.Model.ThoiKhoaBieu;
import org.example.Util.ApiEndpoint;

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
    public void exportSchedule(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu lịch học");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel Files", "*.xlsx")
        );

        fileChooser.setInitialFileName("lich_hoc.xlsx");

        File file = fileChooser.showSaveDialog(null);

        if (file == null) {
            return;
        }

        try {
            byte[] data = apiClient.downloadFile(ApiEndpoint.STUDENT_SCHEDULE_EXPORT);

            Files.write(file.toPath(), data);

            System.out.println("Xuất lịch học thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Xuất lịch học thất bại!");
        }
    }
    public List<BuoiHoc> getNearSchedule() {
        String response = apiClient.get(ApiEndpoint.STUDENT_SCHEDULE_NEAR);
        return ApiResponseHandler.readData(response, new TypeReference<List<BuoiHoc>>() {});
    }
}
