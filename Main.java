import javax.swing.JFrame;

import view.RaycastGamePanel;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Generic Raycasting Demo");
        window.setResizable(false);
        window.setLocation(70, 150);

        RaycastGamePanel raycastGamePanel = new RaycastGamePanel(window);
        raycastGamePanel.init();
        window.pack();
        window.setVisible(true);
    }
}