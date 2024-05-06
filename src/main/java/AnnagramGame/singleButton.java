package AnnagramGame;

import javafx.scene.control.Button;

public class singleButton extends Button {
    private Character character;

    public singleButton(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
