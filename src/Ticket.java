import java.util.Date;

public interface Ticket {

    public static String TWO_HOURS = "2 hours";
    public static String DAILY = "Daily";

    public String getType();
    public float getZone1Fare();
    public float getZone2Fare();
    public float getZone1And2Fare();
    public Date getExpireAt();
    public void setExpireAt(Date expireAt);
}
