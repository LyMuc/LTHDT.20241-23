package Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
public class HelpController {

    @FXML
    private BorderPane aimsTag;

    @FXML
    private BorderPane insertTag;

    @FXML
    private BorderPane shellTag;

    @FXML
    private BorderPane mergeTag;
    
    @FXML
    private StackPane stackPane;
    	
    @FXML
    private TextFlow centerContent;
    
    @FXML
    private Button backButton;
    
    private Stage stage;
    private Scene scene;
    
    @FXML
    void BackToMenu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void aimsTagClicked(MouseEvent event) {
        centerContent.getChildren().clear();
     
        // Tạo các Text với nội dung và định dạng
        Text heading = new Text("Our Aims:\n");
        heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
     
        Text aim1 = new Text(" - To make sorting easier to understand.\n");
        aim1.setStyle("-fx-font-size: 14px; -fx-fill: #0073e6;"); // Màu xanh dương
     
        Text aim2 = new Text(" - Provide interactive examples of sorting algorithms.\n");
        aim2.setStyle("-fx-font-size: 14px; -fx-fill: #28a745;"); // Màu xanh lá cây
     
        Text aim3 = new Text(" - Help developers choose the right algorithm.\n");
        aim3.setStyle("-fx-font-size: 14px; -fx-fill: #dc3545;"); // Màu đỏ
     
        // Thêm Text vào centerContent
        centerContent.getChildren().addAll(heading, aim1, aim2, aim3);
    }
    

    @FXML
    void insertTagClicked(MouseEvent event) {
    	centerContent.getChildren().clear();
    	Text heading = new Text("Selection sort:\n");
        heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
     
        Text aim1 = new Text(" - To make sorting easier to understand.\n");
        aim1.setStyle("-fx-font-size: 14px; -fx-fill: #0073e6;"); // Màu xanh dương
     
        Text aim2 = new Text(" - Provide interactive examples of sorting algorithms.\n");
        aim2.setStyle("-fx-font-size: 14px; -fx-fill: #28a745;"); // Màu xanh lá cây
     
        Text aim3 = new Text(" - Help developers choose the right algorithm.\n");
        aim3.setStyle("-fx-font-size: 14px; -fx-fill: #dc3545;"); // Màu đỏ
     
        // Thêm Text vào centerContent
        centerContent.getChildren().addAll(heading, aim1, aim2, aim3);
    }


    @FXML
    void mergeTagClicked(MouseEvent event) {
    	centerContent.getChildren().clear();
    	Text heading = new Text("Merge Sort:\n");
        heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
     
        Text aim1 = new Text(" - To make sorting easier to understand.\n");
        aim1.setStyle("-fx-font-size: 14px; -fx-fill: #0073e6;"); // Màu xanh dương
     
        Text aim2 = new Text(" - Provide interactive examples of sorting algorithms.\n");
        aim2.setStyle("-fx-font-size: 14px; -fx-fill: #28a745;"); // Màu xanh lá cây
     
        Text aim3 = new Text(" - Help developers choose the right algorithm.\n");
        aim3.setStyle("-fx-font-size: 14px; -fx-fill: #dc3545;"); // Màu đỏ
     
        // Thêm Text vào centerContent
        centerContent.getChildren().addAll(heading, aim1, aim2, aim3);
    }

    @FXML
    void shellTagClicked(MouseEvent event) {
    	centerContent.getChildren().clear();
    	Text heading = new Text("Shell Sort:\n");
        heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
     
        Text aim1 = new Text(" - To make sorting easier to understand.\n");
        aim1.setStyle("-fx-font-size: 14px; -fx-fill: #0073e6;"); // Màu xanh dương
     
        Text aim2 = new Text(" - Provide interactive examples of sorting algorithms.\n");
        aim2.setStyle("-fx-font-size: 14px; -fx-fill: #28a745;"); // Màu xanh lá cây
     
        Text aim3 = new Text(" - Help developers choose the right algorithm.\n");
        aim3.setStyle("-fx-font-size: 14px; -fx-fill: #dc3545;"); // Màu đỏ
     
        // Thêm Text vào centerContent
        centerContent.getChildren().addAll(heading, aim1, aim2, aim3);
    }

}
