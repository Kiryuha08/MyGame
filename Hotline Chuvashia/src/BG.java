import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class BG {
    BufferedImage BGImage;

    public BG(int bgindex) throws IOException {
        if (bgindex == 2) {
            this.BGImage = ImageIO.read(new File("data\\OfficeFloor.jpg"));
        }
        else if (bgindex == 3) {
            this.BGImage = ImageIO.read(new File("data\\LabaratoryFloor.jpg"));
        }
        else{
            this.BGImage = ImageIO.read(new File("data\\WarehouseFloor.jpg"));
        }
    }


    void paintBG(Graphics g){

        g.drawImage(BGImage, 0, 0, null);
    }

}