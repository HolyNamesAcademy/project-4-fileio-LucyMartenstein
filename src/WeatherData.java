/*
Holds weather data related to a city
 */
public class WeatherData implements Comparable<WeatherData> {
    private String city;
    private double averageTemp;
    private double averageHumidity;

    // constructor
    public WeatherData(String c, double t, double h) {
        city = c;
        averageTemp = t;
        averageHumidity = h;
    }

    // getters
    public String getCity() {
        return city;
    }
    public double getAverageTemp() {
        return averageTemp;
    }
    public double getAverageHumidity() {
        return averageHumidity;
    }

    /*
    Returns a string representation of WeatherData:

    [City], [Average Temperature], [Average Humidity]
     */
    public String toString()
    {
        return city + ", " + averageTemp + ", " + averageHumidity;
    }

    /*
    Read the compareTo documentation and implement it here:
    https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html#compareTo-T-
     */
    public int compareTo(WeatherData other)
    {
        if(averageTemp > other.getAverageTemp()){
            return 1;
        }
        if(averageTemp < other.getAverageTemp()){
            return -1;
        }
        return 0;
    }
}


// Compare this and other such that if this.getAverageTemp is greater it would return 1 and if other.getAverageTemp is greater it would return -1





































