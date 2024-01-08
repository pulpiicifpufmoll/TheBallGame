package com.mycompany.tg.base;

public class TheGameController {

    TheGameModel model;

    TheGameViewer view;
    public TheGameController() {
        this.view = new TheGameViewer(this);
        this.model = new TheGameModel(this);
    }

    public void addBall(int x, int y) {
        model.addBall(x, y);
    }

    public void paint(int x, int y) {
        this.view.paint(x, y);
    }

    void delete(int x, int y) {
        this.view.delete(x, y);
    }

    int getVWXSize() {
        return this.view.viewer.xSize;
    }

    int getVMYSize() {
        return this.view.viewer.ySize;
    }

}
