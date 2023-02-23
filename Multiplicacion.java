/**
 * Clase que representa una operación de multiplicación entre dos números
 * enteros.
 */
public class Multiplicacion implements Runnable {

    private int factor1;
    private int factor2;

    /**
     * Crea una nueva operación de multiplicación con los dos factores dados.
     *
     * @param factor1 el primer factor de la multiplicación
     * @param factor2 el segundo factor de la multiplicación
     * @throws IllegalArgumentException si alguno de los factores no es un número
     *                                  entero
     */
    public Multiplicacion(int factor1, int factor2) {
        if (!esEntero(factor1) || !esEntero(factor2)) {
            throw new IllegalArgumentException("Ambos factores deben ser números enteros");
        }
        this.factor1 = factor1;
        this.factor2 = factor2;
    }

    /**
     * Calcula el resultado de la multiplicación y lo muestra por pantalla.
     */
    @Override
    public void run() {
        int resultado = factor1 * factor2;
        System.out.println("Resultado " + factor1 + " * " + factor2 + " = " + resultado);
    }

    /**
     * Devuelve una representación en cadena de la operación de multiplicación.
     *
     * @return una cadena que representa la operación de multiplicación
     */
    @Override
    public String toString() {
        return factor1 + " * " + factor2;
    }

    /**
     * Verifica si el número dado es un número entero.
     *
     * @param numero el número a verificar
     * @return true si el número es un entero, false de lo contrario
     */
    private boolean esEntero(int numero) {
        return numero == (int) numero;
    }
}
