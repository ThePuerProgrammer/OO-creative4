package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RaycastGamePanel {

    protected final int HEIGHT = 500;
    protected final int WIDTH = 500;
    private final Dimension gameView = new Dimension(WIDTH, HEIGHT); 

    private JFrame window;

    public RaycastGamePanel(JFrame window) {
        this.window = window;
    }

    public void init() {

        // initialize container, panels, and canvases
        Container contentPane = window.getContentPane();
        JPanel panel2D = new JPanel();
        JPanel panel3D = new JPanel();
        JPanel spacer = new JPanel();
        RaycastCanvas2D canvas2D = new RaycastCanvas2D(this);
        RaycastCanvas3D canvas3D = new RaycastCanvas3D(this);

        // set pref sizes
        panel2D.setPreferredSize(gameView);
        panel3D.setPreferredSize(gameView);
        spacer.setPreferredSize(new Dimension(50, HEIGHT));

        // add canvas to panels and panels to container
        panel2D.add(canvas2D);
        panel3D.add(canvas3D);
        contentPane.add(BorderLayout.WEST, panel2D);
        contentPane.add(BorderLayout.EAST, panel3D);
        contentPane.add(BorderLayout.CENTER, spacer);

        // set background colors
        panel2D.setBackground(Color.BLACK);
        panel3D.setBackground(Color.BLACK);
        spacer.setBackground(Color.DARK_GRAY);
    }
}
