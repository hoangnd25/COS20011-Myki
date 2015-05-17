import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class Application {
    private MykiCard mykiCard;
    private TabTravel tabTravel;
    private TabTopup tabTopup;

    private static Application instance = null;

    private Application(){

    }
    public static Application getInstance() {
        if(instance == null) {
            instance = new Application();
        }
        return instance;
    }

    public void run(){
        JFrame frame = this.createGUI();
        frame.setVisible(true);
        DataLoader.getInstance().setUpStationData();
        setUpMyki();
        updateData();
    }

    private JFrame createGUI(){
        JFrame frame = new JFrame("COS20011_A02_T009 - 209522X - Hoang Nguyen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Travel", null, this.tabTravel = new TabTravel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Top up", null, this.tabTopup = new TabTopup());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        frame.getContentPane().add(tabbedPane);
//        frame.pack();
        return frame;
    }

    private void setUpMyki(){
        mykiCard = new MykiCard(1,0);
    }

    public void updateData(){
        tabTopup.updateData();
        tabTravel.updateData();
    }

    public MykiCard getMykiCard() {
        return mykiCard;
    }
}
