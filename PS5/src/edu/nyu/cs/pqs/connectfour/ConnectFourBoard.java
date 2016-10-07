package edu.nyu.cs.pqs.connectfour;

import java.util.Random;

public class ConnectFourBoard {

  private int[][] grid = new int[ConnectFourConstant.ROW][ConnectFourConstant.COL];
  private int[] availableRow = new int[ConnectFourConstant.COL];
  private int lastPlayer;
  private int lastRow;
  private int lastCol;
  private int totalMoves = ConnectFourConstant.ROW * ConnectFourConstant.COL;

  /**
   * Finds the best move for computer, creates a copy of board and try every
   * move.
   * 
   * @param currentPlayer
   * @return bestColumn number of column that is the best move for computer
   */
  public int computerMove(ConnectFourPlayer currentPlayer) {
    int[][] copyOfBoard = new int[ConnectFourConstant.ROW][ConnectFourConstant.COL];
    int bestColumn = 0;
    boolean willWin = false;
    for (int i = 0; i < ConnectFourConstant.COL; ++i) {
      copyOfBoard = copyBoard(grid);
      if (availableRow[i] != ConnectFourConstant.ROW) {
        copyOfBoard[availableRow[i]][i] = currentPlayer.getId();
        if (check(copyOfBoard, currentPlayer)) {
          bestColumn = i;
          willWin = true;
          break;
        }
      }
    }
    if (willWin == false) {
      do {
        Random rand = new Random();
        bestColumn = rand.nextInt(ConnectFourConstant.COL);
      } while (availableRow[bestColumn] == ConnectFourConstant.ROW);
    }
    return bestColumn;
  }

  /**
   * Checks if the board has winning line.
   * 
   * @param board
   * @param currentPlayer
   * @return true if it has a winning line.
   */
  public boolean check(int[][] board, ConnectFourPlayer currentPlayer) {
    for (int r = 0; r < ConnectFourConstant.ROW; ++r) {
      for (int c = 0; c < ConnectFourConstant.COL; ++c) {
        if (checkHorizon(board, currentPlayer, r, c)
            || checkVertical(board, currentPlayer, r, c)
            || checkDiagonal(board, currentPlayer, r, c)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if there is a horizontal line that contains 4 blocks in same color.
   * 
   * @param board
   * @param currentPlayer
   * @param row
   * @param col
   * @return true if it has a winning horizontal line.
   */
  private static boolean checkHorizon(int[][] board,
      ConnectFourPlayer currentPlayer,
      int row, int col) {
    if (board[row][col] != currentPlayer.id) {
      return false;
    }
    int cLeft = col - 3;
    int cRight = col + 3;
    boolean left = false;
    boolean right = false;
    if (cLeft >= 0) {
      left = true;
      for (int i = cLeft; i < col; ++i) {
        if (board[row][i] != currentPlayer.id) {
          left = false;
          break;
        }
      }
    }
    if (cRight <= 6) {
      right = true;
      for (int i = col; i <= cRight; ++i) {
        if (board[row][i] != currentPlayer.id) {
          right = false;
        }
      }
    }
    if (left == true || right == true) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if there is a vertical line that contains 4 blocks in same color.
   * 
   * @param board
   * @param currentPlayer
   * @param row
   * @param col
   * @return true if it has a winning vertical line.
   */
  private static boolean checkVertical(int[][] board, ConnectFourPlayer turn,
      int row, int col) {
    if (board[row][col] != turn.id) {
      return false;
    }
    int rUp = row - 3;
    int rDown = row + 3;
    boolean up = false;
    boolean down = false;
    if (rUp >= 0) {
      up = true;
      for (int i = rUp; i < row; i++) {
        if (board[i][col] != turn.id) {
          up = false;
        }
      }
    }
    if (rDown <= 5) {
      down = true;
      for (int i = row; i <= rDown; i++) {
        if (board[i][col] != turn.id) {
          down = false;
        }
      }
    }
    if (up || down) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if there is a diagonal line that contains 4 blocks in same color.
   * Need to check different directions
   * 
   * @param board
   * @param currentPlayer
   * @param row
   * @param col
   * @return true if it has a winning diagonal line.
   */
  private static boolean checkDiagonal(int[][] board, ConnectFourPlayer turn,
      int row, int col) {
    if (board[row][col] != turn.id) {
      return false;
    }
    int rUpLeft = row - 3;
    int cUpLeft = col - 3;
    boolean upLeft = false;
    if (rUpLeft >= 0 && cUpLeft >= 0) {
      upLeft = true;
      for (int i = 0; i < 4; i++) {
        if (board[rUpLeft + i][cUpLeft + i] != turn.id) {
          upLeft = false;
        }
      }
    }
    int rDownLeft = row + 3;
    int cDownLeft = col - 3;
    boolean downLeft = false;
    if (rDownLeft <= 5 && cDownLeft >= 0) {
      downLeft = true;
      int r = rDownLeft;
      int c = cDownLeft;
      while (c < col) {
        if (board[r--][c++] != turn.id) {
          downLeft = false;
        }
      }
    }
    int rUpRight = row - 3;
    int cUpRight = col + 3;
    boolean upRight = false;
    if (rUpRight >= 0 && cUpRight <= 6) {
      upRight = true;
      int r = row;
      int c = col;
      while (c <= cUpRight) {
        if (board[r--][c++] != turn.id) {
          upRight = false;
        }
      }
    }
    int rDownRight = row + 3;
    int cDownRight = col + 3;
    boolean downRight = false;
    if (rDownRight <= 5 && cDownRight <= 6) {
      downRight = true;
      int r = row;
      int c = col;
      while (c <= cDownRight) {
        if (board[r++][c++] != turn.id) {
          downRight = false;
        }
      }
    }
    if (upLeft || downLeft || upRight || downRight) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Clones board and returns that array
   * 
   * @return array array that records all the moves in game board.
   */
  public int[][] getGrid() {
    return grid.clone();
  }

  /**
   * Gets the column number where the last move was made.
   * 
   * @return lastCol last column number.
   */
  public int getLastCol() {
    return lastCol;
  }

  /**
   * Gets the ID of the player who made the last move.
   * 
   * @return lastPlayer ID of last player.
   */
  public int getLastPlayer() {
    return lastPlayer;
  }

  /**
   * Gets the row number where the last move was made.
   * 
   * @return lastRow last row number.
   */
  public int getLastRow() {
    return lastRow;
  }

  /**
   * Gets total moves in the game.
   * 
   * @return totalMoves total number of moves that made in this game.
   */
  public int getTotalMoves() {
    return totalMoves;
  }

  /**
   * Resets board to the default value.
   */
  public void resetBoard() {
    for (int r = 0; r < ConnectFourConstant.ROW; r++) {
      for (int c = 0; c < ConnectFourConstant.COL; c++) {
        grid[r][c] = -1;
      }
    }
    availableRow = new int[ConnectFourConstant.COL];
    totalMoves = ConnectFourConstant.ROW * ConnectFourConstant.COL;
  }

  /**
   * Set the availableRow shows the available row in every column.
   * 
   * @param availableRow
   */
  public void setAvailableRow(int[] availableRow) {
    this.availableRow = availableRow;
  }

  public int[] getAvailableRow() {
    return availableRow.clone();
  }

  /**
   * Sets grid with the game board.
   * 
   * @param board
   */
  public void setBoard(int[][] board) {
    this.grid = board;
  }

  /**
   * Sets the column in which the last move was made.
   * 
   * @param lastCol
   */
  public void setLastCol(int lastCol) {
    this.lastCol = lastCol;
  }

  /**
   * Sets the player who made the last move.
   * 
   * @param lastPlayer
   */
  public void setLastPlayer(int lastPlayer) {
    this.lastPlayer = lastPlayer;
  }

  /**
   * Sets the row in which the last move was made.
   * 
   * @param lastRow
   */
  public void setLastRow(int lastRow) {
    this.lastRow = lastRow;
  }

  /**
   * Setter for the total moves in this game.
   * 
   * @param totalMoves
   */
  public void setTotalMoves(int totalMoves) {
    this.totalMoves = totalMoves;
  }

  /**
   * Checks if this column is still available for player, if yes, update grid
   * and total moves then return row number.
   * 
   * @param col
   * @param playerId
   * @return row next available row number in the column.
   */
  public int checkRowbyColumn(int col, int playerId) {
    int row = availableRow[col];
    if (row == ConnectFourConstant.ROW) {
      return -1;
    }
    grid[row][col] = playerId;
    availableRow[col]++;
    totalMoves--;
    return row;
  }

  /**
   * Copies grid to a temporary array.
   * 
   * @param tempBoard
   */
  private int[][] copyBoard(int[][] tempBoard) {
    int[][] copyOfBoard = new int[ConnectFourConstant.ROW][ConnectFourConstant.COL];
    for (int r = 0; r < ConnectFourConstant.ROW; r++) {
      for (int c = 0; c < ConnectFourConstant.COL; ++c) {
        copyOfBoard[r][c] = tempBoard[r][c];
      }
    }
    return copyOfBoard;
  }

}
