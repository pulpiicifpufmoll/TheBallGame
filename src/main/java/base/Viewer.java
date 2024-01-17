package base;

import base.Objects.VOD;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class Viewer extends Canvas implements Runnable{

    // Attributes:
    private final TheGameViewer gameViewer;
    private BufferStrategy bs;
    private final int width, height;

    // Constructor:
    public Viewer(int width, int height, TheGameViewer gameViewer) {
        this.gameViewer = gameViewer;
        bs = null;

        this.width = width;
        this.height = height;

        setUI();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gameViewer.createBall(e.getX(), e.getY());
            }
        });
    }

    // Setter:
    private void setUI() {
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(30, 30, 30));
    }

    // Run:
    @Override
    public void run() {
        while (true) {
            repaintCanvas();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Private methods:
    private void checkBufferStrategy() {
        if (bs == null) {
            createBufferStrategy(2);
            bs = getBufferStrategy();
        }
    }

    private void repaintCanvas() {
        checkBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        g.clearRect(0, 0, width, height);

        for (VOD dynamicObject : gameViewer.getDynamicObjects()) {
            dynamicObject.paint(g);
        }

        bs.show();
        g.dispose();
    }
}
