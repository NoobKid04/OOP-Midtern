package Dictionary.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class addAPIController extends noticeBox{
    boolean added = false;

    public boolean isAdded() {
        return added;
    }

    @FXML
    public void clickYes(ActionEvent e) {
        super.clkYes(e);
        added = true;
    }

    @FXML
    public void clickNo(ActionEvent e) {
        super.clkNo(e);
        added = false;
    }
}
