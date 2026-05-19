import com.sun.source.tree.Tree;

import java.io.PrintWriter;
import java.util.*;

public class DijkstraAlgorithm {
    public static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static class NodeDist implements Comparable<NodeDist> {
        int vertex, dist;
        NodeDist(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
        @Override
        public int compareTo(NodeDist other) {
            return Integer.compare(this.dist, other.dist);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            NodeDist nodeDist = (NodeDist) o;
            return vertex == nodeDist.vertex && dist == nodeDist.dist;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex, dist);
        }
    }

    public static int[] dijkstra(List<List<Edge>> adj, int source, int[] iterations) {
        int V = adj.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        TreeSet<NodeDist> set = new TreeSet<>();
        set.add(new NodeDist(source, 0));
        iterations[0] = 0;

        while (!set.isEmpty()) {
            NodeDist current = set.pollFirst();
            int u = current.vertex;
            for (Edge e : adj.get(u)) {
                iterations[0]++;
                int v = e.to;
                int newDist = dist[u] + e.weight;
                if (newDist < dist[v]) {
                    set.remove(new NodeDist(v, dist[v]));
                    dist[v] = newDist;
                    set.add(new NodeDist(v, newDist));
                }
            }
        }
        return dist;
    }

    public static List<List<Edge>> generateGraph(int V, double avgDegree) {
        List<List<Edge>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        Random rand = new Random(42);

        for (int i = 0; i < V - 1; i++) {
            adj.get(i).add(new Edge(i + 1, 1));
        }

        int extraEdges = Math.max(0, (int) avgDegree - 1);
        for (int i = 0; i < V; i++) {
            for (int k = 0; k < extraEdges; k++) {
                int target;
                do {
                    target = rand.nextInt(V);
                } while (target == 1 || (i < V - 1 && target == i + 1));
                int weight = 1 + rand.nextInt(10);
                adj.get(i).add(new Edge(target, weight));
            }
        }
        return adj;
    }

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter("dijkstra_results.csv");
        out.println("V,E,Time_ns,Iterations");

        int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000,
                1200, 1400, 1600, 1800, 2000, 2500, 3000, 3500, 4000,
                4500, 5000, 6000, 7000, 8000, 9000, 10000};

        double avgDegree = 5.0;

        for (int V : sizes) {
            for (int rep = 0; rep < 3; rep++) {
                List<List<Edge>> graph = generateGraph(V, avgDegree);
                int E = graph.stream().mapToInt(List::size).sum();

                int[] iterCounter = new int[1];

                long start = System.nanoTime();
                dijkstra(graph, 0, iterCounter);
                long end = System.nanoTime();

                long timeNs = end - start;
                out.printf("%d,%d,%d,%d%n", V, E, timeNs, iterCounter[0]);
            }
        }
        out.close();
        System.out.println("Данные сохранены в dijkstra_results.csv");
    }
}