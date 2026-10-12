import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Graph {
    public static class TraversalResult {
        public final List<String> order;
        public final long steps;
        public final long nanos;

        public TraversalResult(List<String> order, long steps, long nanos) {
            this.order = order;
            this.steps = steps;
            this.nanos = nanos;
        }
    }

    private final Map<String, List<String>> adjacency = new LinkedHashMap<>();

    public boolean isEmpty() {
        return adjacency.isEmpty();
    }

    public boolean hasVertex(String vertex) {
        return adjacency.containsKey(vertex);
    }

    public int vertexCount() {
        return adjacency.size();
    }

    public int edgeCount() {
        int total = 0;
        for (List<String> neighbours : adjacency.values()) {
            total += neighbours.size();
        }
        return total / 2;
    }

    public void clear() {
        adjacency.clear();
    }

    public boolean addVertex(String vertex) {
        if (adjacency.containsKey(vertex)) {
            return false;
        }
        adjacency.put(vertex, new ArrayList<>());
        return true;
    }

    public boolean addEdge(String a, String b) {
        if (!adjacency.containsKey(a) || !adjacency.containsKey(b)) {
            throw new IllegalArgumentException("Both vertices must exist before adding an edge.");
        }
        if (a.equals(b)) {
            throw new IllegalArgumentException("Self-loops are not allowed.");
        }
        if (adjacency.get(a).contains(b)) {
            return false;
        }
        adjacency.get(a).add(b);
        adjacency.get(b).add(a);
        return true;
    }

    public void display() {
        if (adjacency.isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }
        System.out.println("Adjacency List (vertices: " + vertexCount() + ", edges: " + edgeCount() + ")");
        for (Map.Entry<String, List<String>> entry : adjacency.entrySet()) {
            String neighbours = entry.getValue().isEmpty() ? "(none)" : String.join(", ", entry.getValue());
            System.out.println("  " + entry.getKey() + " -> " + neighbours);
        }
    }

    public TraversalResult bfs(String start) {
        if (!adjacency.containsKey(start)) {
            throw new IllegalArgumentException("Vertex '" + start + "' does not exist.");
        }
        long begin = System.nanoTime();
        long steps = 0;
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            steps++;
            order.add(current);
            for (String neighbour : adjacency.get(current)) {
                steps++;
                if (visited.add(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }
        return new TraversalResult(order, steps, System.nanoTime() - begin);
    }

    public TraversalResult dfs(String start) {
        if (!adjacency.containsKey(start)) {
            throw new IllegalArgumentException("Vertex '" + start + "' does not exist.");
        }
        long begin = System.nanoTime();
        long steps = 0;
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (!visited.add(current)) {
                continue;
            }
            steps++;
            order.add(current);
            List<String> neighbours = adjacency.get(current);
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                steps++;
                if (!visited.contains(neighbours.get(i))) {
                    stack.push(neighbours.get(i));
                }
            }
        }
        return new TraversalResult(order, steps, System.nanoTime() - begin);
    }

    public List<String> shortestPath(String from, String to) {
        if (!adjacency.containsKey(from) || !adjacency.containsKey(to)) {
            throw new IllegalArgumentException("Both vertices must exist.");
        }
        Map<String, String> parent = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        parent.put(from, null);
        queue.add(from);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(to)) {
                break;
            }
            for (String neighbour : adjacency.get(current)) {
                if (!parent.containsKey(neighbour)) {
                    parent.put(neighbour, current);
                    queue.add(neighbour);
                }
            }
        }
        List<String> path = new ArrayList<>();
        if (!parent.containsKey(to)) {
            return path;
        }
        for (String at = to; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public void loadSample() {
        clear();
        String[] names = {"A", "B", "C", "D", "E", "F", "G"};
        for (String name : names) {
            addVertex(name);
        }
        addEdge("A", "B");
        addEdge("A", "C");
        addEdge("B", "D");
        addEdge("B", "E");
        addEdge("C", "F");
        addEdge("E", "F");
        addEdge("F", "G");
    }

    public static Graph generateRandom(int vertices, long seed) {
        Graph graph = new Graph();
        Random random = new Random(seed);
        for (int i = 0; i < vertices; i++) {
            graph.addVertex("V" + i);
        }
        for (int i = 1; i < vertices; i++) {
            graph.addEdge("V" + i, "V" + random.nextInt(i));
        }
        for (int i = 0; i < vertices; i++) {
            int a = random.nextInt(vertices);
            int b = random.nextInt(vertices);
            if (a != b) {
                graph.addEdge("V" + a, "V" + b);
            }
        }
        return graph;
    }
}
