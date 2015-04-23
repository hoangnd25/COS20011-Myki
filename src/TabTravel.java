import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TabTravel extends JPanel{

    final int MARGIN_TOP = 20;
    final int MARGIN_LEFT = 20;
    final int MARGIN_RIGHT = -MARGIN_LEFT;
    final Color ERROR_COLOR = Color.red;
    final String DATE_FORMAT = "dd/MM/yy";
    final String TIME_FORMAT = "HH:mm";

    JLabel errTouchOn, errTouchOff;
    JTextField txtDateTouchOn, txtTimeTouchOn, txtStationOn,
               txtDateTouchOff, txtTimeTouchOff, txtStationOff;
    JList lstTravelHistory;

    public TabTravel(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel lblDateTouchOn = new JLabel("Touch on time");
        txtDateTouchOn =  new JTextField(6);
        txtDateTouchOn.setText(getDateFormat().format(getTimeNow()));
        txtTimeTouchOn =  new JTextField(4);
        txtTimeTouchOn.setText(getTimeFormat().format(getTimeNow()));
        this.add(lblDateTouchOn);
        this.add(txtDateTouchOn);
        this.add(txtTimeTouchOn);

        JLabel lblStationOn = new JLabel("Touch on station");
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
        txtDateTouchOff.setText(getDateFormat().format(getTimeNow()));
        txtTimeTouchOff =  new JTextField(4);
        txtTimeTouchOff.setText(getTimeFormat().format(getTimeNow()));
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
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
        return label;
    }

    private void touchOn(){
        if(checkTouchOnInput()){
            Station station = getStation(txtStationOn.getText());
            Application.getInstance().getMykiCard().touchOn(getTime(txtDateTouchOn.getText(), txtTimeTouchOn.getText()), station);
            updateData();
        }
    }

    private void touchOff(){
        if(checkTouchOffInput()){
            Station station = getStation(txtStationOff.getText());
            Application.getInstance().getMykiCard().touchOff(getTime(txtDateTouchOff.getText(), txtTimeTouchOff.getText()), station);
            updateData();
        }
    }

    private boolean checkTouchOnInput(){
        clearError();
        boolean isValid = true;
        String errMessage = "";

        if(Application.getInstance().getMykiCard().getBalance() < 10){
            errMessage += "Your Myki card balance is less than $10. Please recharge before using it <BR>";
            isValid = false;
        }

        Date touchOnTime = getTime(txtDateTouchOn.getText(), txtTimeTouchOn.getText());
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

    private boolean checkTouchOffInput(){
        clearError();
        boolean isValid = true;
        String errMessage = "";

        Date touchOnTime = getTime(txtDateTouchOff.getText(), txtTimeTouchOff.getText());
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


    private Station getStation(String name){
        return DataLoader.getInstance().getStation(name);
    }

    private void clearError(){
        errTouchOn.setText("");
        errTouchOff.setText("");
    }

    private Date getTime(String date, String time){
        try{
            String combinedDateTime = String.format("%s %s", date, time);
            SimpleDateFormat formatter = new SimpleDateFormat(String.format("%s %s", DATE_FORMAT, TIME_FORMAT));

            return formatter.parse(combinedDateTime);
        }catch (Exception ex){
            return null;
        }
    }

    public void updateData(){
        MykiCard mykiCard = Application.getInstance().getMykiCard();
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

        java.util.List listData = new ArrayList<String>();
        for(TravelLog log : mykiCard.getTravelLogs()){
            listData.add(log.toString());
        }
        lstTravelHistory.setListData(listData.toArray());
    }

    private SimpleDateFormat getDateFormat(){
        return new SimpleDateFormat(DATE_FORMAT);
    }

    private SimpleDateFormat getTimeFormat(){
        return new SimpleDateFormat(TIME_FORMAT);
    }

    private Date getTimeNow(){
        return Calendar.getInstance().getTime();
    }
}
