package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RaycastGamePanel implements Runnable {

    protected final int HEIGHT = 600;
    protected final int WIDTH = 600;
    private final Dimension gameView = new Dimension(WIDTH, HEIGHT); 

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
        RaycastCanvas2D canvas2D = new RaycastCanvas2D(this);
        RaycastCanvas3D canvas3D = new RaycastCanvas3D(this);

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
        surface.setBackground(Color.DARK_GRAY);
        panel2D.setBackground(Color.DARK_GRAY);
        panel3D.setBackground(Color.DARK_GRAY);
        spacer.setBackground(Color.DARK_GRAY);

        canvas2D.repaint();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
}
