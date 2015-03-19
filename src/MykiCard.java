import java.util.*;

public class MykiCard {

    protected int id;
    protected float balance;
    protected List<TravelLog> travelLogs;
    protected List<TopUpLog> topUpLogs;
    protected Ticket ticket;

    public MykiCard(int id) {
        this(id, 0);
    }

    public MykiCard(int id, float balance) {
        this.id = id;
        this.balance = balance;
        this.ticket = null;
        travelLogs = new ArrayList<TravelLog>();
        topUpLogs = new ArrayList<TopUpLog>();
    }

    public void touchOn(Date time, Station station){
        touchOff(time, station);
        TravelLog log = new TravelLog(time, null, station, null);
    }

    public void touchOff(Date time, Station station){
        for (int i = 0; i < travelLogs.size(); i++) {
            TravelLog log = travelLogs.get(i);
            if(log.getTouchOffTime() == null && log.getTouchOffStation() == null){
                log.setTouchOffTime(time);
                log.setTouchOffStation(station);
            }
        }
    }

    public void topUp(float amount){
        TopUpLog log = new TopUpLog(amount);
        this.balance += amount;
        this.topUpLogs.add(log);
    }

    protected void charge(float amount, Date time){
        float amountPaidToday = 0;

        Calendar now = Calendar.getInstance();
        now.setTime(time);
        now.set(Calendar.HOUR_OF_DAY, 3);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        Date todayAt3 = now.getTime();

//        now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);
//        Date tomorrowAt3 = now.getTime();

        for (int i = 0; i < this.travelLogs.size(); i++) {
            TravelLog log = this.travelLogs.get(i);
            if(log.getTouchOnTime().compareTo(todayAt3) >= 0){
                amountPaidToday += log.fee;
            }
        }

        if(amount > amountPaidToday){
            this.balance -= (amount - amountPaidToday);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<TravelLog> getTravelLogs() {
        return travelLogs;
    }

    public List<TopUpLog> getTopUpLogs() {
        return topUpLogs;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
