import java.io.FileNotFoundException;

public abstract class File {
    private final String name;
    private File parent;

    public File (String s) {
        name = s;
        parent = null;
    }

    public File (String s, File f) {
        name = s;
        parent = f;
    }

    public String getName() {
        return name;
    }
    public void setParent(File f) {
        parent = f;
    }
    public File getParent() {
        return parent;
    }

    public abstract File chdir() throws FileNotFoundException;
    public abstract void cat();
    public abstract void ls();
    public abstract void search(String keyword);
    public void rmrf() {}
}
