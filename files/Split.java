package br.com.unisinos.lab2.utils;

public class Split {

	public static void main(String[] args) {
		// Vari�vel do tipo String com n�meros separados por ponto-v�rgula (similar ao trabalho)
		String linha = "0;1;2;3;0";
		
		// "Parser" da vari�vel linha usando o comando split (m�todo constante na classe String)
		// Retornar� um array com os n�meros (em String) separados
		String[] cols = linha.split(";");
		
		// Mostrando na tela
		for (int i = 0; i < cols.length; i++) {
			System.out.println(cols[i]);
		}
		
		// Converter String para Inteiro
		// Criando um array de inteiros com o mesmo tamanho do array de Strings
		int[] numeros = new int[cols.length];

		// Convertendo o conte�do do array de String para Inteiro. Ex.: Integer.parseInt("1") -> 1
		for (int i = 0; i < cols.length; i++) {
			numeros[i] = Integer.parseInt(cols[i]); 
		}
		
		// Mostrando na tela
		for (int i = 0; i < numeros.length; i++) {
			System.out.println(numeros[i]);
		}
	}

}
