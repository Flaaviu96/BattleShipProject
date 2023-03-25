

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameLogic {
    boolean sankTheLastShip(HashSet<BattleShip>battleShips) {
        return battleShips.stream().allMatch(battleShip -> battleShip.isSunked());
    }

    List<BattleShip>findBattleShipsNotSinked(HashSet<BattleShip> battleShipList, Coordinates coordinates) {
        return battleShipList.stream()
                .filter(battleShip -> !battleShip.isSunked())
                .filter(battleShip -> battleShip.getCoordinatesList().stream().anyMatch(coordinates1 -> coordinates1.equals(coordinates)))
                .collect(Collectors.toList());
    }
    char evaluateSunkenShip(HashSet<BattleShip> battleShipList, Coordinates coordinates) {
        List<BattleShip> battleShipsFound = findBattleShipsNotSinked(battleShipList,coordinates);

        if(battleShipsFound.isEmpty()) {
            return 'M';
        } else {
            BattleShip battleShipInstance = battleShipsFound.get(0);
            battleShipList.remove(battleShipInstance);
            battleShipInstance = setBattleShipPropertise(battleShipInstance,coordinates);
            battleShipList.add(battleShipInstance);

            if(battleShipInstance.isSunked()) {
                return 'S';
            }
            return 'X';
        }

    }
    BattleShip setBattleShipPropertise(BattleShip battleShipInstance,Coordinates coordinates) {
        battleShipInstance.getCoordinatesList().forEach(
                coordinatesPoints -> {
                    if(coordinatesPoints.equals(coordinates) && !coordinatesPoints.getChecked()) {
                        coordinatesPoints.setChecked(true);
                        battleShipInstance.incrementSizeOfShip();

                    }
                }
        );

        if(battleShipInstance.getLenghtOfShip() == battleShipInstance.getCounter()) {
            battleShipInstance.setSunked(true);
        }
        return battleShipInstance;
    }

     boolean checkNeighbors(int row, int col,char[][] board) {
        if (board[row][col] == 'O') {
            return true;
        }
        if (col > 0) {
            if (board[row][col - 1] == 'O') {
                return true;
            }
            if (row > 0) {
                if (board[row - 1][col] == 'O') {
                    return true;
                }
                if (board[row - 1][col - 1] == 'O') {
                    return true;
                }
            }
            if (row < 9) {
                if (board[row + 1][col] == 'O') {
                    return true;
                }
                if (board[row + 1][col - 1] == 'O') {
                    return true;
                }
            }
        }
        if (col < 9) {
            if (board[row][col + 1] == 'O') {
                return true;
            }
            if (row > 0) {
                if (board[row - 1][col] == 'O') {
                    return true;
                }
                if (board[row - 1][col + 1] == 'O') {
                    return true;
                }
            }
            if (row < 9) {
                if (board[row + 1][col] == 'O') {
                    return true;
                }
                if (board[row + 1][col + 1] == 'O') {
                    return true;
                }
            }
        }

        return false;
    }
//    public static void main(String[]args) throws IllegalAccessException {
//        HashSet<BattleShip> battleShips = new HashSet<>();
//        BattleShip battleShip = new BattleShip("Test",5);
//        Coordinates coordinates = new Coordinates(1,2);
//        Coordinates coordinates1 = new Coordinates(3,3);
//
//        battleShip.addCoordinates(coordinates);
//        battleShip.addCoordinates(coordinates1);
//
//        battleShips.add(battleShip);
//
//        BattleShip battleShip1 = new BattleShip("Test2",3);
//
//        Coordinates coordinates2 = new Coordinates(2,2);
//        Coordinates coordinates3 = new Coordinates(5,3);
//
//        battleShip1.addCoordinates(coordinates2);
//        battleShip1.addCoordinates(coordinates3);
//
//        battleShips.add(battleShip1);
//
//
//        GameLogic gameLogic = new GameLogic();
//        int[] coordinatesint = new int[2];
//        coordinatesint[0] = 1;
//        coordinatesint[1] = 2;
//    }
}
