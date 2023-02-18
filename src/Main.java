import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Se establece la ruta del archivo a leer
        // String filePath = "data.csv";
        String filePath = "test_algorithm.csv";
        // Se lee el grafo desde el archivo CSV y se almacena en la variable 'graph'
        ArrayList<ArrayList<Integer>> graph = ReaderCSV.readGraph(filePath);
        // Se imprime el grafo inicial en la consola
        System.out.println("\nInital Graph");
        GraphOperations.printGraph(graph);
        // Se ejecuta el algoritmo de Ford-Fulkerson sobre el grafo leído
        FordFulkersonAlgorithm.execute(graph);
    }
}