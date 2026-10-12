import java.util.ArrayList;
import java.util.List;

public class ResultLog {
    private static final List<String> ENTRIES = new ArrayList<>();

    public static void add(String entry) {
        ENTRIES.add(entry);
    }

    public static void displayAll() {
        if (ENTRIES.isEmpty()) {
            System.out.println("No results recorded yet.");
            return;
        }
        for (int i = 0; i < ENTRIES.size(); i++) {
            System.out.println((i + 1) + ". " + ENTRIES.get(i));
        }
    }
}
