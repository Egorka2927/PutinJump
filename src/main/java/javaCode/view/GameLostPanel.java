package javaCode.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameLostPanel extends JPanel {
    private JButton restartButton;
    public GameLostPanel() {
        setBounds(200, 0, 500, 600);
        restartButton = makeRestartButton(150, 450, 200, 100, "Restart");
        add(restartButton);
        setLayout(null);
        setVisible(false);
    }

    public JButton makeRestartButton(int x, int y, int width, int height, String text) {
        JButton restartButton = new JButton();
        restartButton.setBounds(x, y, width, height);
        restartButton.setFocusable(false);
        restartButton.setText(text);
        Font font = new Font("Dialog", Font.PLAIN, 30);
        restartButton.setFont(font);
        restartButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        restartButton.setBackground(Color.yellow);
        return restartButton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        try {
            File file = new File(getClass().getResource("/youLostBackground.png").getPath());
            image = new BufferedImage(500, 600, BufferedImage.TYPE_INT_RGB);
            image = ImageIO.read(file);
        } catch (Exception e) {
            System.err.println("Error while reading the image");
        }
        g.drawImage(image, 0, 0, null);
    }

    public JButton getRestartButton() {
        return restartButton;
    }
}
