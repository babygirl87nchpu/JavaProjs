package edu.vt.cs5044;

import edu.vt.cs5044.tetris.AI;
import edu.vt.cs5044.tetris.Board;
import edu.vt.cs5044.tetris.Placement;
import edu.vt.cs5044.tetris.Rotation;
import edu.vt.cs5044.tetris.Shape;

/**
 * This class has private instances, helpers, and public methods for the AI interface implementation
 *
 * @author SamIAm
 * @version Mar 2, 2023
 *
 */
public class TetrisAI implements AI {

    private int averageHeightWeight = 12;
    private int heightVarienceWeight = 0;
    private int heightRangeWeight = 4;
    private int gapSumWeight = 12;

    /**
     * 
     * This method calculate the parameters on blocks
     *
     * @param board for tetris
     * @return int of the calculation
     */
    private int calculateCost(Board board) {
        return averageHeightWeight * getAverageColumnHeight(board)
            + heightVarienceWeight * getColumnHeightVariance(board)
            + heightRangeWeight * getColumnHeightRange(board)
            + gapSumWeight * getTotalGapCount(board) / 4;

    }

    @Override
    public Placement findBestPlacement(Board board, Shape shape) {
        Placement bestPlacement = null;
        int minCost = Integer.MAX_VALUE;
        for (Rotation rotation : shape.getValidRotationSet()) {
            for (int col = 0; col <= Board.WIDTH - shape.getWidth(rotation); col++) {
                Placement trialPlace = new Placement(rotation, col);
                Board trialBoard = board.getResultBoard(shape, trialPlace);
                int trialCost = calculateCost(trialBoard);
                if (trialCost < minCost) {
                    minCost = trialCost;
                    bestPlacement = trialPlace;
                }
            }
        }
        return bestPlacement;
    }

    /**
     * 
     * Private helper for Average Column Height.
     * 
     * @param board = getFixedBlocks
     * @param col starts at 0, 10 columns total
     * @return height of each column
     */
    private int getColumnHeight(Board board, int col) {
        boolean[][] fixedBlocks = board.getFixedBlocks();
        boolean[] colBlocks = fixedBlocks[col];
        int height = 0;

        for (int i = 0; i < colBlocks.length; i++) {
            if (colBlocks[i]) {
                height = i + 1;
            }
        }
        return height;
    }

    @Override
    public int getAverageColumnHeight(Board board) {

        int totalHeight = 0;

        for (int col = 0; col < Board.WIDTH; col++) {
            int colHeight = getColumnHeight(board, col);
            totalHeight += colHeight;
        }
        return totalHeight / Board.WIDTH;
    }

    @Override
    public int getColumnHeightRange(Board board) {

        int maxHeight = Integer.MIN_VALUE;
        int minHeight = Integer.MAX_VALUE;

        for (int col = 0; col < Board.WIDTH; col++) {
            int colHeight = getColumnHeight(board, col);
            if (colHeight > maxHeight) {
                maxHeight = colHeight;
            }
            if (colHeight < minHeight) {
                minHeight = colHeight;
            }
        }
        return maxHeight - minHeight;
    }

    @Override
    public int getColumnHeightVariance(Board board) {
        int totalVar = 0;
        int varPerCol = 0;

        for (int col = 0; col < Board.WIDTH - 1; col++) {
            int colHeightA = getColumnHeight(board, col);
            int colHeightB = getColumnHeight(board, col + 1);
            varPerCol = Math.abs(colHeightA - colHeightB);
            totalVar += varPerCol;
        }
        return totalVar;
    }

    /**
     * 
     * Private helper for Total Gap Count. .
     * 
     * @param board = getFixedBlocks
     * @param col starts at 0, 10 columns total
     * @return # of vertical gaps in each column
     */
    private int getGapCount(Board board, int col) {
        boolean[][] fixedBlocks = board.getFixedBlocks();
        boolean[] colBlocks = fixedBlocks[col];
        int colGapCount = 0;
        int gapCount = 0;

        for (int iCol = 0; iCol < colBlocks.length; iCol++) {
            int colHeight = getColumnHeight(board, col);
            if (!colBlocks[iCol]) {
                gapCount = gapCount + 1;
            }
            if (iCol == (colHeight - 1)) {
                colGapCount = gapCount;
                break;
            }
        }
        return colGapCount;
    }

    @Override
    public int getTotalGapCount(Board board) {

        int totalGapCount = 0;
        for (int col = 0; col < Board.WIDTH; col++) {
            int gapCount = getGapCount(board, col);
            totalGapCount += gapCount;
        }
        return totalGapCount;
    }

}
