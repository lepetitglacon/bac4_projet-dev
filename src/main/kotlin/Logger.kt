import java.time.Instant
import java.util.logging.*
import java.util.logging.Logger

object Logger {


    fun log(string: String) {
        println("${Instant.now().toEpochMilli()} \u001B[32m$string\u001B[0m")
    }

    fun error(string: String) {
        println("${Instant.now().toEpochMilli()} \u001B[31m$string\u001B[0m")
    }

    fun warning(string: String) {
        println("${Instant.now().toEpochMilli()} \u001B[33m$string\u001B[0m")
    }
}