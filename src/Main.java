import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aaron on 18/09/2016.
 */
public class Main extends JFrame{

    private static boolean IS_IN_FULLSCREEN = false;
    Board board;

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

        this.board = new Board();


        this.width = 1280;
        this.height = 720;


        init();
    }

    public void init(){

        setVisible(true);
        setSize(width,height);
        add(board);
        JButton fullscreen = board.addFullScreen();
        fullscreen.addActionListener(new FullScreenEffect());
    }

    private class FullScreenEffect implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {


            if(IS_IN_FULLSCREEN == false){

                dispose();

                setUndecorated(true);

                setBounds(0,0,getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
                setVisible(true);
                IS_IN_FULLSCREEN = true;
            }
            else{
                setVisible(true);

                setSize(width,height);
                dispose();
                setUndecorated(false);
                setVisible(true);
                IS_IN_FULLSCREEN = false;
            }
        }
    }
}
