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
    int NextToWall = 0;

    //int sx; // расстояние от x до xd
    //int sy; // расстояние от y до yd


    public Hero(int x, int y){
        this.x = x;
        this.y = y;
    }

    void wallInteraction(Walls wall){
        if((this.x <= wall.x+ wall.wWidx && this.x >= wall.x) && (this.y -
                this.WiHgh/2<= wall.y+ wall.wLeny && this.y + this.WiHgh/2 >= wall.y)){
            this.NextToWall = 1;
        }



        else if((this.x + this.WiHgh >= wall.x && this.x <= wall.x) && (this.y -
                this.WiHgh/2 <= wall.y+ wall.wLeny && this.y + this.WiHgh/2 >= wall.y)){
            this.NextToWall = 3;
        }

        else{
            this.NextToWall = 0;
        }
        System.out.println(this.NextToWall);
    }

    void posUpdate(Walls wall){

        //sx = x - xd;
        //sy = y - yd;

        if (this.x+WiHgh/2 > this.xd && this.NextToWall != 1){
            this.x -= vx;
            //System.out.println(xd);
            //System.out.println(x);
        } else if (this.x+WiHgh/2 < this.xd && this.NextToWall != 3){
            this.x += vx;
            //System.out.println(xd);
            //System.out.println(x);
        }
        //else if()


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

