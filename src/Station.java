import java.util.ArrayList;
import java.util.List;

public class Station {

    public static int ZONE1 = 1;
    public static int ZONE2 = 2;
    public static int OVERLAP = 0;

    private int zone;
    private int id;
    private String name;
    private List<TrainLine> lines;

    public Station() {
    }

    public Station(int id, String name, int zone) {
        this.zone = zone;
        this.id = id;
        this.name = name;
        this.lines = new ArrayList<TrainLine>();
    }

    public List<TrainLine> getTrainLines() {
        return lines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZone() {
        return zone;
    }

    public String getFormatedZone() {
        return zone == 0 ? "Overlap" : String.format("Zone %d", zone);
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", this.getName(), this.getFormatedZone() );
    }
}
