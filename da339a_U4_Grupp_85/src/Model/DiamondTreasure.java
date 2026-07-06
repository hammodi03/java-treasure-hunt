package Model;

import java.awt.Color;

/**
 * @author Arjan
 *
 * The DiamondTreasure class represents a diamond treasure in the game, which is a type of GameItem.
 */
public class DiamondTreasure extends GameItem {

    /**
     * Constructs a DiamondTreasure object with the type "Diamond".
     */
    public DiamondTreasure() {
        super("Diamond");
    }

    /**
     * Updates the player's points when they collect a diamond treasure.
     *
     * @param player the player whose points will be updated
     */
    @Override
    public void updatePoints(Player player) {
        player.addPoints(50);
    }

    /**
     * Returns the type of this game item.
     *
     * @return a string representing the type of this game item
     */
    @Override
    public String getType() {
        return "Diamond";
    }

    /**
     * Returns the color of the game item.
     *
     * @return the color of the game item
     */
    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
