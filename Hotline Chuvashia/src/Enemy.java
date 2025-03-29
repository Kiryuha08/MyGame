
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Enemy {
    double x;
    double y;
    double vx = 0.2;
    double vy = 0;
    double v = 2.0;
    int WH = 50;
    int IsDead = 0;
    int ShootDist = 50;
    int EnemyShooting = 0;
    long curTime;
    long prevTime = 0;
    int enemystate;
    // 1 - patrol;
    // 2 - rotating;
    // 3 - pursuit;
    // 4 - returning;

    double X1 = 700;
    double Y1 = this.y;
    int Angle1 = 0;
    int Delta = 5;
    double X2 = 100;
    double Y2 = this.y;
    int Angle2 = 180;
    int Angle = this.Angle1;

    BufferedImage Image;
    BufferedImage Image1;

    public Vizor vizor;
    public Bullet bullet;
    public Room room;


    public Enemy(int x, int y, Room room) throws IOException {
        this.x = x;
        this.y = y;
        this.room = room;
        this.vizor = new Vizor(x,y);
        this.bullet = new Bullet(x,y);

        this.Image = ImageIO.read(new File("Hotline Chuvashia\\data\\EnemyBasic1.png"));
        this.Image1 = ImageIO.read(new File("Hotline Chuvashia\\data\\EnemyKilled1.png"));

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


    boolean IsShooting(Hero hero){
        if (this.IsDead != 1) {
            if (Math.signum(this.vx) == Math.signum(hero.x - this.x)) {
                if (((int) this.x + this.ShootDist <= hero.x + hero.WiHgh || (int) this.x + this.WH + this.ShootDist >= hero.x) && ((int) this.y <= hero.y + hero.WiHgh / 2 && (int) this.y + this.WH >= hero.y - hero.WiHgh / 2)) {
                    return true;
                }
            }
        }
        return false;
    }




    //void patrol(){
    //    this.x += this.vx*(1 - this.Angle/90) * ((this.curTime - this.prevTime)/4);
    //}

    //void rotating(){
    //    if (this.Angle < this.Angle2) {
    //        this.Angle += this.Delta2;
    //    }
    //    if (this.Angle > this.Angle1) {
    //        this.Angle -= this.Delta2;
    //    }
    //}

    void posUpdate(Hero hero){


        this.curTime = System.currentTimeMillis();
        if (this.prevTime == 0) {
            this.prevTime = this.curTime;
        }




        int HeroKilledMe = 0;
        for (int i = (int)this.x - this.WH/2; i <= (int)this.x + this.WH/2; i++) {
            if (HeroKilledMe == 1){
                break;
            }
            for (int j = (int)this.y - this.WH/2; j <= (int)this.y + this.WH/2; j++) {
                if (i >= 0 && i < 800 && j >= 0 && j < 600) {
                    if (room.RoomPoints[i][j] == 2) {  // 2 means hero's area
                        hero.HeroIsAttack = 1;
                        this.IsDead = 1;  // Set enemy to dead
                        System.out.println("Enemy has been killed!");
                        HeroKilledMe = 1;
                        break;  // Exit the loop since enemy is dead
                    }
                    else{
                        hero.HeroIsAttack = 0;
                    }
                    //System.out.println(hero.HeroIsAttack);
                    room.RoomPoints[i][j] = 4;

                }
            }
        }
        //if (this.moving )
        //
//
        //if (this.moving == 1){
        //    this.x += vx * ((this.curTime - this.prevTime)/4);
        //    this.y += vy * ((this.curTime - this.prevTime)/4);
        //}

        //if (HeShooting == 0 || hero.YouDead == 1){
        //    if ((this.x == X2 && this.y == this.Y2) || (this.x + this.WH >= X1 && this.y >= this.Y1)){
        //        this.v *= -1;
        //    }
//
        //    if (this.Angle < this.Angle2){
        //        this.Angle += this.Delta1;
        //    }
        //    if
        //        this.x += v * ((this.curTime - this.prevTime)/4);

        // 1 - patrol;
        // 2 - rotating;
        // 3 - pursuit;
        // 4 - returning;

        if (vizor.ISeeYou == 0) {
            System.out.println(Y1 + "      " + Y2);
            if (this.y < this.Y1 - 1 || this.y > this.Y1 + 1){
                this.y += this.vy*(1 - this.Angle/90) * ((this.curTime - this.prevTime)/4);
            }
            if ((((int) this.x <= this.X2) && (this.Angle > this.Angle1))
                    || (((int) this.x >= this.X1) && (this.Angle < this.Angle2))) {
                this.enemystate = 2;
            }

            else {
                if (this.enemystate == 3 && vizor.GoToHero != 1){
                    this.enemystate = 4;
                }
                else{
                    this.enemystate = 1;
                }
            }


            //System.out.println(enemystate + "     "  +this.y+ "         " +this.Y1+ "         " + (this.y <= this.Y2));
        }
        if (vizor.ISeeYou == 1 || (vizor.ISeeYou == 0 && vizor.GoToHero == 1)){
            this.enemystate = 3;
        }

        //System.out.println(this.enemystate);






        if (this.enemystate == 4){
            double angle1;
            if (X1 - this.x != 0) {
                angle1 = Math.atan((Y1 - this.y) / (X1 - this.x));
                if (X1 - this.x > 0){
                    this.Angle = (int)Math.toDegrees(angle1);
                }
                else {
                    this.Angle = (int)Math.toDegrees(Math.PI + angle1);
                }
            }
            else {
                this.Angle = 180 - (int)Math.signum(Y1 - this.y) * 90;
            }

            // Двигаемся к начальным координатам
            this.x += v * Math.cos(Math.toRadians(this.Angle)) * ((this.curTime - this.prevTime)/4);
            this.y += v * Math.sin(Math.toRadians(this.Angle)) * ((this.curTime - this.prevTime)/4);

            // Если достигли начальных координат (с небольшой погрешностью)
            if (Math.abs(this.x - X1) < 5 && Math.abs(this.y - Y1) < 5) {
                this.x = X1;
                this.y = Y1;
                this.enemystate = 1;
            }

        }


        System.out.println(vizor.GoToHero);
        if (this.enemystate == 3){
            // расчитать траекторию движения dX dY от текущей точки до точки, где последний развидел героя
            double angle;
            if (vizor.heroX - this.x != 0) {
                angle = Math.atan((vizor.heroY - this.y) / (vizor.heroX - this.x));
                if (vizor.heroX - this.x > 0){
                    this.Angle = (int)Math.toDegrees(angle);
                }
                else {
                    this.Angle = (int)Math.toDegrees(Math.PI + angle);
                }
            }
            else {
                this.Angle = 180 - (int)Math.signum(vizor.heroY - this.y) * 90;
            }

            double dX = (vizor.heroX - this.x);
            double dY = (vizor.heroY - this.y);
            //System.out.println(this.Angle);
            //System.out.println(Math.sqrt(Math.pow(this.x - dX, 2) + Math.pow(this.y - dY, 2)));
            //System.out.print(vizor.heroX);
            //System.out.print("-");
            //System.out.println(vizor.heroY);
            if (vizor.GoToHero != 1) {
                if (Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2)) > 200) {
                    this.x += v * Math.cos(this.Angle);
                    this.y += v * Math.sin(this.Angle);
                    //System.out.println("!");
                }
                else {
                    this.EnemyShooting = 1;
                }
            }
            else{
                if (Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2)) > 1) {
                    this.x += v * Math.cos(this.Angle);
                    this.y += v * Math.sin(this.Angle);
                }
                else{
                    vizor.GoToHero = 0;
                }
            }
        }
        //System.out.println(this.curTime - this.prevTime);


        if (enemystate == 2){
            if (this.Angle < this.Angle2) {
                this.Angle += this.Delta;
            }
            else if (this.Angle > this.Angle1) {
                this.Angle += this.Delta;
                if (this.Angle == 360){
                    this.Angle = 0;
                }
            }
        }

        if (enemystate == 1){
            this.x += this.vx*(1 - this.Angle/90) * ((this.curTime - this.prevTime)/4);
        }




    //    if ((int)this.x <= this.X2 && this.y <= this.Y2){
    //        if (this.Angle > this.Angle1) {
    //            this.Angle += this.Delta2;
    //        }
    //        else{
    //            this.vx = -1 * this.vx;
    //        }
    //    }
    //    if ((int)this.x >= this.X1 && this.y >= this.Y1){
    //        if (this.Angle < this.Angle2) {
    //            this.Angle += this.Delta1;
    //        }
    //        else{
    //            vx = -1 * this.vx;
    //        }
    //    }
    //    this.x += vx * ((this.curTime - this.prevTime)/4);


        //System.out.println(this.enemystate + "        " + this.Angle + "        " + this.vx + "        " + this.x + "        " + vizor.ISeeYou);
        this.prevTime = this.curTime;

        this.vizor.PosUpdate(this);
        this.bullet.posUpdate(this, hero);
    }

    //void heroRotate(){
    //    if (this.x <= this.X2 && this.y <= this.Y2 && this.Angle2 > this.Angle1){
    //        this.Angle2 += this.Delta2;
    //        int Angle21 = Angle2 - 45;
    //        while (Angle21 > Angle1 - 45) {
    //            Angle21 -= 5;
    //            this.x = this.x - this.WH*(Math.sqrt(2)-1) + this.WH * Math.sqrt(2) * Math.cos(Angle21);
    //        }
    //    }


    //}


    //boolean isCollided(Hero hero) {
    //    if (hero.YouDead != 1) {
    //        if ((((int) this.x <= hero.x + hero.WiHgh) && ((int) this.x + this.WH >= hero.x)) && (((int) this.y <= hero.y + hero.WiHgh / 2) && ((int) this.y + this.WH >= hero.y - hero.WiHgh / 2))) {
    //            //|| (((int)this.x == hero.x + hero.WiHgh || (int)this.x+this.WH == hero.x) && (int)this.y-this.WH == hero.y)){
    //            return true;
    //        } else {
    //            return false;
    //        }
//
    //    }
    //    return false;
    //}



    void paint(Graphics g) {
        //System.out.println(IsDead);
        vizor.paint(g, this);
        bullet.paint(g, this);
        g.setColor(Color.RED);
        if (this.IsDead != 1) {
            //g.fillRect((int) this.x - WH/2, (int) this.y - WH/2, WH, WH);
            g.drawImage(rotateImage(Image,Math.toRadians(Angle)),(int) this.x - this.WH / 2, (int) this.y - this.WH / 2, null);
        }
        else{
            g.drawImage(Image1,(int) this.x - this.WH / 2, (int) this.y - this.WH / 2, null);
        }
        //else{
        //    this.IsDead = 0;
        //}
    }



}