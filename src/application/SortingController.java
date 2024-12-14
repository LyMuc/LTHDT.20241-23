package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.util.Random;
import java.util.Arrays;

public class SortingController {
    @FXML
    private TextField arrayInput;
    
    @FXML
    private TextArea resultArea;
    
    @FXML
    private Spinner<Integer> arraySizeSpinner;
    
    @FXML
    private Button startSortingBtn;
    
    @FXML
    private HBox sortOptionsBox;
    
    @FXML
    private ToggleGroup sortingAlgorithm;
    
    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = 
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 5);
        arraySizeSpinner.setValueFactory(valueFactory);
        
        arraySizeSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue < 1) {
                arraySizeSpinner.getValueFactory().setValue(1);
            } else if (newValue > 100) {
                arraySizeSpinner.getValueFactory().setValue(100);
            }
        });
        
        sortingAlgorithm = new ToggleGroup();
    }

    @FXML
    void generateRandomArray() {
        try {
            int size = arraySizeSpinner.getValue();
            Random rand = new Random();
            int[] array = new int[size];
            StringBuilder result = new StringBuilder();
            
            for (int i = 0; i < size; i++) {
                array[i] = rand.nextInt(100);
                result.append(array[i]);
                if (i < size - 1) {
                    result.append(" ");
                }
            }
            
            arrayInput.setText(result.toString());
            resultArea.clear();
            resultArea.appendText("Generated array with " + size + " elements:\n");
            resultArea.appendText(Arrays.toString(array));
            
        } catch (Exception e) {
            showAlert("Error generating random array");
        }
    }

    @FXML
    void clearArray() {
        arrayInput.clear();
        resultArea.clear();
    }

    @FXML
    void showSortOptions() {
        startSortingBtn.setVisible(false);
        sortOptionsBox.setVisible(true);
    }
    
    @FXML
    void sortNow() {
        resultArea.setText("Sort Now được click - chờ implement");
        startSortingBtn.setVisible(true);
        sortOptionsBox.setVisible(false);
    }

    @FXML
    void sortWithColor() {
        // anh thành  làm ở đây
        resultArea.setText("Sort with color được click - chờ implement");
        startSortingBtn.setVisible(true);
        sortOptionsBox.setVisible(false);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
} 
//   sort 