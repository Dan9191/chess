package org.example;

import org.example.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.model.Color.BLACK;
import static org.example.model.Color.WHITE;
import static org.example.model.PieceType.KING;

public class ChessBoard {
    private ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    private Color nowPlayer;

    private List<ChessPiece> destroyedPieces = new ArrayList<>();

    public ChessBoard(Color nowPlayer) {
        this.nowPlayer = nowPlayer;
        this.board[0][0] = new Rook(WHITE);
        this.board[0][1] = new Horse(WHITE);
        this.board[0][2] = new Bishop(WHITE);
        this.board[0][3] = new Queen(WHITE);
        this.board[0][4] = new King(WHITE);
        this.board[0][5] = new Bishop(WHITE);
        this.board[0][6] = new Horse(WHITE);
        this.board[0][7] = new Rook(WHITE);
        this.board[1][0] = new Pawn(WHITE);
        this.board[1][1] = new Pawn(WHITE);
        this.board[1][2] = new Pawn(WHITE);
        this.board[1][3] = new Pawn(WHITE);
        this.board[1][4] = new Pawn(WHITE);
        this.board[1][5] = new Pawn(WHITE);
        this.board[1][6] = new Pawn(WHITE);
        this.board[1][7] = new Pawn(WHITE);

        this.board[7][0] = new Rook(BLACK);
        this.board[7][1] = new Horse(BLACK);
        this.board[7][2] = new Bishop(BLACK);
        this.board[7][3] = new Queen(BLACK);
        this.board[7][4] = new King(BLACK);
        this.board[7][5] = new Bishop(BLACK);
        this.board[7][6] = new Horse(BLACK);
        this.board[7][7] = new Rook(BLACK);
        this.board[6][0] = new Pawn(BLACK);
        //this.board[6][0] = new Pawn(WHITE);
        this.board[6][1] = new Pawn(BLACK);
        this.board[6][2] = new Pawn(BLACK);
        this.board[6][3] = new Pawn(BLACK);
        this.board[6][4] = new Pawn(BLACK);
        this.board[6][5] = new Pawn(BLACK);
        this.board[6][6] = new Pawn(BLACK);
        this.board[6][7] = new Pawn(BLACK);

        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    board[i][j] = EmptyCell.getInstance();
                }
            }
        }
    }

    public Color nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (validatePosition(startLine) && validatePosition(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) {
                return false;
            }

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = EmptyCell.getInstance();
                this.nowPlayer = this.nowPlayerColor().equals(WHITE) ? BLACK : WHITE;

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j].toBoardPrint() + "\t");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean validatePosition(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public ChessPiece checkPosition(int line, int column) {
        if (line < 0 || line > 7 || column < 0 || column > 7) {
            return UnavailableCell.getInstance();
        } else {
            return board[line][column];
        }
    }

    public boolean castling0() {
        return false;
    }

    public boolean castling7() {
        return false;
    }

    public void addDestroyedPiece(ChessPiece piece) {
        this.destroyedPieces.add(piece);
        if (piece.getPieceType().equals(KING)) gameEnd();
    }

    public String infoDestroyedPiece() {
        return this.destroyedPieces.toString();
    }

    private void gameEnd() {
        System.out.println("Король убит, игра законченна");
        // todo
    }

    public ChessPiece getChessByCoordinates(int line, int column) {
        if (line < 0 || line > 7 || column < 0 || column > 7) {
            return UnavailableCell.getInstance();
        } else {
            return board[line][column];
        }
    }

    public List<ChessPiece> checkKing() {
        King currentKing = null;
        int line = 0;
        int column = 0;
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board[i][j];
                if (piece.getPieceType().equals(KING) && piece.getColor().equals(nowPlayer)) {
                    line = i;
                    column = j;
                    currentKing = (King) piece;
                }
            }
        }
        assert currentKing != null;
        return currentKing.isUnderAttack(this, line, column);
    }
}