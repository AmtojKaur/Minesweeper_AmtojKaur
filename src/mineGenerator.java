import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

public class mineGenerator {
    public static void main(final String[] theArgs) throws FileNotFoundException {
        PrintStream output = new PrintStream("generated.txt");
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();

        //randomly generate number of mazes between 1 and 10:
        int numMazes = rd.nextInt(11);

        while(numMazes > 0) {
            int rows = rd.nextInt(100) + 1;
            int columns = rd.nextInt(100) + 1;

            double bombSpawnRate = rd.nextDouble();
            int numBombs = 1;
            if ((int) (bombSpawnRate * (rows * columns)) > 1) {
                numBombs = (int) (bombSpawnRate * (rows * columns));
            }

            char[][] mineField = generateLocations(rows, columns,
                    numBombs);
            sb.append(rows + " " + columns + "\n");
            for(int i = 0; i < mineField.length; i++) {
                for(int j = 0; j < mineField[0].length; j++) {
                    sb.append(mineField[i][j]);
                }
                sb.append("\n");
            }
            output.append(sb.toString());
            numMazes--;
        }
        output.append("0 0");
    }

    public static char[][] generateLocations(final int theRows,
                                             final int theCols,
                                             final int theBombs) {
        char[][] field = new char[theRows][theCols];

        //generate bomb locations
        int[][] bombs = genBombLocation(theBombs, theRows, theCols);

        //filling field with bombs
        for(int i = 0; i < bombs.length; i++) {
            for(int j = 0; j < 2; j++) {
                field[bombs[i][0]][bombs[i][1]] = '*';
            }
        }

        //filling the rest of the field:
        for(int i = 0; i < theRows; i++){
            for (int j = 0; j < theCols; j++) {
                if(field[i][j] != '*') {
                    field[i][j] = '.';
                }
            }
        }

        return field;
    }

    private static int[][] genBombLocation(final int theBombs,
                                           final int theRows,
                                           final int theColumns) {
        int[][] locations = new int[theBombs][2];
        Random rd = new Random();
        for(int i = 0; i < theBombs; i++) {
            int bombRow = rd.nextInt(theRows);
            for(int j = 0; j < 1; j++) {
                int bombCol = rd.nextInt(theColumns);
                locations[i][j] = bombRow;
                locations[i][j + 1] = bombCol;
            }
        }
        return locations;
    }

}
