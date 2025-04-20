package haileyArnold.myZoo.com.Module05.CascadeProjects.windsurf_project;

// Represents a Tiger in the zoo
public class Tiger extends Animal {
    private static int counter = 1;

    // Constructor initializes tiger with basic animal attributes
    public Tiger() {
        super();
        setId(genUniqueID());
    }

    // Generates unique ID in format "TI##"
    @Override
    public String genUniqueID() {
        return String.format("TI%02d", counter++);
    }
}
