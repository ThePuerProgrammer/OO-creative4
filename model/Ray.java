package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Ray extends Vertex {
    private Color c = Color.GREEN;

    public Ray(double x, double y, double z) {
        super(x, y, z);
    }

    public Ray(Vertex v) {
        super(v);
    }

    public void render(Graphics2D g2, int x, int y, int rayTX, int rayTY) {
        g2.setColor(c);
        g2.setStroke(new BasicStroke(1));
        g2.drawLine(x, y, rayTX, rayTY);
    }

    public void castRay(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
