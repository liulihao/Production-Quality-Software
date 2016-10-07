package edu.nyu.cs.pqs.connectfour;

/**
 * A representation of a connect four player which composed of following
 * information.
 * name: player's name.
 * human: player is human or computer.
 * moves: total moves made by this player.
 * id: player's id.
 */
public class ConnectFourPlayer {

  String name;
  boolean human;
  int moves;
  int id;

  public ConnectFourPlayer(String name, int id, boolean human) {
    this.name = name;
    this.human = human;
    this.id = id;
    this.moves = 0;
  }

  public String getName() {
    return name;
  }

  public boolean isHuman() {
    return human;
  }

  public int getId() {
    return id;
  }

  public int getMoves() {
    return moves;
  }

  public void setMoves(int moves) {
    this.moves = moves;
  }

  @Override
  public String toString() {
    return "ConnectFourPlayer [name: " + name + ", human: " + human + ", moves:"
        + moves + ", id: " + id + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ConnectFourPlayer other = (ConnectFourPlayer) obj;
    if (human != other.human)
      return false;
    if (id != other.id)
      return false;
    if (moves != other.moves)
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (human ? 1231 : 1237);
    result = prime * result + id;
    result = prime * result + moves;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

}
