package Dictionary.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class deleteWordController extends noticeBox{
    boolean deleted = false;

    public boolean isDeleted() {
        return deleted;
    }

    @FXML
    public void clkYes(ActionEvent e) {
        super.clkYes(e);
        deleted = true;
    }

    @FXML
    public void clkNo(ActionEvent e) {
        super.clkNo(e);
        deleted = false;
    }
}
