import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphOperations {
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

    public static void fillFlowZeroGraph(ArrayList<ArrayList<Integer>> zeroGraph, ArrayList<Integer> path, int flow){
        for (int i = 0; i < path.size() - 1; i++)
            zeroGraph.get(path.get(i)).set(path.get(i + 1), flow);
    }

    public static void graphSubtraction(ArrayList<ArrayList<Integer>> graph, ArrayList<ArrayList<Integer>> zeroGraph){
        for (int i = 0; i < graph.size(); i++)
            for (int j = 0; j < graph.size(); j++)
                graph.get(i).set(j, graph.get(i).get(j) - zeroGraph.get(i).get(j));
    }
}
