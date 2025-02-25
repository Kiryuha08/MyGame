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



    public Vizor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void PosUpdate(Enemy enemy){
        /*this.x = enemy.x + enemy.WH;
        this.y = enemy.y + enemy.WH/2;
        this.enStatus = enemy.IsDead;*/

        int x,y;
        for (int a = (int)enemy.Angle - this.Angle/2; a <= (int)enemy.Angle + this.Angle/2; a++){
            for (int n = 0; n < this.Lenght; n++){
                x = (int)(n * Math.cos(Math.toRadians(a)) + enemy.x);
                y = (int)(n * Math.sin(Math.toRadians(a)) + enemy.y);
                if ((x < 0) || (y < 0) || (x >= 800) || (y >= 600)) continue;
                if (enemy.room.RoomPoints[x][y] == 2) {
                    System.out.println("i see you");
                    this.ISeeYou = 1;
                }
                if (enemy.room.RoomPoints[x][y] != 1){
                    enemy.room.RoomPoints[x][y] = 3;
                } else {
                    this.ISeeYou = 0;
                    break;
                }

            }
        }
    }


    void paint(Graphics g, Enemy enemy){
        if (enStatus == 1) {
            g.setColor(new Color(0xFFDD00));
            for (int x = 0; x<800; x++){
                for (int y = 0; y<600; y++){
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