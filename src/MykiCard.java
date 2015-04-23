import java.util.*;

public class MykiCard {

    private int id;
    private double balance;
    private List<TravelLog> travelLogs;
    private List<TopUpLog> topUpLogs;

    public MykiCard(int id) {
        this(id, 0);
    }

    public MykiCard(int id, double balance) {
        this.id = id;
        this.balance = balance;
        travelLogs = new ArrayList<TravelLog>();
        topUpLogs = new ArrayList<TopUpLog>();
    }

    public void touchOn(Date time, Station station){
        touchOff(time, station);
        TravelLog log = new TravelLog(time, null, station, null);
        travelLogs.add(log);
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

    public void topUp(double amount){
        TopUpLog log = new TopUpLog(amount);
        this.balance += amount;
        this.topUpLogs.add(log);
    }

    private void charge(double amount, Date time){
        double amountPaid = getAmountPaidForTheDay(time);
        if(amount > amountPaid){
            this.balance -= (amount - amountPaid);
        }
    }

    private List<TravelLog> getTravelLogsForTheDay(Date time){
        List<TravelLog> logs = new ArrayList<TravelLog>();

        Date todayAt3 = DateUtils.getTimeAt3(time);
        Date tomorrowAt3 = DateUtils.getTimeTomorrowAt3(time);

        for (int i = 0; i < this.travelLogs.size(); i++) {
            TravelLog log = this.travelLogs.get(i);
            if(log.getTouchOnTime().compareTo(todayAt3) >= 0 && log.getTouchOnTime().compareTo(tomorrowAt3) < 0){
                logs.add(log);
            }
        }

        return logs;
    }

    private double getAmountPaidForTheDay(Date time){
        double amountPaidToday = 0;
        for(TravelLog log : getTravelLogsForTheDay(time)){
            amountPaidToday += log.getFee();
        }
        return amountPaidToday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TravelLog> getTravelLogs() {
        Collections.sort(travelLogs);
        return travelLogs;
    }

    public List<TopUpLog> getTopUpLogs() {
        Collections.sort(topUpLogs);
        return topUpLogs;
    }
}
