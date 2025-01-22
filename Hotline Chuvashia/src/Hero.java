import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    //int sx; // расстояние от x до xd
    //int sy; // расстояние от y до yd


    public Hero(int x, int y) throws IOException {
        this.BGImage = ImageIO.read(new File("data\\png-clipart-protective-gear-in-sports-top-view-people-sport-black.png"));
        this.x = x;
        this.y = y;
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

    void posUpdate(Room room){

        this.curTime = System.currentTimeMillis();
        if (this.prevTime == 0) {
            this.prevTime = this.curTime;

        }

        double x1, y1;
        x1 = this.x + Math.signum(this.xd - this.x) * vx * ((this.curTime - this.prevTime)/4);
        y1 = this.y + Math.signum(this.yd - this.y) * vy * ((this.curTime - this.prevTime)/4);

        // Переделать расчет

        if ((room.pointCheck((int)(x1 + Math.signum(this.xd - this.x) * WiHgh / 2), (int)(y1 + Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)
            && (room.pointCheck((int)(x1 - Math.signum(this.xd - this.x) * WiHgh / 2), (int)(y1 + Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)
            && (room.pointCheck((int)(x1 + Math.signum(this.xd - this.x) * WiHgh / 2), (int)(y1 - Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)
        )
        {
            this.x = x1;
            this.y = y1;
        } else {
            if ((room.pointCheck((int)(this.x - WiHgh/2), (int)(y1 + Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)
                && (room.pointCheck((int)(this.x + WiHgh/2), (int)(y1 + Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)
            ) {
                this.y = y1;
            }
            if ((room.pointCheck((int)(x1 + Math.signum(this.xd - this.x) * WiHgh / 2), (int)(this.y - WiHgh/2)) == 1)
                && (room.pointCheck((int)(x1 + Math.signum(this.xd - this.x) * WiHgh / 2), (int)(this.y + WiHgh/2)) == 1)
            ) {
                this.x = x1;
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

    void paint(Graphics g) {
        if (YouDead != 1){
            g.drawImage(BGImage, (int)this.x-150, (int)this.y-WiHgh/2-130, null);
            g.fillRect((int)this.x-WiHgh/2, (int)this.y-WiHgh/2, WiHgh, WiHgh);
        }
    }
}