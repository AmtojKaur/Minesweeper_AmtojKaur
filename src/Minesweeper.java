import java.io.*;
import java.util.*;
public class Minesweeper {
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

    private static void mineSweeper(final int theRow, final int theCol, final int theNumFields, final Scanner theScanner) {
        int numFields = theNumFields;
        int row = theRow;
        int col = theCol;
        while (!(row == 0 || col == 0)) {
            numFields++;
            System.out.println("Field #" + numFields + ":");
            char[][] field = new char[row + 1][col + 1];
            for (int i = 0; i < row + 1; i++) {
                char[] line = theScanner.nextLine().toCharArray();
                for (int j = 0; j < col; j++) {
                    if (j > 0 && i > 0) {
                        field[i][j] = line[j];
                    }
                }
            }
//            for(int i = 0; i < field.length; i++){
//                for(int j = 0; j < field[0].length; j++){
//                    System.out.print(field[i][j]);
//                }
//                 System.out.println();
//            }
            char[][] filledMineField = fillField(field);
            displayOut(filledMineField);
            row = theScanner.nextInt();
            col = theScanner.nextInt();

        }
    }

    private static void displayOut(final char[][] theFilledMine) {
        for (char[] chars : theFilledMine) {
            for (int j = 0; j < theFilledMine[0].length; j++) {
                System.out.print(chars[j]);
            }
            System.out.println();
        }
    }
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
//    private static char[][] fillField(final char[][] theField) {
//        int row = theField.length;
//        int col = theField[0].length;
//        char[][] mineFields = new char[row][col];
//        for(int i = 0; i < row; i++){
//            for(int j = 0; j < col; j++) {
//                if(theField[i][j] == '*') {
//                    mineFields[i][j] = '*';
//                } else {
//                    mineFields[i][j] = adjacent(theField, row, col);
//                }
//            }
//        }
//
//        return mineFields;
//    }


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
        //current row:
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
        //row below:
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
        //System.out.println(myCount);
        return (char)(myCount + '0');

   }

   private static boolean validValue( final char[][] theMineField, final int theRow, final int theCol) {
       return theRow < theMineField.length && theRow >= 0 && theCol < theMineField[0].length && theCol >= 0;

   }
}
