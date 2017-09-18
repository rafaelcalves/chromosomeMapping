package com.unisinos.labs.trabGA;

public interface IChromosome {

    int[] getGenes();

    void setGenes(int[] genes);

    int getFitness();

    void setFitness(int fitness);

    String toString();
}
