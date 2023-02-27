import java.io.IOException;

public class Main {
	public static void main(String args[]) throws IOException {
		Matriz m1 = new Matriz();
		m1.CarregarMatriz();
		System.out.println("Maior valor possível dentro da matriz "+ m1.getTam() +"X"+ m1.getTam() +": "+m1.maiorConjMatriz());
	}
}
