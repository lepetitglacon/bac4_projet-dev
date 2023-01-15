package server

import java.io.ObjectInputStream
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.atomic.AtomicInteger

class Server(val port: Int) : Thread() {
    val socket = ServerSocket(port)
    val clients = mutableMapOf<Int, ClientHandler>()

    var running = false

    private fun init() {
        running = true
    }

    override fun run() {
        init()
        println("Server initialized, listening on $socket")

        while (running) {
            val newClientSocket = socket.accept()
            val id = Server.id.getAndIncrement()
            clients[id] = ClientHandler(newClientSocket, id)
            println("Client $id connecting...")

            Thread {
                clients[id]?.start()
            }.start()
        }

    }

    inner class ClientHandler(val socket: Socket, val id: Int) : Thread() {

        fun init() {
            // Input thread
            Thread {
                listen()
            }.start()
        }

        override fun run() {
            init()
            println("Client $id connected !")

            while (socket.isConnected) {

            }
        }

        fun listen() {
            try {
                val input = ObjectInputStream(socket.getInputStream())
                while (true) {
                    //handleRequest(input.readObject() as Request)
                }
            } catch (e: Exception) {
                println("client $id disconnected")
                clients.remove(id)
            }
        }
    }

    companion object {
        var id = AtomicInteger(1)
    }
}