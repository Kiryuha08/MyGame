import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
    int Time;
    BufferedImage BGImage;
    BufferedImage BGImage1;
    BufferedImage image;
    double degrees;
    int HeroIsAttack;
    //int sx; // расстояние от x до xd
    //int sy; // расстояние от y до yd


    public Hero(int x, int y) throws IOException {
        this.BGImage = ImageIO.read(new File("data\\HeroBasic1.png"));
        this.BGImage1 = ImageIO.read(new File("data\\HeroKill1.png"));
        this.image = this.BGImage;

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


    public class AudioPlayer {

        public static void playSound(String filePath) {
            try {
                File soundFile = new File(filePath);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }


    void wallInteraction(Walls wall){
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



    //void paint(Graphics g) {
    //    g.setColor(Color.gray);
    //    if (YouDead != 1){
    //        if (HeroIsAttack == 1){
    //            g.drawImage(rotateImage(this.BGImage1, this.degrees), (int)this.x-150, (int)this.y-WiHgh/2-130, null);
    //
    //        }
    //        else {
    //            g.drawImage(rotateImage(this.BGImage, this.degrees), (int) this.x - 150, (int) this.y - WiHgh / 2 - 130, null);
    //        }
    //        g.fillRect((int)this.x-WiHgh/2, (int)this.y-WiHgh/2, WiHgh, WiHgh);
    //    }
    //}


    void posUpdate(Room room){

        this.curTime = System.currentTimeMillis();
        if (this.prevTime == 0) {
            this.prevTime = this.curTime;

        }


        double x1, y1;
        x1 = (int)this.x + Math.signum(this.xd - (int)this.x) * vx * ((this.curTime - this.prevTime)/4);
        y1 = (int)this.y + Math.signum(this.yd - (int)this.y) * vy * ((this.curTime - this.prevTime)/4);

        if (this.x - this.xd == 0 && this.y - this.yd > 0)
            this.degrees = 0;
        if (this.x - this.xd < 0 && this.y - this.yd > 0)
            this.degrees = Math.PI/4;
        if (this.x - this.xd < 0 && this.y - this.yd == 0)
            this.degrees = Math.PI/2;
        if (this.x - this.xd < 0 && this.y - this.yd < 0)
            this.degrees = 3*Math.PI/4;;
        if (this.x - this.xd == 0 && this.y - this.yd < 0)
            this.degrees = Math.PI;
        if (this.x - this.xd > 0 && this.y - this.yd < 0)
            this.degrees = 5*Math.PI/4;
        if (this.x - this.xd > 0 && this.y - this.yd == 0)
            this.degrees = 3*Math.PI/2;
        if (this.x - this.xd > 0 && this.y - this.yd > 0)
            this.degrees = 7*Math.PI/4;



        if ((room.pointCheck((int)((int)x1 + Math.signum(this.xd - (int)this.x) * WiHgh / 2), (int)((int)y1 + Math.signum(this.yd - (int)this.y) * WiHgh / 2)) == 1)
                && (room.pointCheck((int)(x1 - Math.signum(this.xd - this.x) * WiHgh / 2), (int)(y1 + Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)
                && (room.pointCheck((int)(x1 + Math.signum(this.xd - this.x) * WiHgh / 2), (int)(y1 - Math.signum(this.yd - this.y) * WiHgh / 2)) == 1)) {

            this.x = x1;
            this.y = y1;
        }
        else {
            if ((room.pointCheck((int)(this.x - WiHgh/2), (int)(y1 + Math.signum(this.yd - (int)this.y) * WiHgh / 2)) == 1)
                    && (room.pointCheck((int)(this.x + WiHgh/2), (int)(y1 + Math.signum(this.yd - (int)this.y) * WiHgh / 2)) == 1)
            ) {
                this.y = y1;

            }
            if ((room.pointCheck((int)(x1 + Math.signum(this.xd - (int)this.x) * WiHgh / 2), (int)(this.y - WiHgh/2)) == 1)
                    && (room.pointCheck((int)(x1 + Math.signum(this.xd - (int)this.x) * WiHgh / 2), (int)(this.y + WiHgh/2)) == 1)
            ) {
                this.x = x1;

            }

        }

        for (int i = (int) this.x - this.WiHgh / 2; i <= (int) this.x + this.WiHgh / 2; i++) {
            for (int j = (int) this.y - this.WiHgh / 2; j <= (int) this.y + this.WiHgh / 2; j++) {
                if (i >= 0 && i < 800 && j >= 0 && j < 600) {
                    if (room.RoomPoints[i][j] == 4) {
                        this.HeroIsAttack = 1;
                        AudioPlayer.playSound("Hotline Chuvashia\\data\\brue.mp3");
                        // Запускаем таймер для возврата изображения через 500 мс
                        //    new Timer().schedule(new TimerTask() {
                        //        @Override
                        //        public void run() {
                        //            HeroIsAttack = 0;
                        //        }
                        //    }, 500);
                    }
                    //System.out.println(HeroIsAttack);
                    room.RoomPoints[i][j] = 2;
                }
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


    }

    void paint(Graphics g) {
        g.setColor(Color.gray);
        if (YouDead != 1) {
            //System.out.println(HeroIsAttack);
            if (HeroIsAttack == 1) {
                System.out.println("+________________+");
                this.image = this.BGImage1;
                this.Time = 0;
            }
            System.out.println(this.degrees);

            //g.fillRect((int) this.x - WiHgh / 2, (int) this.y - WiHgh / 2, WiHgh, WiHgh);
            g.drawImage(rotateImage(this.image, this.degrees), (int) this.x - WiHgh / 2, (int) this.y - WiHgh / 2, null);
            if (this.Time / 500 < 1) {
                this.Time += (this.curTime - this.prevTime);
            } else {
                this.image = BGImage;
            }
        }

        this.prevTime = this.curTime;
    }






    //void HeroAttack(Room room){
    //    if(this.HeroIsAttack == 1){
    //        System.out.println("hui");
    //        this.HeroIsAttack = 0;
//
    //    }
    //    //System.out.println(HeroIsAttack);
    //}

}