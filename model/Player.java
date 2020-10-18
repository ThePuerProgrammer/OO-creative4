package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
    private int x;
    private int y;
    private Vertex v;
    private Color c;
    private double heading;
    private double cosTheta;
    private double sinTheta;
    private double[][] zRotationMatrix;
    private int headingSpeed = 3;

    public Player() {
        x = 290;
        y = 290;
        v = new Vertex(0, - 20, 0);
        c = new Color(255, 0, 0);
    }

    public void render(Graphics2D g2) {
        g2.setColor(c);
        g2.fillOval(x, y, 20, 20);
        g2.translate(x + 10, y + 10);
        g2.drawLine(0, 0, (int)v.getX(), (int)v.getY());
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

    public void rotateZ(int direction) {
        updateHeading(direction);
        rotate(zRotationMatrix);
    }

    private void rotate(double[][] matrix) {
        double[][] result = VMath.matMul2D(matrix, VMath.convertVertexToMatrix(v));
        v.updateVertex(result[0][0], result[1][0], result[2][0]);
    }


    private void updateHeading(int direction) {
        assert(direction == -1 || direction == 1);
        if (direction == -1) {
            heading = -headingSpeed;
        } else {
            heading =  headingSpeed;
        }
        cosTheta = Math.cos(Math.toRadians(heading));
        sinTheta = Math.sin(Math.toRadians(heading));
        updateZRotationMatrix();
    }

    public void updateZRotationMatrix() {
        double[][] matrix = {
            {cosTheta, -sinTheta, 0},
            {sinTheta, cosTheta, 0},
            {       0,         0, 1}
        };
        zRotationMatrix = matrix;
    }

}