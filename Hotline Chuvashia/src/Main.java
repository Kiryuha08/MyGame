import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Main {
    public static void main(String[] args) throws IOException {
        BG bg = new BG();
        Hero hero = new Hero(400,50);
        Room rooms = new Room();
        rooms.AddWalls(400, 300, 550, 450);
        rooms.AddWalls(100, 100, 150, 250);

        rooms.AddEnemy(375, 200);
        //Enemy enemy = new Enemy(375, 200);
        //Bullet bullet = new Bullet((int)enemy.x + enemy.WH/2, (int)enemy.y + enemy.WH/2);
        //Walls wall = new Walls(400, 300, 150, 150);

        //Vizor vizorEn = new Vizor((int)enemy.x + 2*enemy.WH, (int)enemy.y + enemy.WH/2);
        //ShootVizor shootvizorEn = new ShootVizor((int)enemy.x + 2*enemy.WH, (int)enemy.y + enemy.WH/2);



        MyPanel panel = new MyPanel(hero, rooms, bg);

        // Создаем окно
        JFrame frame = new JFrame();
        frame.addMouseListener(panel);
        frame.addMouseMotionListener(panel);


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