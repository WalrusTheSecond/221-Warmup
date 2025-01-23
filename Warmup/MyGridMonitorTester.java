import java.io.FileNotFoundException;

public class MyGridMonitorTester{
    public static void main(String[] args) {
        String test = "negatives.txt";
        try{
        GridMonitor gm = new GridMonitor(test);
        } catch(FileNotFoundException e){
            
        }
    }
}