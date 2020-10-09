import java.util.Scanner;
public class TicTacToe {
    char board[][] = new char [3][3];
    public TicTacToe(){
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                board[i][j]=' ';
            }
        }
    }
    public boolean checkWinner (char player ){
        int row = 0 ;
        while (row<3) {
            if(board[row][0]==board[row][1] && board[row][1]==board[row][2] && board[row][0]==player)
                return true;
            row++;
        }
        int col = 0 ;
        while (col<3){
            if (board[0][col]==board[1][col]&& board[1][col]==board[2][col] && board[0][col]==player)
                return true ;
            col++;
        }
        if (board[0][0]==board[1][1] && board[1][1] == board [2][2] && board[0][0]==player)
            return true;
        if (board[2][0]==board[1][1] && board[1][1] == board [0][2] && board[2][0]==player)
            return true;

        return  false;
    }
    int EmptySpots(){
        int c = 0;
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j]==' ')
                    c++;
            }
        }
        return c ;
    }
     boolean isATie(){
        return  (!checkWinner('X') && !checkWinner('O') && EmptySpots()==0);
     }
     int minimax(boolean isMaximizing) {
         if (checkWinner('X'))
             return EmptySpots();
         else if (checkWinner('O'))
             return EmptySpots() * -1;
         else if (isATie())
             return 0;
         else if (isMaximizing) {
             int min = Integer.MIN_VALUE;
             for (int i = 0; i < 3; i++) {
                 for (int j = 0; j < 3; j++) {
                     if (board[i][j] == ' ') {
                         board[i][j] = 'X';
                         int score = minimax(false);
                         board[i][j] = ' ';
                         min = Math.max(min, score);
                     }
                 }
             }
             return min;
         } else {
             int max = Integer.MAX_VALUE;
             for (int i = 0; i < 3; i++) {
                 for (int j = 0; j < 3; j++) {
                     if (board[i][j] == ' ') {
                         board[i][j] = 'O';
                         int score = minimax(true);
                         board[i][j] = ' ';
                         max = Math.min(max, score);
                     }
                 }
             }
             return max;
         }
     }
     void print(){
         for (int i = 0; i <3 ; i++) {
             for (int j = 0; j <3 ; j++) {
                 if(board[i][j] == ' ') {
                     if (i == 0)
                         System.out.print(j + 1 + " | ");
                     else if (i == 1)
                         System.out.print(j + 4 +" | ");
                     else
                         System.out.print(j + 7+" | ");
                 }
                 else
               System.out.print(board[i][j]+" | ");
             }
             System.out.println("\n-----------");
         }
     }
}
class run {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        TicTacToe t =new TicTacToe();
        t.print();
        while (true){
            System.out.println("Choose a cell to play in it");

            int play = s.nextInt();

            int row=-1 , col=-1 ;
            if (play<=3) {
                row = 0;
                col = play-1;
            } else if (play<=6){
                row = 1 ; col=play%4;
            }else if (play <=9){
                row = 2 ; col = play%7;
            }
            if(t.board[row][col]!=' '){
                System.out.println ("This spot is taken try another one");
                continue;
            }

            t.board[row][col]='X';
            if(t.isATie() || t.checkWinner('X') || t.checkWinner('O'))
                break;
            t.print();
            System.out.println("AI Turn");
            int movei = 0,movej=0;
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i <3 ; i++) {
                for (int j = 0; j <3 ; j++) {
                    if(t.board[i][j]==' ') {
                        t.board[i][j] = 'O';
                        int score = t.minimax(true);
                        t.board[i][j]=' ' ;
                        if(score<bestScore){
                            bestScore = score;
                            movei=i;movej=j;
                        }
                    }
                }
            }
            t.board[movei][movej]='O';
            if(t.isATie() ||t.checkWinner('X') || t.checkWinner('O'))
                break;
            t.print();
        }
        t.print();
        if(t.isATie())
            System.out.println("it's a tie");
        else
            if (t.checkWinner('X'))
            System.out.println("X wins");
            else
            System.out.println("O wins");

    }
}
