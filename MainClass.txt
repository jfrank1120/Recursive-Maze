package project.pkg5;

import java.util.Scanner;

/**
 *  Main class used for prompting user for information about dimensions of the 
 * Maze, as well as the data that is contained within the Maze itself
 * @author Jared Frank
 */
public class Project5 {
    
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Height of the Maze: ");
        int userL = in.nextInt();
        System.out.print("Enter the width of the Maze: ");
        int userW = in.nextInt();
        char[][] userMaze = new char[userL][userW];
        System.out.println("Input data for Maze");
        for(int i=0; i< userL; i++)
        {
            String inputValues = "";
            if(in.hasNext())
            {
                // input from user 
                inputValues = in.next();
            }else
            {
                break;
            }
            for(int j = 0; j < userW; j++)
            {
                userMaze[i][j] = inputValues.charAt(j);
            }
        }
        Maze m = new Maze(userL, userW);
        m.fillMaze(userMaze);
        m.nextMove(userL, userW);
    }
}
