package view;

import javax.swing.JPanel;

import model.Player;
import model.Vertex;

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
    private final int PLAYER_SPEED = 2;
    
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

    public void forward() {
        Vertex v = player.getV();
        int x = (int)v.getX() + player.getX();
        int y = (int)v.getY() + player.getY();
        player.setX(player.getX() + (x - player.getX()) / 6);
        player.setY(player.getY() + (y - player.getY()) / 6);

    }

    public void down() {
        Vertex v = player.getV();
        int x = (int)v.getX() + player.getX();
        int y = (int)v.getY() + player.getY();
        player.setX(player.getX() - (x - player.getX()) / 6);
        player.setY(player.getY() - (y - player.getY()) / 6);
    }

    public void left() {
        player.rotateZ(-1);
    }

    public void right() {
        player.rotateZ(1);
    }
}
