package Dictionary.MainApp;

import static Dictionary.MainApp.LoadingApp.dictionaryScene;
import static Dictionary.MainApp.LoadingApp.translateScene;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class Menu implements Initializable {
  @FXML
  protected Button searchIcon = new Button();
  @FXML
  protected Button imgBookmark = new Button();
  @FXML
  protected Button imgHistory = new Button();
  @FXML
  protected Button imgNew = new Button();
  @FXML
  protected Button imgTranslate = new Button();
  @FXML
  protected Button imgGame = new Button();
  @FXML
  protected ImageView imgToggle = new ImageView();
  protected TranslateTransition transition = new TranslateTransition(Duration.millis(130));

  String getFile(String path) {
    return new File(path).toURI().toString();
  }
  void setStyle(Node x, String style) {
    x.getStyleClass().add(style);
  }

  protected ImageView getImage(String img, int h, int w) {
    ImageView ret = new ImageView(new Image(getFile(
            "src/main/resources/Images/" + img + ".png")));
    ret.setFitHeight(h);
    ret.setFitWidth(w);
    return ret;
  }

  public void setTooltip(Node tmp, String msg) {
    Tooltip k = new Tooltip(msg); k.setShowDelay(Duration.millis(300));
    Tooltip.install(tmp, k);
  }

  public void initialize(URL url, ResourceBundle rb) {
    searchIcon.setGraphic(getImage("search", 40, 40));
    imgBookmark.setGraphic(getImage("star", 40, 40));
    imgHistory.setGraphic(getImage("History", 40, 40));
    imgNew.setGraphic(getImage("addWord", 40, 40));
    imgTranslate.setGraphic(getImage("translate", 40, 40));
    imgGame.setGraphic(getImage("anagramGameIcon", 40, 40));
    setStyle(searchIcon, "toHandCursor"); setStyle(imgBookmark, "toHandCursor");
    setStyle(imgHistory, "toHandCursor"); setStyle(imgNew, "toHandCursor");
    setStyle(imgTranslate, "toHandCursor"); setStyle(imgGame, "toHandCursor");
    transition.setNode(imgToggle);
    setTooltip(searchIcon, "Tra từ"); setTooltip(imgBookmark, "Library");
    setTooltip(imgHistory, "Lịch sử"); setTooltip(imgNew, "Thêm từ");
    setTooltip(imgTranslate, "Dịch"); setTooltip(imgGame, "Game");
  }

  void toggleMenu(Node img) {
    transition.setToY(img.getLayoutY() - 8 - imgToggle.getLayoutY());
    transition.play();
  }

  void mainMenu() {
    Stage stage = (Stage) searchIcon.getScene().getWindow();
    stage.setScene(dictionaryScene);
  }

  void translateMenu() {
    Stage stage = (Stage) searchIcon.getScene().getWindow();
    stage.setScene(translateScene);
  }

  public void switchToSearch() {
    toggleMenu(searchIcon);
  }
  public void switchToBookmark() {
    toggleMenu(imgBookmark);
  }
  public void switchToHistory() {
    toggleMenu(imgHistory);
  }
  public void switchToTranslate() { toggleMenu(imgTranslate); }


  abstract void menuSearch(ActionEvent e) throws IOException;
  abstract void menuBookmark(ActionEvent e) throws IOException;
  abstract void menuHistory(ActionEvent e) throws IOException;
  abstract void menuTranslate(ActionEvent e) throws IOException;
  abstract void menuGame(ActionEvent event) throws IOException;
}
