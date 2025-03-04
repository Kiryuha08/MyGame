
import java.awt.*;
import java.util.ArrayList;

public class Enemy {
    double x;
    double y;
    double vx = 0.2;
    double vy = 0;
    int WH = 50;
    int IsDead = 0;
    int ShootDist = 50;
    int HeShooting = 0;
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

    public Vizor vizor;
    public Room room;


    public Enemy(int x, int y, Room room){
        this.x = x;
        this.y = y;
        this.room = room;
        this.vizor = new Vizor(x,y);

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

        if (vizor.ISeeYou == 0 && enemystate != 3) {
            if ((((int) this.x <= this.X2) && (this.Angle > this.Angle1))
                    || (((int) this.x >= this.X1) && (this.Angle < this.Angle2))) {
                this.enemystate = 2;

            } else {
                this.enemystate = 1;
            }

            //System.out.println(enemystate + "     "  +this.y+ "         " +this.Y1+ "         " + (this.y <= this.Y2));
        }
        if (vizor.ISeeYou == 1){
            this.enemystate = 3;
        }
        if (enemystate == 3){
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
                this.Angle = -180 + (int)Math.signum(vizor.heroY - this.y) * 90;
                System.out.println();
            }
            //double dX = (vizor.heroX - this.x) ;
        }


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
        this.HeShooting = 0;
        this.prevTime = this.curTime;

        this.vizor.PosUpdate(this);
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


    boolean isCollided(Hero hero) {
        if (hero.YouDead != 1) {
            if ((((int) this.x <= hero.x + hero.WiHgh) && ((int) this.x + this.WH >= hero.x)) && (((int) this.y <= hero.y + hero.WiHgh / 2) && ((int) this.y + this.WH >= hero.y - hero.WiHgh / 2))) {
                //|| (((int)this.x == hero.x + hero.WiHgh || (int)this.x+this.WH == hero.x) && (int)this.y-this.WH == hero.y)){
                return true;
            } else {
                return false;
            }

        }
        return false;
    }



    void paint(Graphics g) {
        //System.out.println(IsDead);
        vizor.paint(g, this);
        g.setColor(Color.RED);
        if (this.IsDead != 1) {
            g.fillRect((int) this.x - WH/2, (int) this.y - WH/2, WH, WH);
        }
        //else{
        //    this.IsDead = 0;
        //}
    }



}