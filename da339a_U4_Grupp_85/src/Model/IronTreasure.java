package Model;

import java.awt.*;

/**
 * @author Arjan
 *
 * The IronTreasure class represents an iron treasure in the game, which is a type of GameItem.
 */
public class IronTreasure extends GameItem {

    /**
     * Constructs an IronTreasure object with the default type "Iron".
     */
    public IronTreasure() {
        super("Iron");
    }

    /**
     * Returns the type of this game item.
     *
     * @return a string representing the type of this game item
     */
    @Override
    public String getType() {
        return "Iron";
    }

    /**
     * Updates the player's points when they land on an iron treasure.
     *
     * @param player the player whose points will be updated
     */
    @Override
    public void updatePoints(Player player) {
        player.addPoints(5);
    }

    /**
     * Returns the color of the game item.
     *
     * @return the color of the game item
     */
    @Override
    public Color getColor() {
        return Color.DARK_GRAY;
    }
}
