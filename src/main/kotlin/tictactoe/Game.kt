package tictactoe

open class Game {

    companion object {

        var board = Board(0)
        var currentPlayer = ""
        const val PLAYER1 = Color.PURPLE + "X" + Color.RESET
        const val PLAYER2 = Color.RED + "O" + Color.RESET

        fun takeTurns() { //Se encarga de asignar los turnos a los 2 jugadores.
            currentPlayer = if (PLAYER1 == currentPlayer) {
                PLAYER2
            } else {
                PLAYER1
            }
        }

        fun playAgain(answer: String): Boolean { //Si quieres volver a jugar escribe "si" al final del juego.
            return when (answer.lowercase()) {
                "si" -> true
                "no" -> false
                else -> false
            }
        }
    }
}