package org.example.util;

import org.example.model.ChessPiece;
import org.example.model.Color;
import org.example.model.PieceType;

import java.util.function.Predicate;

public class Constants {

    public static Predicate<ChessPiece> IS_BLACK_PAWN = piece ->
            piece.getPieceType().equals(PieceType.PAWN) && piece.getColor().equals(Color.BLACK);

    public static Predicate<ChessPiece> IS_WHITE_PAWN = piece ->
            piece.getPieceType().equals(PieceType.PAWN) && piece.getColor().equals(Color.WHITE);

    public static Predicate<ChessPiece> IS_PAWN_OR_KING = piece ->
            piece.getPieceType().equals(PieceType.KING) || piece.getPieceType().equals(PieceType.PAWN);
}
