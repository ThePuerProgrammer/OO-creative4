package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import view.RaycastGamePanel;

public class PlayerListener implements KeyListener {

    private RaycastGamePanel raycastGamePanel;
    public PlayerListener(RaycastGamePanel raycastGamePanel) {
        this.raycastGamePanel = raycastGamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                raycastGamePanel.setUp(true);
                break;
            case KeyEvent.VK_A:
                raycastGamePanel.setLeft(true);
                break;
            case KeyEvent.VK_S:
                raycastGamePanel.setDown(true);
                break;
            case KeyEvent.VK_D:
                raycastGamePanel.setRight(true);
                break;
            default: break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                raycastGamePanel.setUp(false);
                break;
            case KeyEvent.VK_A:
                raycastGamePanel.setLeft(false);
                break;
            case KeyEvent.VK_S:
                raycastGamePanel.setDown(false);
                break;
            case KeyEvent.VK_D:
                raycastGamePanel.setRight(false);
                break;
            default: break;
        }

    }
    
}
