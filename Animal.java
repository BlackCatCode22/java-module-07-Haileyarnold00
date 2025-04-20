package haileyArnold.myZoo.com.Module05.CascadeProjects.windsurf_project;

import java.time.LocalDate;

// Base abstract class for all animals in the zoo
public abstract class Animal {
    // Animal attributes
    private String id;
    private int age;
    private String gender;
    private String birthSeason;
    private String color;
    private double weight;
    private String origin;
    private String species;
    private String name;
    private LocalDate birthDate;
    private LocalDate arrivalDate;

    // Constructor initializes all animal attributes
    public Animal() {
        this.arrivalDate = LocalDate.now();
    }

    // Generates birthday based on birth season and age
    protected LocalDate genBirthDay() {
        int birthYear = LocalDate.now().getYear() - age;
        int birthMonth;
        int birthDay;
        
        switch(birthSeason.toLowerCase()) {
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
        
        return LocalDate.of(birthYear, birthMonth, birthDay);
    }

    // Abstract method to be implemented by each animal type for unique ID generation
    public abstract String genUniqueID();

    // Formats animal information for output
    @Override
    public String toString() {
        return String.format("%s; %s; birth date: %s; %s color; %s; %.2f pounds; from %s; arrived %s",
            id, name, birthDate, color, gender, weight, origin, arrivalDate);
    }

    // Getters
    public String getId() { return id; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getBirthSeason() { return birthSeason; }
    public String getColor() { return color; }
    public double getWeight() { return weight; }
    public String getOrigin() { return origin; }
    public String getSpecies() { return species; }
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public LocalDate getArrivalDate() { return arrivalDate; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setBirthSeason(String birthSeason) { this.birthSeason = birthSeason; }
    public void setColor(String color) { this.color = color; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setOrigin(String origin) { this.origin = origin; }
    public void setSpecies(String species) { this.species = species; }
    public void setName(String name) { this.name = name; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setArrivalDate(LocalDate arrivalDate) { this.arrivalDate = arrivalDate; }
}
