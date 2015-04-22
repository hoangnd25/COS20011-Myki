import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TopUpLog implements Comparable<TopUpLog>{

    private double amount;
    private Date time;

    public TopUpLog(double amount) {
        this.amount = amount;
        setTime();
    }

    public TopUpLog(){
        setTime();
    }

    public Date getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }

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
