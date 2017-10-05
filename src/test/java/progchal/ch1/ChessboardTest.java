package progchal.ch1;

import org.junit.Test;

import static org.junit.Assert.*;

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
    public void pawnCanOnlyMoveForward() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "..P.....");
        board.setRow(i++, ".k......");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertFalse(board.canPawnCheck(3, 1));
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

        i = 0;
        board = new Chessboard();
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "k.....R.");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertTrue(board.canRookCheck(3, 0));
    }

    @Test
    public void bishopMustBeOnDiagonal() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "...k....");
        board.setRow(i++, "......B.");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertFalse(board.canBishopCheck(0, 3));
    }

    @Test
    public void bishopMustHaveClearPath() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "...k....");
        board.setRow(i++, "........");
        board.setRow(i++, ".....r..");
        board.setRow(i++, "........");
        board.setRow(i++, ".......B");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertFalse(board.canBishopCheck(0, 3));
    }

    @Test
    public void bishopCanCheck() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "...k....");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, ".......B");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertTrue(board.canBishopCheck(0, 3));
    }

    @Test
    public void queenMustBeAlongVertHorizOrDiag() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "...k....");
        board.setRow(i++, "........");
        board.setRow(i++, "..Q.....");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertFalse(board.canBishopCheck(0, 3));
    }

    @Test
    public void queenMustHaveClearPath() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "........");
        board.setRow(i++, ".Q......");
        board.setRow(i++, "........");
        board.setRow(i++, "...r....");
        board.setRow(i++, "....k...");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertFalse(board.canQueenCheck(4, 4));
    }

    @Test
    public void queenCanCheck() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "........");
        board.setRow(i++, ".Q......");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "....k...");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.init();
        assertTrue(board.canQueenCheck(4, 4));
    }

    @Test
    public void scenario1() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "..k.....");
        board.setRow(i++, "ppp.pppp");
        board.setRow(i++, "........");
        board.setRow(i++, ".R...B..");
        board.setRow(i++, "........");
        board.setRow(i++, "........");
        board.setRow(i++, "PPPPPPPP");
        board.setRow(i++, "K.......");
        board.init();
        assertEquals("black king is in check", board.status());
    }

    @Test
    public void scenario2() {
        int i = 0;
        Chessboard board = new Chessboard();
        board.setRow(i++, "rnbqk.nr");
        board.setRow(i++, "ppp..ppp");
        board.setRow(i++, "....p...");
        board.setRow(i++, "...p....");
        board.setRow(i++, ".bPP....");
        board.setRow(i++, ".....N..");
        board.setRow(i++, "PP..PPPP");
        board.setRow(i++, "RNBQKB.R");
        board.init();
        assertEquals("white king is in check", board.status());
    }
}