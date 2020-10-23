package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.RaycastCanvas3D;
import view.RaycastGamePanel;

public class PlayerListener implements KeyListener {
    private boolean strafe = false;

    private RaycastGamePanel raycastGamePanel;
    public PlayerListener(RaycastGamePanel raycastGamePanel) {
        this.raycastGamePanel = raycastGamePanel;
    }
    @Override
public void keyTyped(KeyEvent e) {/**/}

    @Override
    public void keyPressed(KeyEvent e) {
        if (RaycastCanvas3D.wasd != "") RaycastCanvas3D.wasd = "";
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_SHIFT:
                strafe = true;
                raycastGamePanel.setLeft(false);
                raycastGamePanel.setRight(false);
                break;
            case KeyEvent.VK_W:
                raycastGamePanel.setUp(true);
                break;
            case KeyEvent.VK_A:
                if (strafe) {
                    raycastGamePanel.setStrafeLeft(true);
                } else {
                    raycastGamePanel.setLeft(true);
                }
                break;
            case KeyEvent.VK_S:
                raycastGamePanel.setDown(true);
                break;
            case KeyEvent.VK_D:
                if (strafe) {
                    raycastGamePanel.setStrafeRight(true);
                } else {
                    raycastGamePanel.setRight(true);
                }
                break;
            default: break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_SHIFT:
                strafe = false;
                raycastGamePanel.setStrafeLeft(false);
                raycastGamePanel.setStrafeRight(false);
                break;
            case KeyEvent.VK_W:
                raycastGamePanel.setUp(false);
                break;
            case KeyEvent.VK_A:
                raycastGamePanel.setStrafeLeft(false);
                raycastGamePanel.setLeft(false);
                break;
            case KeyEvent.VK_S:
                raycastGamePanel.setDown(false);
                break;
            case KeyEvent.VK_D:
                raycastGamePanel.setStrafeRight(false);
                raycastGamePanel.setRight(false);
                break;
            default: break;
        }

    }
    
}
