package component;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SpacedLabel extends JLabel {
    private final int letterSpacing;

    public SpacedLabel(String text) {
        super(text);
        this.letterSpacing = 10;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Font font = getFont();
        FontMetrics metrics = g2d.getFontMetrics(font);

        String text = getText();
        int x = 0;
        int y = metrics.getAscent();
        
        for (char c : text.toCharArray()) {
            g2d.drawString(String.valueOf(c), x, y);
            x += metrics.charWidth(c) + letterSpacing;
        }
    }
}
