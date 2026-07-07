package org.example.Util;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.example.Api.ApiClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ExcelUtil {

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
    public static void handleExport(Window owner,
                              String endpoint,
                              String defaultFileName) {
        byte[] excelData = apiClient.downloadFile(endpoint);

        if (excelData == null || excelData.length == 0) {
            AlertUtil.showAlert("Không có dữ liệu để xuất.");
            return;
        }

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Xuất Excel");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel Workbook (*.xlsx)", "*.xlsx")
        );
        chooser.setInitialFileName(defaultFileName);

        File file = chooser.showSaveDialog(owner);

        if (file == null) return;

        try {
            Files.write(file.toPath(), excelData);
            AlertUtil.showAlert("Xuất Excel thành công.");
        } catch (IOException e) {
            AlertUtil.showAlert("Xuất Excel thất bại.");
            e.printStackTrace();
        }
    }
}

