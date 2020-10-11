package view;

import javax.swing.JPanel;

import model.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

public class RaycastCanvas2D extends JPanel {
    private static final long serialVersionUID = 1L;
    private RaycastGamePanel raycastGamePanel;
    private Graphics2D g2;
    private Player player;
    private int width;
    private int height;
    
    public RaycastCanvas2D(RaycastGamePanel raycastGamePanel) {
        this.raycastGamePanel = raycastGamePanel;
        setBackground(Color.BLACK);
        width = raycastGamePanel.WIDTH;
        height = raycastGamePanel.HEIGHT;
        setPreferredSize(new Dimension(width, height));
        player = new Player();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2 = (Graphics2D) g;

        g2.setColor(Color.DARK_GRAY);
        for (int i = 0; i < 8; ++i) {
            g2.drawLine(width / 8 * i, 0, width / 8 * i, height);
        }

        for (int i = 0; i < 8; ++i) {
            g2.drawLine(0, height / 8 * i, width, height / 8 * i);
        }

        player.render(g2);
    }
}
