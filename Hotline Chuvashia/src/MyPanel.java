import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyPanel extends JPanel implements MouseListener {

    Hero hero;
    Enemy enemy;
    public MyPanel(Hero hero, Enemy enemy){
        this.hero = hero;
        this.enemy = enemy;
    }
    @Override
    protected void paintComponent(Graphics g) {

        if (enemy.isCollided(hero)){
            enemy.IsDead = 1;
        }

        hero.posUpdate();
        hero.paint(g);
        enemy.posUpdate();
        enemy.paint(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        hero.xd = e.getX();
        hero.yd = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
