class WordleGame(private val secretWord: String) {
    private val maxAttempts = 6
    private var attempts = 0

    private val guessedChars = mutableSetOf<Char>()

    fun guess(char: Char): GuessResult {
        if (attempts >= maxAttempts) return GuessResult.GameOver

        guessedChars.add(char)
        attempts++

        val guessedWord = secretWord.map { if (it in guessedChars) it else '_' }

        return if (guessedWord.joinToString("") == secretWord) {
            GuessResult.Won(guessedWord)
        } else {
            GuessResult.InProgress(guessedWord, attempts)
        }
    }

    sealed class GuessResult {
        object GameOver : GuessResult()
        data class Won(val guessedWord: List<Char>) : GuessResult()
        data class InProgress(val guessedWord: List<Char>, val attempts: Int) : GuessResult()
    }
}
