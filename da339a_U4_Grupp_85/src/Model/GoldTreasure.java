package Model;

import java.awt.Color;

/**
 * @author Arjan
 *
 * The GoldTreasure class represents a gold treasure in the game, which is a type of GameItem.
 */
public class GoldTreasure extends GameItem {

    /**
     * Constructs a GoldTreasure object with the default type "Gold".
     */
    public GoldTreasure() {
        super("Gold");
    }

    /**
     * Updates the player's points when they land on a gold treasure.
     *
     * @param player the player whose points will be updated
     */
    @Override
    public void updatePoints(Player player) {
        player.addPoints(40);
    }

    /**
     * Returns the type of this game item.
     *
     * @return a string representing the type of this game item
     */
    @Override
    public String getType() {
        return "Gold";
    }

    /**
     * Returns the color of the game item.
     *
     * @return the color of the game item
     */
    @Override
    public Color getColor() {
        return Color.YELLOW;
    }
}
