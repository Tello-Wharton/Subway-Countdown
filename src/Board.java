import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Aaron on 18/09/2016.
 */
public class Board extends JPanel implements ActionListener {

    private JTextField hourInput;
    private JTextField minuteInput;
    private JPanel timeInput;
    private JButton start;

    private JButton fullScreen;

    private int hour;
    private int minute;

    private static final int DELAY = 25;
    private static int mouseMotion = 5000;
    private Timer timer;
    private boolean started = true;
    private long endTime;
    private long timeRemaining;

    private JLabel countDown;

    public Board(){

        setLayout(new BorderLayout());
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

        add(timeInput,BorderLayout.NORTH);

        countDown = new JLabel("<html><center>---:--:---</center></html>");
        countDown.setFont(new Font(null,Font.PLAIN,300));
        countDown.setForeground(Color.white);
        add(countDown);

        addMouseMotionListener(new MAdapter());
        setFocusable(true);
    }


    public void initApp(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));

        String inputString = hour + ":"+ minute +":00.000";

        SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
        today.setTimeZone(TimeZone.getTimeZone("Europe/London"));

        Date date = null;
        try {
            String timeText = today.format(new Date()) + " " + inputString;
            System.out.println(timeText);
            date = sdf.parse(timeText);
            endTime = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("in milliseconds: " + date.getTime());


        timer = new Timer(DELAY, this);
        timer.start();
    }

    public JButton addFullScreen(){

        fullScreen = new JButton("Fullscreen");
        fullScreen.setVisible(false);
        timeInput.add(fullScreen,BorderLayout.NORTH);

        return fullScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mouseMotion -= DELAY;
        if(mouseMotion < 0)menuVisible(false);

        if(started) {
            timeRemaining = endTime - System.currentTimeMillis();
            countDown.setText(processClock(timeRemaining));
        }else {
            //countDown.setText(processClock((Main.drinkingTime - Main.startTime)* 60000 - spentTime));
        }

        /*
        if((System.currentTimeMillis() - waitStart) > waitTime * 1000){
            waitStart = System.currentTimeMillis();
            entities.add(new AutoEntity(this));
        }
        */

        //ArrayList<AutoEntity> tempEntities = new ArrayList<AutoEntity>(entities);
        //tempEntities.forEach(AutoEntity::calculate);

        repaint();
    }

    private String processClock(long time){
        if (time > 0) {
            int minutes = 0;
            int seconds = 0;
            int milliseconds;

            String mins;
            String secs;
            String mills;

            while (time >= 60000) {
                minutes += 1;
                time -= 60000;
            }
            if (minutes < 10) {
                mins = "00" + minutes;
            } else if (minutes < 100) {
                mins = "0" + minutes;
            } else {
                mins = "" + minutes;
            }

            while (time >= 1000) {
                seconds += 1;
                time -= 1000;
            }

            if (seconds < 10) {
                secs = "0" + seconds;
            } else {
                secs = "" + seconds;
            }
            milliseconds = (int) time;
            if (milliseconds < 10) {
                mills = "00" + milliseconds;
            } else if (milliseconds < 100) {
                mills = "0" + milliseconds;
            } else {
                mills = "" + milliseconds;
            }
            return "<html><center>" + mins + ":" + secs + ":" + mills + "</center></html>";
        }else {
            /*
            drinkText.setText("<html><center>LAST</center><center>DRINK</center><center>:)</center></html>");
            drinkText.setFont(new Font(null,Font.PLAIN,96));
            drinkContainer.setVisible(true);
            */
            return "<html><center>000:00:000</center></html>";

        }
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

            //timeInput.setVisible(false);
            fullScreen.setVisible(true);

            initApp();
        }
    }

    private void menuVisible(boolean isVisible){
        fullScreen.setVisible(isVisible);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        Toolkit.getDefaultToolkit().sync();

    }

    private class MAdapter extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e){
            mouseMotion = 5000;
            menuVisible(true);

        }

    }


}
