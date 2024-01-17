package base;

import base.Objects.VOD;
import base.TGR.Codex;

import java.util.ArrayList;

public class TheGameController {

    // Attributes:
    private final int width, height;

    private final TheGameModel gameModel;
    private final TheGameViewer gameViewer;

    private final Codex codex;

    // Constructor:
    public TheGameController(int width, int height) {
        this.width = width;
        this.height = height;

        this.gameModel = new TheGameModel(width, height, this);
        this.gameViewer = new TheGameViewer(width, height, this);

        this.codex = new Codex();
    }

    // Getters:
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    // Public methods:
    public String solveSituation(VOD objectA, VOD objectB) {
        //método que, dependiendo de los tipo de objectos que colisionan
        // devolverá una regla u otra
        return codex.getRule(objectA, objectB);
    }
    public void createBall(int x, int y) {
        gameModel.createBall(x, y);
    }
    public ArrayList<VOD> getDynamicObjects() {
        return gameModel.getDynamicObjects();
    }
}
