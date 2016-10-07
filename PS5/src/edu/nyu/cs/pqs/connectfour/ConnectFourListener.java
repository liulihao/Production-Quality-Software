package edu.nyu.cs.pqs.connectfour;

public interface ConnectFourListener {
  void gameStart();

  void makeMove(int playerId, int r, int c);

  void gameMessage(String message);

  void gameWinner(String message);

  void gameEnded();

  void gameReset();
}