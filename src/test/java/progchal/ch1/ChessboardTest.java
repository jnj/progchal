package progchal.ch1;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChessboardTest {

    @Test
    public void pawnCannotMoveForward() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "k.......");
        board.setRow(i++, "P.......");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertFalse(board.canPawnCheck(0, 0));
    }

    @Test
    public void pawnCanMoveDiagonallyForward() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "k.......");
        board.setRow(i++, ".P......");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertTrue(board.canPawnCheck(0, 0));

        i = 0;
        board = new Chessboard();
        board.setRow(i++, "k.......");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "...p....");
        board.setRow(i++, "..K.....");
        board.init();
        assertTrue(board.canPawnCheck(7, 2));
    }

    @Test
    public void pawnMustBeOppositeColor() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "k.......");
        board.setRow(i++, ".p......");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertFalse(board.canPawnCheck(0, 0));
    }

    @Test
    public void rookMustBeInSameRowOrColumn() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "k.......");
        board.setRow(i++, ".R......");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertFalse(board.canRookCheck(0, 0));
    }

    @Test
    public void rookMustHaveClearPath() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "...k....");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "...n....");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "...R....");
        board.setRow(i++, "........");
        board.init();
        assertFalse(board.canRookCheck(0, 0));
    }

    @Test
    public void rookCanCheck() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "...k....");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "...R....");
        board.setRow(i++, "........");
        board.init();
        assertTrue(board.canRookCheck(0, 3));
    }

}