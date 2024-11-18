
import java.awt.*;

public class Enemy {
    double x;
    double y;
    double v = 0.1;
    int WH = 50;
    int IsDead = 0;

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }

    void posUpdate(){
        if (this.x < 0 || this.x > 800-this.WH){
            this.v *= -1;
        }
        this.x += v;
    }


    boolean isCollided(Hero hero) {
        if ((((int)this.x <= hero.x + hero.WiHgh) && ((int)this.x+this.WH >= hero.x)) && (((int)this.y == hero.y  + hero.WiHgh) || ((int)this.y + this.WH == hero.y + hero.WiHgh))){
                //|| (((int)this.x == hero.x + hero.WiHgh || (int)this.x+this.WH == hero.x) && (int)this.y-this.WH == hero.y)){
            return true;
        }

        else{
            return false;
        }
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