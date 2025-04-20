package haileyArnold.myZoo.com.Module05.CascadeProjects.windsurf_project;

// Represents a Hyena in the zoo
public class Hyena extends Animal {
    private static int counter = 1;

    // Constructor initializes hyena with basic animal attributes
    public Hyena() {
        super();
        setId(genUniqueID());
    }

    // Generates unique ID in format "HY##"
    @Override
    public String genUniqueID() {
        return String.format("HY%02d", counter++);
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
