public class PerformanceAnalyzer {
    private static void printRow(String operation, String algorithm, long steps, long nanos, String complexity) {
        System.out.printf("%-26s %-16s %10d %14.2f   %s%n", operation, algorithm, steps, nanos / 1000.0, complexity);
        ResultLog.add(String.format("Performance | %s | %s | steps=%d | time=%.2f us | %s",
                operation, algorithm, steps, nanos / 1000.0, complexity));
    }

    public static void run(int n) {
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = i * 2;
        }
        int missingTarget = n * 2 + 1;

        SearchOps.Result linear = SearchOps.linear(sorted, missingTarget);
        SearchOps.Result binary = SearchOps.binary(sorted, missingTarget);

        DynamicArray array = new DynamicArray();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            array.insertAt(0, i);
        }
        long arrayNanos = System.nanoTime() - start;
        long arraySteps = array.getSteps();

        LinkedListDS list = new LinkedListDS();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            list.insertAtHead(i);
        }
        long listNanos = System.nanoTime() - start;
        long listSteps = list.getSteps();

        Graph graph = Graph.generateRandom(n, 42L);
        Graph.TraversalResult bfs = graph.bfs("V0");
        Graph.TraversalResult dfs = graph.dfs("V0");

        System.out.println("=====================================================================================");
        System.out.println("PERFORMANCE COMPARISON  (n = " + n + ", graph: " + graph.vertexCount() + " vertices, "
                + graph.edgeCount() + " edges)");
        System.out.println("=====================================================================================");
        System.out.printf("%-26s %-16s %10s %14s   %s%n", "Operation", "Algorithm", "Steps", "Time (us)", "Complexity");
        System.out.println("-------------------------------------------------------------------------------------");
        printRow("Search (absent target)", "Linear Search", linear.steps, linear.nanos, "O(n)");
        printRow("Search (absent target)", "Binary Search", binary.steps, binary.nanos, "O(log n)");
        printRow("Insert at front (n items)", "Array", arraySteps, arrayNanos, "O(n) each");
        printRow("Insert at front (n items)", "Linked List", listSteps, listNanos, "O(1) each");
        printRow("Graph Traversal", "BFS", bfs.steps, bfs.nanos, "O(V + E)");
        printRow("Graph Traversal", "DFS", dfs.steps, dfs.nanos, "O(V + E)");
        System.out.println("=====================================================================================");
        System.out.println("Note: step counts are exact; execution times vary slightly between runs.");
    }
}
