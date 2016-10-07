package edu.nyu.cs.test;

import edu.nyu.cs.pqs.connectfour.ConnectFourBoard;
import edu.nyu.cs.pqs.connectfour.ConnectFourConstant;
import edu.nyu.cs.pqs.connectfour.ConnectFourModel;
import edu.nyu.cs.pqs.connectfour.ConnectFourPlayer;
import edu.nyu.cs.pqs.connectfour.ConnectFourView;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConnectFourTest {

  ConnectFourModel testModel;
  ConnectFourView testView;
  ConnectFourBoard testBoard;
  ConnectFourPlayer testPlayer;
  ConnectFourPlayer testComputerPlayer;
  int[][] testBoardArr;
  int[] actualAvailableRows;

  @Before
  public void setUp() {
    testModel = new ConnectFourModel();
    testBoard = new ConnectFourBoard();
    testModel.setBoard(testBoard);
    testPlayer = new ConnectFourPlayer("TestHumanPlayer",
        ConnectFourConstant.PLAYER1, true);
    testComputerPlayer = new ConnectFourPlayer("TestComputerPlayer",
        ConnectFourConstant.PLAYER2, false);
    testModel.setFirstPlayer(testPlayer);
    testModel.setSecondPlayer(testComputerPlayer);
    actualAvailableRows = new int[ConnectFourConstant.COL];
    testBoardArr = new int[ConnectFourConstant.ROW][ConnectFourConstant.COL];
    for (int r = 0; r < ConnectFourConstant.ROW; r++) {
      for (int c = 0; c < ConnectFourConstant.COL; c++) {
        testBoardArr[r][c] = -1;
      }
    }

  }

  @After
  public void cleanUp() {
    testView = null;
  }

  @Test
  public void testAddListener() {
    testView = new ConnectFourView(testModel);
    assertTrue("Listener is not added",
        testModel.getListeners().contains(testView));
  }

  @Test
  public void testBoardButtonsActionListener() {
    testPlayer = new ConnectFourPlayer("TestHumanPlayer",
        ConnectFourConstant.PLAYER1, true);
    testView = new ConnectFourView(testModel);
    JButton[][] testButtons = testView.getBoardButtons();
    JButton starttestButton = testView.getStartButton();
    starttestButton.doClick();
    testButtons[0][1].doClick();
    testButtons[0][2].doClick();
    assertTrue(
        testButtons[0][1].getBackground() == ConnectFourConstant.COLOR_PLAYER1);
    assertTrue(
        testButtons[0][2].getBackground() == ConnectFourConstant.COLOR_PLAYER2);
  }

  @Test
  public void testPlayerActionListener() {
    testPlayer = new ConnectFourPlayer("Player",
        ConnectFourConstant.PLAYER1, true);
    testView = new ConnectFourView(testModel);
    JButton starttestButton = testView.getStartButton();
    JRadioButton onePlayerButton = testView.getOnePlayer();
    onePlayerButton.doClick();
    starttestButton.doClick();
    assertTrue(testView.getSecondPlayer().getName() == "Computer");
  }

  @Test
  public void testGameStart() {
    testView = new ConnectFourView(testModel);
    testView.gameStart();
    JButton[][] testButtons = testView.getBoardButtons();
    assertTrue(testButtons[0][0].getText() == "");
    assertTrue(testButtons[5][6].getText() == "");
    assertTrue(testButtons[5][6].getText() == "");
    assertTrue(testButtons[5][6].getText() == "");
  }

  @Test
  public void testStartButtonActionListener() {
    testView = new ConnectFourView(testModel);
    JButton starttestButton = testView.getStartButton();
    assertEquals("Button Text Wrong", "Start", starttestButton.getText());
    testView.gameStart();
    assertEquals("Button Text Wrong", "Restart", starttestButton.getText());
    starttestButton.doClick();
    assertEquals("Button Text Wrong", "Start", starttestButton.getText());
  }

  @Test
  public void testPlay() {
    testModel.setCurrentPlayer(testPlayer);
    testModel.play(3);
    assertEquals("Number of moves of player is updated correctly", 1,
        testPlayer.getMoves());
    assertEquals("Integer board is not updated correctly",
        ConnectFourConstant.PLAYER1, testModel.getBoard()
            .getGrid()[0][3]);
  }

  @Test
  public void testResetGame() {
    testModel.resetGame();
    assertEquals("Total moves not reset correctly",
        testModel.getBoard().getTotalMoves(),
        ConnectFourConstant.ROW * ConnectFourConstant.COL);
    assertArrayEquals("Available rows array is not reset", actualAvailableRows,
        testModel
            .getBoard().getAvailableRow());
    int[][] actualBoard = testModel.getBoard().getGrid();
    assertArrayEquals("Row 0 of board is not reset correctly", testBoardArr[0],
        actualBoard[0]);
    assertArrayEquals("Row 1 of board is not reset correctly", testBoardArr[1],
        actualBoard[1]);
    assertArrayEquals("Row 2 of board is not reset correctly", testBoardArr[2],
        actualBoard[2]);
    assertArrayEquals("Row 3 of board is not reset correctly", testBoardArr[3],
        actualBoard[3]);
    assertArrayEquals("Row 4 of board is not reset correctly", testBoardArr[4],
        actualBoard[4]);
    assertArrayEquals("Row 5 of board is not reset correctly", testBoardArr[5],
        actualBoard[5]);
  }

  @Test
  public void testStartGame() {
    testModel.startGame();

    assertEquals("Current player is not set correctly",
        testModel.getFirstPlayer(), testModel
            .getCurrentPlayer());
    assertEquals("Total moves not reset correctly",
        testModel.getBoard().getTotalMoves(),
        ConnectFourConstant.ROW * ConnectFourConstant.COL);
    assertArrayEquals("Available rows array is not reset", actualAvailableRows,
        testModel
            .getBoard().getAvailableRow());
  }

}
