import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Main {
    public static void main(String[] args) throws IOException {

        Menu menu = new Menu();

        // Создаем окно
        JFrame frameM = new JFrame();
        frameM.addMouseListener(menu);
        frameM.addMouseMotionListener(menu);

        frameM.add(menu);
        frameM.setSize(800, 600);
        frameM.setVisible(true);
        frameM.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        while (true) {
            frameM.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(15);
            } catch (InterruptedException e) {
            }

            if (menu.bStartPress == 1)
            {
                menu.bStartPress = 0;
                frameM.setVisible(false);

                BG bg = new BG();
                Hero hero = new Hero(400, 300);
                Room rooms = new Room();
                rooms.AddWalls(0, 525, 799, 599);
                rooms.AddWalls(0, 0, 799, 75);
                rooms.AddWalls(300, 0, 500, 125);
                rooms.AddWalls(300, 475, 500, 599);
                rooms.AddWalls(250, 200, 350, 400);
                rooms.AddWalls(450, 200, 550, 400);

                rooms.AddEnemy(100, 150);
                rooms.AddEnemy(700, 150);
                rooms.AddEnemy(100, 450);
                rooms.AddEnemy(700, 450);
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
                        TimeUnit.MILLISECONDS.sleep(15);
                    } catch (InterruptedException e) {
                    }

                    if (hero.YouDead == 1){
                        try {
                            TimeUnit.MILLISECONDS.sleep(3000);
                        } catch (InterruptedException e) {
                        }
                        break;
                    }
                }

                frameM.setVisible(true);
            }
        }

    }
}