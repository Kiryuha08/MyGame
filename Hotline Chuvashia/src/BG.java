import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class BG {
    BufferedImage BGImage;

    public BG() throws IOException {
        this.BGImage = ImageIO.read(new File("Hotline Chuvashia\\data\\dark-rock-wall-seamless-texture-free-105.jpg"));
    }


    void paintBG(Graphics g){

        g.drawImage(BGImage, 0, 0, null);
    }

}