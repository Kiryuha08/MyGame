import java.awt.*;

public class Bullet {
    double x;
    double y;
    int w = 10;
    int v = 5;
    int IsShooted = 0;

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
    }

    void paint(Graphics g) {
        g.setColor(new Color(0xFFA900));
        if(IsShooted == 1) {
            g.fillRect((int) this.x,(int) this.y, this.w, this.w);
        }
    }

    void posUpdate(Enemy enemy) {
        if (enemy.IsDead != 1) {
            if (IsShooted == 1) {
                this.x += Math.signum(enemy.v) * v;
            } else {
                this.x = enemy.x + enemy.WH;
            }
        }
    }

    boolean HeroDied(Hero hero, Enemy enemy) {
        if (enemy.IsDead != 1) {
            if ((((int) this.x <= hero.x + hero.WiHgh) && ((int) this.x + this.w >= hero.x)) && (((int) this.y <= hero.y + hero.WiHgh / 2) && ((int) this.y + this.w >= hero.y - hero.WiHgh / 2))) {
                //if ((((int)this.x + this.w >= hero.x || (int)this.x <= hero.x + hero.WiHgh) && (int)this.y + this.w >= hero.y)
                //    ||
                //    (((int)this.x + this.w >= hero.x || (int)this.x <= hero.x + hero.WiHgh) && (int)this.y <= hero.vy + hero.WiHgh)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
