import java.net.*

fun main() {
    val port = 8081
    val serverSocket = ServerSocket(port)
    println("Server started on port $port")

    while (true) {
        val clientSocket = serverSocket.accept()
        println("New client connected")

        val inputStream = clientSocket.getInputStream()
        val input = inputStream.bufferedReader().readLine()
        println("Received message from client: $input")

        val outputStream = clientSocket.getOutputStream()
        outputStream.write("Hello from server\n".toByteArray())
    }
}