import java.awt.*;

public class ShootVizor {
    double x;
    double y;
    double x1 = 150;
    double y1 = 100;
    double x2 = 150;
    double y2 = 100;
    int enStatus = 1;


    public ShootVizor(int x, int y) {
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
            g.drawPolygon(new int[]{(int) x, (int) (x + x1), (int) (x + x2)}, (new int[]{(int) y, (int) (y - y1), (int) (y + y2)}), 3);
        }
    }
}