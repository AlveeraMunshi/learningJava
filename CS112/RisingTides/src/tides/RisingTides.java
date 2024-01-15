package tides;

import java.util.*;

/**
 * This class contains methods that provide information about select terrains 
 * using 2D arrays. Uses floodfill to flood given maps and uses that 
 * information to understand the potential impacts. 
 * Instance Variables:
 *  - a double array for all the heights for each cell
 *  - a GridLocation array for the sources of water on empty terrain 
 * 
 * @author Original Creator Keith Scharz (NIFTY STANFORD) 
 * @author Vian Miranda (Rutgers University)
 */
public class RisingTides {

    // Instance variables
    private double[][] terrain;     // an array for all the heights for each cell
    private GridLocation[] sources; // an array for the sources of water on empty terrain 

    /**
     * DO NOT EDIT!
     * Constructor for RisingTides.
     * @param terrain passes in the selected terrain 
     */
    public RisingTides(Terrain terrain) {
        this.terrain = terrain.heights;
        this.sources = terrain.sources;
    }

    /**
     * Find the lowest and highest point of the terrain and output it.
     * 
     * @return double[][], with index 0 and index 1 being the lowest and 
     * highest points of the terrain, respectively
     */
    public double[] elevationExtrema() {

        double[] extrema = new double[2]; // array to store the min and max
        extrema[0] = terrain[0][0];
        extrema[1] = terrain[0][0];
        for (int i = 0; i < terrain.length; i++) // iterate through the rows
        {
            for (int j = 0; j < terrain[0].length; j++)  // iterate through the columns
            {
                if (terrain[i][j] < extrema[0]) { // if the current cell is less than the current min
                    extrema[0] = terrain[i][j];
                }
                if (terrain[i][j] > extrema[1]) { // if the current cell is greater than the current max
                    extrema[1] = terrain[i][j];
                }
            }
        }
        return extrema; // substitute this line. It is provided so that the code compiles.
    }

    /**
     * Implement the floodfill algorithm using the provided terrain and sources.
     * 
     * All water originates from the source GridLocation. If the height of the 
     * water is greater than that of the neighboring terrain, flood the cells. 
     * Repeat iteratively till the neighboring terrain is higher than the water 
     * height.
     * 
     * 
     * @param height of the water
     * @return boolean[][], where flooded cells are true, otherwise false
     */
    public boolean[][] floodedRegionsIn(double height) 
    {
        // Initialize a boolean 2D array, we will call this array the resulting array.
        boolean[][] result = new boolean[terrain.length][terrain[0].length]; // array to store the flooded cells
        // Initialize an ArrayList of GridLocations.
        ArrayList<GridLocation> flooded = new ArrayList<GridLocation>(); // arraylist to store the flooded cells
        //System.out.println("Sources: " + sources.length);
        for (int i = 0; i < sources.length; i++) // iterate through the sources
        {
            flooded.add(sources[i]); // add the source to the flooded arraylist
            result[sources[i].row][sources[i].col] = true; // set the source to true in the result array
        }
        int[][] offset = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // 4 cardinal directions
        while (!flooded.isEmpty())
        {
            GridLocation current = flooded.remove(0); // remove the first element from the flooded arraylist
            //check all cardinal neighbors
            for (int o = 0; o < offset.length; o++) // check adjacent cells
            {
                int newRow = current.row+offset[o][0], newCol = current.col+offset[o][1];
                if (newRow < terrain.length && newRow >= 0 && newCol < terrain[0].length && newCol >= 0 && !result[newRow][newCol] && terrain[newRow][newCol] <= height) //if position in bounds, not already checked, and should be flooded
                {
                    flooded.add(new GridLocation(newRow, newCol)); // add to list to check its adjacents
                    result[newRow][newCol] = true; // mark as flooded
                }
            }
        }
        return result; // substitute this line. It is provided so that the code compiles.
    }

    /**
     * Checks if a given cell is flooded at a certain water height.
     * 
     * @param height of the water
     * @param cell location 
     * @return boolean, true if cell is flooded, otherwise false
     */
    public boolean isFlooded(double height, GridLocation cell) 
    {
        boolean[][] flooded = floodedRegionsIn(height); // get the flooded regions
        if (cell.row < 0 || cell.row >= flooded.length || cell.col < 0 || cell.col >= flooded[0].length) // if the cell is out of bounds
        {
            return false; // return false
        }
        return flooded[cell.row][cell.col]; // return whether or not its flooded
    }

    /**
     * Given the water height and a GridLocation find the difference between 
     * the chosen cells height and the water height.
     * 
     * If the return value is negative, the Driver will display "meters below"
     * If the return value is positive, the Driver will display "meters above"
     * The value displayed will be positive.
     * 
     * @param height of the water
     * @param cell location
     * @return double, representing how high/deep a cell is above/below water
     */
    public double heightAboveWater(double height, GridLocation cell) {
        
        /* WRITE YOUR CODE BELOW */
        return terrain[cell.row][cell.col] - height; // chosen cell height - water height
    }

    /**
     * Total land available (not underwater) given a certain water height.
     * 
     * @param height of the water
     * @return int, representing every cell above water
     */
    public int totalVisibleLand(double height) {
        
        /* WRITE YOUR CODE BELOW */
        
        boolean[][] flooded = floodedRegionsIn(height); // get the flooded regions
        int sum = 0; //counts not underwater cells
        for (int r = 0; r < flooded.length; r++) // iterate through rows
        {
            for (int c = 0; c < flooded[0].length; c++) // iterate through columns
            {
                if (!flooded[r][c]) // if not flooded
                {
                    sum++; // count as not underwater cell
                }
            }
        }
        
        return sum; // returns # of not underwater cells
    } 


    /**
     * Given 2 heights, find the difference in land available at each height. 
     * 
     * If the return value is negative, the Driver will display "Will gain"
     * If the return value is positive, the Driver will display "Will lose"
     * The value displayed will be positive.
     * 
     * @param height of the water
     * @param newHeight the future height of the water
     * @return int, representing the amount of land lost or gained
     */
    public int landLost(double height, double newHeight) {
        
        /* WRITE YOUR CODE BELOW */
        return totalVisibleLand(height) - totalVisibleLand(newHeight); // difference between og and new height
    }

    /**
     * Count the total number of islands on the flooded terrain.
     * 
     * Parts of the terrain are considered "islands" if they are completely 
     * surround by water in all 8-directions. Should there be a direction (ie. 
     * left corner) where a certain piece of land is connected to another 
     * landmass, this should be considered as one island. A better example 
     * would be if there were two landmasses connected by one cell. Although 
     * seemingly two islands, after further inspection it should be realized 
     * this is one single island. Only if this connection were to be removed 
     * (height of water increased) should these two landmasses be considered 
     * two separate islands.
     * 
     * @param height of the water
     * @return int, representing the total number of islands
     */
    public int numOfIslands(double height) {
        
        /* WRITE YOUR CODE BELOW */
        boolean[][] flooded = floodedRegionsIn(height); // get the flooded regions
        WeightedQuickUnionUF w = new WeightedQuickUnionUF(flooded.length, flooded[0].length); // use weighted quick union class
        int[][] offset = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}}; // 8 adjacent cells
        for (int r = 0; r < flooded.length; r++) // iterate through rows
        {
            for (int c = 0; c < flooded[0].length; c++) // iterate through columns
            {
                if (!flooded[r][c]) // if non-flooded cell
                {
                    for (int o = 0; o < offset.length; o++) // check adjacent cells
                    {
                        int newRow = r+offset[o][0], newCol = c+offset[o][1];
                        if (newRow < flooded.length && newRow >= 0 && newCol < flooded[0].length && newCol >= 0 && !flooded[newRow][newCol]) // if adjacent cell exists and is not flooded
                        {
                            w.union(new GridLocation(r, c), new GridLocation(newRow, newCol)); // join the cells (create island)
                        }
                    }
                }
            }
        }
        int sum = 0; // counts how many islands
        for (int r = 0; r < flooded.length; r++) // iterate through rows
        {
            for (int c = 0; c < flooded[0].length; c++) // iterate through columns
            {
                if (!flooded[r][c] && (w.find(new GridLocation(r, c))).equals(new GridLocation(r, c))) // not flooded and parent returns itself as a representative, indicative of a unique set (island)
                {
                    sum++; // count as an island
                }
            }
        }

        return sum; // return number of islands
    }
}