package engine.logger

import engine.GameEngine
import java.util.logging.ConsoleHandler
import java.util.logging.Logger

object Logger {
    private val logger: Logger = Logger.getLogger(this::class.java.name)

    init {
        // init logger
        logger.useParentHandlers = false
        val handler = ConsoleHandler()
        handler.formatter = Formatter()
        logger.addHandler(handler)
    }

    fun log(string: String) {
        logger.info(string)
    }

    fun info(string: String) {
        log(string)
    }

    fun warning(string: String) {
        logger.warning(string)
    }

    fun error(string: String) {
        logger.severe(string)
    }
}