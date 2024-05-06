package Dictionary.App;

import AnnagramGame.gameMenuController;
import AnnagramGame.gameModeController;
import AnnagramGame.gameScoreController;
import AnnagramGame.inGameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

import java.io.File;

public class DictionaryApp extends Application {
    public static FXMLLoader fxmlAddWord, fxmlAddAPI, fxmInfnfo, fxmlDelete;
    public static FXMLLoader fxmlDictionary, fxmlTranslate;
    public static FXMLLoader fxmlAnagramGame,fxmlAnagramScore,fxmlAnanagramMain, fxmlAnagramMode;
    public static Scene dictionaryScene,translateScene,infoScene;
    public static Scene anagramGameScene,anagramScoreScene,anagramMainScene,anagramModeScene;
    public static Scene addWordScene, addAPIScence, deleteScence;
    public static Main mainControl;
    public static translate translateControl;
    public static inGameController inGameControl;
    public static gameScoreController gameScoreControl;
    public static gameMenuController gameMainMenuControl;
    public static gameModeController gameModeController;

    Parent dictionaryRoot, translateRoot, addWordRoot, addAPIRoot, deleteRoot, infoRoot;
    Parent anagramGameRoot, anagramScoreRoot, anagramMainRoot, anagramModeRoot;

    String getFile(String fileName) {
        return new File(fileName).toURI().toString();
    }

    @Override
    public void start(Stage stage) throws IOException {
        fxmlDictionary = new FXMLLoader(DictionaryApp.class.getResource("/Dictionary/mainMenu.fxml"));
        fxmlTranslate = new FXMLLoader(DictionaryApp.class.getResource("/Dictionary/translate.fxml"));

        dictionaryRoot = fxmlDictionary.load();
        translateRoot = fxmlTranslate.load();

        mainControl = fxmlDictionary.getController();
        translateControl = fxmlTranslate.getController();

        dictionaryScene = new Scene(dictionaryRoot, 900, 600);
        translateScene = new Scene(translateRoot, 900, 600);

        dictionaryScene.getStylesheets().add(getFile("src/main/resources/style.css"));
        translateScene.getStylesheets().add(getFile("src/main/resources/style.css"));

        stage.setResizable(false);
        stage.setTitle("Translator");
        stage.getIcons().add(new Image(getFile("src/main/resources/Images/dictionaryIcon2.png")));
        stage.setScene(dictionaryScene); stage.show();
        stage.setScene(translateScene); stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}