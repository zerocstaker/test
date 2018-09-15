
public class Board
{
    private static String player1 = "x";
    private static String player2 = "o";
    private static String empty = " ";
    private static String[][] board;
    private static int size;

    //this instanciates the board
    public static void createBoard(int dimensions)
    {
        size = dimensions;
        board = new String[size][size];
        for (int i = 0; i < size; i ++)
            for (int j = 0; j < size; j ++)
                board[i][j] = " ";
    }

    //this prints the whole board
    public static void printBoard()
    {
        //loop through the board and print the symbol with dashes surrounding it making boxes
        printCoordinates();
        printLayer();
        for (int i = 0; i < size; i ++)
            for (int j = 0; j < size; j ++)
            {
                if(j == 0)
                    System.out.print((i+1) + " ");
                System.out.print("| " + board[i][j] + " ");
                if(j == size - 1)
                {
                    System.out.print("|\n");
                    printLayer();
                }
        }
    }

    //this prints the top and bottom of each row of boxes
    private static void printLayer()
    {
        System.out.print("   ");
        for (int x = 0; x < size; x++)
            System.out.print("--- ");
        System.out.println();
    }

    // this helps to print the board by printing the x and y coordinates
    private static void printCoordinates()
    {
        System.out.print("    ");
        for (int i = 0; i < size; i ++)
            System.out.print((i + 1) + "   ");
        System.out.println();
    }
    public static int getSize(){return size;}
    //this method changes board[][] by placing a symbol in the indicated spot
    public static void makeMove(boolean player, int y, int x)
    {
        if(player)
            board[y][x] = player1;
        else
            board[y][x] = player2;
    }

    public static String[][] getBoard()
    {
        return board;
    }
    // this goes through all the possible win conditions and sees if someone has won or not
    public static boolean checkWin(String[][] boardtemp,boolean player)
    {
        boolean won = false;
        won = checkWinX(boardtemp,player);
        if(!won)
        {
            won = checkWinY(boardtemp,player);
            if(!won)
            {
                won = checkWinDiagonal(boardtemp,player);
                if(!won)
                    won = checkWinOtherDiagonal(boardtemp,player);
            }
        }
        return won;
    }

    //this checks to see if a row is filled by a player
    private static boolean checkWinX(String[][] boardtemp,boolean player)
    {
        int numMoves = 0;
        if(player)
            for (int i = 0; i < size; i ++)
                for (int j = 0; j < size; j ++)
                {
                    if(boardtemp[i][j].equals(player1))
                        numMoves ++;
                    if(numMoves == size)
                        return true;
                    if(j == size-1)
                        numMoves = 0;
        }
        else
            for (int i = 0; i < size; i ++)
                for (int j = 0; j < size; j ++)
                {
                    if(boardtemp[i][j].equals(player2))
                        numMoves ++;
                    if(numMoves == size)
                        return true;
                    if(j == size-1)
                        numMoves = 0;
        }
        return false;
    }

    //this method checks to see if a column is filled by a player
    private static boolean checkWinY(String[][] boardtemp,boolean player)
    {
        int numMoves = 0;
        if(player)
            for (int i = 0; i < size; i ++)
                for (int j = 0; j < size; j ++)
                {
                    if(boardtemp[j][i].equals(player1))
                        numMoves ++;
                    if(numMoves == size)
                        return true;
                    if(j == size-1)
                        numMoves = 0;
        }
        else
            for (int i = 0; i < size; i ++)
                for (int j = 0; j < size; j ++)
                {
                    if(boardtemp[j][i].equals(player2))
                        numMoves ++;
                    if(numMoves == size)
                        return true;
                    if(j == size-1)
                        numMoves = 0;
        }
        return false;
    }

    /*
     * This checks the diagonal with a negative slope
     */
    private static boolean checkWinDiagonal(String[][] boardtemp,boolean player)
    {
        int numMoves = 0;
        if(player)
            for (int i = 0; i < size; i ++)
                for (int j = 0; j < size; j ++)
                {
                    if(i == j && boardtemp[j][i].equals(player1))
                        numMoves ++;
                    if(numMoves == size)
                        return true;
        }
        else
            for (int i = 0; i < size; i ++)
                for (int j = 0; j < size; j ++)
                {
                    if(i == j &&boardtemp[j][i].equals(player2))
                        numMoves ++;
                    if(numMoves == size)
                        return true;
        }
        return false;
    }

    /*
     * This checks the other diagonal ie **x
     *                                   *x*
     *                                   x**
     */
    private static boolean checkWinOtherDiagonal(String[][] boardtemp,boolean player)
    {
        int numMoves = 0;
        if(player)
            for (int i = 0; i < size; i ++)
                for (int j = 0; j < size; j ++)
                {
                    if(i == (size -1) -j && boardtemp[j][i].equals(player1))
                        numMoves ++;
                    if(numMoves == size)
                        return true;
        }
        else
            for (int i = 0; i < size; i ++)
                for (int j = 0; j < size; j ++)
                {
                    if(i == (size -1) -j && boardtemp[j][i].equals(player2))
                        numMoves ++;
                    if(numMoves == size)
                        return true;
        }
        return false;
    }

    public static boolean checkTaken (int y, int x)
    {
        if (board[y][x].equals(empty))
            return true;
        else
            return false;
    }
}
