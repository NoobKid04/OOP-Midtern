package Game.Anagram;

import static Dictionary.MainApp.LoadingApp.anagramModeScene;
import static Dictionary.MainApp.LoadingApp.dictionaryScene;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class anagramMainMenuController {
  @FXML
  private ImageView exitBtn = new ImageView();

  @FXML
  private ImageView newAnagramGameBtn = new ImageView();

  @FXML
  void exitGame(MouseEvent event) {
    Stage stage = (Stage) exitBtn.getScene().getWindow();
    stage.setScene(dictionaryScene);
  }

  @FXML
  void newGame(MouseEvent event) {
    Stage stage = (Stage) exitBtn.getScene().getWindow();
    stage.setScene(anagramModeScene);
  }

}
