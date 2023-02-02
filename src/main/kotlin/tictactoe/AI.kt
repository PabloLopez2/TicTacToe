package tictactoe

import tictactoe.Game.Companion.PLAYER1
import tictactoe.Game.Companion.PLAYER_IA
import tictactoe.Game.Companion.board
import java.lang.StrictMath.max
import java.lang.StrictMath.min


open class AI {
    companion object {
        var alpha = Int.MIN_VALUE
        var beta = Int.MAX_VALUE

        private val otherClass = Board(0)

        fun protocoloOmega(
            depth: Int,
            player: String,
            alpha: Int,
            beta: Int,
            row: Int,
            column: Int
        ): Map<String, Any> {

            if (otherClass.isWinningMove(row, column, player)) return mapOf(
                "score" to Int.MAX_VALUE,
                "move" to mapOf("row" to row, "column" to column)
            )
            if (depth == 0) return mapOf("score" to 0, "move" to mapOf("row" to -1, "column" to -1))

            if (player == PLAYER_IA) { //PLAYER1 = "X", PLAYER2 = "O"
                var bestScore = Int.MIN_VALUE
                var bestMove = mapOf("row" to -1, "column" to -1)
                for (i in 0 until board.gameSize) {
                    for (j in 0 until board.gameSize) {
                        if (board.board[i][j] == board.empty) {
                            board.board[i][j] = PLAYER1
                            val score = protocoloOmega(3, PLAYER_IA, alpha, beta, i, j)["score"] as Int
                            board.board[i][j] = board.empty
                            bestScore = score
                            bestMove = mapOf("row" to i, "column" to j)
                            this.alpha = max(alpha, bestScore)
                            if (beta <= alpha) break
                        }
                    }
                    if (beta <= alpha) break
                }
                return mapOf("score" to bestScore, "move" to bestMove)
            } else {
                var bestScore = Int.MAX_VALUE
                var bestMove = mapOf("row" to -1, "column" to -1)
                for (i in 0 until board.gameSize) {
                    for (j in 0 until board.gameSize) {
                        if (board.board[i][j] == board.empty) {
                            board.board[i][j] = PLAYER_IA
                            val score = protocoloOmega(depth - 1, PLAYER_IA, alpha, beta, i, j)["score"] as Int
                            board.board[i][j] = board.empty
                            bestScore = score
                            bestMove = mapOf("row" to i, "column" to j)
                            this.beta = min(beta, bestScore)
                            if (beta <= alpha) break
                        }
                    }
                    if (beta <= alpha) break
                }
                return mapOf("score" to bestScore, "move" to bestMove)
            }
        }
    }
}



