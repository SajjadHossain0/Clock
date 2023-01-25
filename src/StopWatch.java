import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class StopWatch {
    JFrame frame;
    JLabel time_label;
    JPanel panel;
    JButton start_btn, restart_btn, clock_btn, stopwatch_btn;
    Timer timer;
    int time = 0;
    int second = 0;
    int minute = 0;
    int hour = 0;
    boolean start = false;
    String second_str = String.format("%02d", second);
    String minute_str = String.format("%02d", minute);
    String hour_str = String.format("%02d", hour);
    ImageIcon imageIcon;

    StopWatch() {
        frame = new JFrame();
        frame.setTitle("Stopwatch");
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 430);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        imageIcon = new ImageIcon(getClass().getResource("stopwatch.png"));
        frame.setIconImage(imageIcon.getImage());
//====================Button Panel==================
        panel = new JPanel();
        panel.setBounds(0, 0, 520, 50);
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridLayout(1, 2));
        frame.add(panel);
//=================Buttons===================
        clock_btn = new JButton("Clock");
        clock_btn.setFocusable(false);
        clock_btn.setBackground(Color.BLACK);
        clock_btn.setForeground(Color.WHITE);
        clock_btn.setFont(new Font("Verdana", Font.PLAIN, 20));
        clock_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == clock_btn) {
                    frame.dispose();
                    TimeClock time = new TimeClock();
                }
            }
        });
        panel.add(clock_btn);
        stopwatch_btn = new JButton("Stopwatch");
        stopwatch_btn.setFocusable(false);
        stopwatch_btn.setEnabled(false);
        stopwatch_btn.setBackground(Color.BLACK);
        stopwatch_btn.setForeground(Color.WHITE);
        stopwatch_btn.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel.add(stopwatch_btn);

        start_btn = new JButton("Start");
        start_btn.setBounds(120, 210, 100, 50);
        start_btn.setFocusable(false);
        start_btn.setFont(new Font("Verdana", Font.PLAIN, 20));
        start_btn.setBackground(Color.BLACK);
        start_btn.setForeground(Color.WHITE);
        start_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == start_btn) {
                    timer.start();

                    if (start == false) {
                        start = true;
                        start_btn.setText("Stop");
                        timer.start();
                    } else {
                        start = false;
                        start_btn.setText("Start");
                        timer.stop();
                    }
                }
            }
        });
        frame.add(start_btn);

        restart_btn = new JButton("Reset");
        restart_btn.setBounds(280, 210, 100, 50);
        restart_btn.setFocusable(false);
        restart_btn.setFont(new Font("Verdana", Font.PLAIN, 20));
        restart_btn.setBackground(Color.BLACK);
        restart_btn.setForeground(Color.WHITE);
        restart_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == restart_btn) {
                    timer.stop();
                    time = 0;
                    second = 0;
                    minute = 0;
                    hour = 0;
                    second_str = String.format("%02d", second);
                    minute_str = String.format("%02d", minute);
                    hour_str = String.format("%02d", hour);
                    time_label.setText(hour_str + ":" + minute_str + ":" + second_str);
                }
            }
        });
        frame.add(restart_btn);
//===============================================
//=====================Timer=================================
        time_label = new JLabel();
        time_label.setText(hour_str + ":" + minute_str + ":" + second_str);
        time_label.setBounds(125, 90, 250, 100);
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setFont(new Font("Verdana", Font.PLAIN, 45));
        time_label.setBackground(Color.BLACK);
        time_label.setForeground(Color.WHITE);
        time_label.setOpaque(true);
        frame.add(time_label);

        frame.setVisible(true);
        setTimer();
    }
    public void setTimer(){
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                time = time + 1000;
                second = ((time % (60 * 60 * 1000)) % (60 * 1000)) / 1000;
                minute = (time % (60 * 60 * 1000)) / (60 * 1000);
                hour = time / (60 * 60 * 1000);

                second_str = String.format("%02d", second);
                minute_str = String.format("%02d", minute);
                hour_str = String.format("%02d", hour);
                time_label.setText(hour_str + ":" + minute_str + ":" + second_str);
            }
        });
    }
}
