package advisor;

public class Main {

    public static boolean exit = false;

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-access")) {
                Runner.authServerPath = args[i + 1];
            }

            if (args[i].equals("-resource")) {
                Runner.apiServerPath = args[i + 1];
            }

            if (args[i].equals("-page")) {
                Runner.itemsPerPage = Integer.parseInt(args[i + 1]);
            }
        }

            Runner.run();

    }
}
