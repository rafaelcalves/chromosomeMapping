package com.unisinos.labs.trabGA;

import java.util.Arrays;

public class Chromosome implements IChromosome{
    private int[] genes;
    private int fitness;

    public Chromosome(int[] genes, int fitness) {
        this.genes = genes;
        this.fitness = fitness;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public String toString() {
        return "Chromosome{" +
                "genes=" + Arrays.toString(genes) +
                ", fitness=" + fitness +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chromosome)) return false;

        Chromosome that = (Chromosome) o;

        if (fitness != that.fitness) return false;
        return Arrays.equals(genes, that.genes);
    }
}
