package tictactoe

import kotlin.math.pow

class Tablero(private val n: Int) { //El valor de la n serà 3

    private val espaiBuit = "___" // Dibuixa les caselles que seràn substituides per les peçes X i O
    private var moveCount = 0
    var gameOver = false
    private var tablero =
        Array(n) { Array(n) { espaiBuit } }// Una Array es una llista inmutable

    private fun resetTablero() {
        tablero = Array(n) { Array(n) { espaiBuit } } //resetea el tablero
    }

    fun printTablero() { //Dibuixa el tauler
        tablero.forEach { fila -> //Expressió lambda
            fila.forEach { element ->
                if (element == espaiBuit) {
                    print("|$element|")
                } else {
                    print("| $element |")
                }

            }
            println()
        }
        println()
    }

    /* Alternativa de codi de la funció printTablero()

        fun printTablero() {
            for (fila in tablero) {
                for (element in fila) {
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
    */

    fun ubicacionPieza(x: Int, y: Int, move: String) {
        if (!gameOver && posicionPiezaValida(x, y) && tablero[x][y] == espaiBuit) {
            moveCount++
            tablero[x][y] = move
            printTablero()
            gameOver = ganarPartida(x, y, move) || empate()
            if (gameOver && !empate()) {
                println("Has ganado")
            } else if (empate()) {
                println("Empate")
            }
        }
    }

    private fun posicionPiezaValida(x: Int, y: Int): Boolean {
        return ((x in 0 until n) && (y in 0 until n))
    }

    private fun ganarPartida(x: Int, y: Int, move: String): Boolean {
        //Comprobamos la fila
        for (i in 0 until n) {
            if (tablero[x][i] != move) {
                break
            }
            if (i == n - 1) {
                return true
            }
        }
        //Comprobamos la columna
        for (i in 0 until n) {
            if (tablero[i][y] != move) {
                break
            }
            if (i == n - 1) {
                return true
            }
        }
        //Comprobamos la diagonal
        if (x == y) {
            for (i in 0 until n) {
                if (tablero[i][i] != move) {
                    break
                }
                if (i == n - 1) {
                    return true
                }
            }
        }
        // Diagonal inversa
        if (x + y == n - 1) {
            for (i in 0 until n) {
                if (tablero[i][(n - 1) - i] != move) {
                    break
                }
                if (i == n - 1) {
                    return true
                }
            }
        }
        return false
    }

    private fun empate(): Boolean {
        return (moveCount == (n.toDouble().pow(2) - 1).toInt())
    }

    fun resetGame() {
        resetTablero()
        gameOver = false
        moveCount = 0
    }
}