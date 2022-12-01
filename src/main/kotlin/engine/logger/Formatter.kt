package engine.logger

import java.util.logging.Formatter
import java.util.logging.Level
import java.util.logging.LogRecord

class Formatter : Formatter() {
    // ANSI escape code
    val ANSI_RESET = "\u001B[0m"
    val ANSI_BLACK = "\u001B[30m"
    val ANSI_RED = "\u001B[31m"
    val ANSI_GREEN = "\u001B[32m"
    val ANSI_YELLOW = "\u001B[33m"
    val ANSI_BLUE = "\u001B[34m"
    val ANSI_PURPLE = "\u001B[35m"
    val ANSI_CYAN = "\u001B[36m"
    val ANSI_WHITE = "\u001B[37m"

    override fun format(record: LogRecord?): String {
        var string: String = ""
        when (record?.level) {
            Level.INFO -> string += "$ANSI_WHITE [${record?.level}] ${record?.message} $ANSI_WHITE"
            Level.WARNING -> string += "$ANSI_YELLOW [${record?.level}] ${record?.message} $ANSI_YELLOW"
            Level.SEVERE -> string += "$ANSI_RED [${record?.level}] ${record?.message} $ANSI_RED"
        }
        string += "\n"
        return string
    }
}