/**
 * TCSS 360
 * Instructor: Tom Capaul
 * Personal Assignment 1 - MineSweeper.java
 */

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Displays minesweeper minefield output with all adjacency values and bombs.
 *
 * @author Amtoj Kaur
 */
public class Minesweeper {

    /**
     * Instantiates Scanner for the program as well as calls all methods needed to run code
     * it also throws FileNotFoundException.
     *
     * @param theArgs
     */
    public static void main(final String[] theArgs) {
        final Scanner scanner = new Scanner(System.in);
        final int numFields = 0;
        final int row = scanner.nextInt();
        final int col = scanner.nextInt();
        mineSweeper(row, col, numFields, scanner);
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
    private static void mineSweeper(final int theRow, final int theCol, final int theNumFields,
                                    final Scanner theScanner) {
        int numFields = theNumFields;
        int row = theRow;
        int col = theCol;
        while (!(row == 0 || col == 0)) {
            numFields++;
            final char[][] field = new char[row + 1][col + 1];
            for (int i = 0; i < row + 1; i++) {
                final char[] line = theScanner.nextLine().toCharArray();
                for (int j = 0; j < col; j++) {
                    if (j > 0 && i > 0) {
                        field[i][j] = line[j];
                    }
                }
            }
            final char[][] filledMineField = fillField(field);
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
    private static void displayOut(final char[][] theFilledMine,
                                   final int theNumFields) {
        final PrintStream console = new PrintStream(System.out);
        System.setOut(console);

        console.println("Field #" + theNumFields + ":");
        for (char[] chars : theFilledMine) {
            for (int j = 0; j < theFilledMine[0].length; j++) {
                console.print(chars[j]);
            }
            console.println();
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
        final char[][] numFields = new
                char[theMineField.length][theMineField[0].length];
        int i = 0;
        while (i < theMineField.length) {
            int j = 0;
            while (j < theMineField[0].length) {
                if (theMineField[i][j] == '*') {
                    numFields[i][j] = '*';
                } else {
                    numFields[i][j] = adjacent(theMineField, i, j);
                }
                j++;
            }
            i++;
        }
        return numFields;
    }

    /**
     * This method calculates adjacency and calls validValue to help it then returns
     * the integer value cast as a char to fillField method.
     *
     * @param theField
     * @param theRow
     * @param theCol
     * @return myCount
     */
    private static char adjacent(final char[][] theField, final int theRow, final int theCol) {
        int count = 0;
        if (validValue(theField, theRow - 1, theCol - 1)
                && theField[theRow - 1][theCol - 1] == '*') {
            count++;
        }
        if (validValue(theField, theRow - 1, theCol)
                && theField[theRow - 1][theCol] == '*') {
            count++;
        }
        if (validValue(theField, theRow - 1, theCol + 1)
                && theField[theRow - 1][theCol + 1] == '*') {
            count++;
        }
        //current row:
        if (validValue(theField, theRow, theCol - 1)
                && theField[theRow][theCol - 1] == '*') {
            count++;
        }
        if (validValue(theField, theRow, theCol + 1)
                && theField[theRow][theCol + 1] == '*') {
            count++;
        }
        //row below:
        if (validValue(theField, theRow + 1, theCol - 1)
                && theField[theRow + 1][theCol - 1] == '*') {
            count++;
        }
        if (validValue(theField, theRow + 1, theCol)
                && theField[theRow + 1][theCol] == '*') {
            count++;
        }
        if (validValue(theField, theRow + 1, theCol + 1)
                && theField[theRow + 1][theCol + 1] == '*') {
            count++;
        }
        //System.out.println(myCount);
        return (char) (count + '0');

    }
    /**
     * This checks for validity of position  for row and col passed and tells it to adjacent method.
     *
     * @param theMineField
     * @param theRow
     * @param theCol
     * @return boolean
     */
    private static boolean validValue(final char[][] theMineField,
                                      final int theRow, final int theCol) {
        return theRow < theMineField.length && theRow >= 0
                && theCol < theMineField[0].length && theCol >= 0;

    }
}