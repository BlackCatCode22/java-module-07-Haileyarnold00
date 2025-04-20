package haileyArnold.myZoo.com.Module05.CascadeProjects.windsurf_project;

// Represents a Bear in the zoo
public class Bear extends Animal {
    private static int counter = 1;

    // Constructor initializes bear with basic animal attributes
    public Bear() {
        super();
        setId(genUniqueID());
    }

    // Generates unique ID in format "BE##"
    @Override
    public String genUniqueID() {
        return String.format("BE%02d", counter++);
    }
}
