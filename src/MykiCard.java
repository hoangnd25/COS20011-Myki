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

                List<TrainLine> intersectLines = new ArrayList<TrainLine>(log.getTouchOffStation().getTrainLines());
                intersectLines.retainAll(log.getTouchOnStation().getTrainLines());

                boolean isTravellingZone1 = false;
                boolean isTravellingZone2 = false;

                int touchOnZone = log.getTouchOnStation().getZone();
                int touchOffZone = log.getTouchOffStation().getZone();

                if(touchOnZone == touchOffZone){
                    if(touchOnZone == Station.ZONE1){
                        isTravellingZone1 = true;
                        isTravellingZone2 = false;

                        if(intersectLines.isEmpty())
                            isTravellingZone2 = isTransitStationInZone(log.getTouchOnStation(), log.getTouchOffStation(), Station.ZONE2);
                    }else{
                        isTravellingZone1 = false;
                        isTravellingZone2 = true;

                        if(intersectLines.isEmpty())
                            isTravellingZone1 = isTransitStationInZone(log.getTouchOnStation(), log.getTouchOffStation(), Station.ZONE1);
                    }
                }else{
                    if(touchOnZone == Station.OVERLAP || touchOffZone == Station.OVERLAP){
                        int sum = touchOnZone + touchOffZone;
                        if(sum == Station.ZONE1){
                            isTravellingZone1 = true;
                            isTravellingZone2 = false;
                        }else{
                            isTravellingZone1 = false;
                            isTravellingZone2 = true;
                        }
                    }else{
                        isTravellingZone1 = true;
                        isTravellingZone2 = true;
                    }
                }

                //

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

    private boolean isTransitStationInZone(Station s1, Station s2, int zone){
        List<Station> s1LineStations = s1.getTrainLines().get(0).getStations();
        List<Station> s2LineStations = s2.getTrainLines().get(0).getStations();

        List<Station> intersect = new ArrayList<Station>(s1LineStations);
        intersect.retainAll(s2LineStations);

        for(Station station: intersect){
            if(station.getZone() == zone)
                return true;
        }
        return false;
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
