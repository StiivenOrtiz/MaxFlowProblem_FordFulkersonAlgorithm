import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphOperations {
    /**
     * La función imprime en la consola la matriz de adyacencia de un grafo, donde cada fila representa un nodo y cada
     * columna representa las conexiones que tiene ese nodo con otros nodos. Se utilizan tabulaciones y espacios para
     * que la impresión se vea ordenada y legible.
     * @param graph
     */
    public static void printGraph(ArrayList<ArrayList<Integer>> graph) {
        // Imprime una nueva línea en la consola para dejar un espacio antes de imprimir la matriz de adyacencia.
        System.out.println("\n");
        // Usa la clase IntStream de Java 8 para generar un rango de números enteros, desde 1 hasta el tamaño de la
        // lista de adyacencia, y los imprime en la consola con una tabulación antes y después de cada número.
        IntStream.rangeClosed(1, graph.size()).forEach(i -> System.out.print("\t" + i + "\t"));
        // Imprime dos nuevas líneas en la consola para dejar un espacio antes de la siguiente línea de impresión.
        System.out.print("\n\n");
        // Usa la clase IntStream de Java 8 para generar un rango de números enteros, desde 0 hasta el tamaño
        // de la lista de adyacencia menos uno.
        // Luego, para cada número en el rango, imprime el número en la consola con una tabulación,
        // seguido de los elementos de la lista de adyacencia correspondiente,
        // cada uno con una tabulación antes y después del número. Finalmente, se imprime una nueva línea en
        // la consola después de imprimir todos los elementos de la lista de adyacencia correspondiente.
        IntStream.range(0, graph.size()).forEach(i ->
        {
            System.out.print(i + 1);
            graph.get(i).forEach(j -> System.out.print("\t" + j + "\t"));
            System.out.print("\n");
        });
        // Imprime una nueva línea en la consola para dejar un espacio después de la impresión de la
        // matriz de adyacencia.
        System.out.println("\n");
    }

    /**
     * La función crea una matriz de flujo cero con dimensiones n x n y la llena con 0 en cada entrada.
     * @param zeroGraph
     * @param n
     */
    public static void fillZeroGraph(ArrayList<ArrayList<Integer>> zeroGraph, int n) {
        zeroGraph.clear();
        // Crea un flujo de enteros en el rango de 0 a n-1 y itera a través de cada elemento
        IntStream.range(0, n).forEach(i -> {
            // Crea una fila de tamaño n con todos los valores establecidos en 0 utilizando programación funcional
            ArrayList<Integer> row = IntStream.range(0, n)
                    .map(j -> 0) // Establece cada valor en 0
                    .boxed() // Convierte los valores primitivos int a Integer
                    .collect(Collectors.toCollection(ArrayList::new)); // Recopila los valores en una matriz ArrayList
            // Agrega la fila a la matriz zeroGraph
            zeroGraph.add(row);
        });
    }

    /**
     * Función para Actualizar la capacidad de las aristas en un grafo de flujo cero en un camino dado, estableciendo
     * la capacidad de cada arista en el flujo proporcionado.
     * @param zeroGraph
     * @param path
     * @param flow
     */
    public static void fillFlowZeroGraph(ArrayList<ArrayList<Integer>> zeroGraph, ArrayList<Integer> path, int flow) {
        // Recorre el camino de nodos y actualiza la capacidad de la arista entre cada par de nodos en el camino
        for (int i = 0; i < path.size() - 1; i++)
            // Actualiza la capacidad de la arista entre los nodos actuales y siguientes en el camino con el flujo dado
            zeroGraph.get(path.get(i)).set(path.get(i + 1), flow);
    }

    /**
     * Función para la resta la matriz de adyacencia del grafo de flujo cero a la matriz de adyacencia del grafo
     * original para actualizar la capacidad de cada arista en el grafo original.
     * @param graph
     * @param zeroGraph
     */
    public static void graphSubtraction(ArrayList<ArrayList<Integer>> graph, ArrayList<ArrayList<Integer>> zeroGraph) {
        // Recorre la matriz de adyacencia del grafo original y resta la matriz de adyacencia del grafo de flujo
        // cero de cada entrada
        for (int i = 0; i < graph.size(); i++)
            for (int j = 0; j < graph.size(); j++)
                // Actualiza la capacidad de la arista entre los nodos i y j en el grafo original con la nueva
                // capacidad
                graph.get(i).set(j, graph.get(i).get(j) - zeroGraph.get(i).get(j));
    }
}
