import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DictionaryApp extends Application {
    private Map<String, String> dictionary = new HashMap<>();
    private final String FILENAME = "dictionary.txt";
    private TextField wordField;
    private TextField meaningField;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        loadDataFromFile();

        primaryStage.setTitle("Dictionary App");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label wordLabel = new Label("Từ:");
        GridPane.setConstraints(wordLabel, 0, 0);
        wordField = new TextField();
        GridPane.setConstraints(wordField, 1, 0);

        Label meaningLabel = new Label("Nghĩa:");
        GridPane.setConstraints(meaningLabel, 0, 1);
        meaningField = new TextField();
        GridPane.setConstraints(meaningField, 1, 1);

        Button addButton = new Button("Thêm/Sửa");
        GridPane.setConstraints(addButton, 0, 2);
        addButton.setOnAction(e -> addOrUpdateWord());

        Button searchButton = new Button("Tra cứu");
        GridPane.setConstraints(searchButton, 1, 2);
        searchButton.setOnAction(e -> searchWord());

        resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 0, 3);

        grid.getChildren().addAll(wordLabel, wordField, meaningLabel, meaningField, addButton, searchButton, resultLabel);

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> saveDataToFile());
    }

    private void searchWord() {
        String word = wordField.getText().trim();
        if (dictionary.containsKey(word)) {
            resultLabel.setText("Dịch nghĩa: " + dictionary.get(word));
        } else {
            resultLabel.setText("Từ không tồn tại trong từ điển.");
        }
    }

    private void addOrUpdateWord() {
        String word = wordField.getText().trim();
        String meaning = meaningField.getText().trim();
        dictionary.put(word, meaning);
        resultLabel.setText("Đã thêm/sửa từ vào từ điển.");
    }

    private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                dictionary.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.err.println("Không thể đọc dữ liệu từ tệp.");
        }
    }

    private void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Không thể lưu dữ liệu vào tệp.");
        }
    }
}
