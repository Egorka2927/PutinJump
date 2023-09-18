package javaCode.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PlatformPanel extends JPanel {
    private int x;
    private int y;
    public PlatformPanel (int x, int y) {
        this.x = x;
        this.y = y;
        setOpaque(false);
        setBounds(x, y, 72, 21);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        try {
            File file = new File("C:\\Users\\fea00\\IdeaProjects\\summer2023\\putinJump\\src\\main\\resources\\platform2.png");
            image = new BufferedImage(72, 21, BufferedImage.TYPE_INT_RGB);
            image = ImageIO.read(file);
        } catch (Exception e) {
            System.err.println("Error while reading the image");
        }
        g.drawImage(image, 0, 0, null);
    }
}
