package Dictionary.App;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Dictionary.App.DictionaryApp.dictionaryScene;
import static Dictionary.App.DictionaryApp.translateScene;

public abstract class baseMenu implements Initializable {
    @FXML
    protected Button searchIcon = new Button();
    @FXML
    protected Button bookmarkIcon = new Button();
    @FXML
    protected Button historyIcon = new Button();
    @FXML
    protected Button APIIcon = new Button();
    @FXML
    protected Button translateIcon = new Button();
    @FXML
    protected Button gameIcon = new Button();
    @FXML
    protected ImageView toggleIcon = new ImageView();

    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(130));

    String getFile(String fileName) {
        return new File(fileName).toURI().toString();
    }

    protected ImageView getImageView(String fileName, int height, int weight) {
        ImageView img = new ImageView(new Image(getFile("src/main/resources/Images/" + fileName + ".png")));
        img.setFitHeight(height);
        img.setFitWidth(weight);
        return img;
    }

    public void setTooltip(Node node, String s) {
        Tooltip tooltip = new Tooltip(s); tooltip.setShowDelay(Duration.millis(300));
        Tooltip.install(node, tooltip);
    }

    public void style(Node node, String s) {
        node.getStyleClass().add(s);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchIcon.setGraphic(getImageView("searchIcon", 40, 40));
        bookmarkIcon.setGraphic(getImageView("star", 40, 40));
        historyIcon.setGraphic(getImageView("History", 40, 40));
        APIIcon.setGraphic(getImageView("apiIcon", 40, 40));
        translateIcon.setGraphic(getImageView("translate", 40, 40));
        gameIcon.setGraphic(getImageView("game", 40, 40));
        style(searchIcon, "toHandCursor"); style(bookmarkIcon, "toHandCursor");
        style(historyIcon, "toHandCursor"); style(APIIcon, "toHandCursor");
        style(translateIcon, "toHandCursor"); style(gameIcon, "toHandCursor");
        translateTransition.setNode(toggleIcon);
        setTooltip(searchIcon, "Search"); setTooltip(bookmarkIcon, "Bookmark");
        setTooltip(historyIcon, "History"); setTooltip(APIIcon, "API");
        setTooltip(translateIcon, "Translate"); setTooltip(gameIcon, "Game");
    }

    void toggle(Node node) {
        translateTransition.setToY(node.getLayoutY() - 10 - toggleIcon.getLayoutY());
        translateTransition.play();
    }

    public void switchtoSearch() {
        toggle(searchIcon);
    }
    public void switchtoBookmark() {
        toggle(bookmarkIcon);
    }
    public void switchtoHistory() {
        toggle(historyIcon);
    }
    public void switchtoAPI() {
        toggle(APIIcon);
    }
    public void switchtoTranslate() {
        toggle(translateIcon);
    }
    public void switchtoGame() {
        toggle(gameIcon);
    }

    void mainMenu() {
        Stage stage = (Stage) searchIcon.getScene().getWindow();
        stage.setScene(dictionaryScene);
    }

    void translate() {
        Stage stage = (Stage) searchIcon.getScene().getWindow();
        stage.setScene(translateScene);
    }

    abstract void searchChoice(ActionEvent e) throws IOException;
    abstract void historyChoice(ActionEvent e) throws IOException;
    abstract void bookmarkChoice(ActionEvent e) throws IOException;
    abstract void APIChoice(ActionEvent e) throws IOException;
    abstract void translateChoice(ActionEvent e) throws IOException;
    abstract void gameChoice(ActionEvent event) throws IOException;
}
