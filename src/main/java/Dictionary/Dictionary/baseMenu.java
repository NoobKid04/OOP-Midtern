package Dictionary.Dictionary;

import static Dictionary.Dictionary.dictionaryApp.dictionaryScene;
import static Dictionary.Dictionary.dictionaryApp.gameSelectionScene;
import static Dictionary.Dictionary.dictionaryApp.translateScene;

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

public abstract class  baseMenu implements Initializable {
  @FXML
  protected Button imgSearch = new Button();
  @FXML
  protected Button imgBookmark = new Button();
  @FXML
  protected Button imgHistory = new Button();
  @FXML
  protected Button imgAPI = new Button();
  @FXML
  protected Button imgTranslate = new Button();
  @FXML
  protected Button imgGame = new Button();
  @FXML
  protected ImageView imgToggle = new ImageView();
  TranslateTransition transition = new TranslateTransition(Duration.millis(130));
  void setStyle(Node x, String style) {
    x.getStyleClass().add(style);
  }

  String getFile(String path) {
    return new File(path).toURI().toString();
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
    imgSearch.setGraphic(getImage("search", 40, 40));
    imgBookmark.setGraphic(getImage("bookmark", 40, 40));
    imgHistory.setGraphic(getImage("history", 40, 40));
    imgAPI.setGraphic(getImage("api", 40, 40));
    imgTranslate.setGraphic(getImage("translate", 40, 40));
    imgGame.setGraphic(getImage("game", 40, 40));
    setStyle(imgSearch, "toHandCursor"); setStyle(imgBookmark, "toHandCursor");
    setStyle(imgHistory, "toHandCursor"); setStyle(imgAPI, "toHandCursor");
    setStyle(imgTranslate, "toHandCursor"); setStyle(imgGame, "toHandCursor");
    transition.setNode(imgToggle);
    setTooltip(imgSearch, "Tra từ"); setTooltip(imgBookmark, "Bookmark");
    setTooltip(imgHistory, "Lịch sử"); setTooltip(imgAPI, "API");
    setTooltip(imgTranslate, "Dịch"); setTooltip(imgGame, "Game");
  }

  void toggleMenu(Node img) {
    transition.setToY(img.getLayoutY() - 10 - imgToggle.getLayoutY());
    transition.play();
  }

  public void switchToSearch() {
    toggleMenu(imgSearch);
  }
  public void switchToBookmark() {
    toggleMenu(imgBookmark);
  }
  public void switchToHistory() {
    toggleMenu(imgHistory);
  }
  public void switchToAPI() {
    toggleMenu(imgAPI);
  }
  public void switchToTranslate() { toggleMenu(imgTranslate); }
  public void switchToGameSelection() { toggleMenu(imgGame); }

  void mainMenu() {
    Stage stage = (Stage) imgSearch.getScene().getWindow();
    stage.setScene(dictionaryScene);
  }

  void translateMenu() {
    Stage stage = (Stage) imgSearch.getScene().getWindow();
    stage.setScene(translateScene);
  }

  void gameSelectionMenu() {
    Stage stage = (Stage) imgSearch.getScene().getWindow();
    stage.setScene(gameSelectionScene);
  }

  abstract void menuSearch(ActionEvent e) throws IOException;
  abstract void menuHistory(ActionEvent e) throws IOException;
  abstract void menuBookmark(ActionEvent e) throws IOException;
  abstract void menuAPI(ActionEvent e) throws IOException;
  abstract void menuTranslate(ActionEvent e) throws IOException;
  abstract void menuGame(ActionEvent event) throws IOException;
}
