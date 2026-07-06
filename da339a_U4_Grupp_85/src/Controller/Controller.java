package Controller;

import Model.*;
import View.Gui;
import View.Leaderboard;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * @author Arjan & Mohamed
 *
 * The Controller class manages the game's logic, including player turns,
 * the game board, treasure placement, and leaderboard management.
 */
public class Controller {
    private Player player1;
    private Player player2;
    private boolean player1Turn = true;
    private GameItem[][] boardItems;
    private Gui view;
    private Leaderboard leaderboard;
    private Map<String, Integer> remainingTreasures;
    private Map<String, Integer> treasurePoints;
    private static final String LEADERBOARD_FILE = "leaderboard.txt";

    /**
     * @author Arjan
     * Constructor initializes the game board, treasure points,
     * and remaining treasures.
     */
    public Controller() {
        boardItems = new GameItem[10][10];
        remainingTreasures = new HashMap<>();
        treasurePoints = new HashMap<>();
        treasurePoints.put("Diamond", 50);
        treasurePoints.put("Bronze", 10);
        treasurePoints.put("Silver", 20);
        treasurePoints.put("Gold", 30);
        treasurePoints.put("Iron", 15);
    }

    /**
     * @author Arjan
     * Sets the GUI view for the game.
     *
     * @param view the GUI view
     */
    public void setView(Gui view) {
        this.view = view;
    }

    /**
     * @author Arjan
     * Sets the leaderboard for the game and updates it from the file.
     *
     * @param leaderboard the leaderboard
     */
    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
        updateLeaderboardFromFile();
    }

    /**
     * @author Arjan
     * Starts the game with specified player names and board type.
     *
     * @param player1Name the name of player 1
     * @param player2Name the name of player 2
     * @param boardType   the type of the board (1 or 2)
     */
    public void startGame(String player1Name, String player2Name, int boardType) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        player1Turn = true;
        if (boardType == 1) {
            initializeBoard1();
        } else if (boardType == 2) {
            initializeBoard2();
        }
        if (view != null) {
            view.resetBoard();
            view.updateScores(player1.getPoints(), player2.getPoints());
            view.updateTurnLabel("Player 1's Turn");
        }
    }

    /**
     * @author Arjan
     * Initializes board type 1 with treasures and traps.
     */
    public void initializeBoard1() {
        clearBoard();
        placeTreasure(7, 6, "Diamond", Color.BLUE);
        placeTreasure(7, 7, "Diamond", Color.BLUE);
        placeTreasure(8, 7, "Diamond", Color.BLUE);
        placeTreasure(9, 7, "Diamond", Color.BLUE);
        placeTreasure(9, 6, "Diamond", Color.BLUE);
        placeTreasure(0, 0, "Bronze", new Color(205, 127, 50));
        placeTreasure(0, 1, "Bronze", new Color(205, 127, 50));
        placeTreasure(8, 3, "Silver", Color.LIGHT_GRAY);
        placeTreasure(8, 4, "Silver", Color.LIGHT_GRAY);
        placeTreasure(8, 5, "Silver", Color.LIGHT_GRAY);
        placeTreasure(1, 8, "Gold", Color.YELLOW);
        placeTreasure(2, 8, "Gold", Color.YELLOW);
        placeTreasure(3, 8, "Gold", Color.YELLOW);
        placeTreasure(3, 9, "Gold", Color.YELLOW);
        placeTreasure(3, 3, "Iron", new Color(176, 176, 176));
        placeTreasure(3, 4, "Iron", new Color(176, 176, 176));
        placeTreasure(7, 1, "Trap", Color.RED);
        placeTreasure(5, 3, "Trap", Color.RED);
        placeTreasure(3, 5, "Trap", Color.RED);
        placeTreasure(2, 7, "Trap", Color.RED);
        placeTreasure(4, 9, "Trap", Color.RED);
        placeTreasure(7, 4, "Trap", Color.RED);
        placeTreasure(9, 3, "Trap", Color.RED);
        placeTreasure(8, 6, "Trap", Color.RED);
        placeTreasure(1, 2, "Trap", Color.RED);
        placeTreasure(3, 2, "Trap", Color.RED);

        initializeTreasures();
    }

    /**
     * @author Arjan
     * Initializes board type 2 with treasures and traps.
     */
    public void initializeBoard2() {
        clearBoard();
        placeTreasure(8, 1, "Trap", Color.RED);
        placeTreasure(4, 7, "Trap", Color.RED);
        placeTreasure(6, 5, "Trap", Color.RED);
        placeTreasure(1, 7, "Trap", Color.RED);
        placeTreasure(8, 9, "Trap", Color.RED);
        placeTreasure(3, 2, "Trap", Color.RED);
        placeTreasure(5, 3, "Trap", Color.RED);
        placeTreasure(7, 6, "Trap", Color.RED);
        placeTreasure(3, 8, "Trap", Color.RED);
        placeTreasure(6, 2, "Trap", Color.RED);

        // Diamond skatter
        placeTreasure(6, 8, "Diamond", Color.BLUE);
        placeTreasure(5, 9, "Diamond", Color.BLUE);
        placeTreasure(6, 9, "Diamond", Color.BLUE);
        placeTreasure(7, 9, "Diamond", Color.BLUE);
        placeTreasure(7, 8, "Diamond", Color.BLUE);

        // Bronze skatter
        placeTreasure(9, 8, "Bronze", new Color(205, 127, 50));
        placeTreasure(9, 9, "Bronze", new Color(205, 127, 50));

        // Silver skatter
        placeTreasure(8, 3, "Silver", Color.LIGHT_GRAY);
        placeTreasure(8, 4, "Silver", Color.LIGHT_GRAY);
        placeTreasure(8, 5, "Silver", Color.LIGHT_GRAY);

        // Gold skatter
        placeTreasure(2, 7, "Gold", Color.YELLOW);
        placeTreasure(2, 8, "Gold", Color.YELLOW);
        placeTreasure(3, 8, "Gold", Color.YELLOW);
        placeTreasure(3, 9, "Gold", Color.YELLOW);

        // Iron skatter
        placeTreasure(3, 3, "Iron", Color.DARK_GRAY);
        placeTreasure(3, 4, "Iron", Color.DARK_GRAY);

        initializeTreasures();
    }

    /**
     * @author Mohamed
     * Clears the game board by setting all positions to null.
     */
    public void clearBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardItems[i][j] = null;
            }
        }
    }

    /**
     * @author Mohamed
     * Initializes the treasures on the board.
     */
    public void initializeTreasures() {
        remainingTreasures.put("Diamond", 5);
        remainingTreasures.put("Bronze", 2);
        remainingTreasures.put("Silver", 3);
        remainingTreasures.put("Gold", 4);
        remainingTreasures.put("Iron", 2);
    }

    /**
     * @author Mohamed
     * Places a treasure or trap on the board at the specified position.
     *
     * @param x     the x-coordinate
     * @param y     the y-coordinate
     * @param type  the type of treasure or trap
     * @param color the color of the treasure or trap
     */
    public void placeTreasure(int x, int y, String type, Color color) {
        GameItem treasure = null;
        switch (type) {
            case "Diamond":
                treasure = new DiamondTreasure();
                break;
            case "Bronze":
                treasure = new BronzeTreasure();
                break;
            case "Silver":
                treasure = new SilverTreasure();
                break;
            case "Gold":
                treasure = new GoldTreasure();
                break;
            case "Iron":
                treasure = new IronTreasure();
                break;
            case "Trap":
                treasure = new Trap();
        }
        boardItems[x][y] = treasure;
        view.updateButton(x, y, true, type, color);
    }

    /**
     * @author Mohamed & Arjan
     * Handles the button click event, updates the game state,
     * and manages player turns.
     *
     * @param x the x-coordinate of the clicked button
     * @param y the y-coordinate of the clicked button
     */
    public void handleButtonClick(int x, int y) {
        Player currentPlayer;
        if (player1Turn) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
        GameItem item = boardItems[x][y];
        if (item != null) {
            String type = item.getType();
            if (remainingTreasures.containsKey(type)) {
                remainingTreasures.put(type, remainingTreasures.get(type) - 1);
                view.updateButton(x, y, false, type, item.getColor());

                if (remainingTreasures.get(type) == 0) {
                    int points = treasurePoints.get(type);
                    currentPlayer.addPoints(points);
                    highlightTreasure(type);
                }
            } else {
                item.updatePoints(currentPlayer);
                view.updateButton(x, y, false, type, item.getColor());
            }
        } else {
            view.updateButton(x, y, false, "Empty", null);
        }

        view.updateScores(player1.getPoints(), player2.getPoints());
        player1Turn = !player1Turn;
        if (player1Turn) {
            view.updateTurnLabel("Player 1's Turn");
        } else {
            view.updateTurnLabel("Player 2's Turn");
        }

        if (isGameFinished()) {
            view.showGameFinishedMessage();
            updateLeaderboard();
            view.setBoardEnabled(false);
        }
    }

    /**
     * @author Mohamed
     * Checks if the game is finished by verifying if all treasures have been collected.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isGameFinished() {
        for (Integer count : remainingTreasures.values()) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @author Mohamed
     * Highlights gameitem of the specified type on the board.
     *
     * @param type the type of treasure to highlight
     */
    public void highlightTreasure(String type) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                GameItem item = boardItems[i][j];
                if (item != null && item.getType().equals(type)) {
                    view.highlightButton(i, j);
                }
            }
        }
    }

    /**
     * @author Mohamed & Arjan
     * Updates the leaderboard with the current game's winner and writes it to the file.
     */
    public void updateLeaderboard() {
        Player winner;
        if (player1.getPoints() > player2.getPoints()) {
            winner = player1;
        } else {
            winner = player2;
            }
        List<Player> topPlayers = readLeaderboardFromFile();


        boolean foundWinner = false;
        for (Player player : topPlayers) {
            if (player.getName().equals(winner.getName())) {
                if (winner.getPoints() > player.getPoints()) {
                    player.setPoints(winner.getPoints());
                }
                foundWinner = true;
                break;
            }
        }
        if (!foundWinner) {
            topPlayers.add(winner);
        }

        // This sorts the leaderboard for top 10 players
        topPlayers.sort((p1, p2) -> Integer.compare(p2.getPoints(), p1.getPoints()));
        if (topPlayers.size() > 10) {
            topPlayers = topPlayers.subList(0, 10);
        }
        writeLeaderboardToFile(topPlayers);
        updateLeaderboardUI(topPlayers);
    }

    /**
     * @author Mohamed
     * Reads the leaderboard from the file.
     *
     * @return a list of players from the leaderboard
     */
    public List<Player> readLeaderboardFromFile() {
        List<Player> players = new ArrayList<>();
        File leaderboardFile = new File(LEADERBOARD_FILE);

        if (!leaderboardFile.exists()) {
            try {
                leaderboardFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return players;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(leaderboardFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String name = parts[0];
                    int points = Integer.parseInt(parts[1].trim());
                    players.add(new Player(name, points));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * @author Arjan
     * Writes the leaderboard to the file.
     *
     * @param players a list of players to be written to the file
     */
    public void writeLeaderboardToFile(List<Player> players) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LEADERBOARD_FILE))) {
            for (Player player : players) {
                writer.write(player.getName() + ": " + player.getPoints());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Arjan
     * Updates the leaderboard from the file and refreshes the leaderboard UI.
     */
    public void updateLeaderboardFromFile() {
        List<Player> players = readLeaderboardFromFile();
        updateLeaderboardUI(players);
    }

    /**
     * @author Arjan
     * Updates the leaderboard UI with the list of top players.
     *
     * @param topPlayers a list of top players
     */
    public void updateLeaderboardUI(List<Player> topPlayers) {
        StringBuilder sb = new StringBuilder();
        sb.append("Top Players:\n");
        for (Player player : topPlayers) {
            sb.append(player.getName()).append(": ").append(player.getPoints()).append(" points\n");
        }
        leaderboard.setLeaderboardText(sb.toString());
    }
}
