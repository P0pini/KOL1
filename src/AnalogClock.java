import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class AnalogClock extends Clock {
    private final List<ClockHand> hands;

    public AnalogClock(City city) {
        super(city);
        hands = Arrays.asList(
                new HourHand(),
                new MinuteHand(),
                new SecondHand()
        );
        updateHands();
    }

    private void updateHands() {
        for (ClockHand hand : hands) {
            hand.setTime(getTime());
        }
    }

    @Override
    public void setCurrentTime() {
        super.setCurrentTime();
        updateHands();
    }

    @Override
    public void setTime(int hour, int minute, int second) {
        super.setTime(hour, minute, second);
        updateHands();
    }

    public void toSvg(String path) {
        StringBuilder svg = new StringBuilder();
        svg.append("<svg width=\"200\" height=\"200\" xmlns=\"http://www.w3.org/2000/svg\">\n");
        svg.append("<circle cx=\"100\" cy=\"100\" r=\"90\" stroke=\"black\" stroke-width=\"4\" fill=\"white\"/>\n");
        for (ClockHand hand : hands) {
            svg.append(hand.toSvg());
        }
        svg.append("</svg>");

        try (FileWriter writer = new FileWriter(path)) {
            writer.write(svg.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
