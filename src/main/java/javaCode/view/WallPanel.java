package javaCode.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class WallPanel extends JPanel {
    public WallPanel() {
        setVisible(false);
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        try {
            File file = new File(getClass().getResource("/brickWall2.jpg").getPath());
            image = new BufferedImage(200, 600, BufferedImage.TYPE_INT_RGB);
            image = ImageIO.read(file);
        } catch (Exception e) {
            System.err.println("Error while reading the image");
        }
        g.drawImage(image, 0, 0, null);
    }
}
