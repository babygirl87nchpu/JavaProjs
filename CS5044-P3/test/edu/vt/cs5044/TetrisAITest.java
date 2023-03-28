package edu.vt.cs5044;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.vt.cs5044.tetris.AI;
import edu.vt.cs5044.tetris.Board;
import edu.vt.cs5044.tetris.Placement;
import edu.vt.cs5044.tetris.Rotation;
import edu.vt.cs5044.tetris.Shape;

/**
 * 
 * JUnit test class for the TetrisAI
 *
 * @author SamIAm
 * @version Mar 3, 2023
 *
 */
public class TetrisAITest {
    AI smartGamer;
    Board emptyBoard;
    Board oneBlockBoard;
    Board apiBoardACH;
    Board testBoardI;
    Board testBoardJ;
    Board tetrisBoardH;
    Board testBoardL;
    Board testBoardO;
    Board testBoardS;
    Board testBoardT;
    Board testBoardZ;
    Board tetrisBoard1;
    Board tetrisBoard2;
    Board tetrisBoard3;
    Board tetrisBoard4;
    Board tetrisBoard5;

    /**
     * Create default instance of TetrisAI.
     *
     */
    @Before
    public void setUp() {
        smartGamer = new TetrisAI();
        oneBlockBoard = new Board(
            "     #    ");
        // expect: ACH=0; CHR=1; CHV=2; TGC=0

        apiBoardACH = new Board(
            "## ##    #",
            "# ##### ##",
            "#### #####",
            "# ##### ##",
            "## #######",
            "######### ",
            " #########",
            " #########",
            "###  #####",
            "####### ##",
            "######## #",
            " #### ####");
        // expect: ACH=11; CHR=2; CHV=6; TGC=14

        testBoardI = new Board(
            "### ######",
            "### ######",
            "### ######",
            "### ######",
            "##########"); // expect: ACH=4; CHR=4; CHV=8; TGC=14
                           // Best placement for "I" Shape = Rotation: NONE, Col: 3

        testBoardJ = new Board(
            "#####   ##",
            "####### ##",
            "##########"); // expect: ACH=2; CHR=2; CHV=4; TGC=14
                           // Best placement for "J" Shape = Rotation: CCW_90, Col: 5

        testBoardL = new Board(
            "###   ####",
            "### ######",
            "##########"); // expect: ACH=11; CHR=2; CHV=6; TGC=14
                           // Best placement for "L" Shape = Rotation: CCW_270, Col: 3

        testBoardO = new Board(
            "##  ######",
            "##  ######",
            "##########"); // expect: ACH= CHR=2; CHV=; TGC=
                           // Best placement for "O" Shape = Rotation: NONE, Col: 2

        testBoardS = new Board(
            "##  ######",
            "##  ######",
            "### ######",
            "##########"); // expect: ACH=3; CHR=3; CHV=6; TGC=14
                           // Best placement for "S" Shape = Rotation: CCW_90, Col: 2

        testBoardT = new Board(
            "#####   ##",
            "###### ###",
            "##########");
        // expect: ACH=2; CHR=2; CHV=4; TGC=
        // Best placement for "T" Shape = Rotation: CCW_180, Col: 5

        testBoardZ = new Board(
            "####   ###",
            "#####  ###",
            "##########");
        // expect: ACH=11; CHR=2; CHV=6; TGC=14

        tetrisBoard1 = new Board(
            "         #",
            "        ##",
            "     #####",
            "      # ##",
            "     #####",
            "     #### ",
            "     #  ##",
            " ##### ###",
            "##### ####");
        // expect: ACH=4; CHR=8; CHV=8; TGC=7

        tetrisBoard2 = new Board(
            "    ##    ",
            "   ####   ",
            "     #### ",
            "   #### ##",
            "   #######",
            " #### ####",
            "   ###### ",
            " #### ### ",
            " # #######");
        // expect: ACH=6; CHR=9; CHV=12; TGC=10

        tetrisBoard3 = new Board(
            "##        ",
            " ##       ",
            " ###      ",
            " ###      ",
            "  ##      ",
            " ####     ",
            "  ########",
            " #########",
            " #########",
            " ## ######");
        // expect: ACH=6; CHR=6; CHV=6; TGC=12

        tetrisBoard4 = new Board(
            " ###      ",
            "## ##    #",
            "# ####   #",
            "# ## #   #",
            "# #####  #",
            "# ## ##  #",
            "#### #####",
            "# ##### ##",
            "### ######");
        // expect: ACH=6; CHR=6; CHV=12; TGC=11

        tetrisBoard5 = new Board(
            "##        ",
            " ##       ",
            " ###      ",
            " ###      ",
            "  ##      ",
            " ####     ",
            "  ########",
            " #########",
            " #########",
            " ## ######");
        // expect: ACH=6; CHR=6; CHV=6; TGC=12
    }

    /**
     * 
     * Test Average Column Height for each board
     *
     */
    @Test
    public void testAverageColumnHeight() {

        assertEquals(0, smartGamer.getAverageColumnHeight(oneBlockBoard));
        assertEquals(11, smartGamer.getAverageColumnHeight(apiBoardACH));

        assertEquals(4, smartGamer.getAverageColumnHeight(tetrisBoard1));
        assertEquals(6, smartGamer.getAverageColumnHeight(tetrisBoard2));
        assertEquals(6, smartGamer.getAverageColumnHeight(tetrisBoard3));
        assertEquals(6, smartGamer.getAverageColumnHeight(tetrisBoard4));
        assertEquals(6, smartGamer.getAverageColumnHeight(tetrisBoard5));

        assertEquals(2, smartGamer.getAverageColumnHeight(testBoardT));
        assertEquals(2, smartGamer.getAverageColumnHeight(testBoardO));
        assertEquals(4, smartGamer.getAverageColumnHeight(testBoardI));
        assertEquals(2, smartGamer.getAverageColumnHeight(testBoardJ));
        assertEquals(2, smartGamer.getAverageColumnHeight(testBoardL));
        assertEquals(3, smartGamer.getAverageColumnHeight(testBoardS));
        assertEquals(2, smartGamer.getAverageColumnHeight(testBoardZ));
    }

    /**
     * 
     * test findBestPlacement 
     *
     */
    @Test
    public void testfindBestPlacement() {

        Placement expectedPlace = new Placement(Rotation.CCW_90, 5);
        assertEquals(expectedPlace, smartGamer.findBestPlacement(apiBoardACH, Shape.I));

    }

    /**
     * 
     * Test Column Height for each board
     *
     */
    @Test
    public void testColumnHeightRange() {

        assertEquals(1, smartGamer.getColumnHeightRange(oneBlockBoard));
        assertEquals(2, smartGamer.getColumnHeightRange(apiBoardACH));

        assertEquals(8, smartGamer.getColumnHeightRange(tetrisBoard1));
        assertEquals(9, smartGamer.getColumnHeightRange(tetrisBoard2));
        assertEquals(6, smartGamer.getColumnHeightRange(tetrisBoard3));
        assertEquals(6, smartGamer.getColumnHeightRange(tetrisBoard4));
        assertEquals(6, smartGamer.getColumnHeightRange(tetrisBoard5));

        assertEquals(2, smartGamer.getColumnHeightRange(testBoardT));
        assertEquals(2, smartGamer.getColumnHeightRange(testBoardO));
        assertEquals(4, smartGamer.getColumnHeightRange(testBoardI));
        assertEquals(2, smartGamer.getColumnHeightRange(testBoardJ));
        assertEquals(2, smartGamer.getColumnHeightRange(testBoardL));
        assertEquals(3, smartGamer.getColumnHeightRange(testBoardS));
        assertEquals(2, smartGamer.getColumnHeightRange(testBoardZ));

    }

    /**
     * 
     * Test Column Height Variance for each board
     *
     */
    @Test
    public void testColumnHeightVariance() {

        assertEquals(2, smartGamer.getColumnHeightVariance(oneBlockBoard));
        assertEquals(6, smartGamer.getColumnHeightVariance(apiBoardACH));

        assertEquals(8, smartGamer.getColumnHeightVariance(tetrisBoard1));
        assertEquals(12, smartGamer.getColumnHeightVariance(tetrisBoard2));
        assertEquals(6, smartGamer.getColumnHeightVariance(tetrisBoard3));
        assertEquals(12, smartGamer.getColumnHeightVariance(tetrisBoard4));
        assertEquals(6, smartGamer.getColumnHeightVariance(tetrisBoard5));

        assertEquals(4, smartGamer.getColumnHeightVariance(testBoardT));
        assertEquals(4, smartGamer.getColumnHeightVariance(testBoardO));
        assertEquals(8, smartGamer.getColumnHeightVariance(testBoardI));
        assertEquals(4, smartGamer.getColumnHeightVariance(testBoardJ));
        assertEquals(4, smartGamer.getColumnHeightVariance(testBoardL));
        assertEquals(6, smartGamer.getColumnHeightVariance(testBoardS));
        assertEquals(4, smartGamer.getColumnHeightVariance(testBoardZ));

    }

    /**
     * 
     * Test total gap count with in a rowColumn Heighth for each board
     *
     */
    @Test
    public void testTotalGapCount() {

        assertEquals(0, smartGamer.getTotalGapCount(oneBlockBoard));
        assertEquals(14, smartGamer.getTotalGapCount(apiBoardACH));

        assertEquals(7, smartGamer.getTotalGapCount(tetrisBoard1));
        assertEquals(10, smartGamer.getTotalGapCount(tetrisBoard2));
        assertEquals(12, smartGamer.getTotalGapCount(tetrisBoard3));
        assertEquals(11, smartGamer.getTotalGapCount(tetrisBoard4));
        assertEquals(12, smartGamer.getTotalGapCount(tetrisBoard5));

        assertEquals(0, smartGamer.getTotalGapCount(testBoardT));
        assertEquals(0, smartGamer.getTotalGapCount(testBoardO));
        assertEquals(0, smartGamer.getTotalGapCount(testBoardI));
        assertEquals(0, smartGamer.getTotalGapCount(testBoardJ));
        assertEquals(0, smartGamer.getTotalGapCount(testBoardL));
        assertEquals(0, smartGamer.getTotalGapCount(testBoardS));
        assertEquals(0, smartGamer.getTotalGapCount(testBoardZ));

    }

}
