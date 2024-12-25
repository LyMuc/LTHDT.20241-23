package Controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.io.IOException;

import Model.*;

public class ShellController extends ControllerBase {

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

    private ShellSort shellSort;
    private boolean isSorting = false;

    @FXML
    void showSortOptions() {
        startSortingBtn.setVisible(false);
        sortOptionsBox.setVisible(true);
    }

    @FXML
    void BackToMenu(ActionEvent event) {
        try {
            navigateTo("../View/homepage.fxml", (Node) event.getSource());
        } catch (IOException e) {
            showAlert("Navigation Error", "Unable to navigate to the homepage.");
        }
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
            resultArea.getChildren().clear();
            shellSort = new ShellSort(array, size);

        } catch (Exception e) {
            showAlert("Error", "Error generating random array.");
        }
    }

    @FXML
    void clearArray() {
        arrayInput.clear();
        resultArea.getChildren().clear();
    }

    @FXML
    void sortWithColor() {
        if (isSorting) {
            return;
        }

        String inputText = arrayInput.getText().trim();
        if (inputText.isEmpty()) {
            showAlert("Error", "No array input for sorting.");
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

            // Chạy animation sorting
            performSortingStep();
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input. Please enter a valid array.");
        }
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

        int[] array = new int[size];
        try {
            for (int i = 0; i < size; i++) {
                array[i] = Integer.parseInt(inputArray[i]);
            }

            ShellSort shellSort = new ShellSort(array, size);
            shellSort.sort();

            resultArea.getChildren().clear();
            StringBuilder sortedArrayText = new StringBuilder();
            for (int num : array) {
                sortedArrayText.append(num).append(" ");
            }
            resultArea.getChildren().add(new Text(sortedArrayText.toString()));

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input array format.");
        }
    }

    private void performSortingStep() {
        if (shellSort.isSorted()) {
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

    private void displayArray(int[] array, int current, int swapping1, int swapping2) {
        resultArea.getChildren().clear();
        resultArea.setTextAlignment(TextAlignment.CENTER);

        for (int i = 0; i < array.length; i++) {
            Text text = new Text(array[i] + " ");
            text.setStyle("-fx-font-size: 36px;");

            if (i == current) {
                text.setStyle("-fx-fill: red; -fx-font-size: 36px;");
            } else if (i == swapping1 || i == swapping2) {
                text.setStyle("-fx-fill: blue; -fx-font-size: 36px;");
            } else {
                text.setStyle("-fx-fill: black; -fx-font-size: 36px;");
            }

            resultArea.getChildren().add(text);
        }
    }
}
