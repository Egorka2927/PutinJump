package javaCode.model;

import javaCode.controller.MapListener;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private List<Platform> platforms;
    private List<Enemy> enemies;
    private List<MapListener> listeners;
    private MyThread thread;
    private double dy = 0;
    private double height = 150;
    private Putin putin;
    private int scoreCount = 0;
    private int prevScore = 0;
    private Clip clip;

    public Map() {
        putin = new Putin(210, (int)height);
        thread = new MyThread(this);
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        listeners = new ArrayList<>();
        createPlatforms();
    }

    public void createPlatforms() {
        for (int i = 0; i < 10; i++) {
            int x = (int)(Math.random() * (428 + 1));
            int y = (int)(Math.random() * (560 + 1));
            Platform platform = new Platform(x, y);
            platforms.add(platform);
        }
    }

    public void updatePutinPosition() {
        double x = putin.getX();
        double y = putin.getY();
        if (putin.isRight()) {
            putin.setX(x + 7);
        } else if (putin.isLeft()) {
            putin.setX(x - 7);
        }
        dy += 0.2;
        putin.setY(y + dy);
        if (putin.getY() > 600) {
            //dy = -10;
            putin.setAlive(false);
            putin.setX(210);
            putin.setY((int)height);
            scoreCount = 0;
            prevScore = 0;
//            thread.stop();
        }
        movePlatforms();
        checkCollision();
        if (putin.getX() < -50) {
            putin.setX(490);
        } else if (putin.getX() > 500) {
            putin.setX(-30);
        }
        notifyListeners();
    }

    public void jumpingSoundEffect() {
        File file = new File("C:\\Users\\fea00\\IdeaProjects\\summer2023\\putinJump\\src\\main\\resources\\cartoon-jump-6462.wav");
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

    public void movePlatforms() {
        if (putin.getY() < height) {
            scoreCount++;
            putin.setY(height);
            putin.setSitting(false);
            for (int i = 0; i < 10; i++) {
                Platform p = platforms.get(i);
                p.setY(p.getY() - (int) dy);
                if (p.getY() > 550) {
                    p.setY(0);
                    p.setX(new Random().nextInt(428));
                }
            }
        }
    }

    public void checkCollision() {
        for (Platform p : platforms) {
            if (putin.getX() + 50 > p.getX() && putin.getX() < (p.getX() + 72) &&
                    (putin.getY() + 100) > p.getY() &&
                    (putin.getY() + 100) < p.getY() + 21 &&
                     dy > 0) {
                dy = -10;
                jumpingSoundEffect();
                putin.setSitting(true);
            }
        }
    }

    public void addListener(MapListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (MapListener listener : listeners) {
            listener.mapUpdated();
        }
    }

    public Putin getPutin() {
        return putin;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public int getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
    }

    public int getPrevScore() {
        return prevScore;
    }

    public void setPrevScore(int prevScore) {
        this.prevScore = prevScore;
    }

    public MyThread getThread() {
        return thread;
    }
    public void setThread(MyThread thread) {
        this.thread = thread;
    }
}
