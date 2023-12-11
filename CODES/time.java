import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class time {

    JFrame frame;
    SaveLoad save = new SaveLoad(this);
     JLabel label;
    Instant windowCloseTime;

    long elapsedTime = 7 * 24 * 60 * 60 * 1000;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                time window = new time();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public time() {
        initialize();

        // Load saved data
        save.load();

        // Start background timer
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::updateElapsedTime, 0, 1000, TimeUnit.MILLISECONDS);
    }

    private void updateElapsedTime() {
        if (elapsedTime > 0) {
            elapsedTime -= 1000;
            updateLabel();
        }
    }

    private void updateLabel() {
        long hours = elapsedTime / 3600000;
        long minutes = (elapsedTime / 60000) % 60;
        long seconds = (elapsedTime / 1000) % 60;

        String secondsText = String.format("%02d", seconds);
        String minutesText = String.format("%02d", minutes);
        String hoursText = String.format("%02d", hours);

        SwingUtilities.invokeLater(() -> label.setText(hoursText + ":" + minutesText + ":" + secondsText));
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        label = new JLabel("New label");
        label.setBounds(168, 111, 88, 34);
        frame.getContentPane().add(label);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Save elapsed time to serialized file
                windowCloseTime = Instant.now();
                save.save();
                System.exit(0); // Close the application
            }
        });
    }
}
