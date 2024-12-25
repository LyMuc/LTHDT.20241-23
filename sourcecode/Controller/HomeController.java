package Controller;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeController {
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button exitButton;
    @FXML 
    private VBox pane;
    @FXML
    private Button getHelpButton;
    

    @FXML
    void exit(ActionEvent event) {
        // Tạo một hộp thoại xác nhận
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận thoát");
        alert.setHeaderText("Bạn có chắc chắn muốn thoát?");

        // Hiển thị hộp thoại và chờ phản hồi từ người dùng
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Người dùng chọn OK
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        } else {
            // Người dùng chọn Cancel hoặc đóng hộp thoại
            alert.close();
        }
    }

    
    @FXML
    public void switchToInsertion(MouseEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("../View/insertionSort.fxml"));
    	stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    	scene=new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    	
    }

    @FXML
    public void switchToMerge(MouseEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("../View/mergeSort.fxml"));
    	stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    	scene=new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    public void switchToShell(MouseEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("../View/shellSort.fxml"));
    	stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    	scene=new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    
    @FXML
    public void switchToHelp(MouseEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("../View/get_help_page.fxml"));
    	stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    	scene=new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

}