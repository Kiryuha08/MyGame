import java.awt.*;
import java.util.Arrays;

public class Vizor {
    double x;
    double y;
    int Lenght = 400;
    int Angle = 90;
    int enStatus = 1;
    int Diametr = 800;
    public int[][] VisorPoints;



    public Vizor(int x, int y) {
        this.x = x;
        this.y = y;
        this.VisorPoints = new int[800][600]; // Создаём матрицу из всех точек комнаты
    }

    void PosUpdate(Enemy enemy){
        /*this.x = enemy.x + enemy.WH;
        this.y = enemy.y + enemy.WH/2;
        this.enStatus = enemy.IsDead;*/
        for (int[] row: VisorPoints)
            Arrays.fill(row, 0);

        int x,y;
        for (int a = (int)enemy.Angle - this.Angle/2; a <= (int)enemy.Angle + this.Angle/2; a++){
            for (int n = 0; n < this.Lenght; n++){
                x = (int)(n * Math.cos(Math.toRadians(a)) + enemy.x);
                y = (int)(n * Math.sin(Math.toRadians(a)) + enemy.y);
                if (enemy.room.BanPoints[x][y] != 1){
                    VisorPoints[x][y] = 1;
                } else {
                    break;
                }

            }
        }
    }


    void paint(Graphics g){
        if (enStatus == 1) {
            g.setColor(new Color(0xFFDD00));
            for (int x = 0; x<800; x++){
                for (int y = 0; y<600; y++){
                    if (VisorPoints[x][y] == 1){
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