import java.awt.*;

public class Walls {
    int x;
    int y;
    int wLeny;
    int wWidx;

    public Walls(int x, int y, int wLen, int wWid){
        this.x = x;
        this.y = y;
        this.wLeny = wLen;
        this.wWidx= wWid;
    }

    void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(this.x, this.y, this.wWidx, this.wLeny);
    }


}
