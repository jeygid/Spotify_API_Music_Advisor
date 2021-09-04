package advisor.commands;

import advisor.Runner;
import advisor.interfaces.Command;
import advisor.Data;
import advisor.connection.MyHttpHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Auth implements Command {

    @Override
    public void execute() {

        try {

            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
            server.createContext("/", new MyHttpHandler());
            server.start();

            System.out.println("use this link to request the access code (open in browser):");
            System.out.println(Runner.authServerPath + "/authorize?client_id=aee40a6434604e1e952295069d38b510&redirect_uri=http://localhost:8080&response_type=code");

            System.out.println("waiting for code...");

            while (Data.getSecretCode().equals("")) {
                Thread.sleep(10);
            };

            System.out.println("code received");
            server.stop(1);

            System.out.println("making http request for access_token...");

            HttpClient client = HttpClient.newBuilder().build();

            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("authorization", "Basic YWVlNDBhNjQzNDYwNGUxZTk1MjI5NTA2OWQzOGI1MTA6MTliMGVjOWQ2NWFhNGY2ZGI2NmE2N2RkYjFjM2QyOTM=")
                    .uri(URI.create(Runner.authServerPath + "/api/token"))
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code&code=" + Data.getSecretCode() + "&redirect_uri=http%3A%2F%2Flocalhost%3A8080"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//            System.out.println("response:");
//            System.out.println(response.body());

            JsonObject jo = JsonParser.parseString(response.body()).getAsJsonObject();
            String token = jo.get("access_token").getAsString();
            Data.setToken(token);
            System.out.println("Success!");

            Runner.authorized = true;
            Categories.updateCategoriesMap();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        Runner.authorized = true;

    }
}
