package advisor;

import advisor.commands.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

    public static String authServerPath = "https://accounts.spotify.com";
    public static String apiServerPath = "https://api.spotify.com";

    public static int itemsPerPage = 5;

    public static boolean authorized = false;

    private static CommandController commandController = new CommandController();
    public static CommandResult commandResult = new CommandResult();

    public static void run() {

        boolean exit = false;

        while (!exit) {

            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();



            if (command.matches("(featured|new|categories|playlists [A-Za-z ]+|prev|next)") & !authorized) {
                System.out.println("Please, provide access for application.");
                continue;
            }

            if (!command.matches("(auth|featured|new|categories|playlists [A-Za-z ]+|prev|next)")) {
                System.out.println("Wrong command!");
                continue;
            }


            if (!commandResult.getResultSet().isEmpty() && (command.equals("prev") || command.equals("next"))) {

                commandResult.moveOnResult(command);
                continue;

            }

            commandResult.setResultSet(new ArrayList<String>());
            commandResult.setCurrentPage(1);


            if (command.matches("playlists [A-Za-z ]+")) {

                String[] commandArray = command.split(" ");

                String commandArg = "";
                StringBuilder commandBuilder = new StringBuilder();
                if (commandArray.length > 2) {
                    for (int i = 1; i < commandArray.length; i++) {
                        commandBuilder.append(commandArray[i]).append(" ");
                    }
                    commandArg = commandBuilder.toString().trim();
                } else {
                    commandArg = commandArray[1];
                }

                commandController.setCommandWithArgs(new Playlists());
                commandController.setArgument(commandArg);
                commandController.executeCommandWithArgs();
            }

            switch (command) {

                case "auth":
                    commandController.setCommand(new Auth());
                    commandController.executeCommand();
                    break;

                case "featured":
                    commandController.setCommand(new Featured());
                    commandController.executeCommand();
                    break;

                case "new":
                    commandController.setCommand(new New());
                    commandController.executeCommand();
                    break;

                case "categories":
                    commandController.setCommand(new Categories());
                    commandController.executeCommand();
                    break;

                case "exit":
                    System.out.println("---GOODBYE!---");
                    exit = true;
                    break;

                default:
                    break;

            }
        }

    }



}
