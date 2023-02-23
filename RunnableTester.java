import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableTester {

    private static final int NUM_HILOS = 7;
    private static final int NUM_EJECUCIONES = 10;
    private static final int MIN_RANGO_ALEATORIO = 0;
    private static final int MAX_RANGO_ALEATORIO = 10;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Creamos el executor con 7 hilos y lo cerramos automáticamente al finalizar su
        // uso
        ExecutorService executor = Executors.newFixedThreadPool(NUM_HILOS);
        // Creamos una lista de objetos Future que contendrán los resultados de las
        // tareas
        List<Future<Integer>> listaResultados = new ArrayList<>();
        // Creamos una instancia de Random para generar los números aleatorios
        Random random = new Random();

        for (int i = 0; i < NUM_EJECUCIONES; i++) {
            // Generamos dos números aleatorios dentro del rango establecido
            int num1 = random.nextInt(MAX_RANGO_ALEATORIO - MIN_RANGO_ALEATORIO + 1) + MIN_RANGO_ALEATORIO;
            int num2 = random.nextInt(MAX_RANGO_ALEATORIO - MIN_RANGO_ALEATORIO + 1) + MIN_RANGO_ALEATORIO;

            // Creamos una lambda que multiplique los dos números y la pasamos al executor
            // para que la ejecute en un hilo
            Future<Integer> resultado = executor.submit(() -> num1 * num2);
            // Añadimos el objeto Future a la lista de resultados
            listaResultados.add(resultado);
        }

        // Esperamos a que finalicen todas las tareas
        executor.shutdown();
        // Mostramos los resultados en la consola
        mostrarResultados(listaResultados);
    }

    private static void mostrarResultados(List<Future<Integer>> listaResultados)
            throws InterruptedException, ExecutionException {
        for (int i = 0; i < listaResultados.size(); i++) {
            Future<Integer> resultado = listaResultados.get(i);
            // Obtenemos el resultado de la tarea de forma asíncrona
            int multiplicacion = resultado.get();
            // Imprimimos el resultado en la consola
            System.out.println("Resultado tarea " + (i + 1) + ": " + multiplicacion);
        }
    }
}
