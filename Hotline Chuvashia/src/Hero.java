import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Hero {
    double x;
    double y;
    int vx = 1;
    int vy = 1;
    int xd;
    int yd;
    int WiHgh = 50;
    int YouDead;
    int NextToWall = 0;
    long curTime;
    long prevTime = 0;
    BufferedImage BGImage;
    double degrees;
    //int sx; // расстояние от x до xd
    //int sy; // расстояние от y до yd


    public Hero(int x, int y) throws IOException {
        this.BGImage = ImageIO.read(new File("data\\png-clipart-protective-gear-in-sports-top-view-people-sport-black.png"));

        this.x = x;
        this.y = y;
    }

    public static BufferedImage rotateImage(BufferedImage imageToRotate, double degrees) {
        int widthOfImage = imageToRotate.getWidth();
        int heightOfImage = imageToRotate.getHeight();
        int typeOfImage = imageToRotate.getType();

        BufferedImage newImageFromBuffer = new BufferedImage(widthOfImage, heightOfImage, typeOfImage);

        Graphics2D graphics2D = newImageFromBuffer.createGraphics();

        graphics2D.rotate(degrees, widthOfImage / 2, heightOfImage / 2);
        graphics2D.drawImage(imageToRotate, null, 0, 0);

        return newImageFromBuffer;
    }

    void wallInteraction(Walls wall){
        // TODO: заменить проверку доступности точки на room.BanPoints
        /*if(((int)this.x == wall.x + wall.wWidx) && ((int)this.y -
                this.WiHgh/2<= wall.y+ wall.wLeny && (int)this.y + this.WiHgh/2 >= wall.y)){
            this.NextToWall = 1;        //столкновение с левой стенкой
        }

        else if(((int)this.y - this.WiHgh/2 == wall.y + wall.wLeny) && ((int)this.x +
                this.WiHgh >= wall.x && (int)this.x <= wall.x + wall.wWidx)){
            this.NextToWall = 2;        // столкновение со "дном"
        }

        else if(((int)this.x + this.WiHgh == wall.x) && ((int)this.y -
                this.WiHgh/2 <= wall.y+ wall.wLeny && (int)this.y + this.WiHgh/2 >= wall.y)){
            this.NextToWall = 3;        //столкновение с правой стенкой
        }

        else if(((int)this.y + this.WiHgh/2== wall.y) && ((int)this.x +
                this.WiHgh >= wall.x && (int)this.x <= wall.x + wall.wWidx)){
            this.NextToWall = 4;        // столкновение с "верхом"
        }

        else{
            this.NextToWall = 0;
        }*/
        //System.out.println(this.NextToWall);
    }

    void paint(Graphics g) {
        g.setColor(Color.gray);
        if (YouDead != 1){
            g.drawImage(rotateImage(this.BGImage, this.degrees), (int)this.x-150, (int)this.y-WiHgh-130, null);
            g.fillRect((int)this.x-WiHgh/2, (int)this.y-WiHgh, WiHgh, WiHgh);
        }
    }


    void posUpdate(Room room){

        this.curTime = System.currentTimeMillis();
        if (this.prevTime == 0) {
            this.prevTime = this.curTime;

        }


        double x1, y1;
        x1 = this.x + Math.signum(this.xd - this.x) * vx * ((this.curTime - this.prevTime)/4);
        y1 = this.y + Math.signum(this.yd - this.y) * vy * ((this.curTime - this.prevTime)/4);


        //this.degrees = 45 * Math.signum(this.yd - this.y);


        if ((room.pointCheck((int)(x1 + Math.signum(this.xd - this.x) * WiHgh / 2), (int)(y1 + Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)
            && (room.pointCheck((int)(x1 - Math.signum(this.xd - this.x) * WiHgh / 2), (int)(y1 + Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)
            && (room.pointCheck((int)(x1 + Math.signum(this.xd - this.x) * WiHgh / 2), (int)(y1 - Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)) {

            this.x = x1;
            this.y = y1;
        }
        else {
            if ((room.pointCheck((int)(this.x - WiHgh/2), (int)(y1 + Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)
                && (room.pointCheck((int)(this.x + WiHgh/2), (int)(y1 + Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)
            ) {
                this.y = y1;
                //this.degrees = 45 * Math.signum(this.yd - this.y);
            }
            if ((room.pointCheck((int)(x1 + Math.signum(this.xd - this.x) * WiHgh / 2), (int)(this.y - WiHgh/2)) == 1)
                && (room.pointCheck((int)(x1 + Math.signum(this.xd - this.x) * WiHgh / 2), (int)(this.y + WiHgh/2)) == 1)
            ) {
                this.x = x1;
                //this.degrees = 45 * Math.signum(this.xd - this.x);
            }

        }

        for (int i = (int)this.x - this.WiHgh/2; i <= (int)this.x + this.WiHgh/2; i ++){
            for (int j = (int)this.y - this.WiHgh/2; j <= (int)this.y + this.WiHgh/2; j ++){
                if (i >= 0 && i < 800 && j >=0 && j < 600)
                    room.RoomPoints[i][j] = 2;
            }
        }


/*        if (this.x + WiHgh / 2 > this.xd && this.NextToWall != 1) {
            this.x -= vx * ((this.curTime - this.prevTime)/4);
            //System.out.println(xd);
            //System.out.println(x);
        } else if (this.x + WiHgh / 2 < this.xd && this.NextToWall != 3) {
            this.x += vx * ((this.curTime - this.prevTime)/4);
            //System.out.println(xd);
            //System.out.println(x);
        }
        if (this.x + WiHgh / 2 == this.xd || this.NextToWall % 2 == 1) {
            if (this.y + WiHgh / 2 > this.yd && this.NextToWall != 2) {
                this.y -= vy * ((this.curTime - this.prevTime)/4);
                //System.out.println(yd);
                //System.out.println(y);
            } else if (this.y + WiHgh / 2 < this.yd && this.NextToWall != 4) {
                this.y += vy * ((this.curTime - this.prevTime)/4);
                //System.out.println(yd);
                //System.out.println(y);
            }
        }*/
        this.prevTime = this.curTime;
    }

}