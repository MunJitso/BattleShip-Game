import java.util.ArrayList;
import java.util.List;

public class Battleship {
    public static void main(String[] args) {
        List<String> sizes = new ArrayList<>();
        for (int i = 0; i < Board.getSizes.length; i++){
            sizes.add(String.valueOf(Board.getSizes[i]));
        }
        Board board = new Board();
        Player player = new Player();
        board.showBoard();
        player.playerInput();
        for (int x = 0; x < Board.boardWithShips.length; x++){
            for (int y = 0; y < Board.boardWithShips[x].length; y++){
                if (sizes.contains(Board.boardWithShips[x][y])){
                    board.showBoard();
                    player.playerInput();
                } else if (x == 9 && y == 9 && !sizes.contains(Board.boardWithShips[x][y])){
                    System.out.println("YaaY you won!");
                }
            }
        }

    }
}
