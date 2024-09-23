package xyz.sk1.service.httpserver.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HttpPacketHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String requestedFile = exchange.getRequestURI().getPath();

        if (requestedFile.equals("/")) {
            requestedFile = "/index.html";
        }

        byte[] response = Files.readAllBytes(Paths.get("/var/www"+requestedFile));

        exchange.sendResponseHeaders(200, response.length);

        try(OutputStream os = exchange.getResponseBody()) {
            os.write(response);
        } catch (IOException e){
            e.printStackTrace();
            String failureResponse = "<h1>Not Found</h1>";
            exchange.sendResponseHeaders(404, failureResponse.length());

            try(OutputStream os = exchange.getResponseBody()) {
                os.write(failureResponse.getBytes());
            }

        }


    }

}
