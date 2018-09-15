import java.util.*;
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{
    static public void game () {
        boolean end = false;//enable play again function
        while (!end)
        {
            System.out.print("\f");
            boolean player1 = true; // true =first player, false = second player, including A.I
            boolean AITurn = false;
            boolean draw = false;
            int numMoves = 0;
            Scanner scanner = new Scanner (System.in);
            System.out.println("Welcome to Tic Tac Toe");
            // A.I. option
            System.out.print("Do you want to play against A.I.? (y/n)");
            String temp = scanner.nextLine ();
            if (temp.equals ("Y") || temp.equals("y"))
                AITurn = true;
            else
                AITurn = false;
            System.out.println();
            //Board Size
            System.out.print("Please enter the size of the board:");
            Board.createBoard(scanner.nextInt());
            int drawMoves = 0;//this will see if the game is a draw or not
            while (!Board.checkWin(Board.getBoard(), player1))
            {
                System.out.println("\f");
                Board.printBoard();
                // if it is player 1 turn
                if (player1)
                {
                    printTurn (player1,AITurn);
                    playerMove(player1, AITurn);
                    if (Board.checkWin(Board.getBoard(),player1))
                        break;
                }
                // if it is player 2 or AI turn
                else
                {
                    if (!AITurn)
                    {
                        printTurn (player1,AITurn);
                        playerMove(player1, AITurn);
                    }
                    else
                    {
                        printTurn (player1,AITurn);
                        int[] aiMove = AI.move();
                        Board.makeMove(player1, aiMove[0], aiMove[1]);
                        if(Board.checkWin(Board.getBoard(),player1))
                            break;
                    }
                }
                //change turns
                player1=!player1;
                //this ends the loop if all the spaces have been used fordraw
                numMoves ++;
                if(numMoves == Board.getSize()*Board.getSize())
                {
                    draw = true;
                    break;
                }
            }
            System.out.println("\f");
            Board.printBoard();
            // win lose display
            if(player1 && !draw)
                System.out.println("Player 1 Won!");
            else if(!draw)
            {
                if (!AITurn)
                    System.out.println("Player 2 Won!");
                else
                    System.out.println("You Lost!");
            }
            else
                System.out.print("Draw");
            //win lose function
            System.out.print("Do you want to play again?(y/n)");
            scanner = new Scanner (System.in);
            temp = scanner.nextLine ();
            if (temp.equals ("Y") || temp.equals("y"))
                end = false;
            else
                end = true;
        }
    }

    public static void playerMove (boolean player1, boolean AITurn)
    {
        Scanner scanner = new Scanner(System.in);
        // check X
        System.out.print("Please enter the column:");
        int x = scanner.nextInt();
        // for putting in array (0 based)
        x--;
        while (x > Board.getBoard().length && x <= 0)
        {
            System.out.print("Please enter the column:");
            x = scanner.nextInt();
            x--;
        }

        System.out.println();

        // check Y
        System.out.print("Please enter the row:");
        int y = scanner.nextInt();
        y--;
        while(y > Board.getBoard()[0].length && y <= 0)
        {
            System.out.print("Please enter the row:");
            y = scanner.nextInt();
            y--;
        }
        System.out.println();

        //check if the place is taken or not
        while (!Board.checkTaken (y,x))
        {
            System.out.println("\f");
            Board.printBoard();
            System.out.println("The Position is already taken.");
            printTurn (player1,AITurn);
            System.out.print("Please enter the column:");
            x = scanner.nextInt();
            x--;
            while (x > Board.getBoard().length && x <= 0)
            {
                System.out.print("Please enter the column:");
                x = scanner.nextInt();
                x--;
            }

            System.out.println();

            // check Y
            System.out.print("Please enter the row:");
            y = scanner.nextInt();
            y--;
            while(y > Board.getBoard()[0].length && y <= 0)
            {
                System.out.print("Please enter the row:");
                y = scanner.nextInt();
                y--;
            }
            System.out.println();
        }
        Board.makeMove(player1, y, x);
    }

    public static void printTurn (boolean player1, boolean AITurn)
    {
        if (player1)
        {
            System.out.println("Player 1's turn:");
        }
        else
        {
            //second player's turn
            if (!AITurn)
            {
                System.out.println("Player 2's turn:");
            }
            else
            {
                System.out.println("AI's turn:");
            }
        }
    }
}
