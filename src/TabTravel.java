import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * The type Tab travel.
 */
public class TabTravel extends JPanel{

    private final int MARGIN_TOP = 20;
    private final int MARGIN_LEFT = 20;
    private final int MARGIN_RIGHT = -MARGIN_LEFT;
    private final Color ERROR_COLOR = Color.red;

    private JLabel errTouchOn, errTouchOff;
    private JTextField txtDateTouchOn, txtTimeTouchOn, txtStationOn,
               txtDateTouchOff, txtTimeTouchOff, txtStationOff,
               txtHistoryStartDate, txtHistoryEndDate;
    private JList lstTravelHistory;

    /**
     * Instantiates a new Tab travel and create GUI elements
     */
    public TabTravel(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel lblDateTouchOn = new JLabel("Touch on time");
        txtDateTouchOn =  new JTextField(6);
        txtDateTouchOn.setText(DateUtils.getDateFormat().format(DateUtils.getTimeNow()));
        txtTimeTouchOn =  new JTextField(4);
        txtTimeTouchOn.setText(DateUtils.getTimeFormat().format(DateUtils.getTimeNow()));
        this.add(lblDateTouchOn);
        this.add(txtDateTouchOn);
        this.add(txtTimeTouchOn);

        JLabel lblStationOn = new JLabel("Touch on station (Start typing to open auto completion)");
        this.add(lblStationOn);

        txtStationOn =  new JTextField(20);
        StationAutoCompleter stationAutoCompleterOn = new StationAutoCompleter(txtStationOn);
        this.add(txtStationOn);

        JButton btnTouchOn = new JButton("Touch on");
        btnTouchOn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                touchOn();
            }
        });
        this.add(btnTouchOn);

        JLabel lblDateTouchOff = new JLabel("Touch off time");
        txtDateTouchOff =  new JTextField(6);
        txtDateTouchOff.setText(DateUtils.getDateFormat().format(DateUtils.getTimeNow()));
        txtTimeTouchOff =  new JTextField(4);
        txtTimeTouchOff.setText(DateUtils.getTimeFormat().format(DateUtils.getTimeNow()));
        this.add(lblDateTouchOff);
        this.add(txtDateTouchOff);
        this.add(txtTimeTouchOff);

        JLabel lblStationOff = new JLabel("Touch off station");
        this.add(lblStationOff);

        txtStationOff =  new JTextField(20);
        StationAutoCompleter stationAutoCompleterOff = new StationAutoCompleter(txtStationOff);
        this.add(txtStationOff);

        JButton btnTouchOff = new JButton("Touch off");
        btnTouchOff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                touchOff();
            }
        });
        this.add(btnTouchOff);

        errTouchOn = CreateErrorLabel();
        this.add(errTouchOn);

        errTouchOff = CreateErrorLabel();
        this.add(errTouchOff);

        JLabel lblTravelHistory = new JLabel("Travel History");
        txtHistoryStartDate = new JTextField();
        txtHistoryStartDate.setText(DateUtils.getDateFormat().format(DateUtils.getTimeNow()));
        txtHistoryEndDate = new JTextField();
        Calendar tomorrowDate = Calendar.getInstance();
        tomorrowDate.add(Calendar.DATE, 1);
        txtHistoryEndDate.setText(DateUtils.getDateFormat().format(tomorrowDate.getTime()));
        this.add(txtHistoryStartDate);
        this.add(txtHistoryEndDate);
        JButton btnUpdateHistory = new JButton("Update");
        btnUpdateHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        });
        this.add(btnUpdateHistory);
        this.add(lblTravelHistory);
        lstTravelHistory = new JList();
        JScrollPane scrollPane = new JScrollPane(lstTravelHistory);
        scrollPane.setViewportView(lstTravelHistory);
        lstTravelHistory.setBackground(Color.white);
//        lstTravelHistory.setListData(new String[]{"a","b","c","c","c","c","c","c","c","c","c","c","c","c","c","c","c","c","c","c"});
        this.add(scrollPane);

        //Set layout for touch on
        layout.putConstraint(SpringLayout.NORTH, lblDateTouchOn, MARGIN_TOP, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, txtDateTouchOn, 0, SpringLayout.SOUTH, lblDateTouchOn);
        layout.putConstraint(SpringLayout.NORTH, txtTimeTouchOn, 0, SpringLayout.SOUTH, lblDateTouchOn);

        layout.putConstraint(SpringLayout.WEST, lblDateTouchOn, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, txtDateTouchOn, 0, SpringLayout.WEST, lblDateTouchOn);
        layout.putConstraint(SpringLayout.WEST, txtTimeTouchOn, 0, SpringLayout.EAST, txtDateTouchOn);

        layout.putConstraint(SpringLayout.NORTH, txtStationOn, MARGIN_TOP, SpringLayout.SOUTH, txtDateTouchOn);
        layout.putConstraint(SpringLayout.WEST, txtStationOn, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, txtStationOn, MARGIN_RIGHT, SpringLayout.WEST, btnTouchOn);

        layout.putConstraint(SpringLayout.WEST, lblStationOn, 2, SpringLayout.WEST, txtStationOn);
        layout.putConstraint(SpringLayout.SOUTH, lblStationOn, 0, SpringLayout.NORTH, txtStationOn);

        layout.putConstraint(SpringLayout.EAST, btnTouchOn, MARGIN_RIGHT, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.BASELINE, btnTouchOn, 0, SpringLayout.BASELINE, txtStationOn);


        //Set layout for touch off
        layout.putConstraint(SpringLayout.NORTH, lblDateTouchOff, 2*MARGIN_TOP, SpringLayout.SOUTH, txtStationOn);
        layout.putConstraint(SpringLayout.NORTH, txtDateTouchOff, 0, SpringLayout.SOUTH, lblDateTouchOff);
        layout.putConstraint(SpringLayout.NORTH, txtTimeTouchOff, 0, SpringLayout.SOUTH, lblDateTouchOff);

        layout.putConstraint(SpringLayout.WEST, lblDateTouchOff, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, txtDateTouchOff, 0, SpringLayout.WEST, lblDateTouchOff);
        layout.putConstraint(SpringLayout.WEST, txtTimeTouchOff, 0, SpringLayout.EAST, txtDateTouchOff);

        layout.putConstraint(SpringLayout.NORTH, txtStationOff, MARGIN_TOP, SpringLayout.SOUTH, txtDateTouchOff);
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
        layout.putConstraint(SpringLayout.SOUTH, lblTravelHistory, -7, SpringLayout.NORTH, scrollPane);
        layout.putConstraint(SpringLayout.BASELINE, txtHistoryStartDate, 0, SpringLayout.BASELINE, lblTravelHistory);
        layout.putConstraint(SpringLayout.BASELINE, txtHistoryEndDate, 0, SpringLayout.BASELINE, lblTravelHistory);
        layout.putConstraint(SpringLayout.BASELINE, btnUpdateHistory, 0, SpringLayout.BASELINE, lblTravelHistory);
        layout.putConstraint(SpringLayout.WEST, txtHistoryStartDate, MARGIN_LEFT, SpringLayout.EAST, lblTravelHistory);
        layout.putConstraint(SpringLayout.WEST, txtHistoryEndDate, MARGIN_LEFT, SpringLayout.EAST, txtHistoryStartDate);
        layout.putConstraint(SpringLayout.WEST, btnUpdateHistory, MARGIN_LEFT, SpringLayout.EAST, txtHistoryEndDate);


        // Set layout for error labels
        layout.putConstraint(SpringLayout.NORTH, errTouchOn, 0, SpringLayout.SOUTH, txtStationOn);
        layout.putConstraint(SpringLayout.NORTH, errTouchOff, 0, SpringLayout.SOUTH, txtStationOff);

        layout.putConstraint(SpringLayout.WEST, errTouchOn, 2, SpringLayout.WEST, txtStationOn);
        layout.putConstraint(SpringLayout.WEST, errTouchOff, 2, SpringLayout.WEST, txtStationOff);
    }

    /**
     * Convenient method for creating error label
     *
     * @return the label
     */
    private JLabel CreateErrorLabel(){
        JLabel label = new JLabel();
        label.setForeground(ERROR_COLOR);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
        return label;
    }

    /**
     * Handle touch on button clicked
     */
    private void touchOn(){
        if(checkTouchOnInput()){
            Station station = getStation(txtStationOn.getText());
            Application.getInstance().getMykiCard().touchOn(DateUtils.getTime(txtDateTouchOn.getText(), txtTimeTouchOn.getText()), station);
            updateData();
        }
    }
    /**
     * Handle touch off button clicked
     */
    private void touchOff(){
        if(checkTouchOffInput()){
            Station station = getStation(txtStationOff.getText());
            Application.getInstance().getMykiCard().touchOff(DateUtils.getTime(txtDateTouchOff.getText(), txtTimeTouchOff.getText()), station);
            updateData();
        }
    }

    /**
     * Validate inputs for touch on
     */
    private boolean checkTouchOnInput(){
        clearError();
        boolean isValid = true;
        String errMessage = "";

        if(Application.getInstance().getMykiCard().getBalance() < 10){
            errMessage += "Your Myki card balance is less than $10. Please recharge before using it <BR>";
            isValid = false;
        }

        Date touchOnTime = DateUtils.getTime(txtDateTouchOn.getText(), txtTimeTouchOn.getText());
        if(touchOnTime == null){
            errMessage += "Touch on date time is not valid <BR>";
            isValid = false;
        }

        Station station = getStation(txtStationOn.getText());
        if(station == null) {
            errMessage += "Station cannot be found <BR>";
            isValid = false;
        }

        errTouchOn.setText("<HTML>" + errMessage + "</HTML>");
        return isValid;
    }

    /**
     * Validate inputs for touch off
     */
    private boolean checkTouchOffInput(){
        clearError();
        boolean isValid = true;
        String errMessage = "";

        Date touchOnTime = DateUtils.getTime(txtDateTouchOff.getText(), txtTimeTouchOff.getText());
        if(touchOnTime == null){
            errMessage += "Touch on date time is not valid <BR>";
            isValid = false;
        }

        Station station = getStation(txtStationOff.getText());
        if(station == null) {
            errMessage += "Station cannot be found <BR>";
            isValid = false;
        }

        errTouchOff.setText("<HTML>" + errMessage + "</HTML>");
        return isValid;
    }

    /**
     * Short  hand method for getting station by name from DataLoader
     */
    private Station getStation(String name){
        return DataLoader.getInstance().getStation(name);
    }

    /**
     * Clear error labels
     */
    private void clearError(){
        errTouchOn.setText("");
        errTouchOff.setText("");
    }

    /**
     * Update data of the GUI
     */
    public void updateData(){
        MykiCard mykiCard = Application.getInstance().getMykiCard();
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

        java.util.List listData = new ArrayList<String>();

        Date startDate = DateUtils.getDate(txtHistoryStartDate.getText());
        Date endDate = DateUtils.getDate(txtHistoryEndDate.getText());

        if(startDate == null || endDate == null){
            lstTravelHistory.setListData(listData.toArray());
            return;
        }

        for(TravelLog log : mykiCard.getTravelLogs()){
            if(log.getTouchOffTime() != null){
                if(log.getTouchOnTime().compareTo(startDate) >= 0 && endDate.compareTo(log.getTouchOffTime()) >= 0)
                    listData.add(log.toString());
            }else{
                if(log.getTouchOnTime().compareTo(startDate) >= 0)
                    listData.add(log.toString());
            }
        }
        lstTravelHistory.setListData(listData.toArray());
    }
}
