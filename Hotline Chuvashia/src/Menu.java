import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel implements MouseListener, MouseMotionListener {

    private final BufferedImage BGImage;
    int bStartPress = 0;

    public Menu() throws IOException {
        this.BGImage = ImageIO.read(new File("data\\PlayButton1.png"));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(BGImage,100, 100, null);

        //g.setColor(Color.GREEN);
        //g.fillRect(100,100,100,100);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > 100 && e.getX() < 300 && e.getY() > 100 && e.getY() < 200 ) {
            this.bStartPress = 1;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println(e.getX() + " " + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println(e.getX() + " " + e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}