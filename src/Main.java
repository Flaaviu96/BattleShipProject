

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, IOException {

        PlayerVsPlayer playerVsPlayer = new PlayerVsPlayer();
        playerVsPlayer.playersSetup();
        playerVsPlayer.playGame();
    }
}