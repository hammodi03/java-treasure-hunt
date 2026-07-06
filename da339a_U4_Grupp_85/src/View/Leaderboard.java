package View;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mohamed
 *
 * The Leaderboard class represents a window that displays the leaderboard for the game.
 * It shows the scores of the players.
 */
public class Leaderboard extends JFrame {

    private JTextArea leaderboardArea;

    /**
     * Constructs a Leaderboard object and sets up the window.
     */
    public Leaderboard() {
        setTitle("Leaderboard");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        leaderboardArea = new JTextArea();
        leaderboardArea.setEditable(false);
        add(new JScrollPane(leaderboardArea), BorderLayout.CENTER);
    }

    /**
     * Sets the text to be displayed in the leaderboard.
     *
     * @param text the text to be displayed in the leaderboard. text received from controller.
     */
    public void setLeaderboardText(String text) {
        leaderboardArea.setText(text);
    }
}
