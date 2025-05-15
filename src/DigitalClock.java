public class DigitalClock extends Clock {
    private Format format;
    public enum Format {
        H24,
        H12
    }
    public DigitalClock(Format format) {
        this.format = format;
    }

    @Override
    public String toString() {
        int hour = getHour();
        int minute = getMinute();
        int second = getSecond();

        if (format == Format.H24) {
            return String.format("%02d:%02d:%02d", hour, minute, second);
        } else {
            String meridian = (hour < 12) ? "AM" : "PM";
            int hour12 = hour % 12;
            if (hour12 == 0) hour12 = 12;
            return String.format("%02d:%02d:%02d %s", hour12, minute, second, meridian);
        }
    }
}