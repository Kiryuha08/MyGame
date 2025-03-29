import java.awt.*;

public class Bullet {
    double x;
    double y;
    int w = 10;
    int v = 3;
    int BulletActive = 0;
    int IsShooted = 0;
    long curTime;
    long prevTime = 0;
    int BulAngle;
    int Time = 1000;

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
    }



    //todo координаты и posupdate пули
    void posUpdate(Enemy enemy, Hero hero) {

        this.curTime = System.currentTimeMillis();
        if (this.prevTime == 0) {
            this.prevTime = this.curTime;
        }

        if(this.BulletActive == 1) {
            for (int i = (int) this.x - this.w / 2; i <= (int) this.x + this.w / 2; i++) {
                for (int j = (int) this.y - this.w / 2; j <= (int) this.y + this.w / 2; j++) {
                    if (i >= 0 && i < 800 && j >= 0 && j < 600) {
                        if (enemy.room.RoomPoints[i][j] == 2) {
                            hero.YouDead = 1;
                        }
                        else if (enemy.room.RoomPoints[i][j] == 1) {
                            Time = 0;
                            this.BulletActive = 0;
                            enemy.EnemyShooting = 0;
                            this.x = enemy.x;
                            this.y = enemy.y;
                        }
                        enemy.room.RoomPoints[i][j] = 5;
                    }
                }
            }
        }

        if (this.Time / 500 < 1) {
            this.Time += (this.curTime - this.prevTime);
        }
        else {
            if (enemy.IsDead != 1) {
                if (enemy.EnemyShooting == 1) {
                    if (BulletActive == 0) {
                        this.BulAngle = enemy.Angle;
                    }
                    this.x += Math.cos(Math.toRadians(BulAngle)) * v * ((this.curTime - this.prevTime) / 4);
                    this.y += Math.sin(Math.toRadians(BulAngle)) * v * ((this.curTime - this.prevTime) / 4);

                    this.BulletActive = 1;

                } else {
                    this.x = enemy.x;
                    this.y = enemy.y;
                }
            }
        }


        this.prevTime = this.curTime;
        //System.out.println(this.x);
        //System.out.println(this.y);
    }



    void paint(Graphics g, Enemy enemy) {
        if (enemy.IsDead != 1) {
            if (enemy.EnemyShooting == 1) {
                if (BulletActive == 1) {
                    g.setColor(new Color(0xFF0000));
                    g.fillRect((int) this.x - this.w / 2, (int) this.y - this.w / 2, this.w, this.w);
                }
            }
        }
    }

//    boolean HeroDied(Hero hero, Enemy enemy) {
//        if (enemy.IsDead != 1) {
//            if ((((int) this.x <= hero.x + hero.WiHgh) && ((int) this.x + this.w >= hero.x)) && (((int) this.y <= hero.y + hero.WiHgh / 2) && ((int) this.y + this.w >= hero.y - hero.WiHgh / 2))) {
//                //if ((((int)this.x + this.w >= hero.x || (int)this.x <= hero.x + hero.WiHgh) && (int)this.y + this.w >= hero.y)
//                //    ||
//                //    (((int)this.x + this.w >= hero.x || (int)this.x <= hero.x + hero.WiHgh) && (int)this.y <= hero.vy + hero.WiHgh)) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//        return false;
//    }
}
