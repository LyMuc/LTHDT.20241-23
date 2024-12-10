import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.event.ActionEvent;

public class PainterController {
	@FXML
    private BorderPane drawingAreaPane;

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
    	Circle newCircle = new Circle((double)event.getX(), (double)event.getY(), 4.0, Color.BLACK);
    	drawingAreaPane.getChildren().add(newCircle);
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
    	drawingAreaPane.getChildren().clear();
    }

}
