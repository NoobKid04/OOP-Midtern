package Dictionary.Utilities;

import Implement.History;
import Implement.WordStorage.Standardization;
import Implement.WordStorage.DictionaryMap;
import Implement.WordStorage.Trie.Trie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addWordController {
    @FXML
    private TextField word;
    @FXML
    TextField phonetic;
    @FXML
    TextField meaning;

    public void setWord(String WORD) {
        word.setText(WORD);
    }

    @FXML
    public void clkAdd(ActionEvent e) {
        String word = Standardization.normalize(this.word.getText());
        String phonetic = Standardization.normalize(this.phonetic.getText());
        String meaning = Standardization.normalize(this.meaning.getText());
        if (word.isBlank() || phonetic.isBlank() || meaning.isBlank()) {
            return;
        }
        Trie.add(word, phonetic, meaning, "");
        DictionaryMap.add(word, meaning);
        History.add(word);
        Stage stage = (Stage) this.word.getScene().getWindow();
        stage.close();
    }
}
