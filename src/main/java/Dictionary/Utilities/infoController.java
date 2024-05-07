package Dictionary.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class infoController {
    @FXML
    private Button confirm;

    @FXML
    private ImageView infoIcon;

    @FXML
    private Label message;

    public void setPrompt(String MSG) {
        message.setText(MSG);
    }
    @FXML
    public void clkOK(ActionEvent e) {
        Stage stage = (Stage) message.getScene().getWindow();
        stage.close();
    }
}
