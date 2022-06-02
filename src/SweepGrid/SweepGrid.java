/**
 * Implementation of SweepGrid class which contains the
 * minesweeper grid and initializes the game based on the selected difficulty.
 */
package SweepGrid;

import java.util.Random;
import java.lang.Math;

enum Difficulty {
    EASY, MEDIUM, HARD
};

// Class that implements the grid of the minesweeper game.
public class SweepGrid {
    // The grid will be represented by ints.
    // A 0 is no mines are in the vicinty of the cell.
    // A -1 is a mine.
    // A 1-8 is the number of mines in the vicinty.
    private int[][] grid;
    // The number of rows and columns in the grid.
    private int rows;
    private int columns;
    private int numMines;

    // Constructor for the class.
    public SweepGrid(Difficulty diff) {
        // Set the grid size based on difficulty.
        switch (diff) {
            case EASY:
                rows = 10;
                columns = 10;
                numMines = 10;
                break;
            case MEDIUM:
                rows = 16;
                columns = 16;
                numMines = 40;
                break;
            case HARD:
                rows = 16;
                columns = 30;
                numMines = 99;
                break;
            default:
                rows = 10;
                columns = 10;
                numMines = 10;
                break;
        }

        // Initialize the grid.
        grid = new int[rows][columns];

        // Initialize the grid.
        int minesAdded = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = 0;
            }
        }

        // Add mines to random locations
        Random rand = new Random();
        while (minesAdded < numMines) {
            // Get a value for the row and column to add the mine in.
            int newCol = rand.nextInt(columns);
            int newRow = rand.nextInt(rows);

            // Check that a mine is not already at this location.
            if (grid[newRow][newCol] != -1) {
                grid[newRow][newCol] = -1;
                minesAdded++;
            }
        }

        // Fill in the grid with proximity to other mines.
        addMineProximity();
    }

    public void addMineProximity() {
        // At each point in the grid, count how many mines are surrounding a location.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] != -1) {
                    // Do a search for mines.
                    int surroundingMines = 0;

                    // Loop on indices around the location.
                    for (int x = Math.max(0, i - 1); x <= Math.min(i + 1, rows - 1); x++) {
                        for (int y = Math.max(0, j - 1); y <= Math.min(j + 1, columns - 1); y++) {
                            if ((x != i || y != j) && grid[x][y] == -1) {
                                surroundingMines++;
                            }
                        }
                    }
                    grid[i][j] = surroundingMines;
                }
            }
        }
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == -1) {
                    output += "M ";
                } else {
                    output += grid[i][j] + " ";
                }
            }
            output += "\n";
        }
        return output;
    }

    // Gets the value of the grid at a specific location.
    // Returns -2 on error.
    public int getGrid(int row, int column) {
        try {
            return grid[row][column];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index is out of bounds.\n");
            return -2;
        }
    }

    public static void main(String args[]) {
        SweepGrid grid = new SweepGrid(Difficulty.EASY);
        System.out.println(grid.toString());
    }
}