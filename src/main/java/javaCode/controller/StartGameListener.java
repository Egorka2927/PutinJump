package javaCode.controller;

import javaCode.model.Map;
import javaCode.view.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameListener implements ActionListener {
    private Frame frame;
    private Map map;

    public StartGameListener(Map map, Frame frame) {
        this.map = map;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getIntroPanel().setVisible(false);
        frame.getIntroPanel().getClip().stop();
        map.getThread().start();
        frame.getMainPanel().setVisible(true);
        frame.getLeftWallPanel().setVisible(true);
        frame.getRightWallPanel().setVisible(true);
    }
}
