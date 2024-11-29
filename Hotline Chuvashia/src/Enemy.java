
import java.awt.*;

public class Enemy {
    double x;
    double y;
    double v = 0.1;
    int WH = 50;
    int IsDead = 0;
    int ShootDist = 50;
    int HeShooting = 0;

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }

    boolean IsShooting(Hero hero){
        if (this.IsDead != 1) {
            if (Math.signum(this.v) == Math.signum(hero.x - this.x)) {
                if (((int) this.x + this.ShootDist <= hero.x + hero.WiHgh || (int) this.x + this.WH + this.ShootDist >= hero.x) && ((int) this.y <= hero.y + hero.WiHgh / 2 && (int) this.y + this.WH >= hero.y - hero.WiHgh / 2)) {
                    return true;
                }
            }
        }
        return false;
    }

    void posUpdate(Hero hero){
        if (HeShooting == 0 || hero.YouDead == 1){
            if (this.x < 0 || this.x > 800-this.WH){
                this.v *= -1;
            }
            this.x += v;
            }
        this.HeShooting = 0;
    }


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
        g.setColor(Color.RED);
        //System.out.println(IsDead);
        if (this.IsDead != 1) {
            g.fillRect((int) this.x, (int) this.y, WH, WH);
        }
        //else{
        //    this.IsDead = 0;
        //}
    }



}