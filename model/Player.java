package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.BasicStroke;

public class Player {
    private int x;
    private int y;
    private Vertex v;
    private Vertex vL;
    private Vertex vR;
    private Color c;

    public Player() {
        x = 290;
        y = 290;
        v = new Vertex(  0, -10, 0);
        vL = new Vertex(-10,   0, 0);
        vR = new Vertex( 10,   0, 0);
        c = new Color(255,  0, 0);
    }

    public void render(Graphics2D g2) {
        g2.setColor(c);
        g2.fillOval(x, y, 20, 20);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2.5f));
        g2.drawOval(x, y, 20, 20);
        g2.translate(x + 10, y + 10);
        g2.drawLine(0, 0, (int)(v.getX()), (int)(v.getY()));
    }

    public Rectangle getBoundary(int x2, int y2) {
        return new Rectangle(x2, y2, 20, 20);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vertex getV() {
        return v;
    }

    public Vertex getVL() {
        return vL;
    }

    public Vertex getVR() {
        return vR;
    }
}