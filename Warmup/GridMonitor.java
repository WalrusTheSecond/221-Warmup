import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GridMonitor implements GridMonitorInterface{
    private double[][] grid;

    public GridMonitor(String fileName) throws FileNotFoundException{
        
        File file = new File("Warmup\\" + fileName);
        System.out.println("Looking for file at: " + file.getAbsolutePath());
        if (!file.exists()) {
            System.out.println("File not found at: " + file.getAbsolutePath());
            throw new FileNotFoundException();
        }
        else {
            System.out.println("File found at: " + file.getAbsolutePath());
        }

        ArrayList<double[]> gridList = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                String[] values = line.split("\\s+"); // Split by spaces
                double[] row = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                    row[i] = Double.parseDouble(values[i]); // Convert to double
                }
                gridList.add(row); // Add row to the list
            }
        }

        scanner.close();

        // Convert ArrayList to 2D array
        grid = new double[gridList.size()][];
        for (int i = 0; i < gridList.size(); i++) {
            grid[i] = gridList.get(i);
        }

    }
        
    


  
	public double[][] getBaseGrid(){
        return grid;
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
        int rows = grid.length;
        int cols = grid[0].length;
        double above;
        double right;
        double left;
        double below;
        double sum;
        double[][] sumGrid = new double[rows][cols];
        for(int i = 0; i<sumGrid.length; i++){
            for(int j = 0; j<sumGrid.length; j++) {
                if(i == 0 && j == 0){
                    above = grid[i][j];
                    left = grid[i][j];   
                }
                above = grid[i][j + 1];
                right = grid[i + 1][j];
                below = grid[i][j - 1];
                left = grid[i - 1][j]; 
                sum = above + right + left + below;
                sumGrid[i][j] = sum;
            }
        }
        //Find sum of the surrounding elements
        //Set that spot in new grid to be the sum


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

    /**
     * Testing Method
     */
    private void printGrid(double[][] grid) {
        for(double[] row : grid) {
            for(double val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}