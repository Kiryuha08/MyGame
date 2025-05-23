
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
    int deltaX;
    int Angle1 = 0;
    int Delta = 1;
    double X2 = 100;
    double Y2 = this.y;
    int Angle2 = 180;
    int Angle = this.Angle1;
    int NextTTWall = 0;

    BufferedImage Image;
    BufferedImage Image1;

    public Vizor vizor;
    public Bullet bullet;
    public Room room;
    int Time = 0;


    public Enemy(int x, int y, int x1, int x2, Room room) throws IOException {
        this.x = x;
        this.y = y;
        this.X1 = x1;
        this.X2 = x2;
        this.room = room;
        this.vizor = new Vizor(x,y);
        this.bullet = new Bullet(x,y);

        this.Image = ImageIO.read(new File("data\\EnemyBasic1.png"));
        this.Image1 = ImageIO.read(new File("data\\EnemyKilled1.png"));
        room.EnemyCount++;
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


    void posUpdate(Hero hero){

        this.curTime = System.currentTimeMillis();
        if (this.prevTime == 0) {
            this.prevTime = this.curTime;
        }

        this.NextTTWall = 0;
        if ((this.enemystate == 1) || (this.Angle == this.Angle2) || (this.Angle == this.Angle1)) {
            this.deltaX = (1 - this.Angle / 90);
        }
        int nextX = (int) (this.x + this.vx * this.deltaX * ((this.curTime - this.prevTime) / 4)) +
                this.deltaX * this.WH / 2;
        if (room.RoomPoints[nextX][(int) this.y] == 1) {
            this.NextTTWall = 1;
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
                        room.EnemyCount--;
                        System.out.println("Enemy has been killed!");
                        HeroKilledMe = 1;
                        break;  // Exit the loop since enemy is dead
                    }
                    else{
                        hero.HeroIsAttack = 0;
                    }
                    /*
                    if (room.RoomPoints[i][j] == 1){
                        this.NextTTWall = 1;

                    }
                    */

                    //System.out.println(hero.HeroIsAttack);
                    room.RoomPoints[i][j] = 4;

                }
            }
        }

        // 1 - patrol;
        // 2 - rotating;
        // 3 - pursuit;
        // 4 - returning;

        if (vizor.ISeeYou == 0) {
            //System.out.println(Y1 + "      " + Y2 + "      " + y);
            if (this.y < this.Y1 - 1 || this.y > this.Y1 + 1){
                this.y += this.vy*(1 - this.Angle/90) * ((this.curTime - this.prevTime)/4);
            }
            System.out.println(NextTTWall);
            System.out.println(this.Angle);
            if ((((int) this.x <= this.X2) && (this.Angle > this.Angle1))
                    || (((int) this.x >= this.X1) && (this.Angle < this.Angle2))
                    || ((this.NextTTWall == 1) && ((this.Angle < this.Angle2) || (this.Angle > this.Angle1)))) {
                this.enemystate = 2;
                this.NextTTWall = 0;
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
            if (vizor.heroX - this.x != 0)  {
                if(this.Time / 100 > 1) {
                    this.Time = 0;
                    angle = Math.atan((vizor.heroY - this.y) / (vizor.heroX - this.x));
                    if (vizor.heroX - this.x > 0) {
                        this.Angle = (int) Math.toDegrees(angle);
                    } else {
                        this.Angle = (int) Math.toDegrees(Math.PI + angle);
                    }
                }
                else{
                    this.Time += (this.curTime - this.prevTime);
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
            System.out.println(this.Angle);
            if (vizor.GoToHero != 1) {
                if (Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2)) > 200) {
                    //if()
                    this.x += Math.signum(dX) * v * Math.abs(Math.cos(Math.toRadians(this.Angle)));
                    this.y += Math.signum(dY) * v * Math.abs(Math.sin(Math.toRadians(this.Angle)));
                    //System.out.println(Math.signum(dX) * v * Math.abs(Math.sin(Math.toRadians(this.Angle))));
                    //System.out.println(Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2)));

                }
                else {
                    this.EnemyShooting = 1;
                }
            }

            else{
                if (Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2)) > 5) {
                    this.x += Math.signum(dX) * v * Math.abs(Math.cos(Math.toRadians(this.Angle)));
                    this.y += Math.signum(dY) * v * Math.abs(Math.sin(Math.toRadians(this.Angle)));
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
            //if (){
                this.x += this.vx*(1 - this.Angle/90) * ((this.curTime - this.prevTime)/4);
            //}
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




    void paint(Graphics g) {
        //System.out.println(IsDead);
        //vizor.paint(g, this);
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