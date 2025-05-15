import java.time.LocalTime;

public class SecondHand extends ClockHand {
    @Override
    public void setTime(LocalTime time) {
        angle = 6 * time.getSecond();
    }

    @Override
    public String toSvg() {
        return String.format("<line x1=\"100\" y1=\"100\" x2=\"100\" y2=\"20\" stroke=\"red\" stroke-width=\"1\" transform=\"rotate(%.2f 100 100)\" />\n", angle);
    }
}
