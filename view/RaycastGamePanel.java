package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.PlayerListener;

public class RaycastGamePanel implements Runnable {

    protected final int HEIGHT = 600;
    protected final int WIDTH = 600;
    private final Dimension gameView = new Dimension(WIDTH, HEIGHT); 
    private Color bg = new Color(40, 90, 80);
    private Thread thread;
    private RaycastCanvas2D canvas2D;
    private RaycastCanvas3D canvas3D;
    private PlayerListener l;
    private final int FPS = 60;
    private final int TIME = 1000 / FPS;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private enum GameState {
        STANDBY, RUNNING
    }

    GameState gameState;

    private JFrame window;

    public RaycastGamePanel(JFrame window) {
        this.window = window;
        window.setFocusable(true);
        window.requestFocus();
    }

    public void init() {

        // initialize container, panels, and canvases
        Container contentPane = window.getContentPane();
        JPanel surface = new JPanel();
        JPanel panel2D = new JPanel();
        JPanel panel3D = new JPanel();
        JPanel spacer = new JPanel();
        canvas2D = new RaycastCanvas2D(this);
        canvas3D = new RaycastCanvas3D(this);

        // set pref sizes
        surface.setPreferredSize(new Dimension(WIDTH * 2 + 100, HEIGHT + 20));
        panel2D.setPreferredSize(gameView);
        panel3D.setPreferredSize(gameView);
        spacer.setPreferredSize(new Dimension(50, HEIGHT + 5));

        // add canvas to panels and panels to container
        panel2D.add(canvas2D);
        panel3D.add(canvas3D);
        surface.add(BorderLayout.WEST, panel2D);
        surface.add(BorderLayout.CENTER, spacer);
        surface.add(BorderLayout.EAST, panel3D);
        contentPane.add(surface);

        // set background colors
        surface.setBackground(bg);
        panel2D.setBackground(bg);
        panel3D.setBackground(bg);
        spacer.setBackground(bg);

        l = new PlayerListener(this);
        window.addKeyListener(l);

        canvas2D.repaint();
        start();
    }

    public void start() {
        gameState = GameState.RUNNING;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (gameState == GameState.RUNNING) {
            // To aim for consistent FPS
            long nano = System.nanoTime();
            tick();
            canvas2D.repaint();
            canvas3D.repaint();
            long updated = System.nanoTime() - nano;
            long buffer = TIME - updated / 1_000_000;
            if (buffer <= 0) {
                buffer = 5;
            }
            try {
                Thread.sleep(buffer);
            } catch (Exception e) {
                System.out.println("Caught Exception - Thread.sleep(buffer)");
                System.exit(1);
            }
        }
    }

    public void tick() {
        if (up) {
            canvas2D.up();
        } else if (down) {
            canvas2D.down();
        }

        if (left) {
            canvas2D.left();
        } else if (right) {
            canvas2D.right();
        }
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
