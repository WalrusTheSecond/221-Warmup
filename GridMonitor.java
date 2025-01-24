import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridMonitor implements GridMonitorInterface {
    private double[][] grid;
    private int col;
    private int row;
    public GridMonitor(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        Scanner scanner = new Scanner(file);
        String size = scanner.nextLine().trim();
        String[] value = size.split("\\s+"); 
        row = Integer.parseInt(value[0]); 
        col = Integer.parseInt(value[1]); 
        grid = new double[row][col];
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                String[] values = line.split("\\s+");
                for (int i = 0; i < col; i++) {
                    grid[lineCount][i] = Double.parseDouble(values[i]);
                }
                lineCount++;
            }
        }

        scanner.close();
    }

    public double[][] getBaseGrid() {
        double[][] copy = new double[grid.length][grid[0].length];
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        return copy;
    }

    public double[][] getSurroundingSumGrid() {
        double above;
        double right;
        double left;
        double below;
        double sum;
        double[][] sumGrid = new double[grid.length][grid.length];
        for (int i = 0; i < sumGrid.length; i++) {
            sumGrid[i] = new double[grid[i].length];
            for (int j = 0; j < sumGrid[i].length; j++) {
                // check each direction
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
                sum = above + right + left + below;
                sumGrid[i][j] = sum;
            }
        }
        return sumGrid;
    }

    public double[][] getSurroundingAvgGrid() {
        double[][] avgGrid = getSurroundingSumGrid();
        for (int i = 0; i < avgGrid.length; i++) {
            for (int j = 0; j < avgGrid[i].length; j++) {
                if (avgGrid[i][j] == 0) {
                    avgGrid[i][j] = 0;
                } else {
                    avgGrid[i][j] = avgGrid[i][j] / 4.0;
                }
            }
        }
        return avgGrid;
    }

    public double[][] getDeltaGrid() {
        double[][] deltGrid = getSurroundingAvgGrid();
        for (int i = 0; i < deltGrid.length; i++) {
            for (int j = 0; j < deltGrid[i].length; j++) {
                if (deltGrid[i][j] == 0) {
                    deltGrid[i][j] = 0;
                } else if (deltGrid[i][j] < 0) {
                    deltGrid[i][j] = (deltGrid[i][j] * -1) / 2.0;
                } else {
                    deltGrid[i][j] = deltGrid[i][j] / 2.0;
                }
            }
        }
        return deltGrid;
    }

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
            for (int j = 0; j < danGrid.length; j++) {
                if (danGrid[i][j] == true) {
                    returnString += "Cell (" + i + " " + j + ") is at Risk\n";
                }
            }
        }
        return returnString;
    }
    
}