package javaCode.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PutinPanel extends JPanel {
    private double x;
    private double y;
    private boolean sitting;

    public PutinPanel(double x, double y) {
        this.x = x;
        this.y = y;
        setOpaque(false);
        setBounds((int)x, (int)y, 50, 100);
    }

    public void setNewBounds(int x, int y) {
        setBounds(x, y, 50, 100);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        try {
            File file;
            if (sitting) {
                file = new File("C:\\Users\\fea00\\IdeaProjects\\summer2023\\putinJump\\src\\main\\resources\\littlePutinSitting.png");
            } else {
                file = new File("C:\\Users\\fea00\\IdeaProjects\\summer2023\\putinJump\\src\\main\\resources\\littlePutin.png");
            }
            image = new BufferedImage(50, 100, BufferedImage.TYPE_INT_RGB);
            image = ImageIO.read(file);
        } catch (Exception e) {
            System.err.println("Error while reading the image");
        }
        g.drawImage(image, 0, 0, null);
    }

    public void setSitting(boolean sitting) {
        this.sitting = sitting;
    }
}
