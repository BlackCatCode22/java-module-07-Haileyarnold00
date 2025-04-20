package haileyArnold.myZoo.com.Module05.CascadeProjects.windsurf_project;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.*;

public class ZooKeeper {
    private Map<String, Queue<String>> animalNames;
    private List<Animal> animals;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Pattern ANIMAL_PATTERN = Pattern.compile("(\\d+)\\s+year\\s+old\\s+(\\w+)\\s+(\\w+)");
    private static final Pattern INFO_PATTERN = Pattern.compile("born\\s+in\\s+(\\w+),\\s+(.+?)\\s+color,\\s+(\\d+)\\s+pounds,\\s+from\\s+(.+)");

    public ZooKeeper() {
        animalNames = new HashMap<>();
        animals = new ArrayList<>();
    }

    public void loadAnimalNames(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String currentSpecies = "";
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                if (line.endsWith("Names:")) {
                    currentSpecies = line.substring(0, line.indexOf(" Names:")).toLowerCase();
                    animalNames.put(currentSpecies, new LinkedList<>());
                } else if (!currentSpecies.isEmpty() && !line.isEmpty()) {
                    String[] names = line.split(",");
                    for (String name : names) {
                        String trimmedName = name.trim();
                        if (!trimmedName.isEmpty()) {
                            animalNames.computeIfAbsent(currentSpecies, k -> new LinkedList<>()).add(trimmedName);
                        }
                    }
                }
            }
        }
    }

    public void processArrivingAnimals(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    processAnimalEntry(line.trim());
                }
            }
        }
    }

    public void processAnimalEntry(String line) {
        try {
            // Parse line: "4 year old female hyena, born in spring, tan color, 70 pounds, from Friguia Park, Tunisia"
            String[] parts = line.split(",", 2);
            if (parts.length != 2) {
                System.err.println("Invalid line format: " + line);
                return;
            }

            // Parse first part: "4 year old female hyena"
            Matcher animalMatcher = ANIMAL_PATTERN.matcher(parts[0]);
            if (!animalMatcher.find()) {
                System.err.println("Invalid animal format: " + parts[0]);
                return;
            }

            int age = Integer.parseInt(animalMatcher.group(1));
            String gender = animalMatcher.group(2);
            String species = animalMatcher.group(3).toLowerCase();

            // Parse second part: "born in spring, tan color, 70 pounds, from Friguia Park, Tunisia"
            Matcher infoMatcher = INFO_PATTERN.matcher(parts[1]);
            if (!infoMatcher.find()) {
                System.err.println("Invalid info format: " + parts[1]);
                return;
            }

            String birthSeason = infoMatcher.group(1);
            String color = infoMatcher.group(2);
            double weight = Double.parseDouble(infoMatcher.group(3));
            String origin = infoMatcher.group(4);

            Animal animal;
            switch (species) {
                case "hyena":
                    animal = new Hyena();
                    break;
                case "lion":
                    animal = new Lion();
                    break;
                case "tiger":
                    animal = new Tiger();
                    break;
                case "bear":
                    animal = new Bear();
                    break;
                default:
                    System.err.println("Invalid species: " + species);
                    return;
            }

            animal.setAge(age);
            animal.setGender(gender);
            animal.setSpecies(species);
            animal.setBirthSeason(birthSeason);
            animal.setColor(color);
            animal.setWeight(weight);
            animal.setOrigin(origin);

            // Get a name from the appropriate queue
            Queue<String> names = animalNames.get(species);
            if (names != null && !names.isEmpty()) {
                animal.setName(names.poll());
            }

            // Calculate birth date based on age and season
            LocalDate now = LocalDate.now();
            int birthYear = now.getYear() - age;
            int birthMonth;
            int birthDay;
            
            switch (birthSeason.toLowerCase()) {
                case "spring":
                    birthMonth = 3;
                    birthDay = 21;
                    break;
                case "summer":
                    birthMonth = 6;
                    birthDay = 21;
                    break;
                case "fall":
                    birthMonth = 9;
                    birthDay = 21;
                    break;
                case "winter":
                    birthMonth = 12;
                    birthDay = 21;
                    break;
                default:
                    birthMonth = 1;
                    birthDay = 1;
            }
            
            LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
            animal.setBirthDate(birthDate);
            animal.setArrivalDate(LocalDate.now());

            animals.add(animal);

        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in data: " + line);
        } catch (Exception e) {
            System.err.println("Error processing animal entry: " + e.getMessage());
        }
    }

    public void generateZooPopulation(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Sort animals by species
            animals.sort((a1, a2) -> a1.getSpecies().compareTo(a2.getSpecies()));

            String currentSpecies = "";
            for (Animal animal : animals) {
                if (!animal.getSpecies().equals(currentSpecies)) {
                    currentSpecies = animal.getSpecies();
                    writer.println("\n" + currentSpecies.toUpperCase() + " HABITAT");
                }

                writer.printf("%s; %s; birth date: %s; %s color; %s; %.1f pounds; from %s; arrived %s%n",
                    animal.getId(),
                    animal.getName(),
                    animal.getBirthDate().format(DATE_FORMAT),
                    animal.getColor(),
                    animal.getGender(),
                    animal.getWeight(),
                    animal.getOrigin(),
                    animal.getArrivalDate().format(DATE_FORMAT)
                );
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper();
        zooKeeper.loadAnimalNames("animalNames.txt");
        zooKeeper.processArrivingAnimals("arrivingAnimals.txt");
        zooKeeper.generateZooPopulation("zooPopulation.txt");
    }
}
