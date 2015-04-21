import javax.swing.*;
import java.awt.*;

public class TabTravel extends JPanel{

    final int MARGIN_TOP = 20;
    final int MARGIN_LEFT = 20;
    final int MARGIN_RIGHT = -MARGIN_LEFT;
    final Color ERROR_COLOR = Color.red;

    public TabTravel(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel lblStationOn = new JLabel("Touch on station");
        this.add(lblStationOn);

        JTextField txtStationOn =  new JTextField(20);
        StationAutoCompleter stationAutoCompleterOn = new StationAutoCompleter(txtStationOn);
        this.add(txtStationOn);

        JButton btnTouchOn = new JButton("Touch on");
        this.add(btnTouchOn);

        JLabel lblStationOff = new JLabel("Touch off station");
        this.add(lblStationOff);

        JTextField txtStationOff =  new JTextField(20);
        StationAutoCompleter stationAutoCompleterOff = new StationAutoCompleter(txtStationOff);
        this.add(txtStationOff);

        JButton btnTouchOff = new JButton("Touch off");
        this.add(btnTouchOff);

        JLabel errTouchOn = CreateErrorLabel();
        this.add(errTouchOn);

        JLabel errTouchOff = CreateErrorLabel();
        this.add(errTouchOff);

        JLabel lblTravelHistory = new JLabel("Travel History");
        this.add(lblTravelHistory);
        JList lstTravelHistory = new JList();
        JScrollPane scrollPane = new JScrollPane(lstTravelHistory);
        scrollPane.setViewportView(lstTravelHistory);
        lstTravelHistory.setBackground(Color.white);
//        lstTravelHistory.setListData(new String[]{"a","b","c","c","c","c","c","c","c","c","c","c","c","c","c","c","c","c","c","c"});
        this.add(scrollPane);

        //Set layout for touch on
        layout.putConstraint(SpringLayout.NORTH, txtStationOn, 2*MARGIN_TOP, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, txtStationOn, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, txtStationOn, MARGIN_RIGHT, SpringLayout.WEST, btnTouchOn);

        layout.putConstraint(SpringLayout.WEST, lblStationOn, 2, SpringLayout.WEST, txtStationOn);
        layout.putConstraint(SpringLayout.SOUTH, lblStationOn, 0, SpringLayout.NORTH, txtStationOn);

        layout.putConstraint(SpringLayout.EAST, btnTouchOn, MARGIN_RIGHT, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.BASELINE, btnTouchOn, 0, SpringLayout.BASELINE, txtStationOn);


        //Set layout for touch off
        layout.putConstraint(SpringLayout.NORTH, txtStationOff, 4*MARGIN_TOP, SpringLayout.SOUTH, txtStationOn);
        layout.putConstraint(SpringLayout.WEST, txtStationOff, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, txtStationOff, MARGIN_RIGHT, SpringLayout.WEST, btnTouchOff);

        layout.putConstraint(SpringLayout.WEST, lblStationOff, 2, SpringLayout.WEST, txtStationOff);
        layout.putConstraint(SpringLayout.SOUTH, lblStationOff, 0, SpringLayout.NORTH, txtStationOff);

        layout.putConstraint(SpringLayout.EAST, btnTouchOff, MARGIN_RIGHT, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.BASELINE, btnTouchOff, 0, SpringLayout.BASELINE, txtStationOff);


        //Set layout for history list
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 4*MARGIN_TOP , SpringLayout.SOUTH, txtStationOff);
        layout.putConstraint(SpringLayout.SOUTH, scrollPane, -MARGIN_TOP , SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, scrollPane, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WIDTH, scrollPane, -(MARGIN_LEFT * 2), SpringLayout.WIDTH, this);

        layout.putConstraint(SpringLayout.WEST, lblTravelHistory, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, lblTravelHistory, 0, SpringLayout.NORTH, scrollPane);


        // Set layout for error labels
        layout.putConstraint(SpringLayout.NORTH, errTouchOn, 0, SpringLayout.SOUTH, txtStationOn);
        layout.putConstraint(SpringLayout.NORTH, errTouchOff, 0, SpringLayout.SOUTH, txtStationOff);

        layout.putConstraint(SpringLayout.WEST, errTouchOn, 2, SpringLayout.WEST, txtStationOn);
        layout.putConstraint(SpringLayout.WEST, errTouchOff, 2, SpringLayout.WEST, txtStationOff);
    }

    private JLabel CreateErrorLabel(){
        JLabel label = new JLabel();
        label.setForeground(ERROR_COLOR);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        return label;
    }
}
