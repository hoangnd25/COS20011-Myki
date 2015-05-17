import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Station auto completer.
 */
public class StationAutoCompleter extends AutoCompleter{

    /**
     * Instantiates a new Station auto completer.
     *
     * @param comp the text field
     */
    public StationAutoCompleter(JTextField comp){
        super(comp);
    }

    /**
     * Generate station suggestions for a given string.
     *
     * @param value the station name to look up
     */
    protected List getSuggestionForValue(String value){
        List<Station> result = new ArrayList<Station>();
        List<Station> stations = DataLoader.getInstance().getStations();
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            if(station.getName().toLowerCase().startsWith(value.toLowerCase()))
                result.add(station);
        }

        return result;
    }

    /**
     * Use station name for selected station
     *
     * @param selected selected station
     */
    protected String formatSelectedValue(Object selected){
        Station station = (Station)selected;
        return station.getName();
    }
}