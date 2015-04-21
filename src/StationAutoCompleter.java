import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StationAutoCompleter extends AutoCompleter{

    public StationAutoCompleter(JTextField comp){
        super(comp);
    }

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

    protected String formatSelectedValue(Object selected){
        Station station = (Station)selected;
        return station.getName();
    }
}