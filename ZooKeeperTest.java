package test;

import haileyArnold.myZoo.com.Module05.CascadeProjects.windsurf_project.ZooKeeper;
import java.io.*;

public class ZooKeeperTest {
    public static void main(String[] args) throws IOException {
        testProcessAnimals();
    }

    private static void testProcessAnimals() throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper();
        
        // Process files from Downloads directory
        zooKeeper.loadAnimalNames("C:\\Users\\bella\\Downloads\\animalNames.txt");
        zooKeeper.processArrivingAnimals("C:\\Users\\bella\\Downloads\\arrivingAnimals.txt");
        
        // Generate output in the same directory
        String outputFile = "C:\\Users\\bella\\Downloads\\newAnimals.txt";
        zooKeeper.generateZooPopulation(outputFile);
        
        System.out.println("Zoo population report has been generated at: " + outputFile);
        
        // Display the contents of the generated file
        System.out.println("\nGenerated Output:");
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
