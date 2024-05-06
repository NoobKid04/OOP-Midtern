package AnnagramGame;

import static Dictionary.App.DictionaryApp.dictionaryScene;
import static Dictionary.App.DictionaryApp.anagramModeScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class gameMenuController {
    @FXML
    private ImageView exitGameButton = new ImageView();
    @FXML
    private ImageView newGameButton = new ImageView();

    @FXML
    void exitToMenu(ActionEvent event) {
        Stage stage = (Stage) exitGameButton.getScene().getWindow();
        stage.setScene(dictionaryScene);
    }

    @FXML
    void newGame(ActionEvent event) {
        Stage stage = (Stage) newGameButton.getScene().getWindow();
        stage.setScene(anagramModeScene);
    }
}
