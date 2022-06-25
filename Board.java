import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ALL")
public class Board {
    private int boardSize;
    public static int[] getSizes = new int[]{5,4,3,3,2};
    private static String[][] coordsX = new String[10][11];
    public Board(int boardSize) {
        this.boardSize = boardSize;
    }

    // Maker of the Board.
    private static String[][] initialBoard(){
        for (int x = 0; x < 10; x++){
            for (int y = 0; y < 11 ; y++){
                if (y == 0){
                    String newX = Integer.toString(x);
                    coordsX[x][y] = newX;
                } else{
                    coordsX[x][y] = "_";
                }
            }
        }
        return coordsX;
    }
    public static String[][] newCoordsX = initialBoard();

    // Ships.
    public static String[][] ships(){
        List<Integer> existingNums = new ArrayList<Integer>();
        boolean notExist = true;
        int i = 0;
        while ( i < getSizes.length ){
            int randomX = ThreadLocalRandom.current().nextInt(0,10);
            int randomY = ThreadLocalRandom.current().nextInt(1,6);
            if (!existingNums.isEmpty()){
                for (int k = 0; k < existingNums.size(); k++ ){
                    if (existingNums.contains(randomX)){
                        notExist = false;
                        break;
                    } else {
                        notExist = true;
                    }
                }
            }
            if (notExist){
                if((randomY+getSizes[i]) < 10) {
                    for(int j = 0; j < getSizes[i]; j++){
                        newCoordsX[randomX][randomY+j] = String.valueOf(getSizes[i]);
                        existingNums.add(randomX);
                    }
                    i++;
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }

        return newCoordsX;
    }
    public static String[][] boardWithShips = ships();

    // Board's Looks.
    public List<String> makeBoard(){
        // Representer of The Board.
        StringBuilder column = new StringBuilder();
        List<String> columns = new ArrayList<>();
        for (String[] x : boardWithShips) {
            for (String c : x) {
                column.append(c).append(" | ");
            }
            columns.add(column + "");
            column.setLength(0);
        }
        return columns;
    }
    public void showBoard(){
        String coordsY = "    A | B | C | D | E | F | G | H | I | J ";
        System.out.println(coordsY);
        for(int i = 0; i < (makeBoard().size());i++){
            System.out.println(makeBoard().get(i));
        }
    }
}
