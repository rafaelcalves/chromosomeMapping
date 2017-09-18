package com.unisinos.labs.trabGA;

import java.io.File;
import java.io.IOException;

public class GoogleMapsTest {
    public static void main(String[] args) {
        IGoogleMaps googleMaps = new GoogleMaps();
        try {
            String path = System.getProperty("user.dir") + "/files/matrices/";
            googleMaps.loadMatrix(new File(path + "matrix6.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        googleMaps.evolve();
        googleMaps.print();
    }
}