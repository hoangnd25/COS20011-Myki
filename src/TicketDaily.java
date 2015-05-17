import java.util.Date;

/**
 * The type Ticket daily.
 */
public class TicketDaily extends TicketTwoHours{

    /**
     * Instantiates a new Ticket daily.
     */
    public TicketDaily(){}

    /**
     * Instantiates a new Ticket daily with time
     *
     * @param time the time
     */
    public TicketDaily(Date time){
        expireAt = DateUtils.getTimeTomorrowAt3(time);
    }

    public double getZone1Fare() {
        return super.getZone1Fare() * 2;
    }
    public double getZone2Fare() {
        return super.getZone2Fare() * 2;
    }
}
