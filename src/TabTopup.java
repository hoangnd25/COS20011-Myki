import javax.swing.*;
import java.awt.*;

public class TabTopup extends JPanel{

    final int MARGIN_TOP = 20;
    final int MARGIN_BOTTOM = -MARGIN_TOP;
    final int MARGIN_LEFT = 20;
    final int MARGIN_RIGHT = -MARGIN_LEFT;
    final int LABEL_MAX_WIDTH = 150;
    final int ROW_SPACE = 20;
    final Color ERROR_COLOR = Color.red;

    public TabTopup()
    {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel lblBalance = new JLabel("Balance:");
        this.add(lblBalance);
        JLabel lblBalanceNumber = new JLabel("$0");
        lblBalanceNumber.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        this.add(lblBalanceNumber);


        JLabel lblCardNumber = new JLabel("Card number:");
        this.add(lblCardNumber);
        JTextField txtCardNumber =  new JTextField(20);
        this.add(txtCardNumber);

        JLabel lblHolderName = new JLabel("Holder name:");
        this.add(lblHolderName);
        JTextField txtHolderName =  new JTextField(20);
        this.add(txtHolderName);

        JLabel lblExpireDate = new JLabel("Expiration date:");
        this.add(lblExpireDate);
        JTextField txtExpireDate =  new JTextField(20);
        this.add(txtExpireDate);

        JLabel lblAddress = new JLabel("Billing address:");
        this.add(lblAddress);
        JTextField txtAddress =  new JTextField(20);
        this.add(txtAddress);

        JLabel lblAmount = new JLabel("Amount:");
        this.add(lblAmount);
        JTextField txtAmount =  new JTextField(20);
        this.add(txtAmount);

        JButton btnTopUp = new JButton("Top Up");
        this.add(btnTopUp);

        JLabel errCardNumber = CreateErrorLabel();
        this.add(errCardNumber);

        JLabel errHolderName = CreateErrorLabel();
        this.add(errHolderName);

        JLabel errExpireDate = CreateErrorLabel();
        this.add(errExpireDate);

        JLabel errAddress = CreateErrorLabel();
        this.add(errAddress);

        JLabel errAmount = CreateErrorLabel();
        this.add(errAmount);

        JLabel lblMessage = new JLabel("");
        this.add(lblMessage);

        JLabel lblTopupHistory = new JLabel("Top Up History");
        this.add(lblTopupHistory);

        JList lstTopupHistory = new JList();
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
        layout.putConstraint(SpringLayout.EAST, lblMessage, 0, SpringLayout.WEST, btnTopUp);

        layout.putConstraint(SpringLayout.NORTH, scrollPane, (int)Math.round(ROW_SPACE *1.5), SpringLayout.SOUTH, btnTopUp);
        layout.putConstraint(SpringLayout.SOUTH, scrollPane, MARGIN_BOTTOM , SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, scrollPane, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WIDTH, scrollPane, -(MARGIN_LEFT * 2), SpringLayout.WIDTH, this);

        layout.putConstraint(SpringLayout.WEST, lblTopupHistory, MARGIN_LEFT, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, lblTopupHistory, 0, SpringLayout.NORTH, scrollPane);

    }

    private JLabel CreateErrorLabel(){
        JLabel label = new JLabel();
        label.setForeground(ERROR_COLOR);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        return label;
    }
}
