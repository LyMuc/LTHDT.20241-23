import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ShellController {

    @FXML
    private Button BackButton;

    @FXML
    private TextField arrayInput;

    @FXML
    private TextFlow resultArea;

    @FXML
    private Spinner<Integer> arraySizeSpinner;

    @FXML
    private Button startSortingBtn;

    @FXML
    private HBox sortOptionsBox;

    @FXML
    private ToggleGroup sortingAlgorithm;
    private Timeline timeline;

    // Declare shellSort at class level
    private ShellSort shellSort;
    private boolean isSorting = false;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void BackToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 5);
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
            int[] array = new int[size];
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < size; i++) {
                array[i] = (int) (Math.random() * 100);
                result.append(array[i]);
                if (i < size - 1) {
                    result.append(" ");
                }
            }

            arrayInput.setText(result.toString());

            // Clear the result area and display the generated array
            resultArea.getChildren().clear();

            // Initialize shellSort object
            shellSort = new ShellSort(array, size);

        } catch (Exception e) {
            showAlert("Error generating random array");
        }
    }

    @FXML
    void clearArray() {
        arrayInput.clear();
        resultArea.getChildren().clear();
    }

    @FXML
    void showSortOptions() {
        startSortingBtn.setVisible(false);
        sortOptionsBox.setVisible(true);
    }

    @FXML
    void sortNow() {
        // Parse the input array from the TextField
        String[] inputArray = arrayInput.getText().split("\\s+");
        int size = inputArray.length;
        if (size == 0) {
            resultArea.getChildren().clear();
            resultArea.getChildren().add(new Text("No array generated for sorting."));
            return;
        }

        // Convert the input to an integer array
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(inputArray[i]);
        }

        // Create shellSort instance
        ShellSort shellSort = new ShellSort(array, size);

        // Perform sorting
        while (!shellSort.bSortDone) {
            shellSort.getStateSorting();
        }

        // Clear resultArea and display the sorted array
        resultArea.getChildren().clear();
        StringBuilder sortedArrayText = new StringBuilder();
        for (int num : array) {
            sortedArrayText.append(num).append(" ");
        }
        resultArea.getChildren().add(new Text(sortedArrayText.toString()));
    }

    @FXML
    void sortWithColor() {
        if (isSorting) {
            return; // If already sorting, don't start another sorting process
        }

        // Reset the bSortDone flag before starting new sort
        Sort.bSortDone = false;

        // Get data from input area
        String inputText = arrayInput.getText().trim();
        if (inputText.isEmpty()) {
            showAlert("No array input provided.");
            return;
        }

        try {
            // Convert input to integer array
            String[] parts = inputText.split("\\s+");
            int[] array = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                array[i] = Integer.parseInt(parts[i]);
            }

            // Initialize shellSort instance
            shellSort = new ShellSort(array, array.length);
            isSorting = true;

            // Display the initial array
            displayArray(array, -1, -1);

            // Reset the timeline and start from the beginning
            if (timeline != null) {
                timeline.stop();
            }

            timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> performSortingStep()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

        } catch (NumberFormatException e) {
            showAlert("Invalid input. Please enter a valid array of integers.");
        }
    }

    private void performSortingStep() {
        if (shellSort.bSortDone) {
            timeline.stop();
            displayArray(shellSort.iArray, -1, -1);
            isSorting = false;
        } else {
            StateSorting stateSorting = shellSort.getStateSorting();
            StateSwap stateSwap = shellSort.getSwapSorting();
            displayArray(shellSort.iArray, stateSorting.getiArg1(), stateSwap.getiArg1());
        }
    }

    private void displayArray(int[] array, int current, int swapping) {
        resultArea.getChildren().clear();
        resultArea.setTextAlignment(TextAlignment.CENTER);

        for (int i = 0; i < array.length; i++) {
            Text text = new Text(array[i] + " ");

            // Adjust font-size and color
            text.setStyle("-fx-font-size: 36px;");

            if (i == current) {
                text.setStyle("-fx-fill: red; -fx-font-size: 36px;");
            } else if (i == swapping) {
                text.setStyle("-fx-fill: blue; -fx-font-size: 36px;");
            } else {
                text.setStyle("-fx-fill: black; -fx-font-size: 36px;");
            }

            resultArea.getChildren().add(text);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
