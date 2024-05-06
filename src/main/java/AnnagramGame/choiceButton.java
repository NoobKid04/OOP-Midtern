package AnnagramGame;

import javafx.scene.control.Button;

public class choiceButton extends Button {
    private singleButton parentButton;

    public choiceButton(singleButton parentButton) {
        this.parentButton = parentButton;
    }

    public singleButton getParentButton() {
        return parentButton;
    }

    public void setParentButton(singleButton parentButton) {
        this.parentButton = parentButton;
    }
}
