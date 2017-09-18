package br.com.unisinos.lab2.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ArquivoUtils {
	public static void main(String[] args) {
		// Cuidar com o charset (UTF-8, etc.)
		ArquivoUtils.mostrar1(new File("c:/temp/ft03.txt"));
		ArquivoUtils.mostrar2(new File("c:/temp/ft03.txt"));
		ArquivoUtils.mostrar3("c:/temp/ft03.txt");
	}

	public static void mostrar1(File file) {
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	    	String linha = null;
	    	while ((linha = br.readLine()) != null) {
	    		System.out.println(linha);
	    	}
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	public static void mostrar2(File file) {
		try (Scanner in = new Scanner(new FileReader(file))) {
			while (in.hasNextLine()) {
			    System.out.println(in.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void mostrar3(String file) {
		try {
			for (String line : Files.readAllLines(Paths.get(file))) {
			    System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
