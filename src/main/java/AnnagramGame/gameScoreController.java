package AnnagramGame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static Dictionary.App.DictionaryApp.*;

public class gameScoreController {
    @FXML
    private Label lblScore = new Label();
    public void initialize(URL url, ResourceBundle rb) {
        setFinalScore("0");
    }

    public void setFinalScore(String x) {
        lblScore.setText(x);
    }

    @FXML
    void retry(MouseEvent event) {
        Stage stage = (Stage) lblScore.getScene().getWindow();
        stage.setScene(anagramGameScene);
        inGameControl.startGame(
                inGameControl.getTimeLimit(), inGameControl.getMinLength(), inGameControl.getMaxLength());
    }

    @FXML
    void toMenu(MouseEvent event) {
        Stage stage = (Stage) lblScore.getScene().getWindow();
        stage.setScene(anagramMainScene);
    }
}
