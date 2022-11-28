import java.io.File
import java.io.FileInputStream
import java.util.*

object PropertyReader {
    fun readFile(filename: String): Properties {
        val file = File("/properties/$filename.properties")
        val prop = Properties()
        FileInputStream(file).use {
            prop.load(it)
        }
        return prop
    }

    fun getProperty(filename: String, property: String): String {
        return readFile(filename).getProperty(property)
    }

    fun getInt(filename: String, property: String): Int {
        return getProperty(filename, property).toInt()
    }

    fun getDouble(filename: String, property: String): Double {
        return getProperty(filename, property).toDouble()
    }

}