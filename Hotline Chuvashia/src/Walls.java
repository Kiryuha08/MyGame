import java.awt.*;
import java.awt.geom.Point2D;

public class Walls {
    int x1;
    int y1;
    int x2;
    int y2;

    //todo: Переименовать параметры
    public Walls(int x, int y, int wLen, int wWid){
        this.x1 = x;
        this.y1 = y;
        this.x2 = wLen;
        this.y2 = wWid;
    }


    void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(this.x1, this.y1, this.x2 - this.x1, this.y2- this.y1);
    }


}