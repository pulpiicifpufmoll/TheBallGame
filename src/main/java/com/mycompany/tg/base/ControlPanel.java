package com.mycompany.tg.base;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ControlPanel extends JPanel {

    TheGameViewer view;
    JToggleButton playPause;
    JLabel cPanelL;

    public ControlPanel(TheGameViewer view) {
        this.view = view;
        setLayout(new GridBagLayout());
        configureControlPanel();
    }

    private void configureControlPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 2;

        crearComponentes();
        añadirComponentes(c);

    }

    private void crearComponentes() {
        this.cPanelL = new JLabel("PANEL DE CONTROL");
        this.playPause = new JToggleButton("Play");
        this.playPause.addActionListener(this.view);
    }

    private void añadirComponentes(GridBagConstraints c) {

        this.cPanelL.setForeground(Color.BLUE);
        this.cPanelL.setBackground(Color.BLACK);
        this.cPanelL.setOpaque(true);
        this.cPanelL.setHorizontalAlignment(JLabel.CENTER);
        this.cPanelL.setVerticalAlignment(JLabel.CENTER);

        this.add(this.cPanelL, c);
        c.gridy++;
        this.add(this.playPause, c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy++;
    }
}
