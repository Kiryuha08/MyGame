import java.awt.*;

public class Walls {
    int x;
    int y;
    int wLen;
    int wWid;

    public Walls(int x, int y, int wLen, int wWid){
        this.x = x;
        this.y = y;
        this.wLen = wLen;
        this.wWid = wWid;
    }

    void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(this.x, this.y, this.wWid, this.wLen);
    }


}
