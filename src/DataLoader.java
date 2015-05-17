import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Data loader. Handle data loading from files
 */
public class DataLoader {

    private static DataLoader instance = null;
    private List<Station> stations;
    private List<TrainLine> lines;

    private static String FILE_STATION = "stations.txt";
    private static String FILE_TRAINLINE = "lines.txt";

    private DataLoader() {
        stations = new ArrayList<Station>();
        lines = new ArrayList<TrainLine>();
    }

    /**
     * Gets instance of DataLoader
     *
     * @return the instance
     */
    public static DataLoader getInstance() {
        if(instance == null) {
            instance = new DataLoader();
        }
        return instance;
    }

    /**
     * Load train lines and stations data
     */
    public void setUpStationData(){
        loadStations();
        loadTrainLines();
    }

    /**
     * Get stations by name
     *
     * @param name the name
     * @return the stations
     */
    public Station getStation(String name){
        for(Station station : getStations()){
            if(station.getName().equals(name))
                return station;
        }
        return null;
    }

    /**
     * Get all stations.
     *
     * @return the list of all stations
     */
    public List<Station> getStations(){
        if(stations.size() == 0){

        }
        return stations;
    }

    /**
     * Get all train lines.
     *
     * @return the list of train lines
     */
    public List<TrainLine> getTrainLines(){
        if(lines.size() == 0){
            loadTrainLines();
        }

        return lines;
    }
    /**
     * Load stations if the list is empty
     */
    private void loadStations(){
        File file = new File(DataLoader.FILE_STATION);
        if(!(file.exists() && !file.isDirectory())) {
            createStationDataFile();
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                Station station = new Station(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]));
                stations.add(station);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Load train lines if the list is empty
     */
    private void loadTrainLines(){
        File file = new File(DataLoader.FILE_TRAINLINE);
        if(!(file.exists() && !file.isDirectory())) {
            createLineDataFile();
        }

        if(this.stations.size() == 0)
            this.loadStations();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String[] stationIds = data[1].split(" ");

                TrainLine trainLine = new TrainLine(data[0]);

                for (int i = 0; i < stationIds.length; i++) {
                    for (int j = 0; j < this.stations.size(); j++) {
                        Station station = this.stations.get(j);
                        if(Integer.parseInt(stationIds[i]) == station.getId()){
                            trainLine.getStations().add(station);
                            station.getTrainLines().add(trainLine);
                        }
                    }
                }
                lines.add(trainLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create data files. Create default data files if it is not exist
     */
    public void createDataFiles(){
        createStationDataFile();
        createLineDataFile();
    }

    /**
     * Create default data for stations
     */
    private void createStationDataFile(){
        String[] stations = {
            "1,Parliament Station,1",
            "2,Melbourne Central Station,1",
            "3,Flagstaff Station,1",
            "4,Southern Cross Station,1",
            "5,Flinders Street Station,1",
            "6,Richmond Station,1",
            "7,East Richmond Station,1",
            "8,Burnley Station,1",
            "9,Hawthorn Station,1",
            "10,Glenferrie Station,1",
            "11,Auburn Station,1",
            "12,Camberwell Station,1",
            "13,East Camberwell Station,1",
            "14,Canterbury Station,0",
            "15,Chatham Station,0",
            "16,Surrey Hills Station,0",
            "17,Mont Albert Station,0",
            "18,Box Hill Station,2",
            "19,Laburnum Station,2",
            "20,Blackburn Station,2",
            "21,Nunawading Station,2",
            "22,Mitcham Station,2",
            "23,Heatherdale Station,2",
            "24,Ringwood Station,2",
            "25,Heathmont Station,2",
            "26,Bayswater Station,2",
            "27,Boronia Station,2",
            "28,Ferntree Gully Station,2",
            "29,Upper Ferntree Gully Station,2",
            "30,Upwey Station,2",
            "31,Tecoma Station,2",
            "32,Belgrave Station,2",

            "43,Syndal Station,2",
            "42,Mount Waverley Station,2",
            "41,Jordanville Station,2",
            "40,Holmesglen Station,0",
            "39,East Malvern Station,0",
            "38,Darling Station,0",
            "37,Glen Iris Station,1",
            "36,Gardiner Station,1",
            "35,Tooronga Station,1",
            "34,Kooyong Station,1",
            "33,Heyington Station,1"
        };
        try {
            File file = new File(DataLoader.FILE_STATION);
            BufferedWriter output = new BufferedWriter(new FileWriter(file, false));
            for (int i = 0; i < stations.length; i++) {
                output.write(stations[i]);
                output.newLine();
            }
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * Create default data for train lines
     */
    private void createLineDataFile(){
        String[] lines = {
            "Belgrave Line,1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32",
            "Glen Waverley line,1 2 3 4 5 6 7 8 33 34 35 36 37 38 39 40 41 42 43"
        };
        try {
            File file = new File(DataLoader.FILE_TRAINLINE);
            BufferedWriter output = new BufferedWriter(new FileWriter(file, false));
            for (int i = 0; i < lines.length; i++) {
                output.write(lines[i]);
                output.newLine();
            }
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

}
