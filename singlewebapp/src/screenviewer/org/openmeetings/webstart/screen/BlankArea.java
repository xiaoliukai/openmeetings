package org.openmeetings.webstart.screen;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;

public class BlankArea extends JLabel {
	private static final long serialVersionUID = -7548278286826461299L;
	Dimension minSize = new Dimension(100, 50);

    public BlankArea(Color color) {
        setBackground(color);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }
}
