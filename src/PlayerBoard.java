

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class PlayerBoard {
    private final int BOARD_ROW = 10;
    private final int BOARD_COL = 10;
    private char[][] playerBoard = new char[BOARD_ROW][BOARD_COL];
    private char[][] fogOfWarBoard = new char[BOARD_ROW][BOARD_COL];
    HashSet<BattleShip> battleShips = new HashSet<>();
    GameLogic gameLogic;
    static Scanner scanner = null;

    PlayerBoard(GameLogic gameLogic){
        populateBoard(playerBoard);
        populateBoard(fogOfWarBoard);
        this.gameLogic = gameLogic;
        scanner = new Scanner(System.in);
    }
    char[][] GetfogOfWarBoard(){
        return fogOfWarBoard;
    }
    void markPlayerBoard(char[][]playerBoard,char[][]fogOfWar,Coordinates coordinates,char mark) {
        playerBoard[coordinates.getA()][coordinates.getB()] = mark;
        printBoard(fogOfWarBoard);
    }
    boolean attackBattleShip() throws IllegalAccessException {
        boolean isGameOver = false;
        int[] coordinates = null;
        coordinates = toDigitCoordinates(scanner.nextLine());
        Coordinates coordinatesObject = null;
            try{
                coordinatesObject = checkAttackCoordinate(coordinates);
            }catch(IllegalAccessException e) {
                System.out.println(e.getMessage()+" Try again: ");
            }
            isGameOver = getResultFromGameLogic(coordinatesObject);
            return isGameOver;
    }
    boolean getResultFromGameLogic(Coordinates coordinates){
        boolean isGameOVer = false;
       char mark = gameLogic.evaluateSunkenShip(battleShips,coordinates);
       markPlayerBoard(playerBoard,fogOfWarBoard,coordinates,mark=='S'?'X':mark);
        switch (mark) {
            case 'M' :
                System.out.println("You missed! Try again:");
                break;
            case 'X' :
                System.out.println("You hit a ship! Try again:");
                break;
            case 'S':
                if(gameLogic.sankTheLastShip(battleShips)) {
                    isGameOVer = true;
                    System.out.println("You sank the last ship. You won. Congratulations!");
                } else {
                    System.out.println("You sank a ship! Specify a new target:");
                }
                break;
        }
        return isGameOVer;
    }
    void markBattleShipOnPlayerBoard(Ship ship) throws IllegalAccessException {
        int[]coordinates = new int[4];
       // scanner = new Scanner(System.in);
        boolean isCoordinatesValid = false;
        System.out.printf("Enter the coordinates of the %s (%d cells):\n",
                ship.getPrintName(), ship.getLength());
        while (!isCoordinatesValid) {
            coordinates = toDigitCoordinates(scanner.nextLine());
            try {
                isCoordinatesValid = validateCoordinates(ship, coordinates);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage() + " Try again:");
            }
        }
        BattleShip battleShip = new BattleShip(ship.getPrintName(),ship.getLength());
        for (int i = Math.min(coordinates[0], coordinates[2]); i <= Math.max(coordinates[0], coordinates[2]); i++) {
            for (int j = Math.min(coordinates[1], coordinates[3]); j <= Math.max(coordinates[1], coordinates[3]) ; j++) {
                playerBoard[i][j] = 'O';
                battleShip.getCoordinatesList().add(new Coordinates(i,j));
            }
        }
        this.battleShips.add(battleShip);
    }
    void populateBoard(char[][]board) {
        Arrays.stream(board).forEach(row -> Arrays.fill(row, '~'));
    }

    void printBoard(char[][]board) {
        char letter = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] row : board) {
            System.out.print(letter++);
            for (char element : row) {
                System.out.print(" " + element);
            }
            System.out.println();
        }
    }

        private int[] toDigitCoordinates(String coordinates) {
        int size = 0;
        if(coordinates.split(" ").length != 2) {
            size = 2;
        }
        else {
            size = 4;
        }
        int[] result = new int[size];
        int position=0;
        for(int i = 0; i < size; i+=2) {
            position = i == 0 ? 0 : 1;
            result[i] = coordinates.split(" ")[position].charAt(0) - 65;
            result[i+1] = Integer.parseInt(coordinates.split(" ")[position].substring(1)) - 1;
        }
        return result;
    }
    void printBoard() {
        char letter = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] row : playerBoard) {
            System.out.print(letter++);
            for (char element : row) {
                System.out.print(" " + element);
            }
            System.out.println();
        }
    }
       private Coordinates checkAttackCoordinate(int[] coordinates) throws IllegalAccessException {
        Coordinates coordinatesObject;
        try{
            coordinatesObject = new Coordinates(coordinates[0],coordinates[1]);
        }catch (IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage());
        }
        return coordinatesObject;

    }

    private boolean validateCoordinates(Ship ship,int[] coordinates) throws IllegalAccessException {
        boolean isValid = true;
        if ((coordinates[0] == coordinates[2]) == (coordinates[1] == coordinates[3])) {
            throw new IllegalAccessException("Error! Wrong ship location!");
        }
        if (Math.abs((coordinates[0] - coordinates[2]) - (coordinates[1] - coordinates[3])) + 1 != ship.getLength()) {
            throw new IllegalAccessException("Error! Wrong length of the " + ship.getPrintName() + "!");
        }
        for (int i = Math.min(coordinates[0], coordinates[2]); i <= Math.max(coordinates[0], coordinates[2]); i++) {
            for (int j = Math.min(coordinates[1], coordinates[3]); j <= Math.max(coordinates[1], coordinates[3]) ; j++) {
                if (gameLogic.checkNeighbors(i, j,playerBoard)) {
                    isValid = false;
                    throw new IllegalAccessException("Error! You placed it too close to another one.");
                }
            }
        }
        return isValid;
    }

}
