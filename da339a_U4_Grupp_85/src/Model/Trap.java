package Model;

import java.awt.Color;

/**
 * @author Arjan
 *
 * The Trap class represents a trap in the game, which is a type of GameItem.
 * When a player lands on a trap, they lose points.
 */
public class Trap extends GameItem {

    /**
     * Constructs a Trap object with the default type "Trap".
     */
    public Trap() {
        super("Trap");
    }

    /**
     * Updates the player's points when they land on a trap.
     *
     * @param player the player whose points will be updated
     */
    @Override
    public void updatePoints(Player player) {
        player.addPoints(-10);
    }

    /**
     * Returns the type of this game item.
     *
     * @return a string representing the type of this game item
     */
    @Override
    public String getType() {
        return "Trap";
    }

    /**
     * Returns the color of the game item.
     *
     * @return the color of the game item
     */
    @Override
    public Color getColor() {
        return Color.RED;
    }
}
