package Dictionary.MainApp;

import Component.Library;
import Component.History;
import Component.InputOutput.AddFromFile;
import Component.InputOutput.ExportToFile;
import Component.Standalization;
import Component.TextToSpeech;
import Component.WordStorage.DictionaryMap;
import Component.WordStorage.Trie;
import Component.WordStorage.TrieNode;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import static Dictionary.MainApp.LoadingApp.*;

public class MainController extends Menu implements Initializable {
  @FXML
  private TextField searchBar = new TextField();
  @FXML
  private TextField tfPhonetic = new TextField();
  @FXML
  private TextArea txtEditor = new TextArea();
  @FXML
  private Label lblWord = new Label();
  @FXML
  private Label spelling = new Label();
  @FXML
  private TextFlow meaning = new TextFlow();
  @FXML
  private ListView<String> suggestWord = new ListView<>();
  @FXML
  private ImageView bookmarkStar = new ImageView();
  @FXML
  private ImageView recycleBin = new ImageView();
  @FXML
  private ImageView imgSpeaker = new ImageView();
  @FXML
  private ImageView imgEditor = new ImageView();
  @FXML
  private ImageView imgTick = new ImageView();
  @FXML
  private ImageView miniGlass = new ImageView();
  @FXML
  private ImageView imgCross = new ImageView();
  @FXML
  private ImageView imgAdd = new ImageView();
  @FXML
  private ScrollPane scrollMeaning = new ScrollPane();
  @FXML
  private Button importBtn = new Button();
  @FXML
  private Button exportBtn = new Button();
  private String currentMenu = "Search";
  private String currentWord;
  private boolean noSound = true;
  String Audio = "";

  void setEditor(boolean type) {
    imgTick.setVisible(type);   imgCross.setVisible(type);
    txtEditor.setVisible(type); tfPhonetic.setVisible(type);

  }
  void setSound() {
    imgSpeaker.setDisable(noSound);
    if (noSound) {
      imgSpeaker.setOpacity(0.3);
    } else {
      imgSpeaker.setOpacity(1);
    }
  }
  String getImgPath(String name) {
    return new File("src/main/resources/Images/" + name + ".png").toURI().toString();
  }
  public void getSuggestion(String[] suggestion) {
    searchBar.setText("");
    suggestWord.getItems().clear();
    suggestWord.getItems().addAll(suggestion);
  }
  public void cellFormat() {
    suggestWord.getStyleClass().add("list-cell");
    suggestWord.setCellFactory(new Callback<>() {
      @Override
      public ListCell<String> call(ListView<String> param) {
        return new ListCell<>() {
          @Override
          protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
              setText("   " + item);
            } else {
              setText(null);
            }
          }
        };
      }
    });
  }

  public void buttonFormat() {
    setStyle(bookmarkStar, "imageViewStyle"); setStyle(recycleBin, "imageViewStyle");
    setStyle(imgEditor, "imageViewStyle"); setStyle(imgAdd, "imageViewStyle");
    setStyle(imgCross, "imageViewStyle"); setStyle(imgTick, "imageViewStyle");
    setStyle(imgSpeaker, "imageViewStyle"); imgAdd.setVisible(false);
    searchIcon.toFront(); imgBookmark.toFront(); imgHistory.toFront(); imgNew.toFront();
    setEditor(false); new TranslateTransition(Duration.millis(130)).setNode(imgToggle);
    importBtn.setGraphic(getImage("import", 23,23));
    exportBtn.setGraphic(getImage("export", 23,23));
  }

  void setMeaningOfWord(String mean) {
    String[] data = mean.split("\n");
    meaning.getChildren().clear();
    for (String tmp : data) {
      if (tmp.isEmpty()) {
        continue;
      }
      boolean example = false;
      Text text = new Text(tmp + "\n");
      text.setFont(Font.font("Times New Roman", 17));
      if (tmp.contains("=")) {
        Text temp = new Text("");
        meaning.getChildren().add(temp);
        example = true;
        text.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 17));
      }
      if (tmp.charAt(0) == '*') {
        text = new Text("\n" + tmp + "\n");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 17));
        text.setFill(Color.LIGHTBLUE);
      }
      meaning.getChildren().add(text);
      if (example) {
        Text temp = new Text("\n");
        meaning.getChildren().add(temp);
      }
    }
    meaning.setPrefWidth(scrollMeaning.getPrefWidth());
    scrollMeaning.setContent(meaning);
  }
  String getMeaning() {
    StringBuilder mean = new StringBuilder();
    for (Node node : meaning.getChildren()) {
      if (node instanceof Text) {
        String add = ((Text) node).getText();
        if (add.isBlank()) {
          continue;
        }
        mean.append(add);
      }
    }
    mean.deleteCharAt(0);
    return mean.toString();
  }

  void setContent(TrieNode node) {
    Audio = node.getAudio(); noSound = Audio.isBlank(); setSound();
    searchBar.setText(currentWord);
    lblWord.setText(currentWord);
    spelling.setText(node.getSpelling());
    setMeaningOfWord(node.getMeaning());
    imgAdd.setVisible(false);
    if (!node.getBookmarked()) {
      bookmarkStar.setImage(new Image(getImgPath("starUntoggle")));
    } else {
      bookmarkStar.setImage(new Image(getImgPath("star")));
    }
  }

  public void addWord() {
    Stage stage = new Stage();
    stage.setScene(addWordScene);
    addWordControl.setWord(searchBar.getText());
    stage.initModality(Modality.APPLICATION_MODAL);
    stage
        .getIcons()
        .add(
            new Image(
                new File("src/main/resources/Images/plusIcon.png").toURI().toString()));
    stage.setResizable(false);
    stage.setTitle("Thêm từ");
    stage.showAndWait();
  }

  public void tFieldFormat() {
    searchBar.getStyleClass().add("txtField");
  }
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    super.initialize(url, rb);
    cellFormat(); buttonFormat(); setSound();
    AddFromFile.add(null); getSuggestion(DictionaryMap.getKeys());
    menuInit(false); tFieldFormat();
    suggestWord
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (observableValue, s, t1) -> {
              menuInit(true);
              currentWord = suggestWord.getSelectionModel().getSelectedItem();
              if (currentWord == null || currentWord.isEmpty()) {
                return;
              }
              if (currentWord.equals("Thêm...")) {
                addWord();
                return;
              }
              setContent(Trie.find(currentWord));
              if (!currentMenu.equals("History")) {
                History.add(currentWord);
              }
              setTooltip(imgAdd, "Thêm vào từ điển");
              setTooltip(imgTick, "Lưu"); setTooltip(imgCross, "Hủy");
            });
  }

  @FXML
  public void findPrefix(KeyEvent e) {
    String prefix = searchBar.getText();
      suggestWord.getItems().clear();
      if (prefix == null || prefix.isEmpty()) {
        getSuggestion(DictionaryMap.getKeys());
      } else if (!prefix.isBlank()) {
        List<String> suggestion = Trie.getPrefix(Standalization.formatString(prefix));
        suggestion.add(0, "Thêm...");
        suggestWord.getItems().addAll(suggestion.toArray(new String[(int)suggestion.size()]));
      }
  }

  void menuInit(boolean active) {
    suggestWord.setVisible(true); bookmarkStar.setVisible(active);
    if (active && Library.find(lblWord.getText())) {
      bookmarkStar.setImage(new Image(getImgPath("star")));
    } else {
      bookmarkStar.setImage(new Image(getImgPath("starUntoggle")));
    }
    recycleBin.setVisible(active); imgEditor.setVisible(active);
  }

  public void Switch(boolean mInit, Node node, boolean searchB, String[] lst, String cMenu) {
    menuInit(mInit);
    toggleMenu(node);
    searchBar.setVisible(searchB); miniGlass.setVisible(searchB);
    if (lst != null) getSuggestion(lst);
    currentMenu = cMenu;
  }

  @Override
  public void switchToSearch() {
    super.switchToSearch();
    Switch(true, searchIcon, true, DictionaryMap.getKeys(), "Search");
  }

  public void menuSearch(ActionEvent e) {
    switchToSearch();
    translateControl.switchToSearch();
  }

  @Override
  public void switchToBookmark() {
    super.switchToBookmark();
    Switch(true, imgBookmark, true, Library.getList(), "Library");
  }

  public void menuBookmark(ActionEvent e) {
    switchToBookmark();
    translateControl.switchToBookmark();
  }

  @Override
  public void switchToHistory() {
    super.switchToHistory();
    Switch(true, imgHistory, false, History.getList(), "History");
  }

  public void menuHistory(ActionEvent e) {
    switchToHistory();
    translateControl.switchToHistory();
  }

  public void menuAdd(ActionEvent e) {
    switchToSearch();
    translateControl.switchToSearch();
    addWord();
  }


  public void changeBookmarkState(MouseEvent e) {
    String word = lblWord.getText();
    if (word.equals("LingoBench") || word.isBlank()) {
      return;
    }
    TrieNode node = Trie.find(word);
    if (!node.getBookmarked()) {
      if (!DictionaryMap.exist(word)) {
        Trie.add(word, spelling.getText(), getMeaning(), Audio);
        DictionaryMap.add(word, getMeaning());
        History.add(word);
      }
      bookmarkStar.setImage(new Image(getImgPath("star")));
      node.setBookmarked(true);
      Library.add(word);
    } else {
      bookmarkStar.setImage(new Image(getImgPath("starUntoggle")));
      node.setBookmarked(false);
      Library.remove(word);
    }
    if (currentMenu.equals("Library")) {
      getSuggestion(Library.getList());
    }
  }

  public void deleteWord(MouseEvent e) {
    String word = lblWord.getText();
    if (word.equals("LingoBench")) {
      return;
    }
    deleteControl.setPrompt("Bạn có chắc muốn xóa " + lblWord.getText() + " khỏi từ điển?");
    Stage stage = new Stage();
    stage.setScene(deleteScene);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage
        .getIcons()
        .add(
            new Image(
                new File("src/main/resources/Images/minusIcon.png").toURI().toString()));
    stage.setResizable(false);
    stage.setTitle("Xóa từ");
    infoControl.setPrompt("Đã xóa " + word + " khỏi từ điển");
    stage.showAndWait();
    if (!deleteControl.isDeleted()) {
      return;
    }
    Trie.delete(word);
    DictionaryMap.delete(word); Library.remove(word); History.remove(word);
    switch (currentMenu) {
      case "Search" -> getSuggestion(DictionaryMap.getKeys());
      case "Library" -> getSuggestion(Library.getList());
      case "History" -> getSuggestion(History.getList());
    }
    searchBar.setText(""); lblWord.setText(""); spelling.setText(""); setMeaningOfWord("");
    bookmarkStar.setVisible(false); recycleBin.setVisible(false); imgEditor.setVisible(false);
  }

  public void playMedia(MouseEvent e) {
    TextToSpeech.voice(Audio);
  }

  void iconDisable(boolean b) {
    searchIcon.setDisable(b); imgBookmark.setDisable(b); imgHistory.setDisable(b);
    imgNew.setDisable(b); imgTranslate.setDisable(b); imgGame.setDisable(b);
    bookmarkStar.setDisable(b); recycleBin.setDisable(b);
    meaning.setOpacity(1); spelling.setOpacity(1);
    double opacity = 1;
    if (b) {
      opacity = 0.3; imgSpeaker.setDisable(true); imgSpeaker.setOpacity(opacity);
      meaning.setOpacity(0); spelling.setOpacity(0);
    } else {
      TrieNode node = Trie.find(lblWord.getText());
      Audio = node.getAudio(); noSound = Audio.isBlank(); setSound();
    }
    searchIcon.setOpacity(opacity); imgBookmark.setOpacity(opacity); imgHistory.setOpacity(opacity);
    imgNew.setOpacity(opacity); imgTranslate.setOpacity(opacity); imgGame.setOpacity(opacity);
    bookmarkStar.setOpacity(opacity); recycleBin.setOpacity(opacity);
  }

  public void openEditor(MouseEvent e) {
    if (lblWord.getText().equals("LingoBench")) {
      return;
    }
    setEditor(true);
    txtEditor.setText(getMeaning());
    tfPhonetic.setText(spelling.getText());
    suggestWord.setDisable(true);
    iconDisable(true);
    switch (currentMenu) {
      case "Search" -> searchIcon.setOpacity(1);
      case "Library" -> imgBookmark.setOpacity(1);
      case "History" -> imgHistory.setOpacity(1);
    }
  }

  void closeEditor() {
    setEditor(false);
    iconDisable(false);
    suggestWord.setDisable(false);
  }

  public void exitEditor(MouseEvent e) {
    closeEditor();
  }

  public void saveEditor(MouseEvent e) {
    String tmp = txtEditor.getText();
    if (tmp.isEmpty() || tmp.charAt(tmp.length() - 1) != '\n') {
      tmp += '\n';
    }
    setMeaningOfWord(tmp);
    String newMeaning = tmp;
    String newSpelling = tfPhonetic.getText();
    spelling.setText(newSpelling);
    Trie.add(lblWord.getText(), newSpelling, newMeaning, "");
    closeEditor();
  }

  public void menuTranslate(ActionEvent e) {
    translateMenu();
    switchToTranslate();
    translateControl.switchToTranslate();
  }

  public void menuGame(ActionEvent e) {
    Stage stage = (Stage) imgGame.getScene().getWindow();
    stage.setScene(anagramMainMenuScene);
  }

  public void importFile(ActionEvent e) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select file to import from...");
    File initLocation = new File("src/main/resources");
    fileChooser.setInitialDirectory(initLocation);
    Stage stage = (Stage) imgAdd.getScene().getWindow();
    File selected = fileChooser.showOpenDialog(stage);
    if (selected == null) {
      return;
    }
    AddFromFile.add(selected);
    infoControl.setPrompt("Đã nhập file vào dữ liệu từ điển");
    Stage stage1 = new Stage();
    stage1.setScene(infoScene);
    stage1.setTitle("Thông báo");
    stage1
        .getIcons()
        .add(
            new Image(
                new File("src/main/resources/Images/infoIcon.png").toURI().toString()));
    stage1.showAndWait();
  }

  public void exportFile(ActionEvent e) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select file to export to...");
    File initLocation = new File("src/main/resources");
    fileChooser.setInitialDirectory(initLocation);
    Stage stage = (Stage) imgAdd.getScene().getWindow();
    File selected = fileChooser.showOpenDialog(stage);
    if (selected == null) {
      return;
    }
    ExportToFile.export(selected);
    infoControl.setPrompt("Đã xuất dữ liệu từ điển ra file");
    Stage stage1 = new Stage();
    stage1.setScene(infoScene);
    stage1.setTitle("Thông báo");
    stage1
        .getIcons()
        .add(
            new Image(
                new File("src/main/resources/Images/infoIcon.png").toURI().toString()));
    stage1.showAndWait();
  }
}