import java.awt.*;

public class Vizor {
    double x;
    double y;
    int Lenght = 400;
    int Angle = 90;
    int enStatus = 1;
    int Diametr = 800;


    public Vizor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void PosUpdate(Enemy enemy){
        this.x = enemy.x + enemy.WH;
        this.y = enemy.y + enemy.WH/2;
        this.enStatus = enemy.IsDead;
    }


    void paint(Graphics g){
        if (enStatus != 1) {
            g.setColor(new Color(0xFFDD00));
            //g.drawPolygon(new int[]{(int) x, (int) (x + x1), (int) (x + x2)}, (new int[]{(int) y, (int) (y - y1), (int) (y + y2)}), 3);
            //g.drawLine((int)this.x,(int)this.y,(int)this.x + 400,(int)this.y+ 200);
            //g.drawLine((int)this.x,(int)this.y,(int)this.x + 400,(int)this.y - 200);
            //g.drawOval((int)this.x-this.Diametr/2,(int)this.y-this.Diametr/2,this.Diametr, this.Diametr);
        }
    }
}