package com.unisinos.labs.trabGA;

import com.unisinos.labs.trabGA.exceptions.WrongChromosomeSizeException;
import com.unisinos.labs.trabGA.exceptions.WrongGeneIndexException;
import org.junit.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class GoogleMapsUnitTestsTry {
    public static IGoogleMaps googleMaps;

    @BeforeClass
    public static void initGoogleMaps(){
        googleMaps = new GoogleMaps();
        String path = System.getProperty("user.dir") + "/files/matrices/";
        File file = new File(path + "matrix6.csv");
        try {
            googleMaps.loadMatrix(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadMatrixGeneratingChromosome(){
        String path = System.getProperty("user.dir") + "/files/matrices/";
        File file = new File(path + "matrix6.csv");
        Chromosome chromosome;

        try {
            googleMaps.loadMatrix(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        chromosome = googleMaps.generateChromosome();
        int genes[] = chromosome.getGenes();
        for(int i = 0; i<genes.length; i++){
            try {
                int[] tests;
                switch (i) {
                    case 0:
                        tests = new int[]{0};
                        break;

                    case 1:
                        tests = new int[]{1, 2, 3};
                        break;

                    case 2:
                        tests = new int[]{4, 5, 6, 7};
                        break;

                    case 3:
                        tests = new int[]{8, 9, 10, 11};
                        break;

                    case 4:
                        tests = new int[]{12, 13, 14};
                        break;

                    case 5:
                        tests = new int[]{15};
                        break;

                    default:
                        throw new WrongChromosomeSizeException("genes[] bigger than 6");
                }

                boolean isGeneOk = false;

                for(int j = 0; j < tests.length; j++){
                    if(tests[j] == genes[i]) {
                        isGeneOk = true;
                        break;
                    }
                }

                if (!isGeneOk)
                    throw new WrongGeneIndexException("gene[" + i +"] inválido. -> " + genes[i]);

            } catch (WrongGeneIndexException | WrongChromosomeSizeException e){
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testDistance(){
        int[] genes = new int[]{0,2,6,9,12,15};
        assertEquals(9,googleMaps.distance(genes));
    }
}
