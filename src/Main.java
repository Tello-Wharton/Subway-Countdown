import javax.swing.*;
import java.awt.*;

/**
 * Created by Aaron on 18/09/2016.
 */
public class Main extends JFrame{

    private int width;
    private int height;

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

    public Main(){
        setVisible(true);
        add(new Board());

        this.width = 1280;
        this.height = 720;


        init();
    }

    public void init(){
        setSize(width,height);
    }
}
