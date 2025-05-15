import java.time.LocalTime;

public abstract class Clock {
    private int hour;
    private int minute;
    private int second;
    private City city;

    public Clock(City city) {
        this.city = city;
    }

    public void setCurrentTime() {
        LocalTime now = LocalTime.now();
        this.hour = now.getHour();
        this.minute = now.getMinute();
        this.second = now.getSecond();
    }

    public void setTime(int hour, int minute, int second) {
        if (hour < 0 || hour > 23) {
            System.out.println("Invalid hour");
        }
        if (minute < 0 || minute > 59) {
            System.out.println("Invalid minute");
        }
        if (second < 0 || second > 59) {
            System.out.println("Invalid second");
        }
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public LocalTime getTime() {
        return LocalTime.of(hour, minute, second);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    protected int getHour() {
        return hour;
    }

    protected int getMinute() {
        return minute;
    }

    protected int getSecond() {
        return second;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    public Clock(){
        this.city = null;
    }
}