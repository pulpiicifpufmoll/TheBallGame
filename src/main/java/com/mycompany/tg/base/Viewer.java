package com.mycompany.tg.base;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Viewer extends Canvas implements Runnable, ActionListener {

    private BufferedImage backgroundImg;
    private BufferStrategy bs;
    private TheGameViewer view;
    int xSize;
    int ySize;
    
    public Viewer(int pixWidth, int pixHeight, TheGameViewer view) {
        Dimension d = new Dimension(pixWidth, pixHeight);
        this.view = view;
        this.setPreferredSize(d);
        this.loadBackground();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Obtener las nuevas coordenadas cuando se redimensiona el viewer
                xSize = getWidth();
                ySize = getHeight();
            }
        });
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //To do

    public void paintObject(int x, int y) {
        if (this.bs == null) {
            System.out.println("x: " + x + "y: " + y);
            System.out.println("kgd");
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }

        Graphics g = bs.getDrawGraphics();
        int r = 20;
        x = x - (r / 2);
        y = y - (r / 2);
        g.setColor(Color.yellow);
        g.fillOval(x, y, r, r);
        bs.show();
        g.dispose();
    }

    void deleteLastPositionObject(int x, int y) {
        if (this.bs == null) {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }

        Graphics g = bs.getDrawGraphics();
        int r = 20;
        x = x - (r / 2);
        y = y - (r / 2);
        g.setColor(Color.black);
        g.fillOval(x, y, r, r);
        bs.show();
        g.dispose();
    }

    private void loadBackground() {
        try {
            this.backgroundImg = ImageIO.read(new File("C:\\Users\\alumnat\\Desktop\\espacio.webp"));
            System.out.println("Width: " + this.backgroundImg.getWidth());
            System.out.println("Height: " + this.backgroundImg.getHeight());
        } catch (IOException e) {
            System.err.println("Error loading background. ");
            System.err.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
