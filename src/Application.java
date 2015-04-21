import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Application {

    public void run(){
        JFrame frame = this.createGUI();
        frame.setVisible(true);
    }

    protected JFrame createGUI(){
        JFrame frame = new JFrame("209522X - Hoang Nguyen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Travel", null, new TabTravel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Top up", null, new TabTopup());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        frame.getContentPane().add(tabbedPane);
//        frame.pack();
        return frame;
    }
}
