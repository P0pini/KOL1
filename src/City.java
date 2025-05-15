import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class City {
    private String name;
    private int summerTime;
    private String latitude;
    private String longitude;

    public City(String name, int summerTime, String latitude, String longitude) {
        this.name = name;
        this.summerTime = summerTime;
        this.latitude = latitude.trim();
        this.longitude = longitude.trim();
    }
    public String getName() {
        return name;
    }
    public int getSummerTime() {
        return summerTime;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    private static City parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Wrong number of parts: " + line);
        }
        String name = parts[0].trim();
        int timeZone = Integer.parseInt(parts[1].trim());
        String lat = parts[2].trim();
        String lon = parts[3].trim();
        return new City(name, timeZone, lat, lon);
    }
    public static Map<String, City> parseFile(String filePath) {
        Map<String, City> cities = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            boolean firstLine = true;
            while ((line=reader.readLine())!=null){
                if (firstLine){
                    firstLine=false;
                    continue;
                }
                City city = parseLine(line);
                cities.put(city.getName(), city);
            }
        }catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
    return cities;
    }
    @Override
    public String toString() {
        return String.format("%s: %d %d %s", name, summerTime, latitude, longitude);
    }

    public LocalTime localMeanTime(LocalTime timeInZone) {
        double longintudeDegrees=parseLongintudeToDegrees(this.longitude);
        double geoOffsetHours=longintudeDegrees/15.0;
        LocalTime utcTime=timeInZone.minusHours(this.summerTime);
        int secoundsOffset=(int)Math.round(geoOffsetHours*3600);
        LocalTime localMean = utcTime.plusSeconds(secoundsOffset);
        return localMean;
    }
    private double parseLongintudeToDegrees(String lon) {
        lon=lon.trim().toUpperCase();
        if(lon.endsWith("E")){
            return Double.parseDouble(lon.replace("E","").trim());
        } else if (lon.endsWith("W")) {
            return -Double.parseDouble(lon.replace("W","").trim());
        }else {
            throw new IllegalArgumentException("Wrong longitude format: " + lon);
        }
    }

    public static Comparator<City> worstTimezoneFit() {
        return(c1,c2)->{
            double diff1=Math.abs(c1.getGeoOffsetHours()-c1.summerTime);
            double diff2=Math.abs(c2.getGeoOffsetHours()-c2.summerTime);
            return Double.compare(diff2,diff1);
        };
    }
    private double getGeoOffsetHours() {
        double longDeg=parseLongintudeToDegrees(this.longitude);
        return longDeg/15.0;
    }

    public static void generateAnalogClocksSvg(List<City> cities, AnalogClock clock) {
        String dirName = clock.toString();
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdir();
        }

        for (City city : cities) {
            clock.setCity(city);
            clock.setCurrentTime();
            File file = new File(dir, city.getName() + ".svg");
            clock.toSvg(file.getPath());
        }
    }

}
