package org.example.Util;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.example.Api.ApiClient;

import java.io.File;

public class ExcelImportUtil {

    private static final ApiClient apiClient = new ApiClient();

    public static void handleImportExcel(Node ownerNode,
                                         String endpoint,
                                         String requestParam,
                                         Runnable reloadAction) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn file Excel");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel Files", "*.xlsx", "*.xls")
        );

        File file = fileChooser.showOpenDialog(ownerNode.getScene().getWindow());

        if (file == null) return;

        boolean success = apiClient.importExcel(file, endpoint, requestParam);

        Alert alert = new Alert(success
                ? Alert.AlertType.INFORMATION
                : Alert.AlertType.ERROR);

        alert.setHeaderText(null);
        alert.setContentText(success
                ? "Import Excel thành công!"
                : "Import Excel thất bại!");

        alert.showAndWait();

        if (success && reloadAction != null) {
            reloadAction.run();
        }
    }
}
