package tictactoe

import tictactoe.Game.Companion.currentPlayer
import kotlin.math.pow

open class Board(val gameSize: Int) { //El valor de gameSize será 3

    val empty = Color.CYAN + "___" + Color.RESET// Dibuixa les caselles que seràn substituides per les peçes X i O
    var moveCount = 0
    var isGameOver = false
    var board =
        Array(gameSize) { Array(gameSize) { empty } }// Una Array es una llista inmutable

    private fun resetBoard() {
        board = Array(gameSize) { Array(gameSize) { empty } } //resetea el tablero
    }

    fun printBoard() { //Dibuixa el tauler
        board.forEach { row -> //Expressió lambda
            row.forEach { element ->
                if (element == empty) {
                    print("|$element|")
                } else {
                    print("| $element |")
                }
            }
            println()
        }
        println()
    }

    fun placePiece(row: Int, column: Int, move: String) {
        if (!isGameOver && isPositionValid(row, column) && board[row][column] == empty) {
            moveCount++
            board[row][column] = move
            printBoard()
            isGameOver = isWinningMove(row, column, move) || draw()
            if (isGameOver && !draw()) {
                println(
                    " _________________________ \n" +
                            "< ¡Ha ganado el jugador: $currentPlayer!  >\n" +
                            " ------------------------- \n" +
                            "        \\   ^__^\n" +
                            "         \\  (oo)\\_______\n" +
                            "            (__)\\       )\\/ \\\n" +
                            "                ||----w |\n" +
                            "                ||     ||"
                )
            } else if (draw()) {
                println(
                    "_____________\n" +
                            "< ¡Empate!  >\n" +
                            " ------------\n" +
                            "   \\\n" +
                            "    \\\n" +
                            "        .--.\n" +
                            "       |o_o |\n" +
                            "       |:_/ |\n" +
                            "      //   \\ \\\n" +
                            "     (|     | )\n" +
                            "    /'\\_   _/`\\\n" +
                            "    \\___)=(___/"
                )
            }
        }
    }

    open fun isPositionValid(row: Int, column: Int): Boolean {
        return ((row in 0 until gameSize) && (column in 0 until gameSize))
    }

    open fun isWinningMove(row: Int, column: Int, move: String): Boolean {
        //Comprobamos la fila
        for (i in 0 until gameSize) { // Indican las posiciones 0,1 0,2 0,3 etc.
            if (board[row][i] != move) {
                break
            }
            if (i == gameSize - 1) {
                return true
            }
        }
        //Comprobamos la columna
        for (i in 0 until gameSize) { //Indican las posiciones 0,0 1,0 2,0 etc.
            if (board[i][column] != move) {
                break
            }
            if (i == gameSize - 1) {
                return true
            }
        }
        //Comprobamos la diagonal
        if (row == column) { //Indican las posiciones 0,0 1,1 2,2 etc.
            for (i in 0 until gameSize) {
                if (board[i][i] != move) {
                    break
                }
                if (i == gameSize - 1) {
                    return true
                }
            }
        }
        // Diagonal inversa
        if (row + column == gameSize - 1) { //Indican las posiciones 0,3 1,1 2,1
            for (i in 0 until gameSize) {
                if (board[i][(gameSize - 1) - i] != move) {
                    break
                }
                if (i == gameSize - 1) {
                    return true
                }
            }
        }
        return false
    }

   open fun draw(): Boolean {
        return (moveCount == (gameSize.toDouble().pow(2) - 1).toInt())
    }
    fun resetGame() {
        resetBoard()
        isGameOver = false
        moveCount = 0
    }



}