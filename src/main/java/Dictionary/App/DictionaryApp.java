package Dictionary.App;

import AnnagramGame.gameMenuController;
import AnnagramGame.gameModeController;
import AnnagramGame.gameScoreController;
import AnnagramGame.inGameController;
import Dictionary.Utilities.addAPIController;
import Dictionary.Utilities.addWordController;
import Dictionary.Utilities.deleteWordController;
import Dictionary.Utilities.infoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

import java.io.File;

public class DictionaryApp extends Application {
    public static FXMLLoader fxmlAddWord, fxmlAddAPI, fxmlInfo, fxmlDelete;
    public static FXMLLoader fxmlDictionary, fxmlTranslate;
    public static FXMLLoader fxmlAnagramGame,fxmlAnagramScore,fxmlAnagramMain, fxmlAnagramMode;
    public static Scene dictionaryScene,translateScene,infoScene;
    public static Scene anagramGameScene,anagramScoreScene,anagramMainScene,anagramModeScene;
    public static Scene addWordScene, addAPIScene, deleteScene;
    public static Main mainControl;
    public static transController translateControl;
    public static inGameController inGameControl;
    public static gameScoreController gameScoreControl;
    public static gameMenuController gameMenuControl;
    public static gameModeController gameModeControll;
    public static addWordController addWordControl;
    public static addAPIController addAPIControl;
    public static infoController infoControl;
    public static deleteWordController deleteControl;

    Parent dictionaryRoot, translateRoot, addWordRoot, addAPIRoot, deleteRoot, infoRoot;
    Parent anagramGameRoot, anagramScoreRoot, anagramMainRoot, anagramModeRoot;

    String getFile(String fileName) {
        return new File(fileName).toURI().toString();
    }

    @Override
    public void start(Stage stage) throws IOException {
        fxmlDictionary = new FXMLLoader(DictionaryApp.class.getResource("/Dictionary/mainMenu.fxml"));
        fxmlTranslate = new FXMLLoader(DictionaryApp.class.getResource("/Dictionary/translate.fxml"));
        fxmlAnagramGame = new FXMLLoader(DictionaryApp.class.getResource("/Game/Anagram/inGame.fxml"));
        fxmlAnagramScore = new FXMLLoader(DictionaryApp.class.getResource("/Game/Anagram/score.fxml"));
        fxmlAnagramMain = new FXMLLoader(DictionaryApp.class.getResource("/Game/Anagram/gameMenu.fxml"));
        fxmlAnagramMode = new FXMLLoader(DictionaryApp.class.getResource("/Game/Anagram/mode.fxml"));
        fxmlAddWord = new FXMLLoader(DictionaryApp.class.getResource("/Utilities/addWordNotice.fxml"));
        fxmlAddAPI = new FXMLLoader(DictionaryApp.class.getResource("/Utilities/addAPINotice.fxml"));
        fxmlInfo = new FXMLLoader(DictionaryApp.class.getResource("/Utilities/infoNotice.fxml"));
        fxmlDelete = new FXMLLoader(DictionaryApp.class.getResource("/Utilities/deleteWarning.fxml"));

        dictionaryRoot = fxmlDictionary.load();
        translateRoot = fxmlTranslate.load();
        anagramGameRoot = fxmlAnagramGame.load();
        anagramScoreRoot = fxmlAnagramScore.load();
        anagramMainRoot = fxmlAnagramMain.load();
        anagramModeRoot = fxmlAnagramMode.load();
        addWordRoot = fxmlAddWord.load();
        addAPIRoot = fxmlAddAPI.load();
        infoRoot = fxmlInfo.load();
        deleteRoot = fxmlDelete.load();

        mainControl = fxmlDictionary.getController();
        translateControl = fxmlTranslate.getController();
        addWordControl = fxmlAddWord.getController();
        addAPIControl = fxmlAddAPI.getController();
        infoControl = fxmlInfo.getController();
        deleteControl = fxmlDelete.getController();
        inGameControl = fxmlAnagramGame.getController();
        gameScoreControl = fxmlAnagramScore.getController();
        gameMenuControl = fxmlAnagramMain.getController();
        gameModeControll = fxmlAnagramMode.getController();

        dictionaryScene = new Scene(dictionaryRoot, 900, 600);
        translateScene = new Scene(translateRoot, 900, 600);
        anagramGameScene = new Scene(anagramGameRoot, 900, 600);
        anagramScoreScene = new Scene(anagramScoreRoot, 900, 600);
        anagramMainScene = new Scene(anagramMainRoot, 900, 600);
        anagramModeScene = new Scene(anagramModeRoot, 900, 600);
        addWordScene = new Scene(addWordRoot);
        addAPIScene = new Scene(addAPIRoot);
        infoScene = new Scene(infoRoot);
        deleteScene = new Scene(deleteRoot);

        dictionaryScene.getStylesheets().add(getFile("src/main/resources/style.css"));
        translateScene.getStylesheets().add(getFile("src/main/resources/style.css"));

        stage.setResizable(false);
        stage.setTitle("Translator");
        stage.getIcons().add(new Image(getFile("src/main/resources/Images/dictionaryIcon2.png")));
        stage.setScene(dictionaryScene); stage.show();
        stage.setScene(translateScene); stage.show();
        stage.setScene(anagramMainScene); stage.show();
        stage.setScene(anagramModeScene); stage.show();
        stage.setScene(anagramGameScene); stage.show();
        stage.setScene(anagramScoreScene); stage.show();
        stage.setScene(translateScene); stage.show();
        stage.setScene(dictionaryScene); stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}