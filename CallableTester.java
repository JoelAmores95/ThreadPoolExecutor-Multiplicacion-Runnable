import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class CallableTester {

    public static final int NUM_HILOS = 7;
    public static final int NUM_EJECUCIONES = 10;

    public static void main(String[] args) throws InterruptedException {

        // Creo un executor con 7 hilos
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(NUM_HILOS);

        // Lista de Multiplicaciones
        List<Multipliacion> listaTareas = new ArrayList<Multipliacion>();

        // Lleno las multiplicaciones
        listaTareas = llenarLista(listaTareas);

        // Lista que guardara los resultados
        List<Future<Integer>> listaResultados;

        // Proceso los resultados
        listaResultados = ejecutor.invokeAll(listaTareas);

        // Paro el pool, es necesario
        ejecutor.shutdown();

        // Muestro resultados
        mostrarLista(listaResultados);

    }

    public static List<Multipliacion> llenarLista(List<Multipliacion> listaTareas) {

        for (int i = 0; i < NUM_EJECUCIONES; i++) {

            // Creo un objeto multiplicacion con numeros random entre 0 y 10
            Multipliacion operacion = new Multipliacion((int) (Math.random() * 10), (int) (Math.random() * 10));

            // Guardo el objeto en la Lista
            listaTareas.add(operacion);
        }

        return listaTareas;
    }

    public static void mostrarLista(List<Future<Integer>> listaResultados) {
        for (int i = 0; i < listaResultados.size(); i++) {
            Future<Integer> resultat = listaResultados.get(i);

            try {
                System.out.println("Resultado tarea " + (i + 1) + " es:" +
                        resultat.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
