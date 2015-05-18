import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * The type Travel log.
 */
public class TravelLog implements Comparable<TravelLog>{

    private Date touchOnTime;
    private Date touchOffTime;
    private Station touchOnStation;
    private Station touchOffStation;
    private double fee;

    /**
     * Instantiates a new Travel log.
     *
     * @param touchOnTime the touch on time
     * @param touchOffTime the touch off time
     * @param touchOnStation the touch on station
     * @param touchOffStation the touch off station
     */
    public TravelLog(Date touchOnTime, Date touchOffTime, Station touchOnStation, Station touchOffStation) {
        this.touchOnTime = touchOnTime;
        this.touchOffTime = touchOffTime;
        this.touchOnStation = touchOnStation;
        this.touchOffStation = touchOffStation;
        this.fee = 0;
    }

    /**
     * Instantiates a new Travel log.
     */
    public TravelLog() {
        this.fee = 0;
    }

    /**
     * Gets touch on time.
     *
     * @return the touch on time
     */
    public Date getTouchOnTime() {
        return touchOnTime;
    }

    /**
     * Sets touch on time.
     *
     * @param touchOnTime the touch on time
     */
    public void setTouchOnTime(Date touchOnTime) {
        this.touchOnTime = touchOnTime;
    }

    /**
     * Gets touch off time.
     *
     * @return the touch off time
     */
    public Date getTouchOffTime() {
        return touchOffTime;
    }

    /**
     * Sets touch off time.
     *
     * @param touchOffTime the touch off time
     */
    public void setTouchOffTime(Date touchOffTime) {
        this.touchOffTime = touchOffTime;
    }

    /**
     * Gets touch on station.
     *
     * @return the touch on station
     */
    public Station getTouchOnStation() {
        return touchOnStation;
    }

    /**
     * Sets touch on station.
     *
     * @param touchOnStation the touch on station
     */
    public void setTouchOnStation(Station touchOnStation) {
        this.touchOnStation = touchOnStation;
    }

    /**
     * Gets touch off station.
     *
     * @return the touch off station
     */
    public Station getTouchOffStation() {
        return touchOffStation;
    }

    /**
     * Sets touch off station.
     *
     * @param touchOffStation the touch off station
     */
    public void setTouchOffStation(Station touchOffStation) {
        this.touchOffStation = touchOffStation;
    }

    /**
     * Gets fee.
     *
     * @return the fee
     */
    public double getFee() {
        return fee;
    }

    /**
     * Sets fee.
     *
     * @param fee the fee
     */
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
