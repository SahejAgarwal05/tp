package studybuddy.data.storage;

import java.util.ArrayList;

public class CommandHistoryManager {
    private static final ArrayList<String> commandHistory = new ArrayList<>();

    public static void record(String fullCommand) {
        if (!fullCommand.toLowerCase().startsWith("summary")) {
            commandHistory.add(fullCommand);
        }
    }

    public static String getSummary() {
        if (commandHistory.isEmpty()) {
            return "No commands were executed in this session.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("---- Command Summary ----\n");
        int count = 1;
        for (String cmd : commandHistory) {
            sb.append(count++).append(". ").append(cmd).append("\n");
        }
        return sb.toString().trim();
    }

    public static void clear() {
        commandHistory.clear();
    }
}


