import java.util.ArrayList;
import java.util.List;

/**
 * The type Train line.
 */
public class TrainLine {

    private List<Station> stations;
    private String name;

    /**
     * Instantiates a new Train line.
     *
     * @param name the name of the train line
     */
    public TrainLine(String name) {
        this.name = name;
        this.stations = new ArrayList<Station>();
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
     * Get station in the train line by name.
     *
     * @param name the name
     * @return the station
     */
    public Station getStation(String name){
        for (int i = 0; i < stations.size() ; i++) {
            Station s = (Station)stations.get(i);
            if(s.getName() == name){
                return s;
            }
        }
        return null;
    }

    /**
     * Get station in the train line by id.
     *
     * @param id the id
     * @return the station
     */
    public Station getStation(int id){
        for (int i = 0; i < stations.size() ; i++) {
            Station s = (Station)stations.get(i);
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }

    /**
     * Gets all stations in ther train line.
     *
     * @return the stations
     */
    public List<Station> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return name;
    }
}
