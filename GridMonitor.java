import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridMonitor implements GridMonitorInterface {
    private double[][] grid;
    private int col;
    private int row;

    /*
     * Initializes a 2d array from a file input.
     */
    public GridMonitor(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        Scanner scanner = new Scanner(file);
        String size = scanner.nextLine().trim(); // read the dimensions of the array
        String[] value = size.split("\\s+"); // Split by spaces
        row = Integer.parseInt(value[0]); // rows
        col = Integer.parseInt(value[1]); // cols
        grid = new double[row][col]; // Set grid dimensions
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                String[] values = line.split("\\s+");// Split by spaces
                for (int i = 0; i < col; i++) {
                    grid[lineCount][i] = Double.parseDouble(values[i]);
                }
                lineCount++;
            }
        }

        scanner.close();
    }

    /*
     * Returns a copy of grid
     */
    public double[][] getBaseGrid() {
        double[][] copy = new double[grid.length][grid[0].length]; // New grid with the same dimensions as grid
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        return copy;
    }

    /*
     * Calculates the sum of the surrounding cells
     */
    public double[][] getSurroundingSumGrid() {
        double above;
        double right;
        double left;
        double below;
        double sum;
        double[][] sumGrid = new double[grid.length][grid.length];// sumGrid size defining
        for (int i = 0; i < sumGrid.length; i++) {
            sumGrid[i] = new double[grid[i].length];
            for (int j = 0; j < sumGrid[i].length; j++) {
                // check each direction
                // Using indexOutOfBoundsExceptions to find if the value is not in the array
                try {
                    above = grid[i - 1][j];
                } catch (IndexOutOfBoundsException e) {
                    above = grid[i][j];
                }
                try {
                    right = grid[i][j + 1];
                } catch (IndexOutOfBoundsException e) {
                    right = grid[i][j];
                }
                try {
                    below = grid[i + 1][j];
                } catch (IndexOutOfBoundsException e) {
                    below = grid[i][j];
                }
                try {
                    left = grid[i][j - 1];
                } catch (IndexOutOfBoundsException e) {
                    left = grid[i][j];
                }
                sum = above + right + left + below;// Calculating sum
                sumGrid[i][j] = sum;// Adding sum to sumGrid
            }
        }
        return sumGrid;
    }

    /*
     * Returns a grid with the average of the surrounding cells
     */
    public double[][] getSurroundingAvgGrid() {
        double[][] avgGrid = getSurroundingSumGrid();
        for (int i = 0; i < avgGrid.length; i++) {
            for (int j = 0; j < avgGrid[i].length; j++) {
                if (avgGrid[i][j] == 0) {
                    avgGrid[i][j] = 0;
                } else {
                    avgGrid[i][j] = avgGrid[i][j] / 4.0; // Calculations for average
                }
            }
        }
        return avgGrid;
    }

    /*
     * Returns a grid with the delta for each cell
     */
    public double[][] getDeltaGrid() {
        double[][] deltGrid = getSurroundingAvgGrid();
        for (int i = 0; i < deltGrid.length; i++) {
            for (int j = 0; j < deltGrid[i].length; j++) {
                if (deltGrid[i][j] == 0) { // to avoid divide by 0 errors
                    deltGrid[i][j] = 0;
                } else if (deltGrid[i][j] < 0) {
                    deltGrid[i][j] = (deltGrid[i][j] * -1) / 2.0; // negative case
                } else {
                    deltGrid[i][j] = deltGrid[i][j] / 2.0; // normal calse
                }
            }
        }
        return deltGrid;
    }

    /*
     * Finds if the the base cell is inside the average +- delta range
     */
    public boolean[][] getDangerGrid() {
        boolean[][] danGrid = new boolean[grid.length][grid[0].length];
        double[][] avgGrid = getSurroundingAvgGrid();
        double[][] delGrid = getDeltaGrid();
        for (int i = 0; i < danGrid.length; i++) {
            for (int j = 0; j < danGrid[i].length; j++) {
                if ((grid[i][j] < avgGrid[i][j] - delGrid[i][j]) || (grid[i][j] > avgGrid[i][j] + delGrid[i][j])) {
                    danGrid[i][j] = true;
                } else
                    danGrid[i][j] = false;
            }
        }
        return danGrid;
    }

    /*
     * To string method that returns
     * Base Grid
     * Danger Grid
     * and which cells are at risk
     */
    public String toString() {
        String returnString = "Constitution-class Starship Status Report:\nCell Value Grid: Current Cell Value Status\n";
        for (double[] row : grid) {
            for (double val : row) {
                returnString += val + " ";
            }
            returnString += "\n";
        }
        returnString += "\n" + "Danger Grid: Current Danger Cell Status\n";
        boolean danGrid[][] = getDangerGrid();
        for (boolean[] row2 : danGrid) {
            for (boolean val2 : row2) {
                if (val2 == true) {
                    returnString += "Danger ";
                } else {
                    returnString += "Safe ";
                }
            }
            returnString += "\n";
        }
        returnString += "\n" + "Cells at Risk: \n";
        for (int i = 0; i < danGrid.length; i++) {
            for (int j = 0; j < danGrid[0].length; j++) {
                if (danGrid[i][j] == true) {
                    returnString += "Cell (" + i + " " + j + ") is at Risk\n";
                }
            }
        }
        return returnString;
    }

}