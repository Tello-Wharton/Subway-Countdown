import javax.swing.*;
import java.awt.*;

/**
 * Created by Aaron on 18/09/2016.
 */
public class Main {

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame window = new JFrame();
                window.setVisible(true);
            }
        });
    }
}
