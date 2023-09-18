package javaCode.view;

import javaCode.controller.PutinMovementListener;
import javaCode.controller.RestartGameListener;
import javaCode.controller.StartGameListener;
import javaCode.model.Map;
import javaCode.model.MyThread;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private Map map;
    private IntroPanel introPanel;
    private MainPanel mainPanel;
    private WallPanel leftWallPanel;
    private WallPanel rightWallPanel;
    private GameLostPanel gameLostPanel;
    private JLabel score;
    private JLabel scoreCount;
//    private MyThread thread;

    public Frame() {
        map = new Map();
//        thread = new MyThread(map);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Putin Jump");
        setPreferredSize(new Dimension(914, 637));
        pack();
        setLocationRelativeTo(null);
        mainPanel = new MainPanel(map);
        introPanel = new IntroPanel();
        introPanel.getStartButton().addActionListener(new StartGameListener(map, this));
        leftWallPanel = new WallPanel();
        leftWallPanel.setBounds(0, 0, 200, 600);
        rightWallPanel = new WallPanel();
        rightWallPanel.setBounds(700, 0, 200, 600);
        gameLostPanel = new GameLostPanel();
        gameLostPanel.getRestartButton().addActionListener(new RestartGameListener(map, this));
        mainPanel.setGameLostPanel(gameLostPanel);
        map.addListener(mainPanel);
        score = makeScoreLabel();
        scoreCount = makeScoreCountLabel();
        mainPanel.setCountScoreLabel(scoreCount);
        leftWallPanel.add(score);
        leftWallPanel.add(scoreCount);
        add(gameLostPanel);
        add(introPanel);
        add(mainPanel);
        add(leftWallPanel);
        add(rightWallPanel);
        addKeyListener(new PutinMovementListener(map));
        setVisible(true);
        setLayout(null);
    }
    public JLabel makeScoreLabel() {
        JLabel label = new JLabel();
        Font font = new Font("Dialog", Font.PLAIN, 15);
        label.setText("RUSSIAN SOLDIERS DEAD:");
        label.setFont(font);
        label.setOpaque(true);
        label.setBackground(Color.black);
        label.setForeground(Color.yellow);
        label.setBounds(0, 200, 200, 50);
        label.setLayout(null);
        return label;
    }

    public JLabel makeScoreCountLabel() {
        JLabel label = new JLabel();
        Font font = new Font("Dialog", Font.PLAIN, 30);
        label.setText("" + map.getScoreCount());
        label.setForeground(Color.yellow);
        label.setOpaque(true);
        label.setBackground(Color.black);
        label.setFont(font);
        label.setBounds(00, 240, 200, 50);
        label.setLayout(null);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public Map getMap() {
        return map;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public IntroPanel getIntroPanel() {
        return introPanel;
    }

    public WallPanel getLeftWallPanel() {
        return leftWallPanel;
    }

    public WallPanel getRightWallPanel() {
        return rightWallPanel;
    }

    public GameLostPanel getGameLostPanel() {
        return gameLostPanel;
    }
    //    public MyThread getThread() {
//        return thread;
//    }
}
