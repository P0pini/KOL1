import java.time.LocalTime;

public class HourHand extends ClockHand {
    @Override
    public void setTime(LocalTime time) {
        angle = 30 * (time.getHour() % 12 + time.getMinute() / 60.0 + time.getSecond() / 3600.0);
    }

    @Override
    public String toSvg() {
        return String.format("<line x1=\"100\" y1=\"100\" x2=\"100\" y2=\"50\" stroke=\"black\" stroke-width=\"3\" transform=\"rotate(%.2f 100 100)\" />\n", angle);
    }
}