package Dictionary.MainApp;

import Component.InputOutput.Translator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Dictionary.MainApp.LoadingApp.*;

public class TranslateControl extends Menu implements Initializable {
  @FXML private ImageView swap;

  @FXML private TextArea input = new TextArea("");

  @FXML private TextArea result = new TextArea("");

  @FXML private Label lanEn, lanVi, lanEn1, lanVi1, lanFr, lanFr1, lanIt, lanIt1;

  String lanFrom = "en", lanTo = "vi";

  void setStyle(Node x, String style) {
    x.getStyleClass().add(style);
  }
  void removeStyle(Node x, String style) {
    x.getStyleClass().remove(style);
  }
  Font font =  Font.loadFont(("file:src/main/resources/Font/Roboto-Regular.ttf"),23);
  Font lanFont =  Font.loadFont(("file:src/main/resources/Font/Roboto-Bold.ttf"),15);
  Font chineseFont = Font.loadFont(("file:src/main/resources/Font/NotoSansSC-Regular.ttf"),25);

  public void initialize(URL url, ResourceBundle rb) {
    super.initialize(url, rb);
    input.setFont(font); result.setFont(font);
    lanEn.setFont(lanFont); lanVi.setFont(lanFont); lanEn1.setFont(lanFont); lanVi1.setFont(lanFont);
    lanFr.setFont(lanFont); lanFr1.setFont(lanFont); lanIt.setFont(lanFont); lanIt1.setFont(lanFont);
    setStyle(lanEn, "lan"); setStyle(lanEn1, "lan");
    setStyle(lanVi, "lan"); setStyle(lanVi1, "lan");
    setStyle(lanFr, "lan"); setStyle(lanFr1, "lan");
    setStyle(lanIt, "lan"); setStyle(lanIt1, "lan");
    setStyle(imgNew, "toHandCursor"); setStyle(swap, "toHandCursor");
    setStyle(imgBookmark, "toHandCursor"); setStyle(searchIcon, "toHandCursor");
    setStyle(imgHistory, "toHandCursor"); setStyle(imgTranslate, "toHandCursor");
    setStyle(input, "translateArea"); setStyle(result, "translateArea");
    unfocusAllLan("from"); unfocusAllLan("to");
    focusLan(lanEn); focusLan(lanVi1);
    input.setText(""); result.setText("");
    unfocusAllLan("from"); focusLan(lanEn);
  }

  String getFile(String path) {
    return new File(path).toURI().toString();
  }

  public void addWord() {
    Stage stage = new Stage();
    stage.setScene(addWordScene);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.getIcons().add(new Image(new File("src/main/resources/Images/plusIcon.png").toURI().toString()));
    stage.setResizable(false);
    stage.setTitle("Thêm từ");
    stage.showAndWait();
  }

  @FXML
  void menuBookmark(ActionEvent event) throws IOException {
    mainMenu();
    switchToBookmark();
    dictionaryControl.switchToBookmark();
  }

  @FXML
  void menuHistory(ActionEvent event) throws IOException {
    mainMenu();
    switchToHistory();
    dictionaryControl.switchToHistory();
  }

  @FXML
  void menuSearch(ActionEvent event) throws IOException {
    mainMenu();
    switchToSearch();
    dictionaryControl.switchToSearch();
  }

  @FXML
  void menuTranslate(ActionEvent event) throws IOException {
    switchToTranslate();
    dictionaryControl.switchToTranslate();
  }

  @FXML
  void menuGame(ActionEvent event) throws IOException {
    Stage stage = (Stage) imgGame.getScene().getWindow();
    stage.setScene(anagramMainMenuScene);
  }

  void trans() {
    if (input.getText().isBlank()) {
      result.setText("");
      return;
    }
    String from = input.getText();
    Translator tran = new Translator();
    tran.translate(from, lanFrom, lanTo, (String str) -> {
      result.setText(str);
    });
  }

  void fromEn() {
    lanFrom = "en"; unfocusAllLan("from"); focusLan(lanEn);
    input.setFont(font);
  }

  void fromVi() {
    lanFrom = "vi"; unfocusAllLan("from"); focusLan(lanVi);
    input.setFont(font);
  }

  void fromFr() {
    lanFrom = "fr"; unfocusAllLan("from"); focusLan(lanFr);
    input.setFont(font);
  }

  void fromIt() {
    lanFrom = "zh"; unfocusAllLan("from"); focusLan(lanIt);
    input.setFont(chineseFont);
  }

  void toEn() {
    lanTo = "en"; unfocusAllLan("to"); focusLan(lanEn1);
    result.setFont(font);
  }

  void toVi() {
    lanTo = "vi"; unfocusAllLan("to"); focusLan(lanVi1);
    result.setFont(font);
  }

  void toFr() {
    lanTo = "fr"; unfocusAllLan("to"); focusLan(lanFr1);
    result.setFont(font);
  }

  void toIt() {
    lanTo = "zh"; unfocusAllLan("to"); focusLan(lanIt1);
    result.setFont(chineseFont);
  }

  void swap() {
    String lFrom = new String(lanFrom);
    String lTo = new String(lanTo);
    switch (lFrom) {
      case "vi" -> toVi();
      case "en" -> toEn();
      case "fr" -> toFr();
      case "zh" -> toIt();
    }
    switch (lTo) {
      case "vi" -> fromVi();
      case "en" -> fromEn();
      case "fr" -> fromFr();
      case "zh" -> fromIt();
    }
    String tmp = new String(input.getText());
    input.setText(result.getText());
    result.setText(tmp);
  }

  @FXML
  void fromEng(MouseEvent e) {
    input.setText(""); fromEn(); trans();
  }

  @FXML
  void fromVie(MouseEvent e) {
    input.setText(""); fromVi(); trans();
  }

  @FXML
  void fromFre(MouseEvent e) {
    input.setText(""); fromFr(); trans();
  }

  @FXML
  void fromIta(MouseEvent e) {
    input.setText(""); fromIt(); trans();
  }

  @FXML
  void toEng(MouseEvent e) {
    toEn(); trans();
  }

  @FXML
  void toVie(MouseEvent e) {
    toVi(); trans();
  }

  @FXML
  void toFre(MouseEvent e) {
    toFr(); trans();
  }

  @FXML
  void toIta(MouseEvent e) {
    toIt(); trans();
  }

  @FXML
  void onKeyPress(KeyEvent e) {
    trans();
  }

  @FXML
  void swapLan(MouseEvent e) {
    swap();
  }

  void unfocusAllLan(String type) {
    if (type.equals("from")) {
      removeStyle(lanEn, "focus");
      removeStyle(lanVi, "focus");
      removeStyle(lanFr, "focus");
      removeStyle(lanIt, "focus");
    } else {
      removeStyle(lanEn1, "focus");
      removeStyle(lanVi1, "focus");
      removeStyle(lanFr1, "focus");
      removeStyle(lanIt1, "focus");
    }
  }

  void focusLan(Node node) {
    setStyle(node, "focus");
  }
}
