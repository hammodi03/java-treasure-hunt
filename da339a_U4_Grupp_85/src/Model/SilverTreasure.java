package Model;

import java.awt.Color;

/**
 * @author Arjan
 *
 * The SilverTreasure class represents a silver treasure in the game, which is a type of GameItem.
 * When a player lands on a silver treasure, they gain points.
 */
public class SilverTreasure extends GameItem {

    /**
     * Constructs a SilverTreasure object with the default type "Silver".
     */
    public SilverTreasure() {
        super("Silver");
    }

    /**
     * Updates the player's points when they land on a silver treasure.
     *
     * @param player the player whose points will be updated
     */
    @Override
    public void updatePoints(Player player) {
        player.addPoints(30);
    }

    /**
     * Returns the type of this game item.
     *
     * @return a string representing the type of this game item
     */
    @Override
    public String getType() {
        return "Silver";
    }

    /**
     * Returns the color of the game item.
     *
     * @return the color of the game item
     */
    @Override
    public Color getColor() {
        return Color.LIGHT_GRAY;
    }
}
