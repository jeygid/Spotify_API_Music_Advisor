package advisor.commands;

import advisor.Runner;
import advisor.interfaces.CommandWithArgs;
import advisor.Data;
import advisor.dto.playlistsresponse.PlaylistsItem;
import advisor.dto.playlistsresponse.PlaylistsResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Playlists implements CommandWithArgs {

    @Override
    public void execute(String arg) {

        if (!Data.categories.containsKey(arg)) {
            System.out.println("Unknown category name.");
            return;
        }

        try {

             Thread.sleep(100);

             HttpClient client = HttpClient.newBuilder().build();

             HttpRequest httpRequest = HttpRequest.newBuilder()
                    .header("Authorization", "Bearer " + Data.getToken())
                    .uri(URI.create(Runner.apiServerPath + "/v1/browse/categories/" + Data.categories.get(arg) + "/playlists"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            PlaylistsResponse playlistsResponse = gson.fromJson(response.body(), PlaylistsResponse.class);


            List<String> resultSet = new ArrayList<>();

            for (PlaylistsItem item : playlistsResponse.getPlaylists().getItems()) {
                resultSet.add(item.getName() + "\n" + item.getExternalUrls().getSpotify() + "\n");
            }

            Runner.commandResult.setResultSet(resultSet);
            Runner.commandResult.generateResults();


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
