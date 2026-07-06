package Model;

import java.awt.Color;

/**
 * @author Arjan
 *
 * The BronzeTreasure class represents a bronze
 * treasure in the game, which is a type of GameItem.
 */
public class BronzeTreasure extends GameItem {

    /**
     * Constructs a BronzeTreasure object with the type "Bronze".
     */
    public BronzeTreasure() {
        super("Bronze");
    }

    /**
     * Updates the player's points when they collect a bronze treasure.
     *
     * @param player the player whose points will be updated
     */
    @Override
    public void updatePoints(Player player) {
        player.addPoints(10);
    }

    /**
     * Returns the type of this game item.
     *
     * @return a string representing the type of this game item
     */
    @Override
    public String getType() {
        return "Bronze";
    }

    /**
     * Returns the color of the game item.
     *
     * @return the color of the game item
     */
    @Override
    public Color getColor() {
        return new Color(205, 127, 50);
    }
}
