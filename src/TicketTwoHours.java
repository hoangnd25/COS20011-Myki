import java.util.Date;

public class TicketTwoHours implements Ticket {
    protected Date expireAt;

    public TicketTwoHours() {
    }

    public String getType() {
        return Ticket.TWO_HOURS;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }

    @Override
    public float getZone1Fare() {
        return Float.parseFloat("3.76");
    }

    @Override
    public float getZone2Fare() {
        return Float.parseFloat("2.6");
    }

    @Override
    public float getZone1And2Fare() {
        return getZone1Fare();
    }
}
