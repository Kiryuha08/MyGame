import java.awt.*;

public class Hero {
    int x;
    int y;
    int vx = 1;
    int vy = 1;
    int xd;
    int yd;
    //int sx; // расстояние от x до xd
    //int sy; // расстояние от y до yd


    public Hero(int x, int y){
        this.x = x;
        this.y = y;
    }
    void posUpdate(){

        //sx = x - xd;
        //sy = y - yd;


        if (this.x > this.xd){
            this.x -= vx;
        } else if (this.x < this.xd){
            this.x += vx;
        }

        if (this.x == this.xd) {
            if (this.y > this.yd) {
                //System.out.println(xd);
                //System.out.println(x);
                this.y -= vy;
            } else if(this.y < this.yd) {
                //System.out.println(xd);
                //System.out.println(x);
                this.y += vy;

            }


        }



    }

    void paint(Graphics g) {
        g.fillRect(this.x, this.y, 50, 50);
    }
}

