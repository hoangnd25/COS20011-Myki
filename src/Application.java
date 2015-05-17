import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * The application class that allow creation of GUI elements, starting application and accessing the current myki card
 */
public class Application {
    private MykiCard mykiCard;
    private TabTravel tabTravel;
    private TabTopup tabTopup;

    private static Application instance = null;

    /**
     * Disallow instantiation of the class
     */
    private Application(){

    }

    /**
     * Gets instance.
     *
     * @return the instance of the application
     */
    public static Application getInstance() {
        if(instance == null) {
            instance = new Application();
        }
        return instance;
    }

    /**
     * Main function to start the application
     */
    public void run(){
        JFrame frame = this.createGUI();
        frame.setVisible(true);
        DataLoader.getInstance().setUpStationData();
        setUpMyki();
        updateData();
    }

    /**
     * Function to create GUI
     * @return the application frame
     */
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

    /**
     * Update all GUI data.
     * Call updateData functions of all the tabs
     */
    public void updateData(){
        tabTopup.updateData();
        tabTravel.updateData();
    }

    /**
     * Gets current active myki card.
     *
     * @return the myki card
     */
    public MykiCard getMykiCard() {
        return mykiCard;
    }
}
