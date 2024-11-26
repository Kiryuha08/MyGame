import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero(375,350);
        Enemy enemy = new Enemy(375, 200);
        Bullet bullet = new Bullet((int)enemy.x + 2*enemy.WH, (int)enemy.y + enemy.WH/2);
        Walls wall = new Walls(100, 500, 50, 600);



        MyPanel panel = new MyPanel(hero, enemy, bullet, wall);

        // Создаем окно
        JFrame frame = new JFrame();
        frame.addMouseListener(panel);

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();   // менеджер по трудоустройству слушателей клавиатуры
        //manager.addKeyEventDispatcher(panel);

        while (true) {
            frame.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            }
            catch (InterruptedException e) {
            }
        }
    }
}