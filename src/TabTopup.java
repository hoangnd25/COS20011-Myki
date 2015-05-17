import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TabTopup extends JPanel{

    final int MARGIN_TOP = 20;
    final int MARGIN_BOTTOM = -MARGIN_TOP;
    final int MARGIN_LEFT = 20;
    final int MARGIN_RIGHT = -MARGIN_LEFT;
    final int LABEL_MAX_WIDTH = 150;
    final int ROW_SPACE = 20;
    final Color ERROR_COLOR = Color.red;

    JLabel lblBalanceNumber, lblMessage,
           errExpireDate, errCardNumber, errHolderName, errAddress, errAmount
    ;
    JTextField txtCardNumber, txtHolderName, txtExpireDate, txtAddress, txtAmount,
               txtHistoryStartDate, txtHistoryEndDate;
    JList lstTopupHistory;

    public TabTopup()
    {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel lblBalance = new JLabel("Balance:");
        this.add(lblBalance);
        lblBalanceNumber = new JLabel();
        lblBalanceNumber.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        this.add(lblBalanceNumber);


        JLabel lblCardNumber = new JLabel("Card number:");
        this.add(lblCardNumber);
        txtCardNumber =  new JTextField(20);
        this.add(txtCardNumber);

        JLabel lblHolderName = new JLabel("Holder name:");
        this.add(lblHolderName);
        txtHolderName =  new JTextField(20);
        this.add(txtHolderName);

        JLabel lblExpireDate = new JLabel("Expiration date:");
        this.add(lblExpireDate);
        txtExpireDate =  new JTextField(20);
        this.add(txtExpireDate);

        JLabel lblAddress = new JLabel("Billing address:");
        this.add(lblAddress);
        txtAddress =  new JTextField(20);
        this.add(txtAddress);

        JLabel lblAmount = new JLabel("Amount:");
        this.add(lblAmount);
        txtAmount =  new JTextField(20);
        this.add(txtAmount);

        JButton btnTopUp = new JButton("Top Up");
        btnTopUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topUp();
            }
        });
        this.add(btnTopUp);

        errCardNumber = CreateErrorLabel();
        this.add(errCardNumber);

        errHolderName = CreateErrorLabel();
        this.add(errHolderName);

        errExpireDate = CreateErrorLabel();
        this.add(errExpireDate);

        errAddress = CreateErrorLabel();
        this.add(errAddress);

        errAmount = CreateErrorLabel();
        this.add(errAmount);

        lblMessage = new JLabel("");
        this.add(lblMessage);

        JLabel lblTopupHistory = new JLabel("Top Up History");
        this.add(lblTopupHistory);
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

        lstTopupHistory = new JList();
        JScrollPane scrollPane = new JScrollPane(lstTopupHistory);
        scrollPane.setViewportView(lstTopupHistory);
        lstTopupHistory.setBackground(Color.white);
//        lstTopupHistory.setListData(new String[]{"a","b","c","c","c","c","c","c","c","c","c","c","c","c"});
        this.add(scrollPane);

        // Set labels margin left
        layout.putConstraint(SpringLayout.WEST, lblBalance, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lblCardNumber, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lblHolderName, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lblExpireDate, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lblAddress, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, lblAmount, MARGIN_LEFT, SpringLayout.WEST, this);

        // Set text inputs margin left
        layout.putConstraint(SpringLayout.WEST, lblBalanceNumber, LABEL_MAX_WIDTH, SpringLayout.WEST, lblCardNumber);
        layout.putConstraint(SpringLayout.WEST, txtCardNumber, LABEL_MAX_WIDTH, SpringLayout.WEST, lblCardNumber);
        layout.putConstraint(SpringLayout.WEST, txtHolderName, LABEL_MAX_WIDTH, SpringLayout.WEST, lblHolderName);
        layout.putConstraint(SpringLayout.WEST, txtExpireDate, LABEL_MAX_WIDTH, SpringLayout.WEST, lblExpireDate);
        layout.putConstraint(SpringLayout.WEST, txtAddress, LABEL_MAX_WIDTH, SpringLayout.WEST, lblAddress);
        layout.putConstraint(SpringLayout.WEST, txtAmount, LABEL_MAX_WIDTH, SpringLayout.WEST, lblAmount);

        // Set text inputs margin right
        layout.putConstraint(SpringLayout.EAST, txtCardNumber, MARGIN_RIGHT, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.EAST, txtHolderName, MARGIN_RIGHT, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.EAST, txtExpireDate, MARGIN_RIGHT, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.EAST, txtAddress, MARGIN_RIGHT, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.EAST, txtAmount, MARGIN_RIGHT, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.EAST, btnTopUp, MARGIN_RIGHT, SpringLayout.EAST, this);

        // Set space between inputs
        layout.putConstraint(SpringLayout.NORTH, txtCardNumber, ROW_SPACE, SpringLayout.SOUTH, lblBalanceNumber);
        layout.putConstraint(SpringLayout.NORTH, txtHolderName, ROW_SPACE, SpringLayout.SOUTH, txtCardNumber);
        layout.putConstraint(SpringLayout.NORTH, txtExpireDate, ROW_SPACE, SpringLayout.SOUTH, txtHolderName);
        layout.putConstraint(SpringLayout.NORTH, txtAddress, ROW_SPACE, SpringLayout.SOUTH, txtExpireDate);
        layout.putConstraint(SpringLayout.NORTH, txtAmount, ROW_SPACE, SpringLayout.SOUTH, txtAddress);
        layout.putConstraint(SpringLayout.NORTH, btnTopUp, ROW_SPACE, SpringLayout.SOUTH, txtAmount);

        // Align labels and inputs baseline
        layout.putConstraint(SpringLayout.BASELINE, lblBalance, 0, SpringLayout.BASELINE, lblBalanceNumber);
        layout.putConstraint(SpringLayout.BASELINE, lblCardNumber, 0, SpringLayout.BASELINE, txtCardNumber);
        layout.putConstraint(SpringLayout.BASELINE, lblHolderName, 0, SpringLayout.BASELINE, txtHolderName);
        layout.putConstraint(SpringLayout.BASELINE, lblExpireDate, 0, SpringLayout.BASELINE, txtExpireDate);
        layout.putConstraint(SpringLayout.BASELINE, lblAddress, 0, SpringLayout.BASELINE, txtAddress);
        layout.putConstraint(SpringLayout.BASELINE, lblAmount, 0, SpringLayout.BASELINE, txtAmount);

        // Set margin top
        layout.putConstraint(SpringLayout.NORTH, lblBalanceNumber, MARGIN_TOP , SpringLayout.NORTH, this);

        // Error labels
        layout.putConstraint(SpringLayout.SOUTH, errCardNumber, 0, SpringLayout.NORTH, txtCardNumber);
        layout.putConstraint(SpringLayout.SOUTH, errHolderName, 0, SpringLayout.NORTH, txtHolderName);
        layout.putConstraint(SpringLayout.SOUTH, errExpireDate, 0, SpringLayout.NORTH, txtExpireDate);
        layout.putConstraint(SpringLayout.SOUTH, errAddress, 0, SpringLayout.NORTH, txtAddress);
        layout.putConstraint(SpringLayout.SOUTH, errAmount, 0, SpringLayout.NORTH, txtAmount);

        layout.putConstraint(SpringLayout.EAST, errCardNumber, -2, SpringLayout.EAST, txtCardNumber);
        layout.putConstraint(SpringLayout.EAST, errHolderName, -2, SpringLayout.EAST, txtHolderName);
        layout.putConstraint(SpringLayout.EAST, errExpireDate, -2, SpringLayout.EAST, txtExpireDate);
        layout.putConstraint(SpringLayout.EAST, errAddress, -2, SpringLayout.EAST, txtAddress);
        layout.putConstraint(SpringLayout.EAST, errAmount, -2, SpringLayout.EAST, txtAmount);


        layout.putConstraint(SpringLayout.BASELINE, lblMessage, 0, SpringLayout.BASELINE, btnTopUp);
        layout.putConstraint(SpringLayout.EAST, lblMessage, MARGIN_RIGHT/2 , SpringLayout.WEST, btnTopUp);

        layout.putConstraint(SpringLayout.NORTH, scrollPane, (int)Math.round(ROW_SPACE *1.5), SpringLayout.SOUTH, btnTopUp);
        layout.putConstraint(SpringLayout.SOUTH, scrollPane, MARGIN_BOTTOM , SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, scrollPane, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WIDTH, scrollPane, -(MARGIN_LEFT * 2), SpringLayout.WIDTH, this);

        layout.putConstraint(SpringLayout.WEST, lblTopupHistory, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, lblTopupHistory, -7, SpringLayout.NORTH, scrollPane);
        layout.putConstraint(SpringLayout.BASELINE, txtHistoryStartDate, 0, SpringLayout.BASELINE, lblTopupHistory);
        layout.putConstraint(SpringLayout.BASELINE, txtHistoryEndDate, 0, SpringLayout.BASELINE, lblTopupHistory);
        layout.putConstraint(SpringLayout.BASELINE, btnUpdateHistory, 0, SpringLayout.BASELINE, lblTopupHistory);
        layout.putConstraint(SpringLayout.WEST, txtHistoryStartDate, MARGIN_LEFT, SpringLayout.EAST, lblTopupHistory);
        layout.putConstraint(SpringLayout.WEST, txtHistoryEndDate, MARGIN_LEFT, SpringLayout.EAST, txtHistoryStartDate);
        layout.putConstraint(SpringLayout.WEST, btnUpdateHistory, MARGIN_LEFT, SpringLayout.EAST, txtHistoryEndDate);

    }

    private JLabel CreateErrorLabel(){
        JLabel label = new JLabel();
        label.setForeground(ERROR_COLOR);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        return label;
    }

    private void topUp(){
        if(checkCreditCardInput()){
            Application.getInstance().getMykiCard().topUp(getTopUpAmount());
            updateData();
            lblMessage.setText("Your top up is successful");
        }else{
            lblMessage.setText("Please retype your credit information & amount");
        }
    }

    private boolean checkCreditCardInput(){
        clearError();
        boolean isInputValid = true;

        if(txtHolderName.getText().trim().equals("")){
            errHolderName.setText("Account name should not be empty or only whitespaces");
            isInputValid = false;
        }

        if(txtAddress.getText().trim().equals("")){
            errAddress.setText("Billing address should not be empty or only whitespaces");
            isInputValid = false;
        }

        int cardNumber = -1;
        try{
            cardNumber = Integer.parseInt(txtCardNumber.getText());
        }catch (Exception ex){
            errCardNumber.setText("Card number should be positive number");
            isInputValid = false;
        }
        if(cardNumber < 0) {
            errCardNumber.setText("Card number should be positive number");
            isInputValid = false;
        }

        int expireDate = -1;
        try{
            expireDate = Integer.parseInt(txtExpireDate.getText());
        }catch (Exception ex){
            errExpireDate.setText("Expiration date should be positive number");
            isInputValid = false;
        }
        if(expireDate < 0){
            errExpireDate.setText("Expiration date should be positive number");
            isInputValid = false;
        }

        if(getTopUpAmount() < 0){
            errAmount.setText("Top up amount should be greater than 0");
            isInputValid = false;
        }

        return isInputValid;
    }

    private double getTopUpAmount(){
        try{
            return Double.parseDouble(txtAmount.getText());
        }catch (Exception ex){
            return -1;
        }
    }

    private void clearError(){
        errHolderName.setText("");
        errCardNumber.setText("");
        errAddress.setText("");
        errAmount.setText("");
        errExpireDate.setText("");
        lblMessage.setText("");
    }

    public void updateData(){
        MykiCard mykiCard = Application.getInstance().getMykiCard();
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

        java.util.List listData = new ArrayList<String>();

        Date startDate = DateUtils.getDate(txtHistoryStartDate.getText());
        Date endDate = DateUtils.getDate(txtHistoryEndDate.getText());
        if(startDate == null || endDate == null){
            lstTopupHistory.setListData(listData.toArray());
            return;
        }

        lblBalanceNumber.setText(
            String.format("%s (This balance will be updated automatically)",numberFormatter.format(mykiCard.getBalance()))
        );

        for(TopUpLog log : mykiCard.getTopUpLogs()){
            if(log.getTime().compareTo(startDate) >= 0 && endDate.compareTo(log.getTime()) >= 0)
                listData.add(log.toString());
        }
        lstTopupHistory.setListData(listData.toArray());
    }
}
