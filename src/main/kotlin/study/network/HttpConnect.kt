package study.network

import study.AbstractStudy
import java.io.*
import java.net.Socket

class HttpConnect: AbstractStudy(HttpConnect::class.java.simpleName) {

    override fun execute() {
        super.execute()

        connectWithSocket()

    }

    private fun connectWithSocket() {
        val apiUrl = "/scraping/lastItemKey"
        val hostName = "localhost"
        val port = 9916

        Socket(hostName, port).let {socket ->
            BufferedWriter(OutputStreamWriter(socket.getOutputStream())).use { writer ->
                BufferedReader(InputStreamReader(socket.getInputStream())).use {reader ->
                    writer.run {
                        write("GET $apiUrl HTTP/1.0\r\n")
                        write("Host:$hostName:$port\r\n")
                        write("\r\n")
                        flush()
                    }
                    reader.readLines().forEach { println(it) }
                }
            }
            socket.close()
        }
    }
}