import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*
    Takes commands from the user and executes them. Possible commands are
    'read' - reads a file of weather data into the system
    'write' - writes weather data to a file -- overwrites the file if it exists
    'sort' - sorts weather data by the hottest to coldest average temperature
    'append' - writes weather data to a file -- appends data to the file if it exists
    'quit' - ends the program
     */
    public static void main(String[] args) throws IOException {
        ArrayList<WeatherData> weatherData = null;
        while (true)
        {
            System.out.print("Enter a command: ");
            Scanner sc = new Scanner(System.in);

            String command = sc.next().toLowerCase();
            switch (command)
            {
                case "read":
                {
                    System.out.print("Enter the path to the file: ");
                    String path = sc.next();

                    weatherData = ReadFile(path);
                    PrintWeatherData(weatherData);
                    break;
                }
                case "sort":
                {
                    if (weatherData == null)
                    {
                        System.out.println("Please call 'read' first, before calling 'append'.");
                        break;
                    }

                    SortWeatherData(weatherData);
                    break;
                }
                case "write":
                {
                    if (weatherData == null)
                    {
                        System.out.println("Please call 'read' first, before calling 'write'.");
                        break;
                    }

                    System.out.print("Enter the path to the file: ");
                    String path = sc.next();

                    WriteFile(path, false, weatherData);
                    break;
                }
                case "append":
                {
                    if (weatherData == null)
                    {
                        System.out.println("Please call 'read' first, before calling 'append'.");
                        break;
                    }

                    System.out.print("Enter the path to the file: ");
                    String path = sc.next();

                    WriteFile(path, true, weatherData);
                    break;
                }
                case "quit":
                {
                    return;
                }
                default:
                {
                    System.out.println("Unrecognized command. Possible commands are 'read', 'write', 'append', 'sort', and 'quit'");
                    break;
                }
            }
        }
    }

    /*
    Reads a file from the given path and puts the information into an ArrayList.
    If the file does not exist, the function catches the exception, prints a message
    to the console, and return an empty (not null) array.
     */
    public static ArrayList<WeatherData> ReadFile(String path) throws IOException {
        File readFile = new File(path);
        PrintWriter printWriter = null;
        System.out.println("Reading file from '" + readFile.getAbsolutePath() + "'.");
        ArrayList<WeatherData> files = new ArrayList<WeatherData>();

        try {
            Scanner s = new Scanner(readFile);
            while (s.hasNextLine()){
                String[] parts = s.nextLine().split(",");
                if(parts.length != 3){
                    continue;}
                double avg1 = Double.parseDouble(parts[1]);
                double avg2 = Double.parseDouble(parts[2]);
                WeatherData line = new WeatherData(parts[0], avg1, avg2);
                files.add(line);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            return files;
        }
        return files;
    }

    /*
    Prints the weather data ArrayList to the console. Each weather data item should
    go on a new line:

    [City1], [Average Temperature], [Average Humidity]
    [City2], [Average Temperature], [Average Humidity]
    ...
     */
    public static void PrintWeatherData(ArrayList<WeatherData> weatherData)
    {
        for(int i = 0; i < weatherData.size(); i++) {
            System.out.print(weatherData.get(i).getCity() + ", ");
            System.out.print(weatherData.get(i).getAverageTemp() + ", ");
            System.out.println(weatherData.get(i).getAverageHumidity());
        }
    }

    /*
    Sorts the given ArrayList from hottest average temperature to coldest average temperature
     */
    public static void SortWeatherData(ArrayList<WeatherData> weatherData)
    {
        for(int i = 0; i < weatherData.size(); i++){
            for(int hotterThan = 0; hotterThan < weatherData.size(); hotterThan++) {
                double avgTemp = weatherData.get(i).getAverageTemp();
                if (avgTemp >= weatherData.get(hotterThan).getAverageTemp()) {
                    weatherData.remove(i);
                    weatherData.add(hotterThan, weatherData.get(i-1));
                }
            }
        }
        System.out.println(weatherData.toString());
    }

    /*
    Writes the weather data information into the file with the given path.
    If shouldAppend is false, the function replaces the existing contents of the file
    (if it exists) with the new weatherData. If shouldAppend is true, the function
    adds the weather data to the end of the file.
    If the file cannot be created, the function catches the exception, prints a message
    to the console, and does not try to write to the file.
     */
    public static void WriteFile(String path, boolean shouldAppend, ArrayList<WeatherData> weatherData) {
        PrintWriter printWriter = null;
        try {
            File readFile = new File(path);
            FileWriter fileWriter = new FileWriter(readFile, shouldAppend);
            printWriter = new PrintWriter(fileWriter);
            for(WeatherData data: weatherData){
                printWriter.println(data);
            }
        } catch (IOException e) {
            System.out.println("File cannot be created");
        }
        printWriter.close();
    }
}
