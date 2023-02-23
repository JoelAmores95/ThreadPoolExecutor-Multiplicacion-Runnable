public class Multipliacion implements Runnable {

    private int num1;
    private int num2;

    public Multipliacion(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public void run() {
        // Runnable no devuelve nada, puede mostrar por pantalla
        System.out.println("Resultado " + this.num1  +" * " + this.num2 +" = " + this.num1*this.num2);
    }
    
}
