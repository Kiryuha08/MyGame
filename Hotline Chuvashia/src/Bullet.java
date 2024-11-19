import java.awt.*;

public class Bullet {
    int x;
    int y;
    int w = 10;
    int v = 5;
    int IsShooted = 0;

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
    }

    void paint(Graphics g) {
        g.setColor(new Color(0xFFA900));
        if(IsShooted == 1) {
            g.fillRect(this.x, this.y, this.w, this.w);
        }
    }

    void posUpdate() {
        if (IsShooted == 1) {
            this.x += v;
        }
    }

}
