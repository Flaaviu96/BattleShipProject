

import java.io.IOException;
import java.util.Scanner;

public class PlayerVsPlayer {
    GameLogic gameLogicPlayerOne = new GameLogic();
    GameLogic gameLogicPlayerTwo = new GameLogic();
    PlayerBoard player1 = new PlayerBoard(gameLogicPlayerOne);
    PlayerBoard player2 = new PlayerBoard(gameLogicPlayerTwo);
    Scanner scanner = new Scanner(System.in);

    private void nextPlayer() {
        boolean hasEnterBeenPressed = false;
        while (!hasEnterBeenPressed) {
            System.out.println();
            System.out.println("Press Enter and pass the move to another player");
            System.out.println("...");
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()){
                hasEnterBeenPressed = true;
            }
        }
    }
    private void changeTurn() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");
        scanner.nextLine();
    }
    protected void playersSetup() throws IllegalAccessException {
        System.out.println("Player 1, place your ships on the game field");
        System.out.println();
        player1.printBoard();
        setShips(player1);

        changeTurn();

        System.out.println("Player 2, place your ships on the game field");
        System.out.println();
        player2.printBoard();
        setShips(player2);
        changeTurn();

    }
    private void setShips(PlayerBoard playerBoard) throws IllegalAccessException {
        for (Ship ship : Ship.values()) {
            playerBoard.markBattleShipOnPlayerBoard(ship);
            playerBoard.printBoard();
        }
    }
    protected void playGame() throws IllegalAccessException, IOException {
        boolean isGameOver = false;
        while (!isGameOver) {
            player2.printBoard(player2.GetfogOfWarBoard());
            System.out.println("---------------------");
            player1.printBoard();

            System.out.println();
            System.out.println("Player 1, it's your turn:");
            isGameOver = player2.attackBattleShip();
            if(isGameOver) {
                break;
            }
            changeTurn();

            player1.printBoard(player1.GetfogOfWarBoard());
            System.out.println("---------------------");
            player2.printBoard();

            System.out.println();
            System.out.println("Player 2, it's your turn:");
            isGameOver = player1.attackBattleShip();
            if(isGameOver) {
                break;
            }
            changeTurn();

        }
    }

}
