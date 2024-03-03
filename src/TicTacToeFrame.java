import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private TicTacToeButton[][] buttons;
    private TicTacToeGame ticTacToeGame;

    public TicTacToeFrame()
    {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(ROW, COL));
        buttonPanel.setPreferredSize(new Dimension(600, 600));
        add(buttonPanel, BorderLayout.CENTER);

        buttons = new TicTacToeButton[ROW][COL];
        ticTacToeGame = new TicTacToeGame();

        ActionListener buttonListener = e ->
        {
            TicTacToeButton clickedButton = (TicTacToeButton) e.getSource();
            int row = clickedButton.getRow();
            int col = clickedButton.getCol();

            if (ticTacToeGame.makeMove(row, col))
            {
                clickedButton.setPlayer(ticTacToeGame.getCurrentPlayer());

                if (ticTacToeGame.checkMoveResult()) {
                    JOptionPane.showMessageDialog(null, "Game result: " + ticTacToeGame.getCurrentPlayer());
                    resetGame();
                }
            }
        };

        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                TicTacToeButton button = new TicTacToeButton(i, j);
                button.addActionListener(buttonListener);
                buttons[i][j] = button;
                buttonPanel.add(button);
            }
        }

        JPanel controlPanel = new JPanel();
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());
        controlPanel.add(resetButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        controlPanel.add(quitButton);

        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean isGameFinished()
    {
        return ticTacToeGame.checkForWin() || ticTacToeGame.checkForTie();
    }

    private void resetGame()
    {
        ticTacToeGame.resetGame();
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                buttons[i][j].setPlayer(' ');
                buttons[i][j].setEnabled(true);
            }
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(TicTacToeFrame::new);
    }
}
