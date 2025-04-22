import java.awt.*;
import java.util.Arrays;

public class Vizor {
    double x;
    double y;
    int Lenght = 400;
    int Angle = 90;
    int enStatus = 1;   // жив ли враг?
    int Diametr = 800;
    int ISeeYou = 0;
    int heroX;
    int heroY;
    int GoToHero = 0;



    public Vizor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void PosUpdate(Enemy enemy){
        /*this.x = enemy.x + enemy.WH;
        this.y = enemy.y + enemy.WH/2;
        this.enStatus = enemy.IsDead;*/

        if (enemy.IsDead != 1) {
            int HeroInVizor = 0;
            int x, y;
            for (int a = enemy.Angle - this.Angle / 2; a <= enemy.Angle + this.Angle / 2; a++) {
                double cos_a = Math.cos(Math.toRadians(a));
                double sin_a = Math.sin(Math.toRadians(a));
                for (int n = 0; n < this.Lenght; n++) {
                    x = (int) (n * cos_a + enemy.x);
                    y = (int) (n * sin_a + enemy.y);
                    if ((x < 0) || (y < 0) || (x >= 800) || (y >= 600)) continue;
                    //System.out.println(enemy.room.RoomPoints[x][y]);

                    if (enemy.room.RoomPoints[x][y] == 2) {
                        //System.out.println("i see you");
                        HeroInVizor = 1;
                        this.heroX = x;
                        this.heroY = y;
                    }


                    if (enemy.room.RoomPoints[x][y] != 1) {
                        enemy.room.RoomPoints[x][y] = 3;
                    }
                    else {
                        break;
                    }

                }
            }

            if (HeroInVizor == 1) {
                this.GoToHero = 0;
                this.ISeeYou = 1;
            }
            else{
                if (this.ISeeYou == 1) {
                    this.GoToHero = 1;
                }
                this.ISeeYou = 0;
            }
        }
    }


    void paint(Graphics g, Enemy enemy){
        if (enStatus == 1) {
            g.setColor(new Color(0xFFEFD200, true));
            for (int x = 0; x<800; x += 3){
                for (int y = 0; y<600; y += 3){
                    if (enemy.room.RoomPoints[x][y] == 3){
                        g.drawLine(x,y,x,y);
                    }
                }
            }
            //g.drawPolygon(new int[]{(int) x, (int) (x + x1), (int) (x + x2)}, (new int[]{(int) y, (int) (y - y1), (int) (y + y2)}), 3);
            //g.drawLine((int)this.x,(int)this.y,(int)this.x + 400,(int)this.y+ 200);
            //g.drawLine((int)this.x,(int)this.y,(int)this.x + 400,(int)this.y - 200);
            //g.drawOval((int)this.x-this.Diametr/2,(int)this.y-this.Diametr/2,this.Diametr, this.Diametr);
        }
    }
}