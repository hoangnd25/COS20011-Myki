public class TicketDaily extends TicketTwoHours {

    public TicketDaily(){}

    public TicketDaily(TicketTwoHours ticket){
        this.expireAt = ticket.expireAt;
    }

    public String getType() {
        return Ticket.DAILY;
    }

    @Override
    public float getZone1Fare() {
        return super.getZone1Fare() * 2;
    }

    @Override
    public float getZone2Fare() {
        return super.getZone2Fare() * 2;
    }
}
