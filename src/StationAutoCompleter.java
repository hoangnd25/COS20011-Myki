import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StationAutoCompleter extends AutoCompleter{

    public StationAutoCompleter(JTextField comp){
        super(comp);
    }

    protected String[] getSuggestionForValue(String value){
        List<String> result = new ArrayList<String>();
        List<Station> stations = DataLoader.getInstance().getStations();
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            if(station.getName().toLowerCase().startsWith(value.toLowerCase()))
                result.add(station.getName());
        }
        return result.toArray(new String[0]);
    }

    protected String formatSelectedValue(String value){
        return value;
    }
}