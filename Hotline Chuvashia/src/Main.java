import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero(375,350);


        MyPanel panel = new MyPanel(hero);

        // Создаем окно
        JFrame frame = new JFrame();

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();   // менеджер по трудоустройству слушателей клавиатуры
        //manager.addKeyEventDispatcher(panel);



        while (true) {
            frame.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            }
            catch (InterruptedException e) {
            }
        }



    }
}