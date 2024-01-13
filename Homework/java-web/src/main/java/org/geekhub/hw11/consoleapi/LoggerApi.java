package org.geekhub.hw11.consoleapi;

import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.service.logging.LoggingService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class LoggerApi {

    private static final String LOGGER_SUB_MENU = """
        Choice the action:
            1 - Show all logged messages;
            2 - Show log entries by specified date;
            3 - Show algorithms using counters
            4 - Show a unique encryptions by their original message.
            Q - Back to the previous menu""";
    private static final String WRONG_INPUT_MESSAGE = "Wrong input, please enter the one of the options in the menu below";

    private final LoggingService loggingService;
    private final Scanner scanner;

    public LoggerApi(LoggingService loggingService, Scanner scanner) {
        this.loggingService = loggingService;
        this.scanner = scanner;
    }

    public void printLogMenu() {
        boolean isInLogMenu = true;
        while (isInLogMenu) {
            System.out.println(LOGGER_SUB_MENU);
            switch (scanner.nextLine()) {
                case "1" -> showLog();
                case "2" -> showLobBySpecifiedDate();
                case "3" -> loggingService.getAlgorithmUsageCount()
                    .forEach((algorithm, counter) -> System.out.printf("%s was used %d times%n", algorithm, counter));
                case "4" -> showUniqueLogEntriesByMessage();
                case "Q" -> isInLogMenu = false;
                default -> System.out.println(WRONG_INPUT_MESSAGE);
            }
        }
    }

    private void showLog() {
        loggingService.showMessagesLog();
    }

    private void showLobBySpecifiedDate() {
        System.out.printf("%nPlease, enter the date in format dd-mm-yyyy: ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = scanner.nextLine();
        try {
            loggingService.getLogsByDate(sdf.parse(date).toInstant())
                .forEach(logEntry -> System.out.printf("%s%n", logEntry.stringForOutput()));
        } catch (ParseException e) {
            System.out.printf("You've entered the wrong date. Please, follow the specified format: dd-mm-yyyy%n");
        }
    }

    private void showUniqueLogEntriesByMessage() {
        System.out.printf("%nPlease, enter a message you wanna find in the program log: ");
        String requestedMessage = scanner.nextLine();
        List<LogEntry> foundEntries = loggingService.getUniqueEncryptions(requestedMessage);
        if (foundEntries.isEmpty()) {
            System.out.printf("Entries not found for '%s' message, please, try again%n", requestedMessage);
        } else {
            LogEntry foundEntry = foundEntries.get(0);
            System.out.printf("Message '%s' was encrypted via %s %d times", foundEntry.input(), foundEntry.algorithm(),
                foundEntries.size());
        }
    }

    public void saveLog() {
        loggingService.save();
    }
}
