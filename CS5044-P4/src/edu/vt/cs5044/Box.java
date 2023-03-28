package edu.vt.cs5044;

import java.util.HashSet;
import java.util.Set;

/**
 * This is a helper class to assist with DABGame class. These methods will be called by the DABGame
 * class to return values and reduce code redundancy.
 *
 * @author SamIAm
 * @version Mar 22, 2023
 *
 */
public class Box {

    private Player owner;
    private Set<Direction> drawnEdges;

    /**
     * Construct a new Box object.
     *
     */
    public Box() {
        owner = null;
        drawnEdges = new HashSet<>();
    }

    /**
     * Gets the owner
     *
     * @return owner gets current owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Gets the drawn edges of the box
     *
     * @return Direction of drawn edges
     */
    public Set<Direction> getDrawnEgdes() { // piazza, it has an issue

        return new HashSet<>(drawnEdges);

    }

    /**
     * Get where the edge is drawn at
     * 
     * @param dir Direction of the edge
     * @return true/false on where the edge is drawn
     */
    public boolean isDrawnEdgeAt(Direction dir) {
        return drawnEdges.contains(dir);
    }

    /**
     * This method is to draw an edge of the box
     * @param dir Direction that the edge is drawn
     * @param currentPlayer current or opposite player
     */
    public void drawEdge(Direction dir, Player currentPlayer) {

        drawnEdges.add(dir);
        if (isBoxCompleted()) {
            owner = currentPlayer;
        }
    }

    /**
     * Returns a complete box
     *
     * @return true that the box is complete
     */
    public boolean isBoxCompleted() {
        return drawnEdges.size() == 4;
    }

}
