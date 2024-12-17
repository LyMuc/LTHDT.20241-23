import javafx.scene.input.MouseEvent;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    void exit(ActionEvent event) {
    	stage=(Stage)pane.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    public void switchToInsertion(MouseEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("insertionSort.fxml"));
    	stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    	scene=new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    	
    }

    @FXML
    public void switchToMerge(MouseEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("mergeSort.fxml"));
    	stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    	scene=new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    public void switchToShell(MouseEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("shellSort.fxml"));
    	stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    	scene=new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

}