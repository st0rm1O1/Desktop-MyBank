package com.github.st0rm1O1.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.Border;


public class RoundedBorder implements Border {

    private int radius;
    private Color color;


    public RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }


    public RoundedBorder() {
    	
	}


	public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {    	
    	
    	Graphics2D g2D = (Graphics2D) g;
    	
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g2D.setColor(color);
    	g2D.fillOval(x, y, width, height);
    	
    	g2D.dispose();
    	
    }
}
