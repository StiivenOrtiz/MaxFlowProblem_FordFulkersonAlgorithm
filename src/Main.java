import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String filePath = "data.csv";
        ArrayList<ArrayList<Integer>> graph = ReaderCSV.readGraph(filePath);
        GraphOperations.printGraph(graph);

        FordFulkersonAlgorithm.execute(graph);
    }
}