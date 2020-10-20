package view;

import javax.swing.JPanel;

import model.Ray;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Dimension;

public class RaycastCanvas3D extends JPanel {
    private static final long serialVersionUID = 1L;
    private RaycastGamePanel raycastGamePanel;
    private Graphics2D g2;
    private ArrayList<Ray> rays;
    private int width;
    private int height;
    
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

        for (int i = 30, y = 0, c = 40; i > 15; --i, --c) {
            g2.setColor(new Color(0, c - 20, c));
            g2.fillRect(0, y, width, i);
            y += i;
        }

        for (int i = 30, y = height, c = 15; i > 15; --i, --c) {
            g2.setColor(new Color(c, c + 10, c, 100));
            g2.fillRect(0, y, width, i);
            y -= i - 1;
        }

        // distance *= cos theta
        int i = 0;
        for (var r: rays) {
            int yPos = (height - (int)(((height / 8) * height) / r.getDistance())) / 2;
            int colorShader = 255 - yPos;
            if (colorShader < 10) colorShader = 10;
            else if (colorShader > 180) colorShader = 180;
            Color c = new Color(colorShader, colorShader + 5, colorShader + 10);
            g2.setColor(c);
            g2.fillRect(i, yPos, 1, (int)(((height / 8) * height) / r.getDistance()));
            i += 1;
        }
    }

    public void setRays(ArrayList<Ray> rays) {
        this.rays = rays;
    }
}