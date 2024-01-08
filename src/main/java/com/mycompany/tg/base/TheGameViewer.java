package com.mycompany.tg.base;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.http.WebSocket.Listener;
import javax.swing.JFrame;

public class TheGameViewer extends JFrame implements Listener, ActionListener, MouseListener {

    TheGameController controller;
    ControlPanel cPanel;
    Viewer viewer;

    public TheGameViewer(TheGameController controller) {
        this.controller = controller;
        this.configureJFrame();
        this.setVisible(true);
    }

    public void addBall() {

    }

    private void configureJFrame() {
        this.setTitle("Thread Lab");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 800);
        this.addComponentsToPane();
    }

    private void addComponentsToPane() {

        Container panel;

        panel = this.getContentPane();
        this.addViewerToPane(panel);
        this.addControlPanel(panel);

    }

    private void addControlPanel(Container panel) {
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.gridheight = 1;
        c.gridwidth = 1;

        this.cPanel = new ControlPanel(this);
        panel.add(this.cPanel, c);
    }

    private void addViewerToPane(Container panel) {
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 4;
        c.gridwidth = 1;

        this.viewer = new Viewer(712, 712, this);
        this.viewer.addMouseListener(this);
        this.viewer.setBackground(Color.black);
        panel.add(this.viewer, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("HEY it wORKS");
        int x = e.getX();
        int y = e.getY();
        controller.addBall(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    void paint(int x, int y) {
        this.viewer.paintObject(x, y);
    }

    void delete(int x, int y) {
        this.viewer.deleteLastPositionObject(x,y);
    }

}
