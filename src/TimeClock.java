import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
public class TimeClock {
    JFrame frame;
    JPanel panel;
    JButton clock_btn, stopwatch_btn;

    SimpleDateFormat time_format, date_format;//for am/pm, 24h date and time format
    JLabel time_label, date_label;
    String time, date;
    ImageIcon imageIcon;

    TimeClock() {
        frame = new JFrame();
        frame.setTitle("Clock");
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 430);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        imageIcon = new ImageIcon(getClass().getResource("clock.png"));
        frame.setIconImage(imageIcon.getImage());
//=============button panel=======================
        panel = new JPanel();
        panel.setBounds(0, 0, 520, 50);
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridLayout(1, 2));
        frame.add(panel);
//===================buttons==========================
        clock_btn = new JButton("Clock");
        clock_btn.setFocusable(false);
        clock_btn.setEnabled(false);
        clock_btn.setBackground(Color.BLACK);
        clock_btn.setForeground(Color.WHITE);
        clock_btn.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel.add(clock_btn);
        stopwatch_btn = new JButton("Stopwatch");
        stopwatch_btn.setFocusable(false);
        stopwatch_btn.setBackground(Color.BLACK);
        stopwatch_btn.setForeground(Color.WHITE);
        stopwatch_btn.setFont(new Font("Verdana", Font.PLAIN, 20));
        stopwatch_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == stopwatch_btn) {
                    frame.dispose();
                    StopWatch stopWatch = new StopWatch();
                }
            }
        });
        panel.add(stopwatch_btn);
//======================================================
//==================Time section=====================================
        
        time_label = new JLabel();
        time_format = new SimpleDateFormat("hh:mm:ss a");
        time = time_format.format(Calendar.getInstance().getTime());
        time_label.setText(time);
        time_label.setBounds(125, 90, 250, 100);
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setFont(new Font("Verdana", Font.PLAIN, 35));
        time_label.setBackground(Color.BLACK);
        time_label.setForeground(Color.WHITE);
        time_label.setOpaque(true);
        frame.add(time_label);
//==================Date section=====================================
        date_label = new JLabel();
        date_format = new SimpleDateFormat("E, MMMMM dd, yyyy");
        date = date_format.format(Calendar.getInstance().getTime());
        date_label.setText(date);
        date_label.setBounds(50, 180, 400, 50);
        date_label.setHorizontalAlignment(JTextField.CENTER);
        date_label.setFont(new Font("Verdana", Font.PLAIN, 30));
        date_label.setBackground(Color.BLACK);
        date_label.setForeground(Color.WHITE);
        date_label.setOpaque(true);
        frame.add(date_label);

        frame.setVisible(true);
        setTime();
    }
    public void setTime() {

        java.util.Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                // code to be executed continuously
                time = time_format.format(Calendar.getInstance().getTime());
                time_label.setText(time);

                date = date_format.format(Calendar.getInstance().getTime());
                date_label.setText(date);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 1000); // 1000 milliseconds = 1 second
    }
}
