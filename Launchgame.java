package proj;

import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    static char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        initboard();
    }

    void initboard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }

        }
    }

    static void dispboard() {
        for (int i = 0; i < board.length; i++) {
            System.out.println("-------------");
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {

                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

    static void placemark(int row, int col, char mark) {
        if (row < 3 && row >= 0 && col < 3 && col >= 0) {
            board[row][col] = mark;
        } else {
            System.out.println("INVALID POSITION");
        }
    }

    static boolean colwin() {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean rowwin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    static boolean diawin() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
                || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        } else {
            return false;
        }
    }

    static boolean draw() {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == ' '){
                    return true;
                }
            }
        }
        return false;
    }
}

abstract class Player {
    String name;
    char mark;

    abstract void makemove();

    boolean validmove(int row, int col) {
        if (row <= 2 && row >= 0 && col >= 0 && col <= 2) {
            if (TicTacToe.board[row][col] == ' ') {
                return true;

            }
        }
        return false;
    }

}

class Humanplayer extends Player {

    Humanplayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    void makemove() {

        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        do {

            row = sc.nextInt();
            col = sc.nextInt();

        } while (!validmove(row, col));

        TicTacToe.placemark(row, col, mark);

    }
}

class AIplayer extends Player {
    String name;
    char mark;

    AIplayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    void makemove() {

        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        do {
            Random ran = new Random();
            row = ran.nextInt(3);
            col = ran.nextInt(3);

        } while (!validmove(row, col));

        TicTacToe.placemark(row, col, mark);

    }
}

public class Launchgame {
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();

        Humanplayer p1 = new Humanplayer("Bob", 'X');
        AIplayer p2 = new AIplayer("AI", 'O');
        Player cp;

        cp = p1;
        while (true) {
            System.out.println(cp.name + " turns");
            cp.makemove();
            TicTacToe.dispboard();
            if (TicTacToe.colwin() || TicTacToe.rowwin() || TicTacToe.diawin()) {
                System.out.println(cp.name + " has won");
                break;

            }
            else if(!TicTacToe.draw()){
                System.out.println("The game is a Draw");
                break;
            }
            else {
                if (cp == p1) {
                    cp = p2;

                } else {
                    cp = p1;
                }
            }
        }
    }
}
