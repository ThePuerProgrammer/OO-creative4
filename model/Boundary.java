package model;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

public class Boundary {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Boundary(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(x + 2, y + 2, width - 3, height - 3);
    }

    public Rectangle getBoundary() {
        return new Rectangle(x, y, width, height);
    }

}
