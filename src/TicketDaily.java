public class TicketDaily {
    public static float getZone1Fare() {
        return TicketTwoHours.getZone1Fare() * 2;
    }
    public static float getZone2Fare() {
        return TicketTwoHours.getZone2Fare() * 2;
    }
    public static float getZone1And2Fare() {
        return getZone1Fare();
    }
}
