import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyPanel extends JPanel implements MouseListener, MouseMotionListener {

    Hero hero;
    //Bullet bullet;
    Room room;
    //Vizor vizorEn;
    //ShootVizor shootvizorEn;
    BG bg;
    public MyPanel(Hero hero, Room room, BG bg){
        // TODO: Всё должно быть в классе Room ( кроме Hero )
        this.hero = hero;
        //this.bullet = bullet;
        this.room = room;
        //this.vizorEn = vizorEn;
        //this.shootvizorEn = shootvizorEn;
        this.bg = bg;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg.paintBG(g);

 /*       if (enemy.isCollided(hero)){
            enemy.IsDead = 1;
        }

        if (enemy.IsShooting(hero)){
            bullet.IsShooted = 1;
            enemy.HeShooting = 1;
        }

        if (bullet.HeroDied(hero, enemy)){
            hero.YouDead = 1;
        }
        //System.out.println(hero.YouDead);*/

        //enemy.posUpdate(hero);
        //enemy.paint(g);
        //bullet.posUpdate(enemy);
        //bullet.paint(g);
        room.RefreshPoints();
        room.UpdateBanPoints();

        for (Walls wall : room.walls) {
            wall.paint(g);
        }

        hero.posUpdate(room);
        //hero.HeroAttack(room);


        for (Enemy enemy : room.Enemies){
            if (enemy.IsDead == 0){
                enemy.posUpdate(this.hero);
            }
            //enemy.paint(g);
        }
        // тут рисуем все визоры сразу (расчеты уже проведены)
        g.setColor(new Color(0xFFDD00));
        for (int x = 0; x<800; x+=3){
            for (int y = 0; y<600; y+=3){
                if (room.RoomPoints[x][y] == 3){
                    g.drawLine(x,y,x,y);
                }
            }
        }
        for (Enemy enemy : room.Enemies){
            enemy.paint(g);
        }
        hero.paint(g);
        //hero.wallInteraction(room.walls.get(0));
        //vizorEn.PosUpdate(enemy);
        //vizorEn.paint(g);
        //shootvizorEn.PosUpdate(enemy);
        //shootvizorEn.paint(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        hero.xd = e.getX();
        hero.yd = e.getY();
        //System.out.println(e.getX() + " " + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        hero.xd = e.getX();
        hero.yd = e.getY();
        //System.out.println(e.getX() + " " + e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}