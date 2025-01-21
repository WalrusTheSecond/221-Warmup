import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridMonitor implements GridMonitorInterface{
    private double[][] grid;

    public GridMonitor(String fileName) throws FileNotFoundException{
        
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File not found at: " + file.getAbsolutePath());
            throw new FileNotFoundException("File not found: " + fileName);
        }
        
        System.out.println("I like turtles");
        Scanner fileScan = new Scanner(file);
        
        /*for(){
            for(){

            }
        }
**/
        String test = fileScan.nextLine();
        System.out.println("I like turtles");
    }


    /**
	 * Returns the original base grid, as read from file.
	 * 
	 * @return base grid
	 */
	public double[][] getBaseGrid(){
        return null;
    }

    /**
	 * Returns a grid with the same dimensions as the base grid, but each element
	 * is the sum of the 4 surrounding base elements. For elements on a grid border,
	 * the base element's own value is used when looking out of bounds, as if there
	 * is a mirror surrounding the grid. This method is exposed for testing.
	 * 
	 * @return grid containing the sum of adjacent positions
	 */
	public double[][] getSurroundingSumGrid() {
        return null;
    }

    /**
	 * Returns a grid with the same dimensions as the base grid, but each element
	 * is the average of the 4 surrounding base elements. This method is exposed for 
	 * testing.
	 * 
	 * @return grid containing the average of adjacent positions
	 */
	public double[][] getSurroundingAvgGrid(){
        return null;
    }

    /**
	 * Returns a grid with the same dimensions as the base grid, but each element 
	 * is the maximum delta from the average. For example, if the surrounding
	 * average at some coordinate is 2.0 and the maximum delta is 50% of the average,
	 * the delta value at the same coordinate in this grid will be 1.0. This method is
	 * exposed for testing.
	 * 
	 * @return grid containing the maximum delta from average of adjacent positions
	 */
	public double[][] getDeltaGrid(){
        return null;
    }

    /**
	 * Returns a grid with the same dimensions as the base grid, but each element
	 * is a boolean value indicating if the cell is at risk. For example, if the cell
	 * at a coordinate is less than the surrounding average minus its maximum delta or
	 * greater than the surrounding average plus its maximum delta, the corresponding
	 * coordinate in this grid will be true. If the base cell value is within the safe
	 * range, however, the corresponding value in this grid will be false.
	 * 
	 * @return grid containing boolean values indicating whether the cell at this
	 * location is in danger of exploding
	 */
	public boolean[][] getDangerGrid(){
        return null;
    }

    /**
	 * Returns a well-formatted, clearly labeled String with useful information
	 * about the GridMonitor. One or more of the grids would be useful as would
	 * information about which cells (if any) are at risk.
	 */
	public String toString(){
        return null;
    }
}