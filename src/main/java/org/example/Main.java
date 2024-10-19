package org.example;

import java.util.Scanner;

import static org.example.model.Color.BLACK;
import static org.example.model.Color.WHITE;

public class Main {

    public static void main(String[] args) {

        ChessBoard board = new ChessBoard(WHITE);
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
               Чтобы проверить игру надо вводить команды:
               'exit' - для выхода
               'replay' - для перезапуска игры
               'castling0' или 'castling7' - для рокировки по соответствующей линии
               'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)
               'destroyed' - список уничтоженных фигур
               'info 1 1' - информация о фигуре на указанной клетке
               'give up' - сдаться текущему игроку
               """);
        System.out.println();
        board.printBoard();

        while (true) {
            String s = scanner.nextLine();
            if (s.equals("exit")) {
                break;
            } else if (s.equals("replay")) {
                System.out.println("Заново");
                board = buildBoard();
                board.printBoard();
            } else if (s.equals("give up")) {
                System.out.println("Поздравляем! победил игрок : "
                        + (board.nowPlayerColor().equals(WHITE) ? BLACK : WHITE));
            } else {
                if (s.equals("castling0")) {
                    if (board.castling0()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.equals("castling7")) {
                    if (board.castling7()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.contains("move")) {
                    String[] a = s.split(" ");
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        int toLine = Integer.parseInt(a[3]);
                        int toColumn = Integer.parseInt(a[4]);
                        if (board.moveToPosition(line, column, toLine, toColumn)) {
                            System.out.println("Успешно передвинулись");
                            board.printBoard();
                        } else System.out.println("Передвижение не удалось");
                    } catch (Exception e) {
                        System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                    }

                }
                else if (s.contains("info")) {
                    String[] a = s.split(" ");
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        System.out.println(board.getChessByCoordinates(line, column).toString());
                    } catch (Exception e) {
                        System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                    }

                } else if (s.equals("destroyed")) {
                    System.out.println(board.infoDestroyedPiece());
                }
            }
        }
    }

    private static ChessBoard buildBoard() {
        return new ChessBoard(WHITE);
    }
}