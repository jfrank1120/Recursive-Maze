package project.pkg5;

/**
 * Maze class that creates a character based Maze with input dimensions.
 * Then recursively solves the maze and prints out a solution once it is
 * found.
 * @author jaf51313
 */
public class Maze {
    private char[][] grid;
    private int row; // Number of rows in maze 
    private int colm; // Number of columns in maze
    private int rowOfEntrance; // Row where the entrance is located
    private int colmOfEntrance; // Column where the entrance is located 
    private final char PATH = 'p'; // Part of the path 
    private final char CLEAR = '0'; // Clear path in maze 
    private final char EXIT = 'e'; // Exit of the maze 
    private final char ENTRANCE = 'b'; // Beginning of the Maze
    private boolean entranceFound = false; // Boolean for if the maze entrance has been found
    private int numOfSolutions; // Number of solutions

    /**
     * Constructor for Maze object that intializes all aspects of it
     * @param r - Number of rows to be created for the Maze
     * @param c - Number of columns to be created for the Maze
     */
    public Maze(int r, int c)
    {
        this.row = r;
        this.colm = c;
        grid = new char[r][c];
        this.numOfSolutions = 0;
    }
    /**
     * Fills the Maze with the input two-dimensional array of characters
     * @param m - The two-dimensional character array that is placed into the Maze
     */
    public void fillMaze(char[][] m)
    {
         for(int i=0; i<this.row; i++)
        {
            for(int j=0; j<colm; j++)
            {
                this.grid[i][j]=m[i][j]; //fills array at each position 
            }
        }
    }
    
    /**
     * Sets the character at the input coordinate to the input value
     * @param y - Y coordinate (row) for the character to be changed
     * @param x - X coordinate (column) for the character to be changed
     * @param inputVal - The value that the character at position y,x is changed to
     */
    public void setPos(int y, int x, char inputVal)
    {
        this.grid[y][x] = inputVal;
    }
    
    /**
     * The recursive method that works its way through the Maze, starting at the
     * entrance and moving through the 
     * @param r - The row coordinate to be checked during the course of the method
     * @param c - The column coordinate to be checked during the course of the method
     */
    public void nextMove(int r, int c)
    {
        // Finds the entrance of the Maze
        if (!entranceFound)
        {
            entranceFound = true; // Ensures that entrance is never looked for again when found
            locateEntrance();
            r = rowOfEntrance;
            c = colmOfEntrance;
        }
        
        // CHECK UP
        /*
        * Looks up and checks if its not out of bounds and it is clear or it is 
        * not out of bounds and the exit 
         */
        if(!isOutOfBounds(r-1, c) && (this.grid[r-1][c] == CLEAR || locateExit(r-1, c)))
        {
            this.grid[r][c] = isPath(r, c);//set position as part of the path
            nextMove(r-1, c);//keeps going in that direction by doing recursion
        }
        
        
        // CHECK RIGHT
        /*
        * Looks right and checks if its not out of bounds and it is clear or it is 
        * not out of bounds and the exit 
         */
        if(!isOutOfBounds(r, c+1) && (this.grid[r][c+1] == CLEAR || locateExit(r, c+1)))
        {
            this.grid[r][c] = isPath(r, c);//set position as part of the path
            nextMove(r, c+1); //keeps going in that direction by doing recursion
        }
        // CHECK DOWN
        /*
        * Looks down and checks if its not out of bounds and it is clear or it is 
        * not out of bounds and the exit 
         */
        if(!isOutOfBounds(r+1, c) && (this.grid[r+1][c] == CLEAR || locateExit(r+1, c)))
        {
            this.grid[r][c] = isPath(r, c);//set position as part of the path
            nextMove(r+1, c);//keeps going in that direction by doing recursion
        }
        // CHECK LEFT
        /*
        * Looks left and checks if its not out of bounds and it is clear or it is 
        * not out of bounds and the exit 
         */
        if(!isOutOfBounds(r, c-1) && (this.grid[r][c-1] == CLEAR || locateExit(r, c-1)))
        {
            this.grid[r][c] = isPath(r, c);//set position as part of the path
            nextMove(r, c-1); //keeps going in that direction by doing recursion
        }

        // Check if the end is found
        if(locateExit(r, c))
        {
            this.grid[r][c] = isPath(r, c); //set position as part of the path
            numOfSolutions++; //increment number of solutions 
            System.out.println("Solution: #" + numOfSolutions);
            System.out.println(this.toString());//print the maze
        }
        
        // Checks if the position is not the end then backtracks 
        if(this.grid[r][c]!=EXIT)
        {
            this.grid[r][c]=CLEAR; //backtracking
        }
    }
    
    /**
     * Locates the position of the entrance of the Maze
     */
    private void locateEntrance()
    {
        for(int i=0; i<this.row; i++)
        {
            for(int j=0; j<this.colm; j++)
            {
                if(this.grid[i][j]==ENTRANCE)
                {
                    rowOfEntrance=i;
                    colmOfEntrance=j;
                    return;
                }
            }
        
        }
    }
    /**
    Finds the exit of the maze array
    @param r - The row
    @param c - The column
    @return  - True if the exit is found otherwise false
     */
    private boolean locateExit(int r, int c)
    {
        return this.grid[r][c]==EXIT;
    }
    
    
    /**
    Places a p where the the path is clear in the maze to mark it as part of the 
    solution of the maze 
    @param r the row
    @param c the column
    @return 'p' if the position(row,column) is not the exit else returns 'e'
     */
    private char isPath(int r, int c)
    {
        if(!(this.grid[r][c] == EXIT))
        {
            return PATH;
        }
        return EXIT;
    }
    
    /**
    Checks if the position (row,column) is out of bounds of the maze array
    @param r the row 
    @param c the column
    @return true if the position(row,column) is out of bounds otherwise false
     */
    private boolean isOutOfBounds(int r, int c)
    {
        return ((r < 0) || (c < 0) || (r >= this.row) || (c >= this.colm));
    }

    /**
     * Returns the string equivalence of the Maze
     * @return The maze in a string form
     */
    @Override
    public String toString()
    {
        String returnString = "";
         for(int i = 0; i < this.row; i++)
        {
            for(int j = 0; j < colm; j++)
            {
                returnString += this.grid[i][j] + " ";
            }
            returnString += '\n';
        }
        return returnString;
    }
}
