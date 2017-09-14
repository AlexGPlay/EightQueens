import java.util.Calendar;

public class MainClass {

	private static int size = 28;
	
	public static void main(String[] args) {
		System.out.println("Inicio de ejecución: " + Calendar.getInstance().getTime());
		System.out.println("Resolviendo tamaño " + size + "\n");
		long x = System.nanoTime();
		
		Tablero.solve(size);
		
		long y = System.nanoTime();
		System.out.printf("Tiempo usado para resolver: %f segundos\n", ((double)(y-x))*(Math.pow(10, -9)));
	}
	
}