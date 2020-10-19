package model;

// Math class for vertex manipulation with static methods
public final class VMath {

    public VMath() {}

    public static double[][] matMul2D(double[][] matrix, double[][] vertices) {
        double[][] result = new double[vertices.length][vertices[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vertices[0].length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    result[i][j] += matrix[i][k] * vertices[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] convertVertexToMatrix(Vertex a) {
        double[][] vertex = {
            {a.getX()},
            {a.getY()},
            {a.getZ()}
        };
        return vertex;
    }
}