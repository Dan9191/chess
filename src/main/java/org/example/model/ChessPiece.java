package org.example.model;

import org.example.ChessBoard;

import java.util.List;

public abstract class ChessPiece {
    Color color;

    PieceType pieceType;
    boolean hasFirstMove = true;

    public ChessPiece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public Color getColor() {
        return this.color;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public String getSymbol() {
        return this.pieceType.getSymbol();
    }

    public String getColorLikeString() {
        return this.color.getName();
    }

    public void doFirstMove() {
        if (this.hasFirstMove) {
            this.hasFirstMove = false;
        }
    }

    public String toString() {
        return pieceType + " " + color + " " +hasFirstMove;
    }

    public String toBoardPrint() {
        return pieceType.getSymbol() + color.getName();
    }

    public boolean checkAvailable(int line, int column) {
        return line >= 0 && line <= 7 && column >= 0 && column <= 7;
    }

    public boolean enemyCheck(ChessPiece other) {
        return (this.getColor().equals(Color.WHITE) && other.getColor().equals(Color.BLACK))
                || (this.getColor().equals(Color.BLACK) && other.getColor().equals(Color.WHITE));
    }

    public boolean emptyCheck(ChessPiece other) {
        return other.pieceType.equals(PieceType.EMPTY_CELL);
    }

    public boolean emptyCheckList(List<ChessPiece> others) {
        return others.stream().allMatch(it -> it.pieceType.equals(PieceType.EMPTY_CELL));
    }

    //todo нельзя ходить на самого себя
}
