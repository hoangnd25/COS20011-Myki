import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * The type Top up log.
 */
public class TopUpLog implements Comparable<TopUpLog>{

    private double amount;
    private Date time;

    /**
     * Instantiates a new Top up log.
     *
     * @param amount the amount charged
     */
    public TopUpLog(double amount) {
        this.amount = amount;
        setTime();
    }

    /**
     * Instantiates a new Top up log.
     */
    public TopUpLog(){
        setTime();
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        String formattedTime = SimpleDateFormat.getInstance().format(this.time);
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

        return String.format("%s - %s",  formattedTime, numberFormatter.format(amount));
    }

    private void setTime(){
        this.time = new Date();
    }

    @Override
    public int compareTo(TopUpLog obj) {
        if(this.time.after(obj.time)){
            return -1;
        }else{
            return 1;
        }
    }
}
