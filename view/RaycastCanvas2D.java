package view;

import javax.swing.JPanel;

import model.Boundary;
import model.Player;
import model.Ray;
import model.Vertex;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;

public class RaycastCanvas2D extends JPanel {
    private static final long serialVersionUID = 1L;

    private final double PI = 3.141592654;
    private RaycastGamePanel raycastGamePanel;
    private Graphics2D g2;
    private Player player;
    private int width;
    private int height;
    private ArrayList<Boundary> boundaries = new ArrayList<>();
    private ArrayList<Ray> rays = new ArrayList<>();
    private final Color boundaryColor = new Color(5, 5, 5);
    private final Color lineColor = new Color(220,220,220);
    private final int PLAYER_SPEED = 2;

    // grid lines for raycasting
    // horizonal 
    private int xGridA;
    private int xGridB;
    private int xGridC;
    private int xGridD;
    private int xGridE;
    private int xGridF;
    private int xGridG;
    private int[] horizonal = {
        xGridA, xGridB, xGridC, xGridD, xGridE, xGridF, xGridG
    };
    // vertical
    private int yGridA;
    private int yGridB;
    private int yGridC;
    private int yGridD;
    private int yGridE;
    private int yGridF;
    private int yGridG;
    private int[] vertical = {
        yGridA, yGridB, yGridC, yGridD, yGridE, yGridF, yGridG
    };

    private boolean[][] map = {
        { true,  true,  true,  true,  true,  true,  true,  true},
        { true, false, false, false, false, false, false,  true},
        { true, false,  true, false, false,  true, false,  true},
        { true, false,  true, false, false, false, false,  true},
        { true, false, false, false, false, false, false,  true},
        { true,  true,  true,  true,  true, false, false,  true},
        { true, false, false, false, false, false,  true,  true},
        { true,  true,  true,  true,  true,  true,  true,  true}
    };
    
    public RaycastCanvas2D(RaycastGamePanel raycastGamePanel) {
        this.raycastGamePanel = raycastGamePanel;
        setBackground(Color.DARK_GRAY);
        width = raycastGamePanel.WIDTH;
        height = raycastGamePanel.HEIGHT;

        // horizonal 
        xGridA = width / 8;
        xGridB = width / 8 * 2;
        xGridC = width / 8 * 3;
        xGridD = width / 8 * 4;
        xGridE = width / 8 * 5;
        xGridF = width / 8 * 6;
        xGridG = width / 8 * 7;
        

        // vertical
        yGridA = height / 8;
        yGridB = height / 8 * 2;
        yGridC = height / 8 * 3;
        yGridD = height / 8 * 4;
        yGridE = height / 8 * 5;
        yGridF = height / 8 * 6;
        yGridG = height / 8 * 7;

        setPreferredSize(new Dimension(width, height));
        player = new Player();
        castRays();
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
        // boundaries.add(new Boundary(width / 8 * 5, height / 8 * 1, width / 8, height / 8, boundaryColor));
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
        for (int i = 1; i < 8; ++i) {
            g2.drawLine(width / 8 * i, 0, width / 8 * i, height);
        }

        for (int i = 1; i < 8; ++i) {
            g2.drawLine(0, height / 8 * i, width, height / 8 * i);
        }

        for (var b: boundaries) {
            b.render(g2);
        }

        for (var r: rays) {
            r.render(g2, player.getX() + 10, player.getY() + 10, (int)(r.getX() + player.getX() + 10), (int)(r.getY() + player.getY() + 10));
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
                player.getX() + (x - player.getX()) / 3, 
                player.getY() + (y - player.getY()) / 3
                ).intersects(b.getBoundary())) {
                hitwall = true;
            }
        }
        if (!hitwall) {
            player.setX(player.getX() + (x - player.getX()) / 3);
            player.setY(player.getY() + (y - player.getY()) / 3);
        }
        castRays();
    }

    public void down() {
        boolean hitwall = false;
        Vertex v = player.getV();
        int x = (int)v.getX() + player.getX();
        int y = (int)v.getY() + player.getY();
        for (var b: boundaries) {
            if (player.getBoundary(
                player.getX() - (x - player.getX()) / 3,
                player.getY() - (y - player.getY()) / 3
                ).intersects(b.getBoundary())) {
                hitwall = true;
            }
        }
        if (!hitwall) {
            player.setX(player.getX() - (x - player.getX()) / 3);
            player.setY(player.getY() - (y - player.getY()) / 3);
        }
        castRays();
    }

    public void left() {
        player.getV().rotateZ(-1);
        player.getVL().rotateZ(-1);
        player.getVR().rotateZ(-1);
        for (var r: rays) {
            r.rotateZ(-1);
        }
        castRays();
    }

    public void strafeLeft() {
        boolean hitwall = false;
        Vertex v = player.getVL();
        int x = (int)v.getX() + player.getX();
        int y = (int)v.getY() + player.getY();
        for (var b: boundaries) {
            if (player.getBoundary(
                player.getX() + (x - player.getX()) / 3, 
                player.getY() + (y - player.getY()) / 3
                ).intersects(b.getBoundary())) {
                hitwall = true;
            }
        }
        if (!hitwall) {
            player.setX(player.getX() + (x - player.getX()) / 3);
            player.setY(player.getY() + (y - player.getY()) / 3);
        }
        castRays();
    }

    public void strafeRight() {
        boolean hitwall = false;
        Vertex v = player.getVR();
        int x = (int)v.getX() + player.getX();
        int y = (int)v.getY() + player.getY();
        for (var b: boundaries) {
            if (player.getBoundary(
                player.getX() + (x - player.getX()) / 3, 
                player.getY() + (y - player.getY()) / 3
                ).intersects(b.getBoundary())) {
                hitwall = true;
            }
        }
        if (!hitwall) {
            player.setX(player.getX() + (x - player.getX()) / 3);
            player.setY(player.getY() + (y - player.getY()) / 3);
        }
        castRays();
    }

    public void right() {
        player.getV().rotateZ(1);
        player.getVL().rotateZ(1);
        player.getVR().rotateZ(1);
        for (var r: rays) {
            r.rotateZ(1);
        }
        castRays();
    }

    public void castRays() {
        final double fov = 30;
        // generate array
        if (rays.size() == 0) {
            for (double i = fov; i >= 0; i -= .1) {
                Vertex v = new Vertex(player.getV());
                v.rotateZ(-1, i);
                rays.add(new Ray(v));
            }
            for (double i = .2; i <= fov; i += .1) {
                Vertex v = new Vertex(player.getV());
                v.rotateZ(1, i);
                rays.add(new Ray(v));
            }
        } else {
            double i = fov, j = .1;
            for (var r: rays) {
                if (i < 0) {
                    r.setX(player.getV().getX());
                    r.setY(player.getV().getY());
                    r.rotateZ(1, j);
                    j += .1;
                } else {
                    r.setX(player.getV().getX());
                    r.setY(player.getV().getY());
                    r.rotateZ(-1, i);
                    i -= .1;
                }
            }
        }
        // cast rays
        for (var r: rays) {
            double dx = r.getX() / 20;
            double dy = r.getY() / 20;
            while ((dx != 0 || dy != 0) && !insideBoundary(r.getX() + player.getX() + 10, r.getY() + player.getY() + 10)) {
                r.castRay(dx, dy);
            }

            r.castRay(-dx, -dy);
            r.setDistance(
                Math.sqrt(
                    Math.pow((r.getX() + player.getX() + 10) - player.getX() + 10, 2) +
                    Math.pow((r.getY() + player.getY() + 10) - player.getY() + 10, 2)
                )
                * Math.cos(player.getV().getTheta() - r.getTheta())
            );
        }
    }

    private boolean insideBoundary(double x, double y) {

        if (x < 0 || x > width || y < 0 || y > height) return true;

        int ySwitch = 7;
        if (y <= yGridG) ySwitch = 6;
        if (y <= yGridF) ySwitch = 5;
        if (y <= yGridE) ySwitch = 4;
        if (y <= yGridD) ySwitch = 3;
        if (y <= yGridC) ySwitch = 2;
        if (y <= yGridB) ySwitch = 1;
        if (y <= yGridA) ySwitch = 0;

        switch (ySwitch) {
            case 0:
                if (x >= 0      && x <= xGridA) return map[0][0];
                if (x >= xGridA && x <= xGridB) return map[0][1];
                if (x >= xGridB && x <= xGridC) return map[0][2];
                if (x >= xGridC && x <= xGridD) return map[0][3];
                if (x >= xGridD && x <= xGridE) return map[0][4];
                if (x >= xGridE && x <= xGridF) return map[0][5];
                if (x >= xGridF && x <= xGridG) return map[0][6];
                if (x >= xGridG && x <=  width) return map[0][7];
                break;

            case 1:
                if (x >= 0      && x <= xGridA) return map[1][0];
                if (x >= xGridA && x <= xGridB) return map[1][1];
                if (x >= xGridB && x <= xGridC) return map[1][2];
                if (x >= xGridC && x <= xGridD) return map[1][3];
                if (x >= xGridD && x <= xGridE) return map[1][4];
                if (x >= xGridE && x <= xGridF) return map[1][5];
                if (x >= xGridF && x <= xGridG) return map[1][6];
                if (x >= xGridG && x <=  width) return map[1][7];
                break;

            case 2:
                if (x >= 0      && x <= xGridA) return map[2][0];
                if (x >= xGridA && x <= xGridB) return map[2][1];
                if (x >= xGridB && x <= xGridC) return map[2][2];
                if (x >= xGridC && x <= xGridD) return map[2][3];
                if (x >= xGridD && x <= xGridE) return map[2][4];
                if (x >= xGridE && x <= xGridF) return map[2][5];
                if (x >= xGridF && x <= xGridG) return map[2][6];
                if (x >= xGridG && x <=  width) return map[2][7];
                break;

            case 3:
                if (x >= 0      && x <= xGridA) return map[3][0];
                if (x >= xGridA && x <= xGridB) return map[3][1];
                if (x >= xGridB && x <= xGridC) return map[3][2];
                if (x >= xGridC && x <= xGridD) return map[3][3];
                if (x >= xGridD && x <= xGridE) return map[3][4];
                if (x >= xGridE && x <= xGridF) return map[3][5];
                if (x >= xGridF && x <= xGridG) return map[3][6];
                if (x >= xGridG && x <=  width) return map[3][7];
                break;

            case 4:
                if (x >= 0      && x <= xGridA) return map[4][0];
                if (x >= xGridA && x <= xGridB) return map[4][1];
                if (x >= xGridB && x <= xGridC) return map[4][2];
                if (x >= xGridC && x <= xGridD) return map[4][3];
                if (x >= xGridD && x <= xGridE) return map[4][4];
                if (x >= xGridE && x <= xGridF) return map[4][5];
                if (x >= xGridF && x <= xGridG) return map[4][6];
                if (x >= xGridG && x <=  width) return map[4][7];
                break;

            case 5:
                if (x >= 0      && x <= xGridA) return map[5][0];
                if (x >= xGridA && x <= xGridB) return map[5][1];
                if (x >= xGridB && x <= xGridC) return map[5][2];
                if (x >= xGridC && x <= xGridD) return map[5][3];
                if (x >= xGridD && x <= xGridE) return map[5][4];
                if (x >= xGridE && x <= xGridF) return map[5][5];
                if (x >= xGridF && x <= xGridG) return map[5][6];
                if (x >= xGridG && x <=  width) return map[5][7];
                break;

            case 6:
                if (x >= 0      && x <= xGridA) return map[6][0];
                if (x >= xGridA && x <= xGridB) return map[6][1];
                if (x >= xGridB && x <= xGridC) return map[6][2];
                if (x >= xGridC && x <= xGridD) return map[6][3];
                if (x >= xGridD && x <= xGridE) return map[6][4];
                if (x >= xGridE && x <= xGridF) return map[6][5];
                if (x >= xGridF && x <= xGridG) return map[6][6];
                if (x >= xGridG && x <=  width) return map[6][7];
                break;

            case 7:
                if (x >= 0      && x <= xGridA) return map[7][0];
                if (x >= xGridA && x <= xGridB) return map[7][1];
                if (x >= xGridB && x <= xGridC) return map[7][2];
                if (x >= xGridC && x <= xGridD) return map[7][3];
                if (x >= xGridD && x <= xGridE) return map[7][4];
                if (x >= xGridE && x <= xGridF) return map[7][5];
                if (x >= xGridF && x <= xGridG) return map[7][6];
                if (x >= xGridG && x <=  width) return map[7][7];
                break;
                
            default: break;
        }
        return false;
    }

    public ArrayList<Ray> getRays() {
        return rays;
    }
}
