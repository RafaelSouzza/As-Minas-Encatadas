import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Matriz {
	int tamanho = 300;
	int matriz[][] = new int[tamanho][tamanho];

	public int getM(int l, int c) {
		return matriz[l][c];
	}

	public int getTam() {
		return matriz.length;
	}

	public void setM(int i, int j, int result) {
		matriz[i][j] = result;
	}

	public void CarregarMatriz() throws FileNotFoundException, IOException {
		FileReader arq = new FileReader("Matriz" + tamanho + ".txt");
		BufferedReader lerArq = new BufferedReader(arq);
		String linha = lerArq.readLine();

		linha = lerArq.readLine();

		int lin = 0;

		while (linha != null) {
			linha = linha.trim();
			linha = linha.replace("  ", ";");
			linha = linha.replace(" ", ";");
			linha = linha.replace(";;", ";");

			String[] a = linha.split(";");

			for (int coluna = 0; coluna < a.length; coluna++) {
				matriz[lin][coluna] = Integer.parseInt(a[coluna]);
			}

			lin = lin + 1;
			linha = lerArq.readLine();
		}

		arq.close();
	}

	public int maiorConjMatriz() {

		int mNew[][] = new int[getTam()][getTam()]; // Nova Matriz com a soma das linhas
		int somaNew = 0; // Recebe a soma da nova matriz
		int soma = 0; // Soma os valores da linha
		int l, c; // Linha e a coluna
		int maior = 0; // Variavel de comparação do maior valor
		int modL = 0; // Modificado da posição inicial da linha
		int modC = 0; // Modificado da posição inicial da coluna
		int compL = 0; // Controlador de quantidade de execuções da linha
		int compC = 0; // Controlador de quantidade de execuções da coluna
		int tamL; // Defini o tamanho maximo da linha
		int tamC; // Defini o tamanho maximo da coluna
		int nL = 0; // Controlar o tamanho maximo da matriz em linha
		int nC = 0; // Controlar o tamanho maximo da matriz em coluna
		int auxL = 0; // Comparação de quando é um retângulo, quadrado e sozinho
		int auxC = 0; // Comparação de quando é um retângulo, quadrado e sozinho

		while (modL < getTam()) {
			tamL = getTam() - nL;
			tamC = getTam() - nC;
			compL = 0;
			for (l = modL; compL < tamL; l++) {
				soma = 0;
				if (l == modL) {
					auxL = 0;
				}
				for (c = modC; compC < tamC; c++) {
					if (c == modC) {
						auxC = 0;
					}
					soma += getM(l, c);
					mNew[l][c] = soma;
					if (modC == 0 && modL == 0) { // Função de valor único
						if (getM(l, c) > maior) {
							maior = getM(l, c);
						}
					}
					if (auxC > auxL) { // Verificação se é um retângulo horizontal
						if (auxL != 0) {
							somaNew = mNew[l][c] + mNew[l - 1][c];
							mNew[l][c] = mNew[l][c] + mNew[l - 1][c];
							if (somaNew > maior) {
								maior = somaNew;
							}
						} else {
							somaNew = mNew[l][c];
							if (somaNew > maior) {
								maior = somaNew;
							}
						}
					}
					if (auxC == auxL) { // Verificação se é um quadrado
						if (auxL != 0) {
							somaNew = mNew[l][c] + mNew[l - 1][c];
							mNew[l][c] = mNew[l][c] + mNew[l - 1][c];
							if (somaNew > maior) {
								maior = somaNew;
							}
						}
					}
					if (auxC < auxL) { // Verificação se é um retângulo vertical
						if (auxL != 0) {
							somaNew = mNew[l][c] + mNew[l - 1][c];
							mNew[l][c] = mNew[l][c] + mNew[l - 1][c];
							if (somaNew > maior) {
								maior = somaNew;
							}
						}
					}
					auxC++;
					compC++;
				}
				compC = 0;
				compL++;
				auxL++;
			}
			nC++;
			if (modC == getTam()) {
				modC = 0;
				nL++;
				nC = 0;
				modL++;
			} else {
				modC++;
			}
		}
		return maior;
	}
}