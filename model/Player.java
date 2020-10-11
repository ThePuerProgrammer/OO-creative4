package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
    private int x;
    private int y;
    private int viewX;
    private int viewY;
    private Color c;

    public Player() {
        x = 290;
        y = 290;
        viewX = x + 30;
        viewY = y + 10;

        c = new Color(255, 0, 0);
    }

    public void render(Graphics2D g2) {
        g2.setColor(c);
        g2.fillOval(x, y, 20, 20);
        g2.drawLine(x + 10, y + 10, viewX, viewY);
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
}