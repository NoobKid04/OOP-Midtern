package Dictionary.App;

import Implement.Input.Paragraph.Translator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Dictionary.App.DictionaryApp.anagramMainScene;
import static Dictionary.App.DictionaryApp.mainControl;

public class transController extends baseMenu implements Initializable {
    @FXML
    private ImageView swapIcon;
    @FXML
    private TextArea ggTransInput = new TextArea("");
    @FXML
    private TextArea ggTransOutput = new TextArea("");
    @FXML
    private Label vietnameseInput, vietnameseOutput, englishInput, englishOutput, franceInput, franceOutput, chineseInput, chineseOutput;

    String lanFr = "en", lanTo = "vi";

    void style(Node x, String style) {
        x.getStyleClass().add(style);
    }
    void removeStyle(Node x, String style) {
        x.getStyleClass().remove(style);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
//        Font font =  Font.loadFont(("file:src/main/resources/Font/Roboto-Regular.ttf"),23);
//        Font lanFont =  Font.loadFont(("file:src/main/resources/Font/Roboto-Bold.ttf"),15);
//        input.setFont(font); result.setFont(font);
//        lanEn.setFont(lanFont); lanVi.setFont(lanFont); lanEn1.setFont(lanFont); lanVi1.setFont(lanFont);
//        lanFr.setFont(lanFont); lanFr1.setFont(lanFont); lanIt.setFont(lanFont); lanIt1.setFont(lanFont);
        style(englishInput, "lan"); style(englishOutput, "lan");
        style(vietnameseInput, "lan"); style(vietnameseOutput, "lan");
        style(franceInput, "lan"); style(franceOutput, "lan");
        style(chineseInput, "lan"); style(chineseOutput, "lan");
        style(APIIcon, "toHandCursor"); style(swapIcon, "toHandCursor");
        style(bookmarkIcon, "toHandCursor"); style(searchIcon, "toHandCursor");
        style(historyIcon, "toHandCursor"); style(translateIcon, "toHandCursor");
        style(ggTransInput, "translateArea"); style(ggTransOutput, "translateArea");
        unfocusAllLan("from"); unfocusAllLan("to");
        focusLan(englishInput); focusLan(vietnameseOutput);
        ggTransInput.setText(""); ggTransOutput.setText("");
        unfocusAllLan("from"); focusLan(englishInput);
    }

    String getFile(String path) {
        return new File(path).toURI().toString();
    }

    @Override
    void APIChoice(ActionEvent e) throws IOException {
        mainMenu();
        switchtoAPI();
        mainControl.switchtoAPI();
    }

    @Override
    void searchChoice(ActionEvent e) throws IOException {
        mainMenu();
        switchtoSearch();
        mainControl.switchtoSearch();
    }

    @Override
    void historyChoice(ActionEvent e) throws IOException {
        mainMenu();
        switchtoHistory();
        mainControl.switchtoHistory();
    }

    @Override
    void bookmarkChoice(ActionEvent e) throws IOException {
        mainMenu();
        switchtoBookmark();
        mainControl.switchtoBookmark();
    }

    @Override
    void translateChoice(ActionEvent e) throws IOException {
        mainMenu();
        switchtoTranslate();
        mainControl.switchtoTranslate();
    }

    @Override
    void gameChoice(ActionEvent event) throws IOException {
        Stage stage = (Stage) gameIcon.getScene().getWindow();
        stage.setScene(anagramMainScene);
    }

    void unfocusAllLan(String type) {
        if (type.equals("from")) {
            removeStyle(vietnameseInput, "focus");
            removeStyle(englishInput, "focus");
            removeStyle(franceInput, "focus");
            removeStyle(chineseInput, "focus");
        } else {
            removeStyle(englishOutput, "focus");
            removeStyle(vietnameseOutput, "focus");
            removeStyle(franceOutput, "focus");
            removeStyle(chineseOutput, "focus");
        }
    }

    void focusLan(Node node) {
        style(node, "focus");
    }

    void trans() {
        if (ggTransInput.getText().isBlank()) {
            ggTransOutput.setText("");
            return;
        }
        String from = ggTransInput.getText();
        Translator tran = new Translator();
        tran.translate(from, lanFr, lanTo, (String str) -> {
            ggTransOutput.setText(str);
        });
    }

    void fromEn() {
        lanFr = "en"; unfocusAllLan("from"); focusLan(englishInput);
    }

    void fromVi() {
        lanFr = "vi"; unfocusAllLan("from"); focusLan(vietnameseInput);
    }

    void fromFr() {
        lanFr = "fr"; unfocusAllLan("from"); focusLan(franceInput);
    }

    void fromChinese() {
        lanFr = "zh-CN"; unfocusAllLan("from"); focusLan(chineseInput);
    }

    void toEn() {
        lanTo = "en"; unfocusAllLan("to"); focusLan(englishOutput);
    }

    void toVi() {
        lanTo = "vi"; unfocusAllLan("to"); focusLan(vietnameseOutput);
    }

    void toFr() {
        lanTo = "fr"; unfocusAllLan("to"); focusLan(franceOutput);
    }

    void toChinese() {
        lanTo = "zh-CN"; unfocusAllLan("to"); focusLan(chineseOutput);
    }

    void swap() {
        String lFrom = new String(lanFr);
        String lTo = new String(lanTo);
        switch (lFrom) {
            case "vi" -> toVi();
            case "en" -> toEn();
            case "fr" -> toFr();
            case "zh-CN" -> toChinese();
        }
        switch (lTo) {
            case "vi" -> fromVi();
            case "en" -> fromEn();
            case "fr" -> fromFr();
            case "zh-CN" -> fromChinese();
        }
        String tmp = new String(ggTransInput.getText());
        ggTransInput.setText(ggTransOutput.getText());
        ggTransOutput.setText(tmp);
    }

    @FXML
    void frEng(MouseEvent e) {
        ggTransInput.setText(""); fromEn(); trans();
    }

    @FXML
    void frVie(MouseEvent e) {
        ggTransInput.setText(""); fromVi(); trans();
    }

    @FXML
    void frFre(MouseEvent e) {
        ggTransInput.setText(""); fromFr(); trans();
    }

    @FXML
    void frChina(MouseEvent e) {
        ggTransInput.setText(""); fromChinese(); trans();
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
    void toChina(MouseEvent e) {
        toChinese(); trans();
    }

    @FXML
    void onKeyPress(KeyEvent e) {
        trans();
    }

    @FXML
    void swapLanguage(MouseEvent e) {
        swap();
    }
}
