package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Kiểm tra tệp FXML
            URL fxmlFile = getClass().getResource("/application/sortingPage.fxml");
            if (fxmlFile == null) {
                throw new IOException("Tệp sortingPage.fxml không tồn tại!");
            }																												

            // Tải giao diện từ FXML
            Parent root = FXMLLoader.load(fxmlFile);
            Scene scene = new Scene(root);
            primaryStage.setTitle("23Sort Application");

            // Lấy kích thước màn hình và đặt kích thước cửa sổ
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setWidth(600);
            primaryStage.setHeight(500);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // Hiển thị thông báo lỗi nếu tệp FXML không tồn tại
            showError("Lỗi", "Không tìm thấy tệp FXML", e.getMessage());
        } catch (Exception e) {
            // Xử lý các lỗi khác
            e.printStackTrace();
        }
    }

    private void showError(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
