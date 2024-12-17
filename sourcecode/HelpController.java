import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.event.ActionEvent;
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
        centerContent.getChildren().addAll(
            new Text("Insertion Sort:\n"),
            new Text(" - Step-by-step process:\n"),
            new Text("   1. Start with the second element and compare it backward.\n"),
            new Text("   2. Place the element in its correct position.\n"),
            new Text("   3. Repeat for all remaining elements.\n")
        );
    }


    @FXML
    void mergeTagClicked(MouseEvent event) {
    	centerContent.getChildren().clear();
        centerContent.getChildren().addAll(
            new Text("Merge Sort:\n"),
            new Text(" - Step-by-step process:\n"),
            new Text("   1. Start with the second element and compare it backward.\n"),
            new Text("   2. Place the element in its correct position.\n"),
            new Text("   3. Repeat for all remaining elements.\n")
        );
    }

    @FXML
    void shellTagClicked(MouseEvent event) {
    	centerContent.getChildren().clear();
        centerContent.getChildren().addAll(
            new Text("Shell Sort:\n"),
            new Text(" - Step-by-step process:\n"),
            new Text("   1. Start with the second element and compare it backward.\n"),
            new Text("   2. Place the element in its correct position.\n"),
            new Text("   3. Repeat for all remaining elements.\n")
        );
    }

}
