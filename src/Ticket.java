import java.util.Date;

public interface Ticket {
    public Date getExpireAt();
    public double getZone1Fare();
    public double getZone2Fare();
    public double getZone1And2Fare();
    public boolean isTravellingZone1();
    public boolean isTravellingZone2();
    public void setTravellingZone1(boolean travel);
    public void setTravellingZone2(boolean travel);
}
