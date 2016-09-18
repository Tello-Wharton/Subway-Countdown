import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aaron on 18/09/2016.
 */
public class Board extends JPanel {

    private JTextField hourInput;
    private JTextField minuteInput;
    private JPanel timeInput;
    private JButton start;

    private JButton fullScreen;

    private int hour;
    private int minute;


    public Board(){

        setBackground(Color.black);

        timeInput = new JPanel();
        timeInput.setBackground(null);
        hourInput = new JTextField(2);
        minuteInput = new JTextField(2);

        JLabel time = new JLabel("Time: ");
        time.setForeground(Color.white);
        timeInput.add(time);
        timeInput.add(hourInput);
        JLabel colon = new JLabel(":");
        colon.setForeground(Color.white);
        timeInput.add(colon);
        timeInput.add(minuteInput);


        start = new JButton("Start");
        start.addActionListener(new StartListener());
        timeInput.add(start);

        add(timeInput);

    }

    public void init(){

    }

    public JButton addFullScreen(){

        fullScreen = new JButton("Fullscreen");
        fullScreen.setVisible(false);
        add(fullScreen);

        return fullScreen;
    }

    private class StartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                hour = Integer.parseInt(hourInput.getText());
                minute = Integer.parseInt(minuteInput.getText());
            }catch (NumberFormatException exception){
                return;
            }

            timeInput.setVisible(false);
            fullScreen.setVisible(true);
        }
    }


}
