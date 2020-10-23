package view;

import javax.swing.JPanel;

import model.Ray;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;

public class RaycastCanvas3D extends JPanel {
    private static final long serialVersionUID = 1L;
    private RaycastGamePanel raycastGamePanel;
    private Graphics2D g2;
    private ArrayList<Ray> rays;
    private int width;
    private int height;
    public static String wasd = "WASD to move!";
    
    public RaycastCanvas3D(RaycastGamePanel raycastGamePanel) {
        this.raycastGamePanel = raycastGamePanel;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(raycastGamePanel.WIDTH, raycastGamePanel.HEIGHT));
        width = raycastGamePanel.WIDTH;
        height = raycastGamePanel.HEIGHT;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g2 = (Graphics2D) g;

        for (int i = 30, y = 0, c = 95; i > 15; --i, --c) {
            g2.setColor(new Color( 0, c - 40, c));
            g2.fillRect(0, y, width, i);
            y += i;
        }

        for (int i = 300, y = height - 1, c = 175; i > 0; --i, y--) {
            if (i % 2 == 0) c--;
            g2.setColor(new Color(c - 25, c + 40, 0));
            g2.fillRect(0, y, width, i);
        }

        g2.setFont(new Font("Times New Roman", Font.BOLD, 50));
        g2.setColor(Color.CYAN);
        g2.drawString(wasd, 20, (height / 2) + 10);

        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // distance *= cos theta
        int i = 0;
        for (var r: rays) {
            int yPos = (int)((height - (height / 8 * height / r.getDistance())) / 2);
            int colorShader = 255 - yPos + 70;
            if (colorShader < 20) colorShader = 20;
            else if (colorShader > 150) colorShader = 150;
            Color c = new Color(colorShader + 100, colorShader + 50, 40);
            g2.setColor(c);
            g2.fillRect(i, yPos, 1, (int)(height / 8 * height / r.getDistance()));
            i += 1;
        }
    }

    public void setRays(ArrayList<Ray> rays) {
        this.rays = rays;
    }
}