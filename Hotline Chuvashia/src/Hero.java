import java.awt.*;

public class Hero {
    int x;
    int y;
    int vx = 1;
    int vy = 1;
    int xd;
    int yd;
    int WiHgh = 50;
    int YouDead;
    //int sx; // расстояние от x до xd
    //int sy; // расстояние от y до yd


    public Hero(int x, int y){
        this.x = x;
        this.y = y;
    }



    void posUpdate(Walls wall){

        //sx = x - xd;
        //sy = y - yd;
        if(this.x >= wall.x)
            if (this.x+WiHgh/2 > this.xd){
                this.x -= vx;
                //System.out.println(xd);
                //System.out.println(x);
            } else if (this.x+WiHgh/2 < this.xd){
                this.x += vx;
                //System.out.println(xd);
                //System.out.println(x);
            }
        else if()


            if (this.x+WiHgh/2 == this.xd) {
                if (this.y+WiHgh/2 > this.yd) {
                    this.y -= vy;
                    //System.out.println(yd);
                    //System.out.println(y);
                } else if(this.y+WiHgh/2 < this.yd) {
                    this.y += vy;
                    //System.out.println(yd);
                    //System.out.println(y);
                }

        }
    }

    void paint(Graphics g) {
        if (YouDead != 1){
            g.fillRect(this.x, this.y-WiHgh/2, WiHgh, WiHgh);
        }
    }
}

