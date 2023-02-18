import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FordFulkersonAlgorithm {
    private static int minCapacity(ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> path) {
        int capacity = graph.get(path.get(0)).get(path.get(1));

        for (int i = 0; i < path.size() - 1; i++)
            if ((graph.get(path.get(i)).get(path.get(i+1)) < capacity) && ((graph.get(path.get(i)).get(path.get(i+1))>0)))
                capacity = graph.get(i).get(i+1);
//ndwldkl
        return capacity;
    }

    private static boolean hasPath(ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> path) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();

        visited[0] = true;
        queue.offer(0);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            path.add(current); // Agregar el nodo actual al camino seguido

            if (current == (graph.size() - 1)) {
                return true; // Se encontró un camino desde la fuente al destino
            }

            for (int i = 0; i < graph.get(current).size(); i++) {
                if ((!visited[i]) && (graph.get(current).get(i) > 0)) {
                    visited[i] = true;
                    queue.offer(i);
                    break;
                }
            }
        }
        return false; // No hay camino desde la fuente al destino
    }

    public static void execute(ArrayList<ArrayList<Integer>> graph) {
        ArrayList<ArrayList<Integer>> zeroGraph = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        int flow = 0;

        while (hasPath(graph, path)) {
            GraphOperations.fillZeroGraph(zeroGraph, graph.size());
            int flowCurrent = minCapacity(graph, path);

            GraphOperations.fillFlowZeroGraph(zeroGraph, path, flowCurrent);

            System.out.println("Zero Graph");
            GraphOperations.printGraph(zeroGraph);

            GraphOperations.graphSubtraction(graph, zeroGraph);

            System.out.println("Residual Graph");
            GraphOperations.printGraph(graph);

            path.clear();
            flow+=flowCurrent;
        }

        System.out.println("The max flow is: " + flow);
    }
}
