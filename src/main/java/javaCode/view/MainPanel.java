package javaCode.view;

import javaCode.controller.MapListener;
import javaCode.model.Map;
import javaCode.model.Platform;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPanel extends JPanel implements MapListener {
    private PutinPanel putinPanel;
    private GameLostPanel gameLostPanel;
    private List<PlatformPanel> platformPanels;
    private Map map;
    private JLabel countScoreLabel;
    private Clip clip;
    public MainPanel(Map map) {
        this.map = map;
        countScoreLabel = new JLabel();
        platformPanels = new ArrayList<>();
        putinPanel = new PutinPanel(map.getPutin().getX(), map.getPutin().getY());
        setBounds(200, 0, 500, 600);
        add(putinPanel);
        for (int i = 0; i < map.getPlatforms().size(); i++) {
            Platform p = map.getPlatforms().get(i);
            int x = p.getX();
            int y = p.getY();
            PlatformPanel platformPanel = new PlatformPanel(x, y);
            platformPanels.add(platformPanel);
            add(platformPanel);
        }
        setLayout(null);
        setVisible(false);
    }

    public File chooseRandomSoundEffect() {
        File file = null;
        int ranNum = new Random().nextInt(5);
        if (ranNum == 0) {
            file = new File(getClass().getResource("/spasti-ego-uje-nevozmojno.wav").getPath());
        } else if (ranNum == 1) {
            file = new File(getClass().getResource("/potomu-chto-potomu.wav").getPath());
        } else if (ranNum == 2) {
            file = new File(getClass().getResource("/ya-nikogda-ne-narushal-konstitutsii-svoey-stranyi.wav").getPath());
        } else if (ranNum == 3) {
            file = new File(getClass().getResource("/leningradskaja-ulica.wav").getPath());
        } else if (ranNum == 4) {
            file = new File(getClass().getResource("/smeh-putina.wav").getPath());
        }
        return file;
    }

    public void randomSoundEffect() {
        File file = chooseRandomSoundEffect();
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clip.start();
    }

    @Override
    public void mapUpdated() {
        if (!map.getPutin().isAlive()) {
            setVisible(false);
            gameLostPanel.setVisible(true);
            map.getThread().stop();
        }
        if (map.getScoreCount() - map.getPrevScore() == 500) {
            randomSoundEffect();
            map.setPrevScore(map.getScoreCount());
        }
        putinPanel.setNewBounds((int)map.getPutin().getX(), (int)map.getPutin().getY());
        putinPanel.setSitting(map.getPutin().isSitting());
        countScoreLabel.setText("" + map.getScoreCount());
        for (int i = 0; i < 10; i++) {
            Platform p = map.getPlatforms().get(i);
            int x = p.getX();
            int y = p.getY();
            PlatformPanel platformPanel = platformPanels.get(i);
            platformPanel.setX(x);
            platformPanel.setY(y);
            platformPanel.setBounds(platformPanel.getX(), platformPanel.getY(), 72, 21);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        try {
            File file = new File(getClass().getResource("/backgroundFinal3.png").getPath());
            image = new BufferedImage(500, 600, BufferedImage.TYPE_INT_RGB);
            image = ImageIO.read(file);
        } catch (Exception e) {
            System.err.println("Error while reading the image");
        }
        g.drawImage(image, 0, 0, null);
    }

    public void setCountScoreLabel(JLabel countScoreLabel) {
        this.countScoreLabel = countScoreLabel;
    }

    public GameLostPanel getGameLostPanel() {
        return gameLostPanel;
    }

    public void setGameLostPanel(GameLostPanel gameLostPanel) {
        this.gameLostPanel = gameLostPanel;
    }
}
