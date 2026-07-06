package Model;

/**
 * @author Mohamed
 *
 * The Player class represents a player in the game.
 * Each player has a name and a score, which can be updated during the game.
 */
public class Player {
    private String name;
    private int points;

    /**
     * Constructs a Player object with the specified name and initializes points to 0.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    /**
     * Constructs a Player object with the specified name and points.
     *
     * @param name the name of the player
     * @param points the initial points of the player
     */
    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current points of the player.
     *
     * @return the current points of the player
     */
    public int getPoints() {
        return points;
    }

    /**
     * Adds the specified number of points to the player's score.
     *
     * @param points the points to add to the player's score
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Sets the player's score to the specified number of points.
     *
     * @param points the new score for the player
     */
    public void setPoints(int points) {
        this.points = points;
    }
}
