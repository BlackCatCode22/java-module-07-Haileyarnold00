package haileyArnold.myZoo.com.Module05.CascadeProjects.windsurf_project;

// Represents a Lion in the zoo
public class Lion extends Animal {
    private static int counter = 1;

    // Constructor initializes lion with basic animal attributes
    public Lion() {
        super();
        setId(genUniqueID());
    }

    // Generates unique ID in format "LI##"
    @Override
    public String genUniqueID() {
        return String.format("LI%02d", counter++);
    }
}
