package advisor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static advisor.Runner.itemsPerPage;
import static java.lang.Math.min;
import static java.util.stream.Collectors.toMap;

public class CommandResult {

    private int currentPage = 1;
    private List<String> resultSet = new ArrayList<>();
    private Map<Integer, List<String>> resultSetPerPage = new HashMap<>();

    public void setResultSet(List<String> resultSet) {
        this.resultSet = resultSet;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void generateResults() {

        resultSetPerPage =

                IntStream.iterate(0, i -> i + itemsPerPage)
                        .limit((resultSet.size() + itemsPerPage - 1) / itemsPerPage)
                        .boxed()
                        .collect(toMap(i -> i / itemsPerPage, i -> resultSet.subList(i, min(i + itemsPerPage, resultSet.size()))));



            List<String> thisPage = resultSetPerPage.get(currentPage - 1);

            for (String str : thisPage) {
                System.out.println(str);
            }

            System.out.println("---PAGE " + currentPage + " OF " + resultSetPerPage.size() + "---");

    }

    public List<String> getResultSet() {
        return resultSet;
    }



    public void moveOnResult(String command) {

        if (command.equals("next")) {
            currentPage++;
        }

        if (command.equals("prev")) {
            currentPage--;
        }

        if (currentPage > resultSetPerPage.size()) {
            System.out.println("No more pages.");
            currentPage--;
            return;
        }

        if (currentPage - 1 < 0) {
            System.out.println("No more pages.");
            currentPage++;
            return;
        }

        List<String> thisPage = resultSetPerPage.get(currentPage - 1);

        for (String str : thisPage) {
            System.out.println(str);
        }

        System.out.println("---PAGE " + currentPage + " OF " + resultSetPerPage.size() + "---");


    }
}

