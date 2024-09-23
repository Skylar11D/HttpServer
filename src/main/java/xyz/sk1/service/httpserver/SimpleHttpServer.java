package xyz.sk1.service.httpserver;

import com.sun.net.httpserver.HttpServer;
import xyz.sk1.service.httpserver.handlers.HttpPacketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        Utils.LOG.info("enter a port to listen to: (0-65535)");

        int port = scanner.nextInt();

        if(port > Utils.Socket.MAXIMUM_PORT.getMax() || port < 1) {
            Utils.LOG.info("Maximum port number is 65535");
            return;
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", new HttpPacketHandler());

        server.setExecutor(null);

        //start the server
        server.start();

        Utils.LOG.info("server is now listening on: " + port);
    }

}
