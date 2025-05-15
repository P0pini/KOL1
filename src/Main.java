import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
    DigitalClock clock24 = new DigitalClock(DigitalClock.Format.H24);
    clock24.setTime(6,5,9);
    System.out.println(clock24);

    DigitalClock clock12 = new DigitalClock(DigitalClock.Format.H12);
    clock12.setTime(20,5,9);
    System.out.println(clock12);

    LocalTime now = LocalTime.of(12,0,0);
    City cities=new City("Lublin",2,"51.2465 N","22.5684 E");
    LocalTime LublinTime=cities.localMeanTime(now);
            System.out.println("Czas strefowy: "+now);
            System.out.println("Czas w Lublinie: "+LublinTime);

            Map<String  ,City> cityMap= City.parseFile("strefy.csv");
            List<City> cityList= new ArrayList<>(cityMap.values());
            cityList.sort(City.worstTimezoneFit());
            for(City city:cityList){
                System.out.println(city.getName());
            }
            City cityExample = cityList.get(0);
            AnalogClock clock = new AnalogClock(cityExample);

            // 5. Ustaw czas aktualny (lokalny)
            clock.setCurrentTime();

            // 6. Wygeneruj plik SVG z tarczą i wskazówkami dla tego miasta
            clock.toSvg(cityExample.getName() + "_clock.svg");
            System.out.println("SVG dla " + cityExample.getName() + " wygenerowany.");

            // 7. Wygeneruj pliki SVG dla wszystkich miast w katalogu
            City.generateAnalogClocksSvg(cityList, clock);
            System.out.println("SVG dla wszystkich miast wygenerowane w katalogu: " + clock.toString());
    }
}