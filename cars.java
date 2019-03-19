import java.io.*;
import java.util.*;
import java.nio.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class cars {
    public static void main (String []args) throws IOException {

        // get cmd arguments

        String pathToDataset = args[0];
        int numberOfCars = Integer.parseInt(args[1]);
        String origin = args[2];

        File f = new File (pathToDataset);

        Map<String, ArrayList<String>> cars = new HashMap<String, ArrayList<String>>();
        
        String US = "US";
        String Europe = "Europe";
        String Japan = "Japan";

        ArrayList<String> usCars = new ArrayList<String>();
        ArrayList<String> europeCars = new ArrayList<String>();
        ArrayList<String> japanCars = new ArrayList<String>();

        // get the data in a input stream and add to hashmap

        try (BufferedReader in =  new BufferedReader(new FileReader(f))) {
            for (String line; (line = in.readLine()) != null; ) {
                if (line.contains(US)) {
                    usCars.add(line);
                }
                if (line.contains(Europe)){
                    europeCars.add(line);
                }
                if (line.contains(Japan)) {
                    japanCars.add(line);
                }
            }
                
        } catch (Exception e) {
            System.out.println("Exception found! " + e);
        } finally {
            cars.put(US, usCars);
            cars.put(Japan, japanCars);
            cars.put(Europe, europeCars);
        }

        ArrayList<String> var = cars.get(origin);
        
        double sum = 0.0;
        int i = 0;
        double d = 0.0;
        
        for (i = 0; i < var.size(); i++) {
            Matcher m = Pattern.compile("(?!=\\d\\.\\d\\.)([\\d.]+)").matcher(var.get(i));
            while (m.find())
                d = Double.parseDouble(m.group(1));
            sum += d;
        }

        double avg = sum / (i-1);
        int count = 0;
        d = 0.0;
        i = 0;

        System.out.println(avg);
                
        while (i < var.size() && i < count) {
            Matcher m = Pattern.compile("(?!=\\d\\.\\d\\.)([\\d.]+)").matcher(var.get(i));
            while (m.find())
                d = Double.parseDouble(m.group(1));
            if (d > avg) {
                ++count;
                System.out.println(var.get(i));
            }
            i++;
        }
    }
}