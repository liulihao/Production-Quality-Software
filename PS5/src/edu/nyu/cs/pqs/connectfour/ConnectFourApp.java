package edu.nyu.cs.pqs.connectfour;

/**
 * Creates a Connect Four game, users can select one player or two players mode.
 * 
 * @author Li-Hao Liu <lhl302@nyu.edu>
 */

public class ConnectFourApp {

  public static void main(String args[]) {
    ConnectFourModel model = new ConnectFourModel();
    new ConnectFourView(model);
  }

}
