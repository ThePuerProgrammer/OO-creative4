package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class RaycastCanvas3D extends JPanel {
    private static final long serialVersionUID = 1L;
    private RaycastGamePanel raycastGamePanel;
    private Graphics2D g2;
    
    public RaycastCanvas3D(RaycastGamePanel raycastGamePanel) {
        this.raycastGamePanel = raycastGamePanel;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(raycastGamePanel.WIDTH, raycastGamePanel.HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g2 = (Graphics2D) g;
    }
}