package javaCode.controller;

import javaCode.model.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PutinMovementListener implements KeyListener {
    private Map map;
    public PutinMovementListener(Map map) {
        this.map = map;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            map.getPutin().setLeft(true);
        } else if (code == 68) {
            map.getPutin().setRight(true);
        }
        map.updatePutinPosition();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            map.getPutin().setLeft(false);
        } else if (code == 68) {
            map.getPutin().setRight(false);
        }
        map.updatePutinPosition();
    }
}
