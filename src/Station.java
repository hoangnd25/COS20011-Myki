import java.util.ArrayList;
import java.util.List;

/**
 * The type Station.
 */
public class Station {

    /**
     * The constant ZONE1.
     */
    public static int ZONE1 = 1;
    /**
     * The constant ZONE2.
     */
    public static int ZONE2 = 2;
    /**
     * The constant OVERLAP.
     */
    public static int OVERLAP = 0;

    private int zone;
    private int id;
    private String name;
    private List<TrainLine> lines;

    /**
     * Instantiates a new Station.
     */
    public Station() {
    }

    /**
     * Instantiates a new Station.
     *
     * @param id the id
     * @param name the name
     * @param zone the zone
     */
    public Station(int id, String name, int zone) {
        this.zone = zone;
        this.id = id;
        this.name = name;
        this.lines = new ArrayList<TrainLine>();
    }

    /**
     * Gets train lines.
     *
     * @return the train lines
     */
    public List<TrainLine> getTrainLines() {
        return lines;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets zone.
     *
     * @return the zone
     */
    public int getZone() {
        return zone;
    }

    /**
     * Gets formated zone.
     *
     * @return the formated zone
     */
    public String getFormatedZone() {
        return zone == 0 ? "Overlap" : String.format("Zone %d", zone);
    }

    /**
     * Sets zone.
     *
     * @param zone the zone
     */
    public void setZone(int zone) {
        this.zone = zone;
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

    @Override
    public String toString() {
        return String.format("%s (%s - %s)", this.getName(), this.getFormatedZone(), this.getTrainLines() );
    }
}
