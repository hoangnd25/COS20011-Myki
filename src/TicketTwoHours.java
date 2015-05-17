import java.util.Date;

/**
 * The type Ticket two hours.
 */
public class TicketTwoHours implements Ticket{
    /**
     * The Expire time of the ticket.
     */
    protected Date expireAt;
    /**
     * Is travelling zone 1.
     */
    protected boolean isTravellingZone1;
    /**
     * Is travelling zone 2.
     */
    protected boolean isTravellingZone2;

    /**
     * Instantiates a new Ticket two hours with time
     *
     * @param time the time
     */
    public TicketTwoHours(Date time) {
        this.expireAt = DateUtils.getTimeAfter2Hours(time);
    }

    /**
     * Instantiates a new Ticket two hours.
     */
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

    /**
     * Sets expire at.
     *
     * @param expireAt the expire at
     */
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
