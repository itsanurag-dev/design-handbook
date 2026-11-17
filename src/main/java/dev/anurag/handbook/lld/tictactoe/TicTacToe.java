package dev.anurag.handbook.lld.tictactoe;

/**
 * Implements a TicTacToe game board.
 */
public class TicTacToe {
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;

    /**
     * Initializes the TicTacToe board.
     * 
     * @param n the size of the board
     */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    /**
     * Player makes a move at (row, col).
     * 
     * @param row    the row of the board
     * @param col    the column of the board
     * @param player the player, can be either 1 or 2
     * @return the current winning condition: 0 (no one wins), 1 (player 1 wins), 2
     *         (player 2 wins)
     */
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;
        rows[row] += toAdd;
        cols[col] += toAdd;
        if (row == col) {
            diagonal += toAdd;
        }
        if (col == (cols.length - row - 1)) {
            antiDiagonal += toAdd;
        }
        int size = rows.length;
        if (Math.abs(rows[row]) == size || Math.abs(cols[col]) == size || Math.abs(diagonal) == size
                || Math.abs(antiDiagonal) == size) {
            return player;
        }
        return 0;
    }
}
