import java.time.LocalTime;

public class MinuteHand extends ClockHand {
    @Override
    public void setTime(LocalTime time) {
        angle = 6 * (time.getMinute() + time.getSecond() / 60.0);
    }

    @Override
    public String toSvg() {
        return String.format("<line x1=\"100\" y1=\"100\" x2=\"100\" y2=\"30\" stroke=\"blue\" stroke-width=\"2\" transform=\"rotate(%.2f 100 100)\" />\n", angle);
    }
}
