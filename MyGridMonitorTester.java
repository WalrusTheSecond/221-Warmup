import java.io.FileNotFoundException;

public class MyGridMonitorTester{
    public static void main(String[] args) {
        String test = "negatives.txt";
        try{
        GridMonitor gm = new GridMonitor(test);

        gm.printGrid(gm.getSurroundingSumGrid());
        gm.printGrid(gm.getSurroundingAvgGrid());
        gm.printGrid(gm.getDeltaGrid());
        gm.printBooleanGrid(gm.getDangerGrid());
        //gm.printGrid(gm.getSurroundingSumGrid());
        //getSurroundingAvgGrid();
        } catch(FileNotFoundException e){
            
        }
        

    }
}