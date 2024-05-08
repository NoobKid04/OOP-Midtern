package Dictionary.Utilities;

import Component.History;
import Component.Standalization;
import Component.WordStorage.DictionaryMap;
import Component.WordStorage.Trie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static Dictionary.MainApp.LoadingApp.infoControl;


public class addWordController extends ChoiceBox {

  @FXML
  private TextField tfWord;
  @FXML
  TextField tfPhonetic;
  @FXML
  TextField tfMeaning;

  public void setWord(String WORD) {
    tfWord.setText(WORD);
  }

  @FXML
  public void clickAdd(ActionEvent e) {
    String word = Standalization.formatString(tfWord.getText());
    String phonetic = Standalization.formatString(tfPhonetic.getText());
    String meaning = Standalization.formatString(tfMeaning.getText());
    if (word.isBlank() || phonetic.isBlank() || meaning.isBlank()) {
      return;
    }
    tfWord.clear(); tfPhonetic.clear(); tfMeaning.clear();
    Trie.add(word, phonetic, meaning, word);
    DictionaryMap.add(word, meaning);
    History.add(word);
    Stage stage = (Stage) tfWord.getScene().getWindow();
    infoControl.setPrompt("Đã thêm " + word + " vào từ điển.");
    stage.close();
  }
}