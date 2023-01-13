package tictactoe

const val reset = "\u001B[0m"
const val purple = "\u001B[35m"
const val red = "\u001B[31m"

private var tablero = Tablero(0)
private const val jugador1 = purple + "X" + reset
private const val jugador2 = red + "O" + reset
private var jugadorActual = ""

fun main() {
    println("|----------------------------------------------------|")
    println("|---------------------3 en raya----------------------|")
    println("|----------------------------------------------------|")

    println(
        "\nBienvenido al super juego 3 en raya. " +
                "\nREGLAS DEL JUEGO: " +
                "\n-El juego es para 2 jugadores. " +
                "\n-Tienes que conseguir alinear las piezas (X, O) en 3 casillas consecutivas, puede ser de manera horizontal, vertical o diagonal." +
                "\n-Si intentas escribir encima de una pieza ya colocada en el tablero, pasará el turno al otro jugador. " +
                "\n-Si intentas colocar una pieza fuera del tablero, pasará el turno al otro jugador" +
                "\n-Una vez acabada la partida, pasará el turno al jugador contrario al que le haya tocado mover la posición, ejemplo: si acaban las X, empieza la O en la siguiente partida."
    )

    println("\nPon el tamaño de tu tablero: (Deberías poner un 3) ")

    var size: Int

    while (true) {
        try {
            val numEscrito = readLine()
            size = numEscrito?.toInt() ?: 3
            if (size > 0) {
                break
            } else {
                println("¿No estarás intentado crear un tablero con un numero negativo, verdad? Pon un numero válido anda.")
            }
        } catch (e: NumberFormatException) {
            println("¿No estarás intentado crear un tablero con una string, verdad? Pon un numero válido anda.")
        }
    }
    tablero = Tablero(size)
    tablero.printTablero()

    fun takeTurns() { //Se encarga de asignar los turnos a los 2 jugadores.
        jugadorActual = if (jugador1 == jugadorActual) {
            jugador2
        } else {
            jugador1
        }
    }

    fun jugarOtraVez(respuesta: String): Boolean { //Si quieres volver a jugar escribe "si" al final del juego.
        return when (respuesta.lowercase()) {
            "si" -> true
            "no" -> false
            else -> false
        }
    }

    /* Alternativa de codi de la funció jugarOtraVez

    fun jugarOtraVez(respuesta: String?): Boolean {
       return (respuesta?.lowercase().equals("si", ignoreCase = true) || respuesta?.lowercase()
           .equals("no", ignoreCase = false))
   }*/


    while (!tablero.gameOver) {
        takeTurns()
        println("Torn de: $jugadorActual")
        println("Pon el numero de la fila: ")

        val fila = try {
            readLine()?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            println("No siguis troll va, posa un numero company, però et quedes sense torn ;).")
            continue
        }

        println("Pon el numero de la columna: ")

        val columna = try {
            readLine()?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            println("No siguis troll va, posa un numero company, però et quedes sense torn ;).")
            continue
        }

        tablero.ubicacionPieza( //Quan s'envia la posició de la peça, es conta des del 0, per això el -1
            fila - 1,
            columna - 1,
            jugadorActual
        )

        if (tablero.gameOver) {
            println("¿Quieres seguir jugando? Escribe si o no: ")
            val respuesta = readLine()
            if (jugarOtraVez(respuesta.toString())) {
                tablero.resetGame()
                tablero.printTablero()
            }
        }
    }
}

