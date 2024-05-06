package Dictionary.App;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends baseMenu implements Initializable {
    @FXML
    private TextField searchBar = new TextField();
    @FXML
    private TextField txtPhonetic = new TextField();
    @FXML
    private TextArea txtEditor = new TextArea();
    @FXML
    private Label labelWord = new Label();
    @FXML
    private Label spelling = new Label();
    @FXML
    private TextFlow meaning = new TextFlow();
    @FXML
    private ListView<String> suggestWord = new ListView<>();
    @FXML
    private ImageView bookmark2 = new ImageView();
    @FXML
    private ImageView recycleBinIcon = new ImageView();
    @FXML
    private ImageView speakerIcon = new ImageView();
    @FXML
    private ImageView editerIcon = new ImageView();
    @FXML
    private ImageView tickIcon = new ImageView();
    @FXML
    private ImageView magnifier = new ImageView();
    @FXML
    private ImageView crossIcon = new ImageView();
    @FXML
    private ImageView addIcon = new ImageView();
    @FXML
    private ScrollPane scrollMeaning = new ScrollPane();
    @FXML
    private Button importIcon = new Button();
    @FXML
    private Button exportIcon = new Button();

    private String currentMenu = "Search";
    private String currentWord;
    private boolean noSound = true;
    private String apiAudio = "";
    private final TranslateTransition translate = new TranslateTransition(Duration.millis(130));

    void setEditor(boolean b) {
        txtEditor.setVisible(b);
        txtPhonetic.setVisible(b);
        tickIcon.setVisible(b);
        crossIcon.setVisible(b);
    }

    void setSound() {
        speakerIcon.setDisable(noSound);
        if(noSound) {
            speakerIcon.setOpacity(0.5);
        } else {
            speakerIcon.setOpacity(1);
        }
    }

    private String getImaginePath(String path) {
        return new File("src/main/resources/Images/" + path + ".png").getAbsolutePath();
    }

    public void setSuggestion(String[] suggestions) {
        searchBar.setText("");
        suggestWord.getItems().clear();
        suggestWord.getItems().addAll(suggestions);
    }

    public void cellFormat() {
        suggestWord.getStyleClass().add("list-cell");
        suggestWord.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ListCell<>(){
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            setText(item);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void buttonFormat() {
        style(bookmark2, "imagineViewStyle"); style(recycleBinIcon, "imagineViewStyle");
        style(speakerIcon, "imagineViewStyle"); style(editerIcon, "imagineViewStyle");
        style(addIcon, "imagineViewStyle"); style(crossIcon, "imagineViewStyle");
        style(tickIcon, "imagineViewStyle"); addIcon.setVisible(false);
        searchIcon.toFront(); bookmarkIcon.toFront(); historyIcon.toFront(); APIIcon.toFront();
        setEditor(false);translateTransition.setNode(toggleIcon);
        importIcon.setGraphic(getImageView("importIcon", 23, 23));
        exportIcon.setGraphic(getImageView("exportIcon", 23, 23));
    }

    public void setMeaningOfWord(String word) {
        String[] words = word.split("\n");
        meaning.getChildren().clear();
        for(String s : words) {
            if (s.isEmpty()) {
                continue;
            }
            boolean b = false;
            Text text = new Text(s + '\n');

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    @Override
    void APIChoice(ActionEvent e) throws IOException {

    }

    @Override
    void searchChoice(ActionEvent e) throws IOException {

    }

    @Override
    void historyChoice(ActionEvent e) throws IOException {

    }

    @Override
    void bookmarkChoice(ActionEvent e) throws IOException {

    }

    @Override
    void translateChoice(ActionEvent e) throws IOException {

    }

    @Override
    void gameChoice(ActionEvent event) throws IOException {

    }
}

