package Dictionary.App;

import Implement.Bookmark;
import Implement.History;
import Implement.Input.AddFromFile;
import Implement.Output.ExportToFile;
import Implement.WordStorage.DictionaryMap;
import Implement.WordStorage.Trie.Trie;
import Implement.WordStorage.Trie.TrieNode;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Dictionary.App.DictionaryApp.*;

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
            text.setFont(Font.font("Times New Roman", 17));
            if (s.contains("• Example")) {
                Text temp = new Text("\n");
                meaning.getChildren().add(temp);
                b = true;
                text.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 17));
            }
            if (s.charAt(0) == '☆') {
                text = new Text("\n" + s + "\n");
                text.setFont(Font.font("Times New Roman", 25));
                text.setFill(Color.RED);
            }
            meaning.getChildren().add(text);
            if (b) {
                Text temp = new Text("\n");
                meaning.getChildren().add(temp);
            }
        }
        meaning.setPrefWidth(scrollMeaning.getPrefWidth());
        scrollMeaning.setContent(meaning);
    }

    String getMeaning() {
        StringBuilder mean = new StringBuilder();
        for (Node node : meaning.getChildren()) {
            if (node instanceof Text) {
                String add = ((Text) node).getText();
                if (add.isBlank()) {
                    continue;
                }
                mean.append(add);
            }
        }
        mean.deleteCharAt(0);
        return mean.toString();
    }

    void setContent(TrieNode node) {
        apiAudio = node.getAudio(); noSound = apiAudio.isBlank(); setSound();
        searchBar.setText(currentWord);
        labelWord.setText(currentWord);
        spelling.setText(node.getSpelling());
        setMeaningOfWord(node.getMeaning());
        addIcon.setVisible(false);
        if (!node.getBookmarked()) {
            bookmark2.setImage(new Image(getImaginePath("starUntoggle")));
        } else {
            bookmark2.setImage(new Image(getImaginePath("star")));
        }
    }

    void addWord() {
        Stage stage = new Stage();
        stage.setScene(addWordScene);
        addWordControl.setWord(searchBar.getText());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(new File("src/main/resources/Images/plusIcon.png").toURI().toString()));
        stage.setResizable(false);
        stage.setTitle("Thêm từ");
        stage.showAndWait();
    }

    public void txtFieldFormat() {
        searchBar.getStyleClass().add("txtField");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        cellFormat(); buttonFormat(); setSound();
        AddFromFile.add(null); setSuggestion(DictionaryMap.getKey());
        menuInit(false); txtFieldFormat();
        suggestWord
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observableValue, s, t1) -> {
                            menuInit(true);
                            currentWord = suggestWord.getSelectionModel().getSelectedItem();
                            if (currentWord == null || currentWord.isEmpty()) {
                                return;
                            }
                            if (currentWord.equals("Thêm...")) {
                                addWord();
                                return;
                            }
                            setContent(Trie.find(currentWord));
                            if (!currentMenu.equals("History")) {
                                History.add(currentWord);
                            }
                            setTooltip(addIcon, "Thêm vào từ điển");
                            setTooltip(tickIcon, "Lưu"); setTooltip(crossIcon, "Hủy");
                        });
    }

    void menuInit(boolean active) {
        suggestWord.setVisible(true); bookmark2.setVisible(active);
        if (active && Bookmark.find(labelWord.getText())) {
            bookmark2.setImage(new Image(getImaginePath("star")));
        } else {
            bookmark2.setImage(new Image(getImaginePath("starUntoggle")));
        }
        recycleBinIcon.setVisible(active); editerIcon.setVisible(active);
    }

    public void Switch(boolean mInit, Node node, boolean searchB, String[] lst, String cMenu) {
        menuInit(mInit);
        toggle(node);
        searchBar.setVisible(searchB); magnifier.setVisible(searchB);
        if (lst != null) setSuggestion(lst);
        currentMenu = cMenu;
    }

    @Override
    public void switchtoAPI() {
        super.switchtoAPI();
        Switch(true, APIIcon, true, null, "API");
        suggestWord.setVisible(false);
        bookmark2.setVisible(false); recycleBinIcon.setVisible(false); editerIcon.setVisible(false);
    }

    @Override
    void APIChoice(ActionEvent e) throws IOException {
        switchtoAPI();
        translateControl.switchtoAPI();
    }

    @Override
    public void switchtoSearch() {
        super.switchtoSearch();
        Switch(true, searchIcon, true, DictionaryMap.getKey(), "Search");
    }

    @Override
    void searchChoice(ActionEvent e) throws IOException {
        switchtoSearch();
        translateControl.switchtoSearch();
    }

    @Override
    public void switchtoHistory() {
        super.switchtoHistory();
        Switch(true, historyIcon, false, History.list(), "History");
    }

    @Override
    void historyChoice(ActionEvent e) throws IOException {
        switchtoHistory();
        translateControl.switchtoHistory();
    }

    @Override
    public void switchtoBookmark() {
        super.switchtoBookmark();
        Switch(true, bookmarkIcon, true, Bookmark.getList(), "Bookmark");
    }

    @Override
    void bookmarkChoice(ActionEvent e) throws IOException {
        switchtoBookmark();
        translateControl.switchtoBookmark();
    }

    private static void extracted() {
        translateControl.switchtoAPI();
    }

    public void changeStarState(MouseEvent e) {
        String word = labelWord.getText();
        if (word.equals("Translator") || word.isBlank()) {
            return;
        }
        TrieNode node = Trie.find(word);
        if (!node.getBookmarked()) {
            if (!DictionaryMap.exist(word)) {
                Trie.add(word, spelling.getText(), getMeaning(), apiAudio);
                DictionaryMap.add(word, getMeaning());
                History.add(word);
            }
            bookmark2.setImage(new Image(getImaginePath("star")));
            node.setBookmarked(true);
            Bookmark.add(word);
        } else {
            bookmark2.setImage(new Image(getImaginePath("starUntoggle")));
            node.setBookmarked(false);
            Bookmark.delete(word);
        }
        if (currentMenu.equals("Bookmark")) {
            setSuggestion(Bookmark.getList());
        }
    }

    public void deleteWord(MouseEvent e) {
        String word = labelWord.getText();
        if (word.equals("Translate")) {
            return;
        }
        deleteControl.setPrompt("Bạn có chắc muốn xóa " + labelWord.getText() + " khỏi từ điển?");
        Stage stage = new Stage();
        stage.setScene(deleteScene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(new File("src/main/resources/Images/minusIcon.png").toURI().toString()));
        stage.setResizable(false);
        stage.setTitle("Xóa từ");
        infoControl.setPrompt("Đã xóa " + word + " khỏi từ điển");
        stage.showAndWait();
        if (!deleteControl.isDeleted()) {
            return;
        }
        Trie.delete(word);
        DictionaryMap.delete(word); Bookmark.delete(word); History.delete(word);
        switch (currentMenu) {
            case "Search" -> setSuggestion(DictionaryMap.getKey());
            case "Bookmark" -> setSuggestion(Bookmark.getList());
            case "History" -> setSuggestion(History.list());
        }
        searchBar.setText(""); labelWord.setText(""); spelling.setText(""); setMeaningOfWord("");
        bookmark2.setVisible(false); recycleBinIcon.setVisible(false); editerIcon.setVisible(false);
    }

    public void playMedia(MouseEvent e) {
        Media media = new Media(apiAudio);
        MediaPlayer player = new MediaPlayer(media);
        if (player.getStatus() == MediaPlayer.Status.PLAYING) {
            player.stop();
        }
        player.seek(Duration.ZERO);
        player.play();
    }

    void iconDisable(boolean b) {
        searchIcon.setDisable(b); bookmarkIcon.setDisable(b); historyIcon.setDisable(b);
        APIIcon.setDisable(b); translateIcon.setDisable(b); gameIcon.setDisable(b);
        bookmark2.setDisable(b); recycleBinIcon.setDisable(b);
        meaning.setOpacity(1); spelling.setOpacity(1);
        double opacity = 1;
        if (b) {
            opacity = 0.3; speakerIcon.setDisable(true); speakerIcon.setOpacity(opacity);
            meaning.setOpacity(0); spelling.setOpacity(0);
        } else {
            TrieNode node = Trie.find(labelWord.getText());
            apiAudio = node.getAudio(); noSound = apiAudio.isBlank(); setSound();
        }
        searchIcon.setOpacity(opacity); bookmarkIcon.setOpacity(opacity); historyIcon.setOpacity(opacity);
        APIIcon.setOpacity(opacity); translateIcon.setOpacity(opacity); gameIcon.setOpacity(opacity);
        bookmark2.setOpacity(opacity); recycleBinIcon.setOpacity(opacity);
    }

    public void openEditor(MouseEvent e) {
        if (labelWord.getText().equals("Translate")) {
            return;
        }
        setEditor(true);
        txtEditor.setText(getMeaning());
        txtPhonetic.setText(spelling.getText());
        suggestWord.setDisable(true);
        iconDisable(true);
        switch (currentMenu) {
            case "Search" -> searchIcon.setOpacity(1);
            case "Bookmark" -> bookmarkIcon.setOpacity(1);
            case "History" -> historyIcon.setOpacity(1);
        }
    }

    void closeEditor() {
        setEditor(false);
        iconDisable(false);
        suggestWord.setDisable(false);
    }

    public void exitEditor(MouseEvent e) {
        closeEditor();
    }

    public void saveEditor(MouseEvent e) {
        String tmp = txtEditor.getText();
        if (tmp.isEmpty() || tmp.charAt(tmp.length() - 1) != '\n') {
            tmp += '\n';
        }
        setMeaningOfWord(tmp);
        String newMeaning = tmp;
        String newSpelling = txtPhonetic.getText();
        spelling.setText(newSpelling);
        Trie.add(labelWord.getText(), newSpelling, newMeaning, "");
        closeEditor();
    }

    public void addFromAPI(MouseEvent e) {
        addAPIControl.setPrompt("Bạn có chắc muốn thêm " + labelWord.getText() + " vào từ điển?");
        infoControl.setPrompt("Đã thêm " + labelWord.getText() + " vào từ điển.");
        Stage stage = new Stage();
        stage.setScene(addAPIScene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(new File("src/main/resources/Images/plusIcon.png").toURI().toString()));
        stage.setTitle("Thêm từ");
        stage.showAndWait();
        if (addAPIControl.isAdded()) {
            Trie.add(labelWord.getText(), spelling.getText(), getMeaning(), apiAudio);
            DictionaryMap.add(labelWord.getText(), getMeaning());
        }
    }

    @Override
    void translateChoice(ActionEvent e) throws IOException {
        translate();
        switchtoTranslate();
        translateControl.switchtoTranslate();
    }

    @Override
    void gameChoice(ActionEvent event) throws IOException {
        Stage stage = (Stage) gameIcon.getScene().getWindow();
        stage.setScene(anagramMainScene);
    }

    public void fileImport(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file to import from...");
        File initLocation = new File("src/main/resources");
        fileChooser.setInitialDirectory(initLocation);
        Stage stage = (Stage) addIcon.getScene().getWindow();
        File selected = fileChooser.showOpenDialog(stage);
        if (selected == null) {
            return;
        }
        AddFromFile.add(selected);
        infoControl.setPrompt("Đã nhập file vào dữ liệu từ điển");
        Stage stage1 = new Stage();
        stage1.setScene(infoScene);
        stage1.setTitle("Thông báo");
        stage1.getIcons().add(new Image(new File("src/main/resources/Images/infoIcon.png").toURI().toString()));
        stage1.showAndWait();
    }

    public void fileExport(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file to export to...");
        File initLocation = new File("src/main/resources");
        fileChooser.setInitialDirectory(initLocation);
        Stage stage = (Stage) addIcon.getScene().getWindow();
        File selected = fileChooser.showOpenDialog(stage);
        if (selected == null) {
            return;
        }
        ExportToFile.export(selected);
        infoControl.setPrompt("Đã xuất dữ liệu từ điển ra file");
        Stage stage1 = new Stage();
        stage1.setScene(infoScene);
        stage1.setTitle("Thông báo");
        stage1.getIcons().add(new Image(new File("src/main/resources/Images/infoIcon.png").toURI().toString()));
        stage1.showAndWait();
    }
}

