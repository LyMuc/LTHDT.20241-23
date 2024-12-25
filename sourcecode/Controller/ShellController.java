package Controller;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
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

import Model.*;

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
        Parent root = FXMLLoader.load(getClass().getResource("../View/homepage.fxml"));
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
        shellSort.sort();

        // Clear resultArea and display the sorted array
        resultArea.getChildren().clear();
        StringBuilder sortedArrayText = new StringBuilder();
        for (int num : array) {
            sortedArrayText.append(num).append(" ");
        }
        resultArea.getChildren().add(new Text(sortedArrayText.toString()));
    }

    private void performSortingStep() {
        if (shellSort.isSorted()) {
            //timeline.stop();
            displayArray(shellSort.getArray(), -1, -1, -1);
            isSorting = false;
        } else {
            StateSorting stateSorting = shellSort.getStateSorting();
            displayArray(shellSort.getArray(), stateSorting.getiArg1(), -1, -1);

            PauseTransition pauseRed = new PauseTransition(Duration.seconds(1));
            pauseRed.setOnFinished(event -> {
                StateSwap stateSwap = shellSort.getSwapSorting();
                displayArray(shellSort.getArray(), -1, stateSwap.getiArg1(), stateSwap.getiArg2());

                PauseTransition pauseBlue = new PauseTransition(Duration.seconds(1));
                pauseBlue.setOnFinished(event2 -> performSortingStep());
                pauseBlue.play();
            });
            pauseRed.play();
        }
    }

    @FXML
    void sortWithColor() {
        if (isSorting) {
            return;
        }

        // Lấy dữ liệu từ inputArea
        String inputText = arrayInput.getText().trim();
        if (inputText.isEmpty()) {

            return;
        }

        try {
            String[] parts = inputText.split("\\s+");
            int[] array = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                array[i] = Integer.parseInt(parts[i]);
            }

            shellSort = new ShellSort(array, array.length);
            isSorting = true;

            // Hiển thị mảng ban đầu
            displayArray(array, -1, -1, -1);

            // Cấu hình animation để tự động chạy sorting
            performSortingStep();
        } catch (NumberFormatException e) {

        }
    }

    private void displayArray(int[] array, int current, int swapping1, int swapping2) {
        resultArea.getChildren().clear();
        resultArea.setTextAlignment(TextAlignment.CENTER);

        // Tô màu đỏ cho phần tử current
        for (int i = 0; i < array.length; i++) {
            Text text = new Text(array[i] + " ");
            text.setStyle("-fx-font-size: 36px;");

            if (i == current) {
                text.setStyle("-fx-fill: red; -fx-font-size: 36px;");
            }
            else if(i == swapping1 || i == swapping2){
            	text.setStyle("-fx-fill: blue; -fx-font-size: 36px;");
            }
            else {
                text.setStyle("-fx-fill: black; -fx-font-size: 36px;");
            }

            resultArea.getChildren().add(text);
        }

      // Kích hoạt độ trễ để chờ 1 giây trước khi chuyển sang tô màu xanh
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}