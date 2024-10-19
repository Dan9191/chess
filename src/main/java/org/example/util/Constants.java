package org.example.util;

import org.example.ChessBoard;
import org.example.model.ChessPiece;
import org.example.model.Color;
import org.example.model.King;
import org.example.model.PieceType;

import java.util.function.Predicate;

/**
 * Утилитный класс для хранения констант.
 */
public class Constants {

    /**
     * Проверка на черную пешку.
     */
    public static Predicate<ChessPiece> IS_BLACK_PAWN = piece ->
            piece.getPieceType().equals(PieceType.PAWN) && piece.getColor().equals(Color.BLACK);

    /**
     * Проверка на белую пешку.
     */
    public static Predicate<ChessPiece> IS_WHITE_PAWN = piece ->
            piece.getPieceType().equals(PieceType.PAWN) && piece.getColor().equals(Color.WHITE);

    /**
     * Проверка на короля или пешку.
     */
    public static Predicate<ChessPiece> IS_PAWN_OR_KING = piece ->
            piece.getPieceType().equals(PieceType.KING) || piece.getPieceType().equals(PieceType.PAWN);

    /**
     * Проверка на левую рокировку для белого короля.
     */
    public static Predicate<ChessBoard> WHITE_KING_CAN_DO_LEFT_CASTLING = board -> {
        ChessPiece supposedlyKing = board.getChessByCoordinates(0, 4);
        ChessPiece supposedlyRook = board.getChessByCoordinates(0, 0);
        return supposedlyKing.getPieceType().equals(PieceType.KING)
                && supposedlyKing.getColor().equals(Color.WHITE)
                && supposedlyKing.isHasFirstMove()
                && ((King) supposedlyKing).isUnderAttack(board, 0, 4)
                && supposedlyRook.getPieceType().equals(PieceType.ROOK)
                && supposedlyRook.getColor().equals(Color.WHITE)
                && supposedlyRook.isHasFirstMove()
                && board.getChessByCoordinates(0, 1).getPieceType().equals(PieceType.EMPTY_CELL)
                && board.getChessByCoordinates(0, 2).getPieceType().equals(PieceType.EMPTY_CELL)
                && board.getChessByCoordinates(0, 3).getPieceType().equals(PieceType.EMPTY_CELL);
    };

    /**
     * Проверка на левую рокировку для черного короля.
     */
    public static Predicate<ChessBoard> BLACK_KING_CAN_DO_LEFT_CASTLING = board -> {
        ChessPiece supposedlyKing = board.getChessByCoordinates(7, 4);
        ChessPiece supposedlyRook = board.getChessByCoordinates(7, 0);
        return supposedlyKing.getPieceType().equals(PieceType.KING)
                && supposedlyKing.getColor().equals(Color.BLACK)
                && supposedlyKing.isHasFirstMove()
                && ((King) supposedlyKing).isUnderAttack(board, 7, 4)
                && supposedlyRook.getPieceType().equals(PieceType.ROOK)
                && supposedlyRook.getColor().equals(Color.BLACK)
                && supposedlyRook.isHasFirstMove()
                && board.getChessByCoordinates(7, 1).getPieceType().equals(PieceType.EMPTY_CELL)
                && board.getChessByCoordinates(7, 2).getPieceType().equals(PieceType.EMPTY_CELL)
                && board.getChessByCoordinates(7, 3).getPieceType().equals(PieceType.EMPTY_CELL);
    };

    /**
     * Проверка на правую рокировку для белого короля.
     */
    public static Predicate<ChessBoard> WHITE_KING_CAN_DO_RIGHT_CASTLING = board -> {
        ChessPiece supposedlyKing = board.getChessByCoordinates(0, 4);
        ChessPiece supposedlyRook = board.getChessByCoordinates(0, 7);
        return supposedlyKing.getPieceType().equals(PieceType.KING)
                && supposedlyKing.getColor().equals(Color.WHITE)
                && supposedlyKing.isHasFirstMove()
                && ((King) supposedlyKing).isUnderAttack(board, 0, 4)
                && supposedlyRook.getPieceType().equals(PieceType.ROOK)
                && supposedlyRook.getColor().equals(Color.WHITE)
                && supposedlyRook.isHasFirstMove()
                && board.getChessByCoordinates(0, 5).getPieceType().equals(PieceType.EMPTY_CELL)
                && board.getChessByCoordinates(0, 6).getPieceType().equals(PieceType.EMPTY_CELL);
    };

    /**
     * Проверка на правую рокировку для черного короля.
     */
    public static Predicate<ChessBoard> BLACK_KING_CAN_DO_RIGHT_CASTLING = board -> {
        ChessPiece supposedlyKing = board.getChessByCoordinates(7, 4);
        ChessPiece supposedlyRook = board.getChessByCoordinates(7, 7);
        return supposedlyKing.getPieceType().equals(PieceType.KING)
                && supposedlyKing.getColor().equals(Color.BLACK)
                && supposedlyKing.isHasFirstMove()
                && ((King) supposedlyKing).isUnderAttack(board, 7, 4)
                && supposedlyRook.getPieceType().equals(PieceType.ROOK)
                && supposedlyRook.getColor().equals(Color.BLACK)
                && supposedlyRook.isHasFirstMove()
                && board.getChessByCoordinates(7, 5).getPieceType().equals(PieceType.EMPTY_CELL)
                && board.getChessByCoordinates(7, 6).getPieceType().equals(PieceType.EMPTY_CELL);
    };


}
