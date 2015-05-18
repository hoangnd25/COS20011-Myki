import java.util.*;

/**
 * The type Myki card.
 */
public class MykiCard {

    private int id;
    private double balance;
    private List<TravelLog> travelLogs;
    private List<TopUpLog> topUpLogs;
    private Ticket activeTicket;

    /**
     * Instantiates a new Myki card with id, balance default to 0
     *
     * @param id the id of myki card
     */
    public MykiCard(int id) {
        this(id, 0);
    }

    /**
     * Instantiates a new Myki card with id and balance
     *
     * @param id the id of myki card
     * @param balance the balance of myki card
     */
    public MykiCard(int id, double balance) {
        this.id = id;
        this.balance = balance;
        travelLogs = new ArrayList<TravelLog>();
        topUpLogs = new ArrayList<TopUpLog>();
    }

    /**
     * Stimulate touch on action. Automatically touch off
     *
     * @param time the time
     * @param station the station
     */
    public void touchOn(Date time, Station station){
        if(getTravelLogsForTheDay(time).isEmpty())
            activeTicket = new TicketTwoHours(time);

        touchOff(time, station);
        TravelLog log = new TravelLog(time, null, station, null);
        travelLogs.add(log);
    }

    /**
     * Stimulate touch off action and calculate travel fees
     *
     * @param time the time
     * @param station the station
     */
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

    /**
     * Top up myki card
     *
     * @param amount the amount
     */
    public void topUp(double amount){
        TopUpLog log = new TopUpLog(amount);
        this.balance += amount;
        this.topUpLogs.add(log);
    }

    /**
     * Stimulate charging money of the myki card. Paid ammount will be deducted for the day
     *
     * @param amount the amount that will used to calculate
     * @param time the time
     */
    private double charge(double amount, Date time){
        double amountPaid = getAmountPaidForTheDay(time);
        if(amount > amountPaid){
            this.balance -= (amount - amountPaid);
            Application.getInstance().updateData();
            return amount - amountPaid;
        }
        return 0;
    }

    /**
     * Find travel today's histories (3am today to 3am of the next day)
     *
     * @param time the time
     */
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

    /**
     * Find total amount paid for ticket today
     *
     * @param time the time
     */
    private double getAmountPaidForTheDay(Date time){
        double amountPaidToday = 0;
        for(TravelLog log : getTravelLogsForTheDay(time)){
            amountPaidToday += log.getFee();
        }
        return amountPaidToday;
    }

    /**
     * Check if in order to travel from station 1 to station 2 must pass through a given zone
     *
     * @param s1 depart station
     * @param s2 destination station
     * @param zone the zone will be used to check if the travel must pass through
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets travel logs.
     *
     * @return the travel logs
     */
    public List<TravelLog> getTravelLogs() {
        Collections.sort(travelLogs);
        return travelLogs;
    }

    /**
     * Sets travel logs.
     *
     * @param travelLogs the travel logs
     */
    public void setTravelLogs(List<TravelLog> travelLogs) {
        this.travelLogs = travelLogs;
    }

    /**
     * Gets top up logs.
     *
     * @return the top up logs
     */
    public List<TopUpLog> getTopUpLogs() {
        Collections.sort(topUpLogs);
        return topUpLogs;
    }

    /**
     * Sets top up logs.
     *
     * @param topUpLogs the top up logs
     */
    public void setTopUpLogs(List<TopUpLog> topUpLogs) {
        this.topUpLogs = topUpLogs;
    }
}
