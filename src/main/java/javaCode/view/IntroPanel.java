package javaCode.view;

import javaCode.controller.ExitGameListener;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IntroPanel extends JPanel {
    private JButton startButton;
    private JButton exitButton;
    private Clip clip;

    public IntroPanel() {
        clip = null;
        createAudio();
        setBounds(0, 0, 900, 600);
        startButton = makeButton(900 / 2 - 150, 280, 300, 100, "Start the game");
        exitButton = makeButton(900 / 2 - 100, 390, 200, 100, "Exit");
        exitButton.addActionListener(new ExitGameListener());
        add(startButton);
        add(exitButton);
        setLayout(null);
    }

    public JLabel makeLabel(int x, int y, int width, int height, String text) {
        JLabel label = new JLabel();
        label.setText(text);
        label.setBounds(x, y, width, height);
        Font font = new Font("Dialog", Font.PLAIN, 80);
        label.setFont(font);
        //label.setOpaque(true);
        label.setBackground(Color.black);
        return label;
    }

    public JButton makeButton(int x, int y, int width, int height, String text) {
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
        button.setText(text);
        Font font = new Font("Dialog", Font.PLAIN, 30);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.setFont(font);
        button.setBackground(Color.white);
        return button;
    }

    public void createAudio() {
        File file = new File(getClass().getResource("/russian-federation-national-anthem.wav").getPath());
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

    public JButton getStartButton() {
        return startButton;
    }

    public Clip getClip() {
        return clip;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        try {
            File file = new File(getClass().getResource("/russianFlag2.png").getPath());
            image = new BufferedImage(900, 600, BufferedImage.TYPE_INT_RGB);
            image = ImageIO.read(file);
        } catch (Exception e) {
            System.err.println("Error while reading the image");
        }
        g.drawImage(image, 0, 0, null);
    }
}
