package advisor.commands;

import advisor.Runner;
import advisor.interfaces.Command;
import advisor.Data;
import advisor.dto.categoriesresponse.CategoriesItem;
import advisor.dto.categoriesresponse.CategoriesResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Categories implements Command {

    public static void updateCategoriesMap() {

        try {

            HttpClient client = HttpClient.newBuilder().build();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .header("Authorization", "Bearer " + Data.getToken())
                    .uri(URI.create(Runner.apiServerPath + "/v1/browse/categories?offset=0&limit=50"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            CategoriesResponse categoriesResponse = gson.fromJson(response.body(), CategoriesResponse.class);

            System.out.println();
            for (CategoriesItem item : categoriesResponse.getCategories().getItems()) {
                Data.categories.put(item.getName(), item.getId());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void execute() {

        try {

            Thread.sleep(100);

            HttpClient client = HttpClient.newBuilder().build();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .header("Authorization", "Bearer " + Data.getToken())
                    .uri(URI.create(Runner.apiServerPath + "/v1/browse/categories?offset=0&limit=50"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            CategoriesResponse categoriesResponse = gson.fromJson(response.body(), CategoriesResponse.class);

            List<String> resultSet = new ArrayList<>();

            for (CategoriesItem item : categoriesResponse.getCategories().getItems()) {
                resultSet.add(item.getName());
            }

            Runner.commandResult.setResultSet(resultSet);
            Runner.commandResult.generateResults();


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
