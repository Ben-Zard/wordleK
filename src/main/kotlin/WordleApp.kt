import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.InputType
import kotlinx.html.dom.create
import kotlinx.html.dom.append
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement

fun main() {
    window.onload = {
        val wordleGame = WordleGame("kotlin")

        val appDiv = document.getElementById("app") as HTMLDivElement

        val input = document.create.input {
            type = InputType.text
            maxLength = "1"
        }
        val submitButton = document.create.button {
            textContent = "Guess"
            onClickFunction = { _ ->
                val char = input.value.firstOrNull()
                if (char != null) {
                    val result = wordleGame.guess(char)
                    handleResult(result, appDiv)
                    input.value = ""
                }
            }
        }
        val output = document.create.div { }

        appDiv.append(input, submitButton, output)
    }
}

fun handleResult(result: WordleGame.GuessResult, output: HTMLElement) {
    when (result) {
        is WordleGame.GuessResult.GameOver -> output.textContent = "Game Over"
        is WordleGame.GuessResult.Won -> output.textContent = "You won! Word: ${result.guessedWord.joinToString("")}"
        is WordleGame.GuessResult.InProgress -> {
            output.textContent = "Guessed: ${result.guessedWord.joinToString(" ")} - Attempts: ${result.attempts}"
        }
    }
}
