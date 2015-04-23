import java.util.Date;

public class TicketTwoHours implements Ticket{
    protected Date expireAt;
    protected boolean isTravellingZone1;
    protected boolean isTravellingZone2;

    public TicketTwoHours(Date time) {
        this.expireAt = DateUtils.getTimeAfter2Hours(time);
    }

    public TicketTwoHours(){

    }

    public double getZone1Fare() {
        return Float.parseFloat("3.76");
    }
    public double getZone2Fare() {
        return Float.parseFloat("2.6");
    }
    public double getZone1And2Fare() {
        return getZone1Fare();
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }

    public boolean isTravellingZone1() {
        return isTravellingZone1;
    }

    public void setTravellingZone1(boolean isTravellingZone1) {
        this.isTravellingZone1 = isTravellingZone1;
    }

    public boolean isTravellingZone2() {
        return isTravellingZone2;
    }

    public void setTravellingZone2(boolean isTravellingZone2) {
        this.isTravellingZone2 = isTravellingZone2;
    }
}
