package Dictionary.MainApp;

import Dictionary.Utilities.*;
import Game.Anagram.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class LoadingApp extends Application {
  public static FXMLLoader fxmlDictionary, fxmlTranslate, fxmlGameSelection, fxmlAddWord, fxmlInfo, fxmlDelete;
  public static FXMLLoader fxmlAnagramGame, fxmlAnagramScore, fxmlAnagramMain, fxmlAnagramMode;
  public static Scene dictionaryScene, translateScene, addWordScene, infoScene, deleteScene;
  public static Scene anagramGameScene, anagramScoreScene, anagramMainMenuScene, anagramModeScene;
  public static MainController dictionaryControl;
  public static TranslateControl translateControl;
  public static anagramGameController anagramGameControl;
  public static anagramScoreController anagramScoreControl;
  public static anagramMainMenuController anagramMainMenuControl;
  public static anagramModeController anagramModeController;
  public static addWordController addWordControl;
  public static infoController infoControl;
  public static deleteWordController deleteControl;
  Parent dictRoot, transRoot, addWordRoot, infoRoot, deleteRoot;
  Parent anagramGameRoot, anagramScoreRoot, anagramMainMenuRoot, anagramModeRoot;

  @Override
  public void start(Stage stage) throws IOException {
    loadFXMLs();
    loadFxmlRoot();
    setControllers();
    setScenes();
    configureStage(stage);
    stage.setScene(anagramMainMenuScene); stage.show();
    stage.setScene(anagramModeScene);   stage.show();
    stage.setScene(anagramGameScene);   stage.show();
    stage.setScene(anagramScoreScene);  stage.show();
    stage.setScene(translateScene);     stage.show();
    stage.setScene(dictionaryScene);    stage.show();
  }

  private void loadFXMLs() {
    fxmlDictionary = new FXMLLoader(LoadingApp.class.getResource("/Dictionary/mainMenu.fxml"));
    fxmlTranslate = new FXMLLoader(LoadingApp.class.getResource("/Dictionary/translateMenu.fxml"));
    fxmlAnagramGame = new FXMLLoader(LoadingApp.class.getResource("/Game/Anagram/game.fxml"));
    fxmlAnagramScore = new FXMLLoader(LoadingApp.class.getResource("/Game/Anagram/finalscore.fxml"));
    fxmlAnagramMain = new FXMLLoader(LoadingApp.class.getResource("/Game/Anagram/mainmenu.fxml"));
    fxmlAnagramMode = new FXMLLoader(LoadingApp.class.getResource("/Game/Anagram/selectMode.fxml"));
    fxmlGameSelection = new FXMLLoader(LoadingApp.class.getResource("/Dictionary/gameMenu.fxml"));
    fxmlAddWord = new FXMLLoader(LoadingApp.class.getResource("/Utilities/addWord.fxml"));
    fxmlInfo = new FXMLLoader(LoadingApp.class.getResource("/Utilities/info.fxml"));
    fxmlDelete = new FXMLLoader(LoadingApp.class.getResource("/Utilities/deleteWarning.fxml"));
  }
  private void loadFxmlRoot() throws IOException {
    dictRoot = fxmlDictionary.load();
    transRoot = fxmlTranslate.load();
    anagramGameRoot = fxmlAnagramGame.load();
    anagramScoreRoot = fxmlAnagramScore.load();
    anagramMainMenuRoot = fxmlAnagramMain.load();
    anagramModeRoot = fxmlAnagramMode.load();
    addWordRoot = fxmlAddWord.load();
    infoRoot = fxmlInfo.load();
    deleteRoot = fxmlDelete.load();
  }
  private void setControllers() {
    dictionaryControl = fxmlDictionary.getController();
    translateControl = fxmlTranslate.getController();
    addWordControl = fxmlAddWord.getController();
    infoControl = fxmlInfo.getController();
    anagramGameControl = fxmlAnagramGame.getController();
    anagramScoreControl = fxmlAnagramScore.getController();
    anagramMainMenuControl = fxmlAnagramMain.getController();
    anagramModeController = fxmlAnagramMode.getController();
    deleteControl = fxmlDelete.getController();
  }

  private void setScenes() {
    dictionaryScene = new Scene(dictRoot, 900, 600);
    translateScene = new Scene(transRoot, 900, 600);
    anagramGameScene = new Scene(anagramGameRoot, 900, 600);
    anagramScoreScene = new Scene(anagramScoreRoot, 900, 600);
    anagramMainMenuScene = new Scene(anagramMainMenuRoot, 900, 600);
    anagramModeScene = new Scene(anagramModeRoot, 900, 600);
    addWordScene = new Scene(addWordRoot);
    infoScene = new Scene(infoRoot);
    deleteScene = new Scene(deleteRoot);
    dictionaryScene.getStylesheets().add(getFile("src/main/resources/style.css"));
    translateScene.getStylesheets().add(getFile("src/main/resources/style.css"));
  }

  private void configureStage(Stage stage) {
    stage.setResizable(false);
    stage.setTitle("Translate");
    stage.getIcons().add(new Image(getFile("src/main/resources/Images/dictionaryIcon.png")));
  }

  public static void main(String[] args) {
    launch();
  }
  String getFile(String path) {
    return new File(path).toURI().toString();
  }
}
