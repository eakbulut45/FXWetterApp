package sample;
public class WetterInfo {
    private String timestamp;
    private String temparature;
    private String speed;
    private String humidity;

    public WetterInfo(String timestamp, String temparature, String speed, String humidity) {
        this.timestamp = timestamp;
        this.temparature = temparature;
        this.speed = speed;
        this.humidity = humidity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTemparature() {
        return temparature;
    }

    public String getSpeed() {
        return speed;
    }

    public String getHumidity() {
        return humidity;
    }
}
