import java.util.Date;

public class TravelLog {

    protected Date touchOnTime;
    protected Date touchOffTime;
    protected Station touchOnStation;
    protected Station touchOffStation;
    protected float fee;

    public TravelLog(Date touchOnTime, Date touchOffTime, Station touchOnStation, Station touchOffStation) {
        this.touchOnTime = touchOnTime;
        this.touchOffTime = touchOffTime;
        this.touchOnStation = touchOnStation;
        this.touchOffStation = touchOffStation;
    }

    public TravelLog() {
    }

    public Date getTouchOnTime() {
        return touchOnTime;
    }

    public void setTouchOnTime(Date touchOnTime) {
        this.touchOnTime = touchOnTime;
    }

    public Date getTouchOffTime() {
        return touchOffTime;
    }

    public void setTouchOffTime(Date touchOffTime) {
        this.touchOffTime = touchOffTime;
    }

    public Station getTouchOnStation() {
        return touchOnStation;
    }

    public void setTouchOnStation(Station touchOnStation) {
        this.touchOnStation = touchOnStation;
    }

    public Station getTouchOffStation() {
        return touchOffStation;
    }

    public void setTouchOffStation(Station touchOffStation) {
        this.touchOffStation = touchOffStation;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }
}
