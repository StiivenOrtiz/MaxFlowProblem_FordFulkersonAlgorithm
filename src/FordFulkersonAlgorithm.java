import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FordFulkersonAlgorithm {
    /**
     * Esta función calcula la capacidad mínima de las aristas en un camino dado de un grafo representado como una
     * lista de adyacencia.
     * @param graph
     * @param path
     * @return
     */
    private static int minCapacity(ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> path) {
        // Inicializa la capacidad mínima con la capacidad de la primera arista en el camino
        int capacity = graph.get(path.get(0)).get(path.get(1));

        // Recorre los nodos en el camino y busca la capacidad mínima
        for (int i = 0; i < path.size() - 1; i++) {
            // Obtiene la capacidad de la arista entre el nodo actual y el siguiente nodo en el camino
            int edgeCapacity = graph.get(path.get(i)).get(path.get(i + 1));
            // Si la capacidad de la arista es menor que la capacidad actual y mayor que cero, actualiza la capacidad mínima
            if ((edgeCapacity < capacity) && ((edgeCapacity) > 0)) {
                //System.out.println("MIN: " + edgeCapacity + " BY: "+ (path.get(i)+1) +" AND "+ (path.get(i+1)+1));
                capacity = edgeCapacity;
            }
        }
        // Devuelve la capacidad mínima calculada
        return capacity;
    }

    /**
     * Verifica si hay un camino desde el nodo de origen hasta el nodo de destino en un grafo dado utilizando BFS
     * y devuelve verdadero si existe un camino. Además, almacena los nodos visitados en la lista de rutas.
     * @param graph
     * @param path
     * @return
     */
    private static boolean hasPath(ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> path) {
        // Inicializa la lista de nodos visitados con el tamaño del grafo
        boolean[] visited = new boolean[graph.size()];

        // Inicializa una cola para almacenar los nodos que se visitarán
        Queue<Integer> queue = new LinkedList<>();

        // Marca el nodo inicial como visitado y agrégalo a la cola
        visited[0] = true;
        queue.offer(0);

        while (!queue.isEmpty()) {
            // Obtiene el siguiente nodo de la cola
            int current = queue.poll();

            // Agrega el nodo actual al camino seguido
            path.add(current);

            // Si el nodo actual es el nodo de destino, se ha encontrado un camino desde la fuente hasta el destino
            if (current == (graph.size() - 1)) {
                return true;
            }

            // Recorre los vecinos del nodo actual
            for (int i = 0; i < graph.get(current).size(); i++)
                // Si el vecino no ha sido visitado y la capacidad de la arista entre el nodo actual y el vecino es mayor que cero, marca el vecino como visitado y agrégalo a la cola
                if ((!visited[i]) && (graph.get(current).get(i) > 0)) {
                    visited[i] = true;
                    queue.offer(i);
                    break;
                }

        }
        // No se ha encontrado un camino desde la fuente hasta el destino
        return false;
    }

    /**
     * La función implementa el algoritmo de Ford-Fulkerson para encontrar el flujo máximo en un grafo de flujo.
     * @param graph
     */
    public static void execute(ArrayList<ArrayList<Integer>> graph) {
        // Inicialización de variables
        ArrayList<ArrayList<Integer>> zeroGraph = new ArrayList<>(); // Grafo que se utilizará para restar el residual
        ArrayList<Integer> path = new ArrayList<>(); // Inicialización del camino
        int flow = 0; // Declaración del flow que pasará por el grafo

        // Búsqueda de caminos aumentantes mientras existan
        while (hasPath(graph, path)) {
            // Crear un grafo de cero con el tamaño del grafo original
            GraphOperations.fillZeroGraph(zeroGraph, graph.size());
            // Determinar la capacidad mínima del camino aumentante encontrado
            int flowCurrent = minCapacity(graph, path);
            // Llenar el grafo de cero con el flujo actual del camino aumentante
            GraphOperations.fillFlowZeroGraph(zeroGraph, path, flowCurrent);

            // Imprimir el camino aumentante encontrado y su flujo
            System.out.println("Path and flow");
            path.forEach(node -> System.out.print((node + 1) + " "));
            System.out.println(" = " + flowCurrent);

            // Imprimir el grafo de cero
            System.out.println("\n\n\nZero Graph");
            GraphOperations.printGraph(zeroGraph);

            // Restar el grafo de cero del grafo original para obtener el grafo residual
            GraphOperations.graphSubtraction(graph, zeroGraph);

            // Imprimir el grafo residual
            System.out.println("Residual Graph");
            GraphOperations.printGraph(graph);

            // Limpiar el camino aumentante encontrado y actualizar el flujo total
            path.clear();
            flow += flowCurrent;
        }
        // Imprimir el flujo máximo encontrado
        System.out.println("The max flow is: " + flow);
    }
}
