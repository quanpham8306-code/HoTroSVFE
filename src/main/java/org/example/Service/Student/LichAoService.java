package org.example.Service.Student;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Api.ApiClient;
import org.example.Api.ApiResponseHandler;
import org.example.Model.HocKy;
import org.example.Model.LopHocPhan;
import org.example.Model.Note;
import org.example.Model.ResponeObject;
import org.example.Util.ApiEndpoint;

import java.util.List;

public class LichAoService {
    private final ApiClient apiClient = new ApiClient();

    public ResponeObject checkThemLop(List<String> maLopHocPhanList, String newMaLopHocPhan) {
        CheckLichAoRequest request = new CheckLichAoRequest(
                maLopHocPhanList,
                newMaLopHocPhan
        );

        String response = apiClient.post(
                ApiEndpoint.STUDENT_VIRTUAL_SCHEDULE_CHECK,
                request
        );
        return ApiResponseHandler.readData(response, ResponeObject.class);
    }

    private static class CheckLichAoRequest {
        private List<String> selectedLopMaLops;
        private String newLopMaLop;

        public CheckLichAoRequest(List<String> selectedLopMaLops, String newLopMaLop) {
            this.selectedLopMaLops = selectedLopMaLops;
            this.newLopMaLop = newLopMaLop;
        }

        public List<String> getSelectedLopMaLops() {
            return selectedLopMaLops;
        }
        public String getNewLopMaLop() {
            return newLopMaLop;
        }
    }
}
