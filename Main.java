import javax.swing.JFrame;

import view.RaycastGamePanel;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocation(200, 200);

        RaycastGamePanel raycastGamePanel = new RaycastGamePanel(window);
        raycastGamePanel.init();
        window.pack();
        window.setVisible(true);
    }
}