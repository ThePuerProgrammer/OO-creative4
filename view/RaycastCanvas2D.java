package view;

import javax.swing.JPanel;

import model.Boundary;
import model.Player;
import model.Vertex;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
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
    private ArrayList<Boundary> boundaries = new ArrayList<>();
    private final Color boundaryColor = new Color(5, 5, 5);
    private final Color lineColor = new Color(220,220,220);
    
    public RaycastCanvas2D(RaycastGamePanel raycastGamePanel) {
        this.raycastGamePanel = raycastGamePanel;
        setBackground(Color.DARK_GRAY);
        width = raycastGamePanel.WIDTH;
        height = raycastGamePanel.HEIGHT;
        setPreferredSize(new Dimension(width, height));
        player = new Player();

        // top walls
        for (int i = 0; i < 8; ++i) {
            boundaries.add(new Boundary(width / 8 * i, 0, width / 8, height / 8, boundaryColor));
        }

        // bottom walls
        for (int i = 0; i < 8; ++i) {
            boundaries.add(new Boundary(width / 8 * i, height - height / 8, width / 8, height / 8, boundaryColor));
        }

        // left walls
        for (int i = 0; i < 8; ++i) {
            boundaries.add(new Boundary(0, height / 8 * i, width / 8, height / 8, boundaryColor));
        }

        // right walls
        for (int i = 0; i < 8; ++i) {
            boundaries.add(new Boundary(width - width / 8, height / 8 * i, width / 8, height / 8, boundaryColor));
        }

        boundaries.add(new Boundary(width / 8 * 5, height / 8 * 2, width / 8, height / 8, boundaryColor));
        boundaries.add(new Boundary(width / 8 * 5, height / 8 * 1, width / 8, height / 8, boundaryColor));
        for (int i = 1; i < 5; ++i) {
            boundaries.add(new Boundary(width / 8 * i, height / 8 * 5, width / 8, height / 8, boundaryColor));
        }

        for (int i = 2; i < 4; ++i) {
            boundaries.add(new Boundary(width / 8 * 2, height / 8 * i, width / 8, height / 8, boundaryColor));
        }

        boundaries.add(new Boundary(width / 8 * 6, height / 8 * 6, width / 8, height / 8, boundaryColor));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2 = (Graphics2D) g;

        g2.setColor(lineColor);
        for (int i = 0; i < 8; ++i) {
            g2.drawLine(width / 8 * i, 0, width / 8 * i, height);
        }

        for (int i = 0; i < 8; ++i) {
            g2.drawLine(0, height / 8 * i, width, height / 8 * i);
        }

        for (var b: boundaries) {
            b.render(g2);
        }

        // translate happens at end of function. Make last render
        player.render(g2);
    }

    public void forward() {
        boolean hitwall = false;
        Vertex v = player.getV();
        int x = (int)v.getX() + player.getX();
        int y = (int)v.getY() + player.getY();
        for (var b: boundaries) {
            if (player.getBoundary(
                player.getX() + (x - player.getX()) / 6, 
                player.getY() + (y - player.getY()) / 6
                ).intersects(b.getBoundary())) {
                hitwall = true;
            }
        }
        if (!hitwall) {
            player.setX(player.getX() + (x - player.getX()) / 6);
            player.setY(player.getY() + (y - player.getY()) / 6);
        }
    }

    public void down() {
        boolean hitwall = false;
        Vertex v = player.getV();
        int x = (int)v.getX() + player.getX();
        int y = (int)v.getY() + player.getY();
        for (var b: boundaries) {
            if (player.getBoundary(
                player.getX() - (x - player.getX()) / 6,
                player.getY() - (y - player.getY()) / 6
                ).intersects(b.getBoundary())) {
                hitwall = true;
            }
        }
        if (!hitwall) {
            player.setX(player.getX() - (x - player.getX()) / 6);
            player.setY(player.getY() - (y - player.getY()) / 6);
        }
    }

    public void left() {
        player.rotateZ(-1);
    }

    public void right() {
        player.rotateZ(1);
    }
}
