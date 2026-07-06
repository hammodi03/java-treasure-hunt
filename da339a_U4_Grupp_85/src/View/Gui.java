package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Arjan & Mohamed
 *
 * The Gui class represents the view for the Treasure Hunt Game.
 * It sets up the game board, player panels, and input fields for player names.
 */
public class Gui extends JFrame {

    private JTextField player1Field;
    private JTextField player2Field;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player1Points;
    private JLabel player2Points;
    private JLabel turnLabel;
    private JPanel gameBoard;
    private JButton[][] buttons;
    private Controller controller;

    /**
     * @author Mohamed
     * Constructs a Gui object.
     *
     * @param controller the controller for handling game logic
     */
    public Gui(Controller controller) {
        this.controller = controller;
        setTitle("Treasure Hunt Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initInputPanel();
        initGameBoard();
        initPlayerPanels();
        setBoardEnabled(false);
    }

    /**
     * @author Mohamed
     * Initializes the input panel for player names and game start buttons.
     */
    public void initInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Player 1:"));
        player1Field = new JTextField();
        inputPanel.add(player1Field);

        inputPanel.add(new JLabel("Player 2:"));
        player2Field = new JTextField();
        inputPanel.add(player2Field);

        JButton playButton1 = new JButton("Play with Board 1");
        inputPanel.add(playButton1);

        JButton playButton2 = new JButton("Play with Board 2");
        inputPanel.add(playButton2);

        turnLabel = new JLabel("Player 1's Turn");
        inputPanel.add(turnLabel);

        add(inputPanel, BorderLayout.NORTH);

        playButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(1);
            }
        });

        playButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(2);
            }
        });
    }

    /**
     * @author Arjan
     * Initializes the game board with buttons for all blocks.
     */
    public void initGameBoard() {
        gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(10, 10));
        buttons = new JButton[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton();
                final int x = i;
                final int y = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.handleButtonClick(y, x);
                    }
                });
                gameBoard.add(buttons[i][j]);
            }
        }

        add(gameBoard, BorderLayout.CENTER);
    }

    /**
     * @author Arjan
     * Initializes the player panels displaying player names and points.
     */
    public void initPlayerPanels() {
        JPanel player1Panel = new JPanel();
        player1Panel.setLayout(new BorderLayout());
        player1Label = new JLabel("Player 1: ");
        player1Points = new JLabel("Points: 0");
        player1Panel.add(player1Label, BorderLayout.NORTH);
        player1Panel.add(player1Points, BorderLayout.SOUTH);

        JPanel player2Panel = new JPanel();
        player2Panel.setLayout(new BorderLayout());
        player2Label = new JLabel("Player 2: ");
        player2Points = new JLabel("Points: 0");
        player2Panel.add(player2Label, BorderLayout.NORTH);
        player2Panel.add(player2Points, BorderLayout.SOUTH);

        add(player1Panel, BorderLayout.WEST);
        add(player2Panel, BorderLayout.EAST);
    }

    /**
     * @author Mohamed
     * Starts the game with the specified board type.
     *
     * @param boardType the type of board to use (1 or 2)
     */
    public void startGame(int boardType) {
        String player1Name = player1Field.getText();
        String player2Name = player2Field.getText();

        if (player1Name.isEmpty() || player2Name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please choose your name for both players");
        } else if (player1Name.equals(player2Name)) {
            JOptionPane.showMessageDialog(null, "You can't choose the same name for both players");
        } else {
            JOptionPane.showMessageDialog(null, "Game successfully started");
            player1Label.setText("Player 1: " + player1Name);
            player2Label.setText("Player 2: " + player2Name);
            controller.startGame(player1Name, player2Name, boardType);
            setBoardEnabled(true);
        }
    }

    /**
     * @author Arjan
     * Updates the specified button on the game board.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param enabled whether the button should be enabled
     * @param text the text to display on the button
     * @param color the background color of the button
     */
    public void updateButton(int x, int y, boolean enabled, String text, Color color) {
        buttons[y][x].setEnabled(enabled);
        buttons[y][x].setText(text);
        buttons[y][x].setBackground(color);
    }

    /**
     * @author Mohamed
     * Updates the scores displayed for both players.
     *
     * @param player1Score the score of player 1
     * @param player2Score the score of player 2
     */
    public void updateScores(int player1Score, int player2Score) {
        player1Points.setText("Points: " + player1Score);
        player2Points.setText("Points: " + player2Score);
    }

    /**
     * @author Mohamed
     * Resets the game board to its initial state.
     */
    public void resetBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                updateButton(i, j, true, "", null);
            }
        }
    }

    /**
     * @author Arjan
     * Highlights the specified button on the game board.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     */
    public void highlightButton(int x, int y) {
        buttons[y][x].setBackground(Color.GREEN);
    }

    /**
     * @author Arjan
     * Updates the turn label with the specified text.
     *
     * @param text the text to display on the gui. text received from the controller.
     */
    public void updateTurnLabel(String text) {
        turnLabel.setText(text);
    }

    /**
     * @author Mohamed
     * Shows a message indicating that the game has finished.
     */
    public void showGameFinishedMessage() {
        JOptionPane.showMessageDialog(this, "Game finished");
    }

    /**
     * @author Arjan
     * Enables or disables all buttons on the game board.
     *
     * @param enabled whether the buttons should be enabled
     */
    public void setBoardEnabled(boolean enabled) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setEnabled(enabled);
            }
        }
    }
}
