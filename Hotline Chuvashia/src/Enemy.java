import java.awt.*;

public class Enemy {
    int x;
    int y;
    double v = 1;
    int WH = 50;

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }

    void posUpdate(){
        if (this.x > 0 && this.x<800){
            this.x -= v;
        }
        if (this.x > 0 && this.x<800){
            this.x += v;
        }


    }

    void isColided(){

    }

    void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, WH, WH);
    }



}