import java.util.Arrays;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;

import implement.SortingAlgorithmsImpl;
import intefaces.SortingAlgorithmsService;
import utils.Utiles;

public class Main {
    public static void main(String[] args) {
        /* Variable para medir tiempo de ejecución */
        long inicio, fin;

        String tipoOrdenador = ""; // Qué ordenador voy a usar
        String fuente = ""; // Args si leo de argumentos, Props si leo de Propiedades
        int[] arregloInput = {}; // Almaceno aquí la entrada
        String[] arregloInputProps = {};
        Properties props = new Properties();
        int[] arrayClone = {};
        String algoritmo = "counting"; // tipo de ordenación por si no se escogió en el archivo de configuración

        // Cargar las propiedades desde el archivo configuration.properties
        try {
            props.load(new FileInputStream(new File("./config/configuration.properties")));

            tipoOrdenador = props.getProperty("ORDENADOR").isEmpty() ? algoritmo.toLowerCase() : props.getProperty("ORDENADOR").toLowerCase();
            fuente = props.getProperty("FUENTE");
            arregloInputProps = props.getProperty("INPUT").split(" ");

            System.out.println("El ordenador escogido es: " + tipoOrdenador);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (args.length > 0) {
            // Si se proporcionan argumentos en la línea de comandos, utilizarlos como fuente
            fuente = "Args";
            arregloInput = Utiles.ValidarEntradaInt(args);
        } else if (fuente.equals("Props")) {
            arregloInput = Utiles.ValidarEntradaInt(arregloInputProps);
        }

        arrayClone = Arrays.copyOf(arregloInput, arregloInput.length);
        Utiles.imprimir(arrayClone);
        SortingAlgorithmsService miOrdenador = new SortingAlgorithmsImpl();

        if (arrayClone != null) {
            int[] sortedArray = arrayClone.clone();

            inicio = System.nanoTime();
            switch (tipoOrdenador) {
                case "bubble" -> {
                    System.out.println("Entro al Bubble");
                    sortedArray = miOrdenador.bubbleSort(sortedArray);
                }
                case "counting" -> {
                    System.out.println("Entro al counting");
                    sortedArray = miOrdenador.countingSort(sortedArray);
                }
                case "bucket" -> {
                    System.out.println("Entro al Bucket");
                    sortedArray = miOrdenador.bucketSort(sortedArray, getMaxValue(sortedArray));
                }
                default -> {
                    System.out.println("Se ejecuta el metodo de ordenación del Sistema");
                    sortedArray = miOrdenador.defaultArr(sortedArray);
                }
            }
            fin = System.nanoTime();

            long tiempoEjecucion = fin - inicio;
            System.out.println("Tiempo de ejecución: " + tiempoEjecucion + " nanosegundos");

            System.out.println("Resultado ordenado:");
            Utiles.imprimir(sortedArray);
        }
    }

    public static int getMaxValue(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}
