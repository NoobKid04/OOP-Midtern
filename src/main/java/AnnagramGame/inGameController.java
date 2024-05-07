package AnnagramGame;

import Implement.WordFormatter;
import Implement.WordStorage.DictionaryMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static Dictionary.App.DictionaryApp.anagramScoreScene;
import static Dictionary.App.DictionaryApp.gameScoreControl;

public class inGameController implements Initializable {
    @FXML
    private HBox answer;
    @FXML
    private GridPane subject;
    @FXML
    private Label intimeScore;
    @FXML
    private TextFlow timeRemain;

    private Timeline timeline;
    private int timeLimit = 60;
    private int minLength = 2;
    private int maxLength = 4;
    private int countdownSeconds;
    private int score;
    private int numCol;
    private String string = new String("");

    public int getMaxLength() {
        return maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    List<Character> characters = new ArrayList<>();
    List<choiceButton> buttons = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        answer.setSpacing(10);
    }

    public void startGame(int timeLimit, int minLength, int maxLength) {
        this.timeLimit = timeLimit;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.countdownSeconds = timeLimit;
        timerControl();
        newGame();
    }

    private void timerControl() {
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            if (countdownSeconds > 0) {
                countdownSeconds--;
                updateTimeRemain();
            } else {
                gameScoreControl.setFinalScore(score + "");
                Stage stage = (Stage) timeRemain.getScene().getWindow();
                stage.setScene(anagramScoreScene);
                timeline.stop();
            }
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTimeRemain() {
        int minutes = countdownSeconds / 60;
        int seconds = countdownSeconds % 60;
        String timeString = String.format("%02d:%02d", minutes, seconds);
        timeRemain.getChildren().clear();
        Text text = new Text(timeString);
        text.setFont(Font.font("System", FontWeight.BOLD, 30));
        text.setFill(Color.WHITE);
        timeRemain.getChildren().add(text);
    }

    String getPath(String s) {
        return new File(s).toURI().toString();
    }

    ImageView getCharImage(Character c) {
        ImageView imageView = new ImageView(new Image(getPath("src/main/resources/Images/Char/char-" + c + ".png")));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        return imageView;
    }

    void newGame() {
        score = 0;
        nextRound();
    }

    void nextRound() {
        intimeScore.setText(score + "");
        characters.clear();
        string = "";
        while (!(string.length() >= minLength && string.length() <= maxLength)) {
            string = DictionaryMap.getRandom();
        }
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            characters.add(c);
        }
        System.out.println(string);
        Collections.shuffle(characters);
        reset();
    }

    void reset() {
        numCol = 0;
        answer.getChildren().clear();
        subject.getColumnConstraints().clear();
        subject.getChildren().clear();
        buttons.clear();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100/characters.size());
        for (int i=0; i<characters.size(); i++) {
            subject.getColumnConstraints().add(columnConstraints);
        }
        for (Character c : characters) {
            addButton(c);
        }
    }

    void addButton(Character c) {
        singleButton button = new singleButton(c);
        button.setPrefWidth(60); button.setPrefHeight(60);
        button.setMinHeight(60); button.setMinWidth(60);
        button.setMaxHeight(60); button.setMaxWidth(60);
        button.setGraphic(getCharImage(c));
        button.setOnAction(event -> onClick(button));
        subject.add(button, numCol++, 0);
        GridPane.setHalignment(button,  HPos.CENTER);
    }

    void selectSingleButton(singleButton button) {
        choiceButton button1 = new choiceButton(button);
        button1.setPrefWidth(60); button1.setPrefHeight(60);
        button1.setMinHeight(60); button1.setMinWidth(60);
        button1.setMaxHeight(60); button1.setMaxWidth(60);
        button1.setGraphic(getCharImage(button.getCharacter()));
        button1.setOnAction(event -> onClick(button1));
        answer.getChildren().add(button1);
        button.setVisible(false);
        buttons.add(button1);
    }

    void selectChoiceButton(choiceButton button) {
        singleButton button1 = button.getParentButton();
        button1.setVisible(true);
        answer.getChildren().remove(button);
        buttons.remove(button);
    }

    void onClick(Button button) {
        if(button instanceof singleButton) {
            singleButton sButton = (singleButton) button;
            selectSingleButton(sButton);
        } else {
            choiceButton button1 = (choiceButton) button;
            selectChoiceButton(button1);
        }
    }

    @FXML
    void confirm(MouseEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        for(choiceButton button : buttons) {
            stringBuilder.append(button.getParentButton().getCharacter().toString());
        }
        String string = WordFormatter.normalize(stringBuilder.toString());
        if(DictionaryMap.exist(string) && string.length() == stringBuilder.length()) {
            score++;
            countdownSeconds+=5;
            nextRound();
        }
        reset();
    }

    @FXML
    void skip(MouseEvent event) {
        countdownSeconds--;
        nextRound();
    }
}
