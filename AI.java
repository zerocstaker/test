
/**
 * Write a description of class AI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AI
{
    static int size = Board.getSize();
    // create two arrays, one for each player for easy AI reading
    //calls the next method to provide possible value
    // order:check if AI has two or more, then checks if player1 has two or more, if not random selecting a point
    // is not the super computer type, because it will not evaluate whether it is more important to block them or to get more for AI itself
    // -> does not count who has more in a certain way
    public static int[] move()
    {
        int[] yx = new int [2];
        String[][] board = Board.getBoard();
        // instantiate arrays filled with AI moves
        String[][] AIMoves = new String [size][size];
        for (int i =0; i < size; i ++)
        {
            for (int j =0; j < size; j++)
            {
                AIMoves[j][i]=" ";
            }
        }
        // insert all the moves done by player AI
        for (int i =0; i < size; i ++)
        {
            for (int j =0; j < size; j++)
            {
                if (board[j][i].equals("o"))
                    AIMoves[j][i]= "o";
            }
        }
        // instantiate arrays filled with Player 1 moves
        String[][] playerMoves = new String [size][size];
        for (int i =0; i < size; i ++)
        {
            for (int j =0; j < size; j++)
            {
                playerMoves[j][i]=" ";
            }
        }
        // insert all the moves done by player AI
        for (int i =0; i <size; i ++)
        {
            for (int j =0; j < size; j++)
            {
                if (board[j][i].equals("x"))
                    playerMoves[j][i]= "x";
            }
        }     

        // check and Evaluate if AI has two or more
        for (int i=0; i<4; i ++)
        {
            yx = checkAndEvaluate (i, AIMoves, playerMoves, false);
            if (yx[0]!= -1)
                return yx;
        }
        // check and Evaluate if Player1 has two or more
        for (int i=0; i<4; i ++)
        {
            yx = checkAndEvaluate (i, AIMoves, playerMoves, true);
            if (yx[0]!= -1)
                return yx;
        }
        //random if else
        yx[0]=(int)(Math.random()*size);
        yx[1]=(int)(Math.random()*size);
        while (!Board.checkTaken (yx[0],yx[1]))
        {
            yx[0]=(int)(Math.random()*size);
            yx[1]=(int)(Math.random()*size);
        }
        return yx;
    }
    // check for possible place and return an array with x and y coordinate
    // order: horizontal, vertical, diagonal
    public static int[] checkAndEvaluate(int way, String[][] AIBoard, String[][] playerBoard, boolean checkedPlayer)
    {
        // for better reading
        int HORIZONTAL = 0;
        int VERTICAL = 1;
        int DIAGONALPOS = 2;
        int DIAGONALNEG = 3;

        int[] yx = new int[2];
        yx[0]=-1;
        yx[1]=-1;
        if (way == HORIZONTAL)
        {
            int numMoves= 0;
            for (int y =0; y < size; y ++)
            {
                for (int x =0; x < size; x++)
                {
                    // checking for self
                    if (!checkedPlayer)
                    {
                        // count the moves done by AI horizontally
                        if (AIBoard[y][x].equals("o"))
                            numMoves++;
                        // if enemy blocked it, do not evaluate
                        if (playerBoard[y][x].equals("x"))
                            break;
                    }
                    else
                    {
                        // count the moves by player 1 horizontally
                        if (playerBoard[y][x].equals("x"))
                            numMoves++;
                        // if AI blocked it already, no need to worry about it anymore
                        if (AIBoard[y][x].equals("o"))
                            break;
                    }
                    // reach end of one direction
                    if (x == size-1)
                    {
                        // if two or more evaluate
                        if (numMoves>1)
                        {
                            yx[0]=y;
                            yx[1]=findOpenSpaceWhen("x",y);
                            return yx;
                        }
                        else//reset
                            numMoves=0;
                    }
                }
            }
        }
        if (way == VERTICAL)
        {
            int numMoves= 0;
            for (int x =0; x < size; x ++)
            {
                for (int y =0; y < size; y++)
                {
                    if(!checkedPlayer)
                    {
                        if (AIBoard[y][x].equals("o"))
                            numMoves++;
                        if (playerBoard[y][x].equals("x"))
                            break;
                    }
                    else
                    {
                        if (playerBoard[y][x].equals("x"))
                            numMoves++;
                        if (AIBoard[y][x].equals("o"))
                            break;
                    }
                    if (y == size-1)
                    {
                        if (numMoves>1)
                        {
                            yx[0]=findOpenSpaceWhen("y",x);
                            yx[1]=x;
                            return yx;
                        }
                        else
                            numMoves=0;
                    }
                }
            }
        }
        if (way == DIAGONALPOS)
        {
            int numMoves= 0;
            for (int i =0; i < size; i ++)
            {
                if(!checkedPlayer)
                {
                    if (AIBoard[size-1-i][i].equals("o"))
                        numMoves++;
                    if (playerBoard[size-1-i][i].equals("x"))
                        break;
                }
                else
                {
                    if (playerBoard[size-1-i][i].equals("x"))
                        numMoves++;
                    if (AIBoard[size-1-i][i].equals("o"))
                        break;
                }
                if (i==size-1)
                {
                    if (numMoves>1)
                    {
                        yx[0]=findOpenSpaceWhen("dp",0);
                        yx[1]=size-1-yx[0];
                        return yx;
                    }
                }
            }
        }
        if (way == DIAGONALNEG)
        {
            int numMoves= 0;
            for (int i =0; i < size; i ++)
            {
                if(!checkedPlayer)
                {
                    if (AIBoard[i][i].equals("o"))
                        numMoves++;
                    if (playerBoard[i][i].equals("x"))
                        break;
                }
                else
                {
                    if (playerBoard[i][i].equals("x"))
                        numMoves++;
                    if (AIBoard[i][i].equals("o"))
                        break;
                }
                if (i==size-1)
                {
                    if (numMoves>1)
                    {
                        yx[0]=findOpenSpaceWhen("dn",0);
                        yx[1]=yx[0];
                        return yx;
                    }
                }
            }
        }
        return yx;
    }
    // find the next possible corrdinate, String indication is the coordinate that AI wants to find, and xy is either x or y that is provided, diagonal does not need int xy
    public static int findOpenSpaceWhen (String indication, int xy)
    {
        // need x
        if (indication.equals ("x"))
        // goes through all the x on given y corrdinate
            for (int x=0; x <size; x ++)
            {
                if (Board.getBoard()[xy][x].equals(" "))
                    return x;
        }
        else if (indication.equals ("y"))
            for (int y=0; y <size; y ++)
            {
                if (Board.getBoard()[y][xy].equals(" "))
                    return y;
        }
        else if (indication.equals ("dn"))
            for (int i=0; i <size; i ++)
            {
                if (Board.getBoard()[i][i].equals(" "))
                    return i;
        }
        else if (indication.equals ("dp"))
            for (int i=0; i <size; i ++)
            {
                if (Board.getBoard()[size-1-i][i].equals(" "))
                    return i;
        }
        return 0;
    }
}
