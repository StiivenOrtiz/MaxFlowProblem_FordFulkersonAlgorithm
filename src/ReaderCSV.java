import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReaderCSV {
    public static ArrayList<ArrayList<Integer>> readGraph(String filePath) {
        // Crea un nuevo objeto ArrayList bidimensional para almacenar la matriz
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        // Abre el archivo en la ruta especificada y crea un objeto BufferedReader para leerlo
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Lee cada línea del archivo CSV
            while ((line = br.readLine()) != null) {
                // Divide la línea en un arreglo de cadenas usando el delimitador ","
                String[] values = line.split(",");

                // Crea un nuevo objeto ArrayList para almacenar una fila de la matriz
                ArrayList<Integer> row = new ArrayList<>();

                // Itera sobre cada valor en la línea y agrega el valor como un entero a la fila
                for (String value : values)
                    row.add(Integer.parseInt(value));

                // Agrega la fila a la matriz
                graph.add(row);
            }
        } catch (IOException e) {
            // Si hay un error al leer el archivo, lanza una excepción RuntimeException
            throw new RuntimeException(e);
        }

        // Devuelve la matriz
        return graph;
    }

}
