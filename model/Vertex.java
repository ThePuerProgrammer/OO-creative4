package model;

public class Vertex {
    private double x;
    private double y;
    private double z;
    private double heading;
    private double cosTheta;
    private double sinTheta;
    private double theta;
    private double[][] zRotationMatrix;
    private int headingSpeed = 4;

    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void updateVertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void rotateZ(int direction) {
        updateHeading(direction);
        rotate(zRotationMatrix);
        theta = Math.atan(y / x);
    }

    private void rotate(double[][] matrix) {
        double[][] result = VMath.matMul2D(matrix, VMath.convertVertexToMatrix(this));
        this.updateVertex(result[0][0], result[1][0], result[2][0]);
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


    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getTheta() {
        return theta;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
