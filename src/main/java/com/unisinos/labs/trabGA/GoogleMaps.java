package com.unisinos.labs.trabGA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GoogleMaps implements IGoogleMaps {
    private int[][] matrix;
    private int geneSize;
    private Chromosome[] shortestChromosomes = new Chromosome[10];

    public void loadMatrix(File file) throws IOException {
        //tenta leitura de file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int counter = 0;
            //enquanto linha lida não for nula
            while ((line = br.readLine()) != null) {
                //caso primeira linha, alimenta geneSize
                if (counter == 0) {
                    geneSize = Integer.parseInt(line);
                }
                else {
                    //quebra linha em array e registra na matrix
                    String splitLine[] = line.split(";");
                    if(counter == 1) {
                        //caso primeira linha da matrix, instancia o objeto
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

        //declara variáveis de auxílio
        //level responsável por registrar as posições válidas
        //levelIndex o contador lógico de level
        //geneIndex o contador lógico do array genes

        int[] level = new int[matrix.length];
        int levelIndex, geneIndex = 0;

        //inicializa posição inicial e final com as posições fixas (A e o maior)
        genes[0] = 0;
        genes[geneSize - 1] = matrix.length - 1;

        //i não precisa ser incrementado pois é somado a levelIndex
        for(int i = 0; i < matrix.length - 2;){
            //levelIndex é zerado a cada ciclo
            levelIndex = 0;
            for(int j = i; j < matrix.length; j++){
                //percorre toda a linha a partir do ponto inicial. Caso diferente de zero, adiciona ao level
                if(matrix[i][j] != 0){
                    level[levelIndex++] = j;
                }
            }

            //adiciona ao genes uma das posições do array level
            genes[++geneIndex] = level[(int)(Math.random() * levelIndex)];

            //adiciona levelIndex ao i
            i += levelIndex;
        }

        //calcula fitness
        fitness = distance(genes);
        return new Chromosome(genes, fitness);
    }

    public void insertOrderedChromosome(Chromosome chromosome) {
        for(int i = 0; (i<shortestChromosomes.length); i++){
            //caso o chromosome a ser inserido for igual a qualquer chromosome do array, o processo é encerrado
            if(shortestChromosomes[i] != null && shortestChromosomes[i].equals(chromosome)) {
                break;
            } else {
                //caso o chormosome for menor em fitness que o do array, é feita substituição
                if (shortestChromosomes[i] == null || shortestChromosomes[i].getFitness() > chromosome.getFitness()) {
                    Chromosome aux = shortestChromosomes[i];
                    shortestChromosomes[i] = chromosome;
                    chromosome = aux;
                }
            }
        }
    }

    public int distance(int[] genes) {
        int fitness = 0;
        for(int i = 1; i < genes.length; i++){
            //adiciona o valor da distância do gene anterior ao atual
            fitness += matrix[genes[i-1]][genes[i]];
        }
        return fitness;
    }

    public void evolve() {
        Chromosome addingChromosome;
        //gera 100 chromosome e insere-os no array de melhores ordenadamente
        for(int i = 0; i < 100; i++){
            addingChromosome = generateChromosome();
            insertOrderedChromosome(addingChromosome);
        }
    }

    public void print() {
        String printingString = "";
        int[] genes;

        for(int i =0; i < shortestChromosomes.length; i++){
            genes = shortestChromosomes[i].getGenes();
            printingString += "Short path -> " + shortestChromosomes[i].getFitness();

            for(int j = 0; j < genes.length; j++){
                printingString += " " + getLetterIndex(genes[j]);
            }

            System.out.println(printingString);
            printingString = "";
        }
    }

    private String getLetterIndex(int index){
        //converte o index para o caracter
        return index >= 0 && index < 27 ? String.valueOf((char)(index + 65)) : null;
    }
}
