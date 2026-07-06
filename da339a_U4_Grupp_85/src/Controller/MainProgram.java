package Controller;

import View.Gui;
import View.Leaderboard;

/**
 * @author Arjan & Mohamed
 * The MainProgram class is the entry point of the application.
 * It initializes the Controller, Gui, and Leaderboard, and sets up
 * the relationships between them. it sets the GUI and
 * leaderboard visible.
 */
public class MainProgram {

    public static void main(String[] args) {
        Controller controller = new Controller();
        Gui gui = new Gui(controller);
        Leaderboard leaderboard = new Leaderboard();
        controller.setView(gui);
        controller.setLeaderboard(leaderboard);

        gui.setVisible(true);
        leaderboard.setVisible(true);
    }
}
