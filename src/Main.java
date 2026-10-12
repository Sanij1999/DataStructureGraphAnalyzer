import java.util.List;
import java.util.Random;

public class Main {
    private static final int STRUCTURE_CAPACITY = 10;
    private static final DynamicArray array = new DynamicArray();
    private static final StackDS stack = new StackDS(STRUCTURE_CAPACITY);
    private static final QueueDS queue = new QueueDS(STRUCTURE_CAPACITY);
    private static final LinkedListDS linkedList = new LinkedListDS();
    private static final Graph graph = new Graph();
    private static final Random random = new Random();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("=============================================");
            System.out.println("DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            int choice = InputUtil.readInt("Enter your choice: ", 1, 9);
            switch (choice) {
                case 1 -> arrayMenu();
                case 2 -> stackMenu();
                case 3 -> queueMenu();
                case 4 -> linkedListMenu();
                case 5 -> searchMenu();
                case 6 -> graphMenu();
                case 7 -> performanceMenu();
                case 8 -> displayAllResults();
                default -> {
                    System.out.println("Goodbye.");
                    running = false;
                }
            }
        }
    }

    private static void arrayMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert at End");
            System.out.println("2. Insert at Index");
            System.out.println("3. Delete by Index");
            System.out.println("4. Delete by Value");
            System.out.println("5. Search for Value");
            System.out.println("6. Display Array");
            System.out.println("7. Sort Array");
            System.out.println("8. Fill with Random Values");
            System.out.println("9. Return to Main Menu");
            int choice = InputUtil.readInt("Enter your choice: ", 1, 9);
            try {
                switch (choice) {
                    case 1 -> {
                        array.insert(InputUtil.readInt("Value: "));
                        System.out.println("Value inserted.");
                    }
                    case 2 -> {
                        int index = InputUtil.readInt("Index (0-" + array.size() + "): ", 0, array.size());
                        array.insertAt(index, InputUtil.readInt("Value: "));
                        System.out.println("Value inserted.");
                    }
                    case 3 -> {
                        if (array.isEmpty()) {
                            System.out.println("Array is empty. Nothing to delete.");
                        } else {
                            int index = InputUtil.readInt("Index (0-" + (array.size() - 1) + "): ", 0, array.size() - 1);
                            System.out.println("Deleted value: " + array.deleteAt(index));
                        }
                    }
                    case 4 -> {
                        if (array.isEmpty()) {
                            System.out.println("Array is empty. Nothing to delete.");
                        } else {
                            int value = InputUtil.readInt("Value to delete: ");
                            int index = array.indexOf(value);
                            if (index < 0) {
                                System.out.println("Value not found.");
                            } else {
                                array.deleteAt(index);
                                System.out.println("Value deleted from index " + index + ".");
                            }
                        }
                    }
                    case 5 -> {
                        if (array.isEmpty()) {
                            System.out.println("Array is empty.");
                        } else {
                            int value = InputUtil.readInt("Value to search: ");
                            SearchOps.Result result = SearchOps.linear(array.toArray(), value);
                            reportSearch("Array", "Linear Search", value, result);
                        }
                    }
                    case 6 -> array.display();
                    case 7 -> {
                        array.sort();
                        System.out.println("Array sorted.");
                        array.display();
                    }
                    case 8 -> {
                        int count = InputUtil.readInt("How many values (1-1000): ", 1, 1000);
                        array.clear();
                        for (int i = 0; i < count; i++) {
                            array.insert(random.nextInt(1000));
                        }
                        System.out.println(count + " random values generated.");
                    }
                    default -> back = true;
                }
            } catch (IndexOutOfBoundsException | IllegalStateException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void stackMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            int choice = InputUtil.readInt("Enter your choice: ", 1, 5);
            try {
                switch (choice) {
                    case 1 -> {
                        stack.push(InputUtil.readInt("Value to push: "));
                        System.out.println("Value pushed.");
                    }
                    case 2 -> System.out.println("Popped value: " + stack.pop());
                    case 3 -> System.out.println("Top value: " + stack.peek());
                    case 4 -> stack.display();
                    default -> back = true;
                }
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void queueMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek Front");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            int choice = InputUtil.readInt("Enter your choice: ", 1, 5);
            try {
                switch (choice) {
                    case 1 -> {
                        queue.enqueue(InputUtil.readInt("Value to enqueue: "));
                        System.out.println("Value enqueued.");
                    }
                    case 2 -> System.out.println("Dequeued value: " + queue.dequeue());
                    case 3 -> System.out.println("Front value: " + queue.peek());
                    case 4 -> queue.display();
                    default -> back = true;
                }
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void linkedListMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------------- LINKED LIST OPERATIONS -------------");
            System.out.println("1. Insert at Head");
            System.out.println("2. Insert at Tail");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete by Value");
            System.out.println("5. Search for Value");
            System.out.println("6. Display List");
            System.out.println("7. Return to Main Menu");
            int choice = InputUtil.readInt("Enter your choice: ", 1, 7);
            try {
                switch (choice) {
                    case 1 -> {
                        linkedList.insertAtHead(InputUtil.readInt("Value: "));
                        System.out.println("Value inserted at head.");
                    }
                    case 2 -> {
                        linkedList.insertAtTail(InputUtil.readInt("Value: "));
                        System.out.println("Value inserted at tail.");
                    }
                    case 3 -> {
                        int position = InputUtil.readInt("Position (0-" + linkedList.size() + "): ", 0, linkedList.size());
                        linkedList.insertAt(position, InputUtil.readInt("Value: "));
                        System.out.println("Value inserted.");
                    }
                    case 4 -> {
                        if (linkedList.isEmpty()) {
                            System.out.println("List is empty. Nothing to delete.");
                        } else if (linkedList.deleteValue(InputUtil.readInt("Value to delete: "))) {
                            System.out.println("Value deleted.");
                        } else {
                            System.out.println("Value not found.");
                        }
                    }
                    case 5 -> {
                        if (linkedList.isEmpty()) {
                            System.out.println("List is empty.");
                        } else {
                            int value = InputUtil.readInt("Value to search: ");
                            linkedList.resetSteps();
                            int position = linkedList.search(value);
                            long steps = linkedList.getSteps();
                            String text = position >= 0 ? "found at position " + position : "not found";
                            System.out.println("Value " + value + " " + text + " (steps: " + steps + ")");
                            ResultLog.add("Linked List | Search " + value + " | " + text + " | steps=" + steps);
                        }
                    }
                    case 6 -> linkedList.display();
                    default -> back = true;
                }
            } catch (IndexOutOfBoundsException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void searchMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-------------- SEARCHING OPERATIONS --------------");
            System.out.println("1. Generate Sorted Array for Searching");
            System.out.println("2. Linear Search");
            System.out.println("3. Binary Search");
            System.out.println("4. Compare Linear vs Binary");
            System.out.println("5. Return to Main Menu");
            int choice = InputUtil.readInt("Enter your choice: ", 1, 5);
            switch (choice) {
                case 1 -> {
                    int count = InputUtil.readInt("Array size (1-100000): ", 1, 100000);
                    array.clear();
                    for (int i = 0; i < count; i++) {
                        array.insert(i * 2);
                    }
                    System.out.println("Sorted array of " + count + " even numbers created.");
                }
                case 2 -> {
                    if (array.isEmpty()) {
                        System.out.println("Array is empty. Add data using the Array menu or option 1.");
                    } else {
                        int value = InputUtil.readInt("Value to search: ");
                        reportSearch("Array", "Linear Search", value, SearchOps.linear(array.toArray(), value));
                    }
                }
                case 3 -> {
                    if (array.isEmpty()) {
                        System.out.println("Array is empty. Add data using the Array menu or option 1.");
                    } else {
                        sortIfNeeded();
                        int value = InputUtil.readInt("Value to search: ");
                        reportSearch("Array", "Binary Search", value, SearchOps.binary(array.toArray(), value));
                    }
                }
                case 4 -> {
                    if (array.isEmpty()) {
                        System.out.println("Array is empty. Add data using the Array menu or option 1.");
                    } else {
                        sortIfNeeded();
                        int value = InputUtil.readInt("Value to search: ");
                        int[] data = array.toArray();
                        SearchOps.Result linear = SearchOps.linear(data, value);
                        SearchOps.Result binary = SearchOps.binary(data, value);
                        System.out.println("\n=============================================");
                        System.out.println("SEARCH COMPARISON (array size " + data.length + ", target " + value + ")");
                        System.out.println("=============================================");
                        System.out.printf("%-16s %-12s %10s %14s%n", "Algorithm", "Result", "Steps", "Time (us)");
                        printSearchRow("Linear Search", linear);
                        printSearchRow("Binary Search", binary);
                        ResultLog.add(String.format("Search Comparison | target=%d | n=%d | linear steps=%d | binary steps=%d",
                                value, data.length, linear.steps, binary.steps));
                    }
                }
                default -> back = true;
            }
        }
    }

    private static void sortIfNeeded() {
        if (!array.isSorted()) {
            array.sort();
            System.out.println("Array was not sorted, so it has been sorted for binary search.");
        }
    }

    private static void printSearchRow(String algorithm, SearchOps.Result result) {
        String outcome = result.found() ? "index " + result.index : "not found";
        System.out.printf("%-16s %-12s %10d %14.2f%n", algorithm, outcome, result.steps, result.nanos / 1000.0);
    }

    private static void reportSearch(String structure, String algorithm, int value, SearchOps.Result result) {
        String outcome = result.found() ? "found at index " + result.index : "not found";
        System.out.println(algorithm + ": value " + value + " " + outcome + " (steps: " + result.steps
                + ", time: " + String.format("%.2f", result.nanos / 1000.0) + " us)");
        ResultLog.add(structure + " | " + algorithm + " | target=" + value + " | " + outcome + " | steps="
                + result.steps);
    }

    private static void graphMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Find Shortest Path (BFS Search)");
            System.out.println("7. Load Sample Graph");
            System.out.println("8. Return to Main Menu");
            int choice = InputUtil.readInt("Enter your choice: ", 1, 8);
            try {
                switch (choice) {
                    case 1 -> {
                        String vertex = InputUtil.readLabel("Vertex name: ");
                        System.out.println(graph.addVertex(vertex) ? "Vertex added." : "Vertex already exists.");
                    }
                    case 2 -> {
                        if (graph.vertexCount() < 2) {
                            System.out.println("Add at least two vertices first.");
                        } else {
                            String a = InputUtil.readLabel("From vertex: ");
                            String b = InputUtil.readLabel("To vertex: ");
                            System.out.println(graph.addEdge(a, b) ? "Edge added." : "Edge already exists.");
                        }
                    }
                    case 3 -> graph.display();
                    case 4 -> traverse("BFS", true);
                    case 5 -> traverse("DFS", false);
                    case 6 -> {
                        if (graph.isEmpty()) {
                            System.out.println("Graph is empty.");
                        } else {
                            String from = InputUtil.readLabel("Start vertex: ");
                            String to = InputUtil.readLabel("Target vertex: ");
                            List<String> path = graph.shortestPath(from, to);
                            if (path.isEmpty()) {
                                System.out.println("No path exists between " + from + " and " + to + ".");
                            } else {
                                System.out.println("Shortest path: " + String.join(" -> ", path)
                                        + " (edges: " + (path.size() - 1) + ")");
                                ResultLog.add("Graph | Shortest path " + from + " to " + to + " | "
                                        + String.join(" -> ", path));
                            }
                        }
                    }
                    case 7 -> {
                        graph.loadSample();
                        System.out.println("Sample graph loaded.");
                        graph.display();
                    }
                    default -> back = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void traverse(String name, boolean breadthFirst) {
        if (graph.isEmpty()) {
            System.out.println("Graph is empty. Add vertices first.");
            return;
        }
        String start = InputUtil.readLabel("Start vertex: ");
        Graph.TraversalResult result = breadthFirst ? graph.bfs(start) : graph.dfs(start);
        System.out.println(name + " order: " + String.join(" -> ", result.order));
        System.out.println("Vertices visited: " + result.order.size() + ", steps: " + result.steps
                + ", time: " + String.format("%.2f", result.nanos / 1000.0) + " us");
        ResultLog.add("Graph | " + name + " from " + start + " | " + String.join(" -> ", result.order)
                + " | steps=" + result.steps);
    }

    private static void performanceMenu() {
        int n = InputUtil.readInt("Input size n (10-20000): ", 10, 20000);
        PerformanceAnalyzer.run(n);
    }

    private static void displayAllResults() {
        System.out.println("\n=============================================");
        System.out.println("CURRENT DATA STRUCTURE CONTENTS");
        System.out.println("=============================================");
        System.out.print("Array: ");
        array.display();
        stack.display();
        queue.display();
        linkedList.display();
        graph.display();
        System.out.println("\n=============================================");
        System.out.println("RECORDED RESULTS");
        System.out.println("=============================================");
        ResultLog.displayAll();
    }
}
