package advisor.commands;

import advisor.Runner;
import advisor.interfaces.Command;
import advisor.Data;
import advisor.dto.newresponse.NewItem;
import advisor.dto.newresponse.NewResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class New implements Command {

    @Override
    public void execute() {

        try {

            Thread.sleep(100);

            HttpClient client = HttpClient.newBuilder().build();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .header("Authorization", "Bearer " + Data.getToken())
                    .uri(URI.create(Runner.apiServerPath + "/v1/browse/new-releases"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            NewResponse newResponse = gson.fromJson(response.body(), NewResponse.class);

            List<String> resultSet = new ArrayList<>();

            for (NewItem newItem : newResponse.getAlbums().getItems()) {
                resultSet.add(newItem + "\n");
            }

            Runner.commandResult.setResultSet(resultSet);
            Runner.commandResult.generateResults();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}