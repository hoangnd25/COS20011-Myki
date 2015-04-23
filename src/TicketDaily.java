import java.util.Date;

public class TicketDaily extends TicketTwoHours{

    public TicketDaily(){}
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
