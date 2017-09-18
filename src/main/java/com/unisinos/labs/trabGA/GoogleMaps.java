package com.unisinos.labs.trabGA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class GoogleMaps implements IGoogleMaps {
    private int[][] matrix;
    private int geneSize;
    private Chromosome[] top10Chromossomes = new Chromosome[10];

    public void loadMatrix(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                if (counter == 0) {
                    geneSize = Integer.parseInt(line);
                }
                else {
                    String splitLine[] = line.split(";");
                    if(counter == 1) {
                        matrix = new int[splitLine.length][splitLine.length];
                    }
                    for (int i = 0; i<splitLine.length; i++){
                        matrix[counter - 1][i] = Integer.parseInt(splitLine[i]);
                    }
                }
                ++ counter;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Chromosome generateChromosome() {
        int[] genes = new int[geneSize];
        int fitness;

        int[] level = new int[matrix.length];
        int levelIndex, geneIndex = 0;

        genes[0] = 0;
        genes[geneSize - 1] = matrix.length - 1;
        for(int i = 0; i < matrix.length - 2;){

            levelIndex = 0;
            for(int j = i; j < matrix.length; j++){
                if(matrix[i][j] != 0){
                    level[levelIndex++] = j;
                }
            }

            genes[++geneIndex] = level[(int)(Math.random() * levelIndex)];

            i += levelIndex;
        }

        fitness = distance(genes);
        return new Chromosome(genes, fitness);
    }

    public void insertOrderedChromosome(Chromosome chromosome) {
        for(int i = 0; (i<top10Chromossomes.length); i++){
            if(top10Chromossomes[i] != null && top10Chromossomes[i].equals(chromosome)) {
                break;
            } else {
                if (top10Chromossomes[i] == null || top10Chromossomes[i].getFitness() > chromosome.getFitness()) {
                    Chromosome aux = top10Chromossomes[i];
                    top10Chromossomes[i] = chromosome;
                    chromosome = aux;
                }
            }
        }
    }

    public int distance(int[] genes) {
        int fitness = 0;
        for(int i = 1; i < genes.length; i++){
            fitness += matrix[genes[i-1]][genes[i]];
        }
        return fitness;
    }

    public void evolve() {
        Chromosome addingChromosome;
        for(int i = 0; i < 100; i++){
            addingChromosome = generateChromosome();
            insertOrderedChromosome(addingChromosome);
        }
    }

    public void print() {
        String printingString = "";
        int[] genes;
        for(int i =0; i < top10Chromossomes.length; i++){
            genes = top10Chromossomes[i].getGenes();
            printingString += "Short path -> " + top10Chromossomes[i].getFitness();
            for(int j = 0; j < genes.length; j++){
                printingString += " " + getLetterIndex(genes[j]);
            }
            System.out.println(printingString);
            printingString = "";
        }
    }

    private String getLetterIndex(int index){
        return index >= 0 && index < 27 ? String.valueOf((char)(index + 65)) : null;
    }
}
