package edu.nyu.cs.pqs.connectfour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectFourModel {
  private ConnectFourPlayer firstPlayer;
  private ConnectFourPlayer secondPlayer;
  private ConnectFourPlayer currentPlayer;
  private ConnectFourBoard board = new ConnectFourBoard();
  private List<ConnectFourListener> listeners = new ArrayList<ConnectFourListener>();

  /**
   * Adds Connect Four listener into listeners list.
   * 
   * @param listener Implementations of the ConnectFourListener interface.
   */
  public void addConnectFourListener(ConnectFourListener listener) {
    listeners.add(listener);
  }

  /**
   * Removes Connect Four listener from listeners list.
   * 
   * @param listener Implementations of the ConnectFourListener interface.
   */
  public void removeConnectFourListener(ConnectFourListener listener) {
    listeners.remove(listener);
  }

  /**
   * Displays message to players.
   * 
   * @param message message shows to players.
   */
  private void fireGameMessageEvent(String message) {
    for (ConnectFourListener listener : listeners) {
      listener.gameMessage(message);
    }
  }

  /**
   * Displays winning message to player.
   * 
   * @param message show when a player wins.
   */
  private void fireGameWonEvent(String message) {
    for (ConnectFourListener listener : listeners) {
      listener.gameWinner(message);
    }
  }

  /**
   * Displays game ended message to players.
   * 
   * @param message show when game is tied.
   */
  private void fireGameEndedEvent() {
    for (ConnectFourListener listener : listeners) {
      listener.gameEnded();
    }
  }

  /**
   * Gets the instance of the ConnectFourBoard class.
   * 
   * @return board an object of ConnectFourBoard.
   */
  public ConnectFourBoard getBoard() {
    return board;
  }

  /**
   * Gets the instance of ConnectFourPlayer class.
   * 
   * @return currentPlayer an object represents the current player in the game.
   */
  public ConnectFourPlayer getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * Gets an instance of ConnectFourPlayer class.
   * 
   * @return firstPlayer an object represents the first player in the game.
   */
  public ConnectFourPlayer getFirstPlayer() {
    return firstPlayer;
  }

  /**
   * Gets an instance of ConnectFourPlayer class.
   * 
   * @return secondPlayer an object represents the second player in the game.
   */
  public ConnectFourPlayer getSecondPlayer() {
    return secondPlayer;
  }

  /**
   * Gets the list of available listeners.
   * 
   * @return a list of ConnectFourListener.
   */
  public List<ConnectFourListener> getListeners() {
    return Collections.unmodifiableList(listeners);
  }

  /**
   * Updates the board and alerts player if the column is already full.
   * Also updates the current player to the first player and second player.
   * 
   * @param col column number which the player has clicked.
   */
  private void makeMove(int col) {
    int row = board.checkRowbyColumn(col, currentPlayer.getId());
    if (row == -1) {
      fireGameMessageEvent("This column is full. Please select another column");
    } else {
      fireGameUpdateEvent(currentPlayer.getId(), row, col);
      board.setLastPlayer(currentPlayer.getId());
      currentPlayer.setMoves(currentPlayer.getMoves() + 1);
      board.setLastRow(row);
      board.setLastCol(col);
      if (board.check(board.getGrid(), currentPlayer)) {
        fireGameWonEvent(currentPlayer.getName() + " won!");
      } else if (board.getTotalMoves() == 0) {
        fireGameEndedEvent();
      }
      if (currentPlayer.equals(firstPlayer)) {
        currentPlayer = secondPlayer;
      } else {
        currentPlayer = firstPlayer;
      }
    }
  }

  /**
   * Calls makeMove and define it's computer's turn or player two's turn.
   * 
   * @param col column number which the player has clicked.
   */
  public void play(int col) {
    makeMove(col);
    if (!currentPlayer.isHuman()) {
      playComputersMove();
    }
  }

  /**
   * Selects the best column for computer.
   */
  private void playComputersMove() {
    board.setLastPlayer(currentPlayer.getId());
    makeMove(board.computerMove(currentPlayer));
  }

  /**
   * Resets the game board and restore the view to the default state.
   */
  public void resetGame() {
    board.resetBoard();
  }

  public void setBoard(ConnectFourBoard board) {
    this.board = board;
  }

  public void setCurrentPlayer(ConnectFourPlayer currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public void setFirstPlayer(ConnectFourPlayer firstPlayer) {
    this.firstPlayer = firstPlayer;
  }

  public void setSecondPlayer(ConnectFourPlayer secondPlayer) {
    this.secondPlayer = secondPlayer;
  }

  public void setListeners(List<ConnectFourListener> listeners) {
    this.listeners = listeners;
  }

  /**
   * Starts the game, sets current player as first player.
   */
  public void startGame() {
    board.resetBoard();
    this.fireGameStartEvent();
    currentPlayer = firstPlayer;
  }

  /**
   * Executes the startGameEvent method to all listeners in the listeners list.
   */
  private void fireGameStartEvent() {
    for (ConnectFourListener listener : listeners) {
      listener.gameStart();
    }
  }

  /**
   * Executes the makeMove method to all listeners in the listeners list.
   */
  private void fireGameUpdateEvent(int playerId, int row, int col) {
    for (ConnectFourListener listener : listeners) {
      listener.makeMove(playerId, row, col);
    }
  }
}