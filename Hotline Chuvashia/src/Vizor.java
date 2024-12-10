import java.awt.*;

public class Vizor {
    double x;
    double y;
    double x1 = 100;
    double y1 = 50;
    double x2 = 100;
    double y2 = 50;


    public Vizor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void PosUpdate(Enemy enemy){
        this.x = enemy.x;
        this.y = enemy.y;
    }


    void paint(Graphics g){
        g.drawPolygon(new int[]{(int)x, (int)x1, (int)x2}, (new int[]{(int)y, (int)y1, (int)y2}), 3);
    }
}