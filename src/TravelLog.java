import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class TravelLog implements Comparable<TravelLog>{

    private Date touchOnTime;
    private Date touchOffTime;
    private Station touchOnStation;
    private Station touchOffStation;
    private double fee;

    public TravelLog(Date touchOnTime, Date touchOffTime, Station touchOnStation, Station touchOffStation) {
        this.touchOnTime = touchOnTime;
        this.touchOffTime = touchOffTime;
        this.touchOnStation = touchOnStation;
        this.touchOffStation = touchOffStation;
        this.fee = 0;
    }

    public TravelLog() {
    }

    public Date getTouchOnTime() {
        return touchOnTime;
    }

    public void setTouchOnTime(Date touchOnTime) {
        this.touchOnTime = touchOnTime;
    }

    public Date getTouchOffTime() {
        return touchOffTime;
    }

    public void setTouchOffTime(Date touchOffTime) {
        this.touchOffTime = touchOffTime;
    }

    public Station getTouchOnStation() {
        return touchOnStation;
    }

    public void setTouchOnStation(Station touchOnStation) {
        this.touchOnStation = touchOnStation;
    }

    public Station getTouchOffStation() {
        return touchOffStation;
    }

    public void setTouchOffStation(Station touchOffStation) {
        this.touchOffStation = touchOffStation;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        if(touchOffTime != null && touchOffStation != null){
            NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
            return String.format("%s: %s - %s: %s (%s)", DateUtils.formatDateTime(touchOnTime), touchOnStation, DateUtils.formatDateTime(touchOffTime), touchOffStation, numberFormatter.format(fee));
        }else{
            return String.format("%s: %s", DateUtils.formatDateTime(touchOnTime), touchOnStation);
        }
    }

    @Override
    public int compareTo(TravelLog obj) {
        if(touchOffTime != null & obj.touchOffTime != null){
            if(this.touchOffTime.after(obj.touchOffTime)){
                return -1;
            }else{
                return 1;
            }
        }else{
            if(this.touchOnTime.after(obj.touchOnTime)){
                return -1;
            }else{
                return 1;
            }
        }

    }
}
