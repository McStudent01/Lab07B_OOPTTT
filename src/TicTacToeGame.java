public class TicTacToeGame
{
    private char[][] board;
    private char currentPlayer;

    public TicTacToeGame()
    {
        board = new char[3][3];
        initializeBoard();
        currentPlayer = 'X';
    }

    public char getCurrentPlayer()
    {
        return currentPlayer;
    }

    public boolean makeMove(int row, int col)
    {
        if (board[row][col] == ' ')
        {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkMoveResult()
    {
        if (checkForWin())
        {
            return true;
        } else if (checkForTie())
        {
            return true;
        } else
        {
            switchPlayer();
            return false;
        }
    }

    public void switchPlayer()
    {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean checkForWin()
    {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    public boolean checkForTie()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == ' ')
                {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkRows()
    {
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)
            {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns()
    {
        for (int i = 0; i < 3; i++)
        {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)
            {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals()
    {
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    public void resetGame()
    {
        initializeBoard();
        currentPlayer = 'X';
    }

    private void initializeBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = ' ';
            }
        }
    }
}
