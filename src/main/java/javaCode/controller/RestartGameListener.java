package javaCode.controller;

import javaCode.model.Map;
import javaCode.model.MyThread;
import javaCode.model.Platform;
import javaCode.view.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartGameListener implements ActionListener {
    private Frame frame;
    private Map map;
    public RestartGameListener(Map map, Frame frame) {
        this.map = map;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        map.getPutin().setAlive(true);
        frame.getGameLostPanel().setVisible(false);
        frame.getMainPanel().setVisible(true);
        map.setThread(new MyThread(map));
        for (int i = 9; i >= 0; i--) {
            map.getPlatforms().remove(i);
        }
        map.createPlatforms();
        map.getThread().start();
    }
}
