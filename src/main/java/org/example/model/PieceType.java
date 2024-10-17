package org.example.model;

public enum PieceType {
    HORSE("H"),
    BISHOP("B"),
    PAWN("P"),
    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    EMPTY_CELL(".."),
    UNAVAILABLE_CELL("U");

    private final String symbol;

    PieceType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
