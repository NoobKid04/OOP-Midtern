package AnnagramGame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static Dictionary.App.DictionaryApp.*;

public class gameModeController {
    @FXML
    private Label difficulty;

    @FXML
    void easy(MouseEvent event) {
        Stage stage = (Stage) difficulty.getScene().getWindow();
        stage.setScene(anagramGameScene);
        inGameControl.startGame(10, 2, 4);
    }

    @FXML
    void medium(MouseEvent event) {
        Stage stage = (Stage) difficulty.getScene().getWindow();
        stage.setScene(anagramGameScene);
        inGameControl.startGame(30, 4, 5);
    }

    @FXML
    void hard(MouseEvent event) {
        Stage stage = (Stage) difficulty.getScene().getWindow();
        stage.setScene(anagramGameScene);
        inGameControl.startGame(60, 5, 7);
    }

    @FXML
    void menu(MouseEvent event) {
        Stage stage = (Stage) difficulty.getScene().getWindow();
        stage.setScene(anagramMainScene);
    }
}
