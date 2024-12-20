import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyPanel extends JPanel implements MouseListener {

    Hero hero;
    Enemy enemy;
    Bullet bullet;
    Walls wall;
    Vizor vizorEn;
    ShootVizor shootvizorEn;
    public MyPanel(Hero hero, Enemy enemy, Bullet bullet, Walls wall, Vizor vizorEn, ShootVizor shootvizorEn){
        this.hero = hero;
        this.enemy = enemy;
        this.bullet = bullet;
        this.wall = wall;
        this.vizorEn = vizorEn;
        this.shootvizorEn = shootvizorEn;
    }
    @Override
    protected void paintComponent(Graphics g) {

        if (enemy.isCollided(hero)){
            enemy.IsDead = 1;
        }

        if (enemy.IsShooting(hero)){
            bullet.IsShooted = 1;
            enemy.HeShooting = 1;
        }

        if (bullet.HeroDied(hero, enemy)){
            hero.YouDead = 1;
        }
        //System.out.println(hero.YouDead);

        hero.posUpdate(wall);
        hero.paint(g);
        enemy.posUpdate(hero);
        enemy.paint(g);
        bullet.posUpdate(enemy);
        bullet.paint(g);
        wall.paint(g);
        hero.wallInteraction(wall);
        vizorEn.PosUpdate(enemy);
        vizorEn.paint(g);
        shootvizorEn.PosUpdate(enemy);
        shootvizorEn.paint(g);
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
}
