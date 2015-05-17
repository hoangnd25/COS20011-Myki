import java.util.Date;

/**
 * The interface Ticket.
 */
public interface Ticket {

    /**
     * Gets expire time for the ticket.
     *
     * @return the expire at
     */
    public Date getExpireAt();

    /**
     * Gets zone 1 fare.
     *
     * @return the zone 1 fare
     */
    public double getZone1Fare();

    /**
     * Gets zone 2 fare.
     *
     * @return the zone 2 fare
     */
    public double getZone2Fare();

    /**
     * Gets zone 1 and 2 fare.
     *
     * @return the zone 1 and 2 fare
     */
    public double getZone1And2Fare();

    /**
     * Is travelling zone 1.
     *
     * @return the boolean
     */
    public boolean isTravellingZone1();

    /**
     * Is travelling zone 2.
     *
     * @return the boolean
     */
    public boolean isTravellingZone2();

    /**
     * Sets travelling zone 1.
     *
     * @param travel the travel
     */
    public void setTravellingZone1(boolean travel);

    /**
     * Sets travelling zone 2.
     *
     * @param travel the travel
     */
    public void setTravellingZone2(boolean travel);
}
