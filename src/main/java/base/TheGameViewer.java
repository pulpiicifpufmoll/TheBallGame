package base;

import base.Objects.VOD;

import javax.swing.*;
import java.util.ArrayList;

public class TheGameViewer extends JFrame {

    // Attributes:
    private final Viewer viewer;

    private final TheGameController gameController;

    // Constructor:
    public TheGameViewer(int width, int height, TheGameController lc) {
        this.gameController = lc;

        setUI();

        viewer = new Viewer(width ,height, this);
        add(viewer);

        pack();
        setVisible(true);

        Thread thread = new Thread(viewer);
        thread.start();
    }

    // Setter:
    private void setUI() {
        setTitle("Bouncing ball");
        setLocation(660, 240);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Public methods:
    public void createBall(int x, int y) {
        gameController.createBall(x,y);
    }
    public ArrayList<VOD> getDynamicObjects() {
        return gameController.getDynamicObjects();
    }

}
