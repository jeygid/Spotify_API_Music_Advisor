package advisor.commands;

import advisor.Runner;
import advisor.interfaces.Command;
import advisor.Data;
import advisor.dto.featuredresponse.FeaturedItem;
import advisor.dto.featuredresponse.FeaturedResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Featured implements Command {

    @Override
    public void execute() {

        try {

            Thread.sleep(100);

            HttpClient client = HttpClient.newBuilder().build();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .header("Authorization", "Bearer " + Data.getToken())
                    .uri(URI.create(Runner.apiServerPath + "/v1/browse/featured-playlists"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            FeaturedResponse featuredResponse = gson.fromJson(response.body(), FeaturedResponse.class);

            List<String> resultSet = new ArrayList<>();

            for (FeaturedItem featuredItem : featuredResponse.getPlaylists().getItems()) {
                resultSet.add(featuredItem + "\n");
            }

            Runner.commandResult.setResultSet(resultSet);
            Runner.commandResult.generateResults();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
