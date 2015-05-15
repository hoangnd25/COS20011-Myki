import java.util.*;

public class MykiCard {

    private int id;
    private double balance;
    private List<TravelLog> travelLogs;
    private List<TopUpLog> topUpLogs;
    private Ticket activeTicket;

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
        if(getTravelLogsForTheDay(time).isEmpty())
            activeTicket = new TicketTwoHours(time);

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

                Date touchOnTime = log.getTouchOnTime();
                Date touchOffTime = log.getTouchOffTime();
                if(log.getTouchOnStation().getId() == log.getTouchOffStation().getId() && DateUtils.getTimeAfter15Mins(touchOnTime).after(touchOffTime)){
                    log.setFee(0);
                    return;
                }

                boolean isTravellingZone1 = false;
                boolean isTravellingZone2 = false;

                int touchOnZone = log.getTouchOnStation().getZone();
                int touchOffZone = log.getTouchOffStation().getZone();

                if(touchOnZone == touchOffZone){
                    if(touchOnZone == Station.OVERLAP){
                        isTravellingZone1 = isTransitStationInZone(log.getTouchOnStation(), log.getTouchOffStation(), Station.ZONE1);
                        isTravellingZone2 = isTransitStationInZone(log.getTouchOnStation(), log.getTouchOffStation(), Station.ZONE2);
                    }else if(touchOnZone == Station.ZONE1){
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

                            if(intersectLines.isEmpty())
                                isTravellingZone2 = isTransitStationInZone(log.getTouchOnStation(), log.getTouchOffStation(), Station.ZONE2);
                        }else{
                            isTravellingZone1 = false;
                            isTravellingZone2 = true;

                            if(intersectLines.isEmpty())
                                isTravellingZone1 = isTransitStationInZone(log.getTouchOnStation(), log.getTouchOffStation(), Station.ZONE1);
                        }
                    }else{
                        isTravellingZone1 = true;
                        isTravellingZone2 = true;
                    }
                }

                if(activeTicket.isTravellingZone1())
                    isTravellingZone1 = true;

                if(activeTicket.isTravellingZone2())
                    isTravellingZone2 = true;

                if(activeTicket.getExpireAt().before(touchOffTime)){
                    activeTicket = new TicketDaily(touchOffTime);
                }

                if(isTravellingZone2 && !isTravellingZone1){
                    log.setFee(charge(activeTicket.getZone2Fare(), touchOffTime));
                }else if(isTravellingZone1 && !isTravellingZone2){
                    log.setFee(charge(activeTicket.getZone1Fare(), touchOffTime));
                }else{
                    log.setFee(charge(activeTicket.getZone1And2Fare(), touchOffTime));
                }

                activeTicket.setTravellingZone1(isTravellingZone1);
                activeTicket.setTravellingZone2(isTravellingZone2);
            }
        }
    }

    public void topUp(double amount){
        TopUpLog log = new TopUpLog(amount);
        this.balance += amount;
        this.topUpLogs.add(log);
    }

    private double charge(double amount, Date time){
        double amountPaid = getAmountPaidForTheDay(time);
        if(amount > amountPaid){
            this.balance -= (amount - amountPaid);
            Application.getInstance().updateData();
            return amount - amountPaid;
        }
        return 0;
    }

    private List<TravelLog> getTravelLogsForTheDay(Date time){
        List<TravelLog> logs = new ArrayList<TravelLog>();

        Date todayAt3 = DateUtils.getTimeAt3(time);
        Date tomorrowAt3 = DateUtils.getTimeTomorrowAt3(time);
        Date yesterdayAt3 = DateUtils.getTimeYesterdayAt3(time);

        if(time.compareTo(todayAt3) < 0){
            for (int i = 0; i < this.travelLogs.size(); i++) {
                TravelLog log = this.travelLogs.get(i);
                if (log.getTouchOnTime().compareTo(yesterdayAt3) >= 0 && log.getTouchOnTime().compareTo(todayAt3) < 0) {
                    logs.add(log);
                }
            }
        }else{
            for (int i = 0; i < this.travelLogs.size(); i++) {
                TravelLog log = this.travelLogs.get(i);
                if (log.getTouchOnTime().compareTo(todayAt3) >= 0 && log.getTouchOnTime().compareTo(tomorrowAt3) < 0) {
                    logs.add(log);
                }
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
