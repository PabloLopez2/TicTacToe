package tictactoe

import tictactoe.AI.Companion.alpha
import tictactoe.AI.Companion.beta
import tictactoe.AI.Companion.protocoloOmega
import tictactoe.Game.Companion.takeTurns
import tictactoe.Game.Companion.playAgain
import tictactoe.Game.Companion.PLAYER1
import tictactoe.Game.Companion.PLAYER2
import tictactoe.Game.Companion.board
import tictactoe.Game.Companion.currentPlayer
import tictactoe.Board
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets

//
fun main() {
    // Set the charset to UTF 8 to the Standard Output
    if (System.out.charset() != StandardCharsets.UTF_8)
        System.setOut(PrintStream(FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8))

    board
    currentPlayer
    PLAYER1
    PLAYER2

    println(
        " _       __     __                             __           _______     ______          ______         \n" +
                "| |     / /__  / /________  ____ ___  ___     / /_____     /_  __(_)___/_  __/___ _____/_  __/___  ___ \n" +
                "| | /| / / _ \\/ / ___/ __ \\/ __ `__ \\/ _ \\   / __/ __ \\     / / / / ___// / / __ `/ ___// / / __ \\/ _ \\\n" +
                "| |/ |/ /  __/ / /__/ /_/ / / / / / /  __/  / /_/ /_/ /    / / / / /__ / / / /_/ / /__ / / / /_/ /  __/\n" +
                "|__/|__/\\___/_/\\___/\\____/_/ /_/ /_/\\___/   \\__/\\____/    /_/ /_/\\___//_/  \\__,_/\\___//_/  \\____/\\___/"
    )
    println(
        "\nTe doy la bienvenida a este TicTacToe. " +
                "\nREGLAS DEL JUEGO: " +
                "\n-El juego es para 2 jugadores. " +
                "\n-Tienes que conseguir alinear las piezas (X, O) en 3 casillas consecutivas, puede ser de manera horizontal, vertical o diagonal." +
                "\n-Si intentas escribir encima de una pieza ya colocada en el tablero, pasará el turno al otro jugador. " +
                "\n-Si intentas colocar una pieza fuera del tablero, pasará el turno al otro jugador." +
                "\n-Una vez acabada la partida, pasará el turno al jugador contrario al que le haya tocado mover la posición, ejemplo: si acaban las X, empieza la O en la siguiente partida."
    )


    println("\nEscoge el modo de juego: 1- 1vs1 sin camiseta contra la IA (spoiler: no es chatGPT). 2- Escoge a un newbie para jugar contra él (2 jugadores): ")

    val isGameModeSelected = false

    while (!isGameModeSelected) {
        val answerGameMode = readLine()?.toIntOrNull()

        if (answerGameMode == null) {
            println("Tienes que poner solo 1 o 2, no petarás el programa amiguito ;) ")
            continue
        }

        if (answerGameMode == 1) {
            println("\nPon el tamaño de tu tablero: (Deberías poner un 3) ")

            var size: Int

            while (true) {

                try {
                    val numSize = readLine()
                    size = numSize?.toInt() ?: 3
                    if (size > 0) {
                        break
                    } else {
                        println("¿No estarás intentado crear un tablero con un numero negativo, verdad? Pon un numero válido anda.")
                    }
                } catch (e: NumberFormatException) {
                    println("¿No estarás intentado crear un tablero con una string, verdad? Pon un numero válido anda.")
                }
            }

            board = Board(size)
            board.printBoard()


            while (!board.isGameOver) {
                if (currentPlayer == PLAYER1) {
                    println("Turno de: $currentPlayer")
                    println("Ingresa la fila y la columna (Ejemplo: 1,1): ")
                    val input = readLine()
                    val (row, column) = try {
                        val pattern = "(\\d+),(\\d+)".toRegex()
                        val match = input?.let { pattern.matchEntire(it) }
                        if (match != null) {
                            val (row, column) = match.destructured
                            Pair(row.toInt() - 1, column.toInt() - 1)
                        } else {
                            throw NumberFormatException()
                        }
                    } catch (e: NumberFormatException) {
                        println("ENTRADA INVALIDA, ingresa en formato: fila,columna (Ejemplo: 1,1), PASA TURNO AL SIGUIENTE JUGADOR")
                        board.printBoard()
                        continue
                    }
                } else {
                    val move = protocoloOmega(3, currentPlayer, alpha, beta, 0, 0)
                    val row =
                    val
                            board.placePiece(row, column, currentPlayer)
                }
                if (board.isGameOver) {
                    println("¿Quieres seguir jugando? Escribe si o no: ")
                    val answer = readLine()
                    if (playAgain(answer.toString())) {
                        board.resetGame()
                        board.printBoard()
                    }
                }
            }
        } else if (answerGameMode == 2) {
            println("\nPon el tamaño de tu tablero: (Deberías poner un 3) ")

            var size: Int

            while (true) {

                try {
                    val numSize = readLine()
                    size = numSize?.toInt() ?: 3
                    if (size > 0) {
                        break
                    } else {
                        println("¿No estarás intentado crear un tablero con un numero negativo, verdad? Pon un numero válido anda.")
                    }
                } catch (e: NumberFormatException) {
                    println("¿No estarás intentado crear un tablero con una string, verdad? Pon un numero válido anda.")
                }
            }

            board = Board(size)
            board.printBoard()

            while (!board.isGameOver) {
                takeTurns()
                println("Turno de: $currentPlayer")
                println("Ingresa la fila y la columna (Ejemplo: 1,1): ")
                val input = readLine()
                val (row, column) = try {
                    val pattern = "(\\d+),(\\d+)".toRegex()
                    val match = input?.let { pattern.matchEntire(it) }
                    if (match != null) {
                        val (row, column) = match.destructured
                        Pair(row.toInt() - 1, column.toInt() - 1)
                    } else {
                        throw NumberFormatException()
                    }
                } catch (e: NumberFormatException) {
                    println("ENTRADA INVALIDA, ingresa en formato: fila,columna (Ejemplo: 1,1), PASA TURNO AL SIGUIENTE JUGADOR")
                    board.printBoard()
                    continue
                }
                board.placePiece(row, column, currentPlayer)

                if (board.isGameOver) {
                    println("¿Quieres seguir jugando? Escribe si o no: ")
                    val answer = readLine()
                    if (playAgain(answer.toString())) {
                        board.resetGame()
                        board.printBoard()
                    }
                }
            }
        }
    }
}






