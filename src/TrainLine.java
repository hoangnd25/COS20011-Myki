import java.util.List;

public class TrainLine {

    private List<Station> stations;
    private String name;

    public TrainLine(String name, List<Station> stations) {
        this.name = name;
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public Station getStation(String name){
        for (int i = 0; i < stations.size() ; i++) {
            Station s = (Station)stations.get(i);
            if(s.getName() == name){
                return s;
            }
        }
        return null;
    }

    public Station getStation(int id){
        for (int i = 0; i < stations.size() ; i++) {
            Station s = (Station)stations.get(i);
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }
}
