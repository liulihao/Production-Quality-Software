package edu.nyu.cs.pqs.connectfour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

public class ConnectFourView implements ConnectFourListener {

  private JFrame mainFrame;
  private JPanel mainPanel;
  private JPanel centerPanel;
  private ConnectFourModel model;
  private JButton[][] boardButtons;
  private int gameMode = ConnectFourConstant.TWO_PLAYERS;
  private JButton startButton;
  private JPanel boardPanel = new JPanel(
      new GridLayout(ConnectFourConstant.ROW, ConnectFourConstant.COL));
  private ConnectFourPlayer firstPlayer;
  private ConnectFourPlayer secondPlayer;
  private boolean active = false;
  private JRadioButton twoPlayer;
  private JRadioButton onePlayer;

  public ConnectFourView(ConnectFourModel model) {
    try {
      UIManager
          .setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.model = model;
    this.model.addConnectFourListener(this);
    initialize();
  }

  /**
   * Initializes the view for main frame and panel.
   */
  private void initialize() {
    setBoardButtons();
    mainFrame = new JFrame("Connect Four");
    mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(getOuterPanel(), BorderLayout.NORTH);
    centerPanel = getCenterPanel();
    mainPanel.add(centerPanel, BorderLayout.CENTER);
    mainFrame.setSize(600, 600);
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.getContentPane().add(mainPanel);
    setPlayers();
    mainFrame.setVisible(true);
  }

  /**
   * Sets properties for all the buttons on the board.
   */
  private void setBoardButtons() {
    boardButtons = new JButton[ConnectFourConstant.ROW][ConnectFourConstant.COL];
    for (int r = ConnectFourConstant.ROW - 1; r >= 0; r--) {
      for (int c = 0; c < ConnectFourConstant.COL; c++) {
        boardButtons[r][c] = new JButton("");
        boardButtons[r][c].putClientProperty("column", c);
        boardButtons[r][c].putClientProperty("row", r);
        boardButtons[r][c].setBackground(Color.WHITE);
        boardButtons[r][c].addActionListener(new BoardButtonActionListener());
      }
    }
  }

  /**
   * Gets the top panel contains the game mode radio buttons and start button.
   * 
   * @return topPanel JPanel contains the game buttons.
   */
  private JPanel getOuterPanel() {
    JPanel topPanel = new JPanel(new FlowLayout());
    twoPlayer = new JRadioButton("Two Players");
    twoPlayer.setMnemonic(KeyEvent.VK_H);
    twoPlayer.putClientProperty("mode", ConnectFourConstant.TWO_PLAYERS);
    twoPlayer.addActionListener(new PlayerActionListener());
    twoPlayer.setSelected(true);
    onePlayer = new JRadioButton("One Player");
    onePlayer.setMnemonic(KeyEvent.VK_C);
    onePlayer.putClientProperty("mode", ConnectFourConstant.ONE_PLAYER);
    onePlayer.addActionListener(new PlayerActionListener());
    ButtonGroup modeGroup = new ButtonGroup();
    modeGroup.add(twoPlayer);
    modeGroup.add(onePlayer);
    startButton = new JButton("Start");
    startButton.putClientProperty("action", ConnectFourConstant.BUTTON_START);
    startButton.addActionListener(new StartButtonActionListener());
    topPanel.add(twoPlayer);
    topPanel.add(onePlayer);
    topPanel.add(startButton);
    return topPanel;
  }

  /**
   * Gets the central panel containing the ConnectFour grid.
   * 
   * @return boardPanel JPanel consisting the game board.
   */
  private JPanel getCenterPanel() {
    for (int r = ConnectFourConstant.ROW - 1; r >= 0; r--) {
      for (int c = 0; c < ConnectFourConstant.COL; c++) {
        boardPanel.add(boardButtons[r][c]);
      }
    }
    return boardPanel;
  }

  /**
   * Set game players in the model.
   */
  private void setPlayers() {
    if (gameMode == ConnectFourConstant.TWO_PLAYERS) {
      firstPlayer = new ConnectFourPlayer("Player 1",
          ConnectFourConstant.PLAYER1, true);
      secondPlayer = new ConnectFourPlayer("Player 2",
          ConnectFourConstant.PLAYER2, true);
    } else {
      firstPlayer = new ConnectFourPlayer("Player", ConnectFourConstant.PLAYER1,
          true);
      secondPlayer = new ConnectFourPlayer("Computer",
          ConnectFourConstant.PLAYER2, false);
    }
    model.setFirstPlayer(firstPlayer);
    model.setSecondPlayer(secondPlayer);
  }

  /**
   * Gets selected game mode and set players.
   */
  private class PlayerActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      gameMode = (int) ((JComponent) e.getSource()).getClientProperty("mode");
      setPlayers();
    }
  }

  /**
   * Starts game when user press start button.
   */
  private class StartButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      model.startGame();
    }
  }

  /**
   * Gets number of column and make move.
   */
  private class BoardButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (active) {
        JButton btn = (JButton) e.getSource();
        int col = (int) btn.getClientProperty("column");
        model.play(col);
      } else {
        JOptionPane.showMessageDialog(mainFrame,
            "Please Select Game Mode",
            "Information",
            JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }

  @Override
  public void gameStart() {
    active = true;
    int action = (int) startButton.getClientProperty("action");
    if (action == ConnectFourConstant.BUTTON_START) {
      for (JButton[] boardButton : boardButtons) {
        for (JButton b : boardButton) {
          b.setText("");
        }
      }
      twoPlayer.setEnabled(false);
      onePlayer.setEnabled(false);
      startButton.setText("Restart");
      startButton.putClientProperty("action",
          ConnectFourConstant.BUTTON_RESTART);
    } else {
      gameReset();
    }
  }

  @Override
  public void gameMessage(String message) {
    JOptionPane.showMessageDialog(mainFrame, message, "Invalid Move",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void gameWinner(String message) {
    JOptionPane.showMessageDialog(mainFrame, message, "Winner",
        JOptionPane.PLAIN_MESSAGE);
    gameReset();
  }

  @Override
  public void makeMove(int playerId, int r, int c) {
    if (playerId == ConnectFourConstant.PLAYER1) {
      boardButtons[r][c].setBackground(ConnectFourConstant.COLOR_PLAYER1);
    } else {
      boardButtons[r][c].setBackground(ConnectFourConstant.COLOR_PLAYER2);
    }
    boardButtons[r][c].setOpaque(true);
    boardButtons[r][c].setBorderPainted(false);
  }

  @Override
  public void gameReset() {
    active = false;
    startButton.setText("Start");
    startButton.putClientProperty("action", ConnectFourConstant.BUTTON_START);
    boardPanel.removeAll();
    boardPanel.revalidate();
    setBoardButtons();
    getCenterPanel();
    gameMode = ConnectFourConstant.TWO_PLAYERS;
    twoPlayer.setEnabled(true);
    onePlayer.setEnabled(true);
    twoPlayer.setSelected(true);
    onePlayer.setSelected(false);
    setPlayers();
    model.resetGame();
  }

  @Override
  public void gameEnded() {
    JOptionPane.showMessageDialog(mainFrame, "Game Tied!!", "Game Ended",
        JOptionPane.CLOSED_OPTION);
    gameReset();
  }

  public JButton[][] getBoardButtons() {
    return boardButtons;
  }

  public JRadioButton getOnePlayer() {
    return onePlayer;
  }

  public void setOnePlayer(JRadioButton onePlayer) {
    this.onePlayer = onePlayer;
  }

  public JRadioButton getTwoPlayer() {
    return twoPlayer;
  }

  public void setTwoPlayer(JRadioButton twoPlayer) {
    this.twoPlayer = twoPlayer;
  }

  public ConnectFourPlayer getFirstPlayer() {
    return firstPlayer;
  }

  public void setFirstPlayer(ConnectFourPlayer firstPlayer) {
    this.firstPlayer = firstPlayer;
  }

  public ConnectFourPlayer getSecondPlayer() {
    return secondPlayer;
  }

  public void setSecondPlayer(ConnectFourPlayer secondPlayer) {
    this.secondPlayer = secondPlayer;
  }

  public JButton getStartButton() {
    return startButton;
  }

  public void setStartButton(JButton startButton) {
    this.startButton = startButton;
  }

}
