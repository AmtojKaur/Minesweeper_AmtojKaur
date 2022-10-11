/**
 * TCSS 360
 * Instructor: Tom Capaul
 * Personal Assignment 1 - MineSweeper.java
 */

import java.io.*;
import java.util.*;

/**
 * Displays minesweeper minefield output with all adjacency values and bombs.
 *
 * @author Amtoj Kaur - amtojkaur1321@gmail.com
 */
public class Minesweeper {

    /**
     * Instantiates Scanner for the program as well as calls all methods needed to run code
     * it also throws FileNotFoundException.
     *
     * @param theArgs
     * @throws FileNotFoundException
     */
    public static void main(final String[] theArgs) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int numFields = 0;
        if (theArgs.length == 1) {
            try {
                scanner = new Scanner(new File(theArgs[0]));
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                mineSweeper(row, col, numFields, scanner);
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("File not found" + e);
            }
        }
        scanner.close();
    }

    /**
     * This method iterates through the given file and reads input to character array.
     * It also calls displayOut method and fillField method.
     *
     * @param theRow
     * @param theCol
     * @param theNumFields
     * @param theScanner
     */
    private static void mineSweeper(final int theRow, final int theCol, final int theNumFields, final Scanner theScanner) {
        int numFields = theNumFields;
        int row = theRow;
        int col = theCol;
        while (!(row == 0 || col == 0)) {
            numFields++;
            char[][] field = new char[row + 1][col + 1];
            for (int i = 0; i < row + 1; i++) {
                char[] line = theScanner.nextLine().toCharArray();
                for (int j = 0; j < col; j++) {
                    if (j > 0 && i > 0) {
                        field[i][j] = line[j];
                    }
                }
            }
            char[][] filledMineField = fillField(field);
            displayOut(filledMineField, numFields);
            row = theScanner.nextInt();
            col = theScanner.nextInt();

        }
    }

    /**
     * This method formats the display of the minefield as well as increments the field #.
     *
     * @param theFilledMine
     * @param theNumFields
     */
    private static void displayOut(final char[][] theFilledMine, int theNumFields) {
        System.out.println("Field #" + theNumFields + ":");
        for (char[] chars : theFilledMine) {
            for (int j = 0; j < theFilledMine[0].length; j++) {
                System.out.print(chars[j]);
            }
            System.out.println();
        }
    }

    /**
     * This method goes through the array created by reading from system.in, and iterates through
     * and returns a character array that has bombs where bombs should be and adjacency
     * values in 'safe' spaces. Calls adjacent method to calculate adjacency value.
     *
     * @param theMineField
     * @return numFields
     */
    private static char[][] fillField(final char[][] theMineField) {
        char[][] numFields = new
                char[theMineField.length][theMineField[0].length];
        for(int i = 0; i < theMineField.length; i++) {
            for(int j = 0; j < theMineField[0].length; j++) {
                if(theMineField[i][j] == '*') {     //bomb
                    numFields[i][j] = '*';
                } else { //safe space
                    numFields[i][j] = adjacent(theMineField, i, j);
                }
            }
        }
        return numFields;
    }

    /**
     * This method calculates adjacency and calls validValue to help it it then returns
     * the integer value cast as a char to fillField method.
     *
     * @param theField
     * @param theRow
     * @param theCol
     * @return myCount
     */
    private static char adjacent(final char[][] theField, final int theRow, final int theCol) {
        int myCount = 0;
        if(validValue(theField, theRow - 1, theCol - 1)) {
                if(theField[theRow - 1][theCol - 1] == '*'){
                myCount++;
            }
        }
        if(validValue(theField, theRow - 1, theCol)) {
            if(theField[theRow - 1][theCol] == '*') {
                myCount++;
            }
        }
        if(validValue(theField, theRow - 1, theCol + 1)) {
            if(theField[theRow - 1][theCol + 1] == '*') {
                myCount++;
            }
        }
        if(validValue(theField, theRow, theCol - 1)) {
            if(theField[theRow][theCol - 1] == '*') {
                myCount++;
            }
        }
        if(validValue(theField, theRow, theCol + 1)) {
            if(theField[theRow][theCol + 1] == '*') {
                myCount++;
            }
        }
        if(validValue(theField, theRow + 1, theCol - 1)) {
            if(theField[theRow + 1][theCol - 1] == '*') {
                myCount++;
            }
        }
        if(validValue(theField, theRow + 1, theCol)) {
            if(theField[theRow + 1][theCol] == '*') {
                myCount++;
            }
        }
        if(validValue(theField, theRow + 1, theCol + 1)) {
            if(theField[theRow + 1][theCol + 1] == '*') {
                myCount++;
            }
        }
        return (char)(myCount + '0');

   }

    /**
     * This checks for validity of position  for row and col passed and tells it to adjacent method.
     *
     * @param theMineField
     * @param theRow
     * @param theCol
     * @return boolean
     */
   private static boolean validValue( final char[][] theMineField, final int theRow, final int theCol) {
       return theRow < theMineField.length && theRow >= 0 && theCol < theMineField[0].length && theCol >= 0;

   }
}
