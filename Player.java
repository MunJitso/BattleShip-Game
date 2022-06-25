import java.util.HashMap;
import java.util.Scanner;
public class Player {
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RESET = "\u001B[0m";
    HashMap<Character, Integer> alphabets = new HashMap<>();
    public Player(){
        alphabets.put('a', 0);
        alphabets.put('b', 1);
        alphabets.put('c', 2);
        alphabets.put('d', 3);
        alphabets.put('e', 4);
        alphabets.put('f', 5);
        alphabets.put('g', 6);
        alphabets.put('h', 7);
        alphabets.put('i', 8);
        alphabets.put('j', 9);

    }
    public String[][] boardWithShips = Board.boardWithShips;
    int x;
    int y;
    String previousAttack = " ";
    public void playerInput(){

        System.out.println("Enter Coordinates. Ex: 'A0', 'C5', 'F9'.");
        System.out.print("Here : ");
        Scanner scanner = new Scanner(System.in);
        var playerCoords = scanner.nextLine().split("");
        if (playerCoords.length == 2){
            try {
                x = Integer.parseInt(playerCoords[1]);
                y = alphabets.get(playerCoords[0].toLowerCase().charAt(0)) + 1;
            } catch (NumberFormatException | NullPointerException err){
                System.out.println( ANSI_YELLOW + "Please enter valid coordinates." + ANSI_RESET);
                playerInput();
            }
        } else {
            playerInput();
        }
        if (boardWithShips[x][y].equalsIgnoreCase("X")) {
            System.out.println( ANSI_RED + "You already attacked it!" + ANSI_RESET);
        } else if(!boardWithShips[x][y].equalsIgnoreCase("_")){
            if (previousAttack.equals(" ") || previousAttack.equals(boardWithShips[x][y])){
                previousAttack = boardWithShips[x][y];
                boardWithShips[x][y] = "X";
                for(int i = 0; i < boardWithShips[x].length; i++){
                    if (i == boardWithShips[x].length-1 && !previousAttack.equals(boardWithShips[x][i])){
                        System.out.println( ANSI_GREEN + "You sank the ship!" + ANSI_RESET);
                        previousAttack = " ";
                        break;
                    } else if (previousAttack.equals(boardWithShips[x][i])){
                        System.out.println( ANSI_GREEN + "You hit the ship!" + ANSI_RESET);
                        break;
                    } else if (!boardWithShips[x][i].equals(previousAttack)){
                        continue;
                    }
                }
            } else if (!previousAttack.equals(boardWithShips[x][y])){
                System.out.println(ANSI_YELLOW + "You have to finish the ship you attacked before!" + ANSI_RESET);
            }
        } else{
            System.out.println( ANSI_RED + "There is no ship here." + ANSI_RESET);
        }

    }
}
