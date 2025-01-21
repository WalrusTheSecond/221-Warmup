import java.io.FileNotFoundException;

public class MyGridMonitorTester{
    public static void main(String[] args) {
        String test = "sample.txt";
        try{
        GridMonitor gm = new GridMonitor("negatives.txt");
        } catch(FileNotFoundException e){
            
        }
    }
}