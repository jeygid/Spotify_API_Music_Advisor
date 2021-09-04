package advisor.connection;

import advisor.Data;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class MyHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String query = exchange.getRequestURI().getQuery();

        if (query == null) query = "";

        if (query.matches("code=\\S*")) {

                String success = "Got the code. Return back to your program. ";
                exchange.sendResponseHeaders(200, success.length());
                exchange.getResponseBody().write(success.getBytes());
                exchange.getResponseBody().close();

                String[] code = query.split("=");
                Data.setSecretCode(code[1]);

            } else {

                String fail = "Authorization code not found. Try again.";
                exchange.sendResponseHeaders(200, fail.length());
                exchange.getResponseBody().write(fail.getBytes());
                exchange.getResponseBody().close();

            }

        }

    }


