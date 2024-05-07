package Dictionary.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

import static Dictionary.App.DictionaryApp.infoScene;

public class noticeBox {
    @FXML
    public static Button yesButton = new Button();
    @FXML
    public static Button noButton = new Button();

    @FXML
    public Label lblMessage;

    public void setPrompt(String msg) {
        lblMessage.setText(msg);
    }

    @FXML
    public void clkYes(ActionEvent e) {
        Stage stage = (Stage) lblMessage.getScene().getWindow();
        stage.close();
        Stage stage1 = new Stage();
        stage1.setScene(infoScene);
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.getIcons().add(new Image(new File("src/main/resources/Images/infoIcon.png").toURI().toString()));
        stage1.setTitle("Thông báo");
        stage1.show();
    }

    @FXML
    public void clkNo(ActionEvent e) {
        Stage stage = (Stage) lblMessage.getScene().getWindow();
        stage.close();
    }
}
