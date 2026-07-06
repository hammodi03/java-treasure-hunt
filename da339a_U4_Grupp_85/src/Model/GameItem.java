package Model;

import java.awt.*;

/**
 * @author Mohamed
 *
 * The GameItem class represents an abstract game item that can be placed on the game board.
 * Each game item has a type, can update a player's points, and has a color.
 */
public abstract class GameItem {
    private String type;

    /**
     * Constructs a GameItem object with the specified type.
     *
     * @param type the type of the game item
     */
    public GameItem(String type) {
        this.type = type;
    }

    /**
     * Returns the type of this game item.
     *
     * @return a string representing the type of this game item
     */
    public abstract String getType();

    /**
     * Updates the player's points based on the type of the game item.
     *
     * @param player the player whose points will be updated
     */
    public abstract void updatePoints(Player player);

    /**
     * Returns the color of the game item.
     *
     * @return the color of the game item
     */
    public abstract Color getColor();
}
