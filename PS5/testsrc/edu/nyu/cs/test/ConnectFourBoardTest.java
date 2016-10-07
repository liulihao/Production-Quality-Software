package edu.nyu.cs.test;

import edu.nyu.cs.pqs.connectfour.ConnectFourBoard;
import edu.nyu.cs.pqs.connectfour.ConnectFourConstant;
import edu.nyu.cs.pqs.connectfour.ConnectFourPlayer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConnectFourBoardTest {

  int[][] testBoardArray;
  int[] testAvailableRowArray;
  ConnectFourBoard testBoard;

  @Before
  public void setup() {
    testBoardArray = new int[ConnectFourConstant.ROW][ConnectFourConstant.COL];
    testAvailableRowArray = new int[ConnectFourConstant.COL];
    for (int r = 0; r < ConnectFourConstant.ROW; r++) {
      for (int c = 0; c < ConnectFourConstant.COL; c++) {
        testBoardArray[r][c] = -1;
      }
    }
    testAvailableRowArray = new int[ConnectFourConstant.COL];
    testBoard = new ConnectFourBoard();
    testBoard.setLastPlayer(ConnectFourConstant.PLAYER2);
  }

  @Test
  public void testCreatePlayer() {
    ConnectFourPlayer testPlayer = new ConnectFourPlayer("Player 1", 1, true);
    ConnectFourPlayer playerone = new ConnectFourPlayer("Player 1", 1, true);
    ConnectFourPlayer playertwo = new ConnectFourPlayer("Player 2", 2, true);
    assertEquals("Wrong name is returned", "Player 1", playerone.getName());
    assertEquals("Wrong id is returned", 1, playerone.getId());
    assertEquals("Wrong human is returned", true, playerone.isHuman());
    assertEquals("Wrong moves is returned", 0, playerone.getMoves());
    assertTrue(playerone.toString().equals(testPlayer.toString()));
    assertTrue(playerone.hashCode() == testPlayer.hashCode());
    assertFalse(playerone.equals(playertwo));
  }

  @Test
  public void testFindBestColForComputer_Vertical() {
    ConnectFourPlayer testPlayer = new ConnectFourPlayer("Player 2", 2, true);
    testAvailableRowArray[3] = 3;
    testBoard.setAvailableRow(testAvailableRowArray.clone());
    int expectedResult = 3;
    testBoardArray[0][expectedResult] = ConnectFourConstant.PLAYER2;
    testBoardArray[1][expectedResult] = ConnectFourConstant.PLAYER2;
    testBoardArray[2][expectedResult] = ConnectFourConstant.PLAYER2;
    testBoard.setBoard(testBoardArray.clone());
    assertEquals("Wrong column is returned", expectedResult,
        testBoard.computerMove(testPlayer));
  }

  @Test
  public void testFindBestColForComputer_RightDiagonal() {
    ConnectFourPlayer testPlayer = new ConnectFourPlayer("Player 2", 2, true);
    testAvailableRowArray[0] = 1;
    testAvailableRowArray[1] = 2;
    testAvailableRowArray[2] = 3;
    testAvailableRowArray[3] = 3;
    testBoard.setAvailableRow(testAvailableRowArray.clone());
    testBoardArray[0][0] = ConnectFourConstant.PLAYER2;
    testBoardArray[1][1] = ConnectFourConstant.PLAYER2;
    testBoardArray[2][2] = ConnectFourConstant.PLAYER2;
    testBoard.setBoard(testBoardArray.clone());
    assertEquals("Wrong column is returned", 3,
        testBoard.computerMove(testPlayer));
  }

  @Test
  public void testFindBestColForComputer_LeftDiagonal() {
    ConnectFourPlayer testPlayer = new ConnectFourPlayer("Player 2", 2, true);
    testAvailableRowArray[5] = 1;
    testAvailableRowArray[4] = 2;
    testAvailableRowArray[3] = 3;
    testAvailableRowArray[2] = 3;
    testBoard.setAvailableRow(testAvailableRowArray.clone());
    testBoardArray[0][5] = ConnectFourConstant.PLAYER2;
    testBoardArray[1][4] = ConnectFourConstant.PLAYER2;
    testBoardArray[2][3] = ConnectFourConstant.PLAYER2;
    testBoard.setBoard(testBoardArray.clone());
    assertEquals("Wrong column is returned", 2,
        testBoard.computerMove(testPlayer));
  }

  @Test
  public void testFindBestColForComputer_Horizontal() {
    ConnectFourPlayer testPlayer = new ConnectFourPlayer("Player 2", 2, true);
    testAvailableRowArray[0] = 1;
    testAvailableRowArray[1] = 1;
    testAvailableRowArray[2] = 1;
    testAvailableRowArray[3] = 0;
    testBoard.setAvailableRow(testAvailableRowArray.clone());
    testBoardArray[0][0] = ConnectFourConstant.PLAYER2;
    testBoardArray[0][1] = ConnectFourConstant.PLAYER2;
    testBoardArray[0][2] = ConnectFourConstant.PLAYER2;
    testBoard.setBoard(testBoardArray.clone());
    assertEquals("Wrong column is returned", 3,
        testBoard.computerMove(testPlayer));
  }

  @Test
  public void testFindWinner_Vertical() {
    ConnectFourPlayer testPlayer = new ConnectFourPlayer("Player 2", 2, true);
    int testCol = 2;
    testAvailableRowArray[3] = testCol;
    testBoard.setAvailableRow(testAvailableRowArray.clone());
    testBoardArray[0][testCol] = ConnectFourConstant.PLAYER2;
    testBoardArray[1][testCol] = ConnectFourConstant.PLAYER2;
    testBoardArray[2][testCol] = ConnectFourConstant.PLAYER2;
    testBoardArray[3][testCol] = ConnectFourConstant.PLAYER2;
    testBoard.setBoard(testBoardArray);
    testBoard.setLastCol(testCol);
    testBoard.setLastRow(3);
    assertTrue("Wrong value for winner",
        testBoard.check(testBoardArray, testPlayer));
  }

  @Test
  public void testFindWinner_Horizontal() {
    ConnectFourPlayer testPlayer = new ConnectFourPlayer("Player 2", 2, true);
    int testRow = 2;
    int testCol = 3;
    testAvailableRowArray[testCol] = testRow;
    testBoard.setAvailableRow(testAvailableRowArray.clone());
    testBoardArray[testRow][0] = ConnectFourConstant.PLAYER2;
    testBoardArray[testRow][1] = ConnectFourConstant.PLAYER2;
    testBoardArray[testRow][2] = ConnectFourConstant.PLAYER2;
    testBoardArray[testRow][3] = ConnectFourConstant.PLAYER2;
    testBoard.setBoard(testBoardArray);
    testBoard.setLastCol(testCol);
    testBoard.setLastRow(testRow);
    assertTrue("Wrong value for winner",
        testBoard.check(testBoardArray, testPlayer));
  }

  @Test
  public void testFindWinner_RightDiagonal() {
    ConnectFourPlayer testPlayer = new ConnectFourPlayer("Player 2", 2, true);
    testAvailableRowArray[0] = 0;
    testBoard.setAvailableRow(testAvailableRowArray.clone());
    testBoardArray[0][0] = ConnectFourConstant.PLAYER2;
    testBoardArray[1][1] = ConnectFourConstant.PLAYER2;
    testBoardArray[2][2] = ConnectFourConstant.PLAYER2;
    testBoardArray[3][3] = ConnectFourConstant.PLAYER2;
    testBoard.setBoard(testBoardArray);
    testBoard.setLastCol(0);
    testBoard.setLastRow(0);
    assertTrue("Wrong value for winner",
        testBoard.check(testBoardArray, testPlayer));
  }

  @Test
  public void testFindWinner_LeftDiagonal() {
    ConnectFourPlayer testPlayer = new ConnectFourPlayer("Player 2", 2, true);
    testAvailableRowArray[3] = 4;
    testBoard.setAvailableRow(testAvailableRowArray.clone());
    testBoardArray[3][3] = ConnectFourConstant.PLAYER2;
    testBoardArray[2][4] = ConnectFourConstant.PLAYER2;
    testBoardArray[4][2] = ConnectFourConstant.PLAYER2;
    testBoardArray[5][1] = ConnectFourConstant.PLAYER2;
    testBoard.setBoard(testBoardArray);
    testBoard.setLastCol(3);
    testBoard.setLastRow(3);
    assertTrue("Wrong value for winner",
        testBoard.check(testBoardArray, testPlayer));
  }

  @Test
  public void testFindWinner_None() {
    ConnectFourPlayer testPlayer = new ConnectFourPlayer("Player 2", 2, true);
    assertFalse("No winner should be found",
        testBoard.check(testBoardArray, testPlayer));
  }

}
