import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// How to OCP 
// use redundant method in parent class
// force type-casting
public class Directory extends File{
    private List<File> content;
    public Directory(String s) {
        super(s);
        content = new ArrayList<>();
    }

    public Directory(String s, File f) {
        super(s, f);
        content = new ArrayList<>();
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public void cat() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void ls() {
        ArrayList<String> output = new ArrayList<>();
        for (File f : content) output.add(f.getName());
        Collections.sort(output);
        for (String s : output) System.out.println(s);
    }

    public void creat(File f) {
        content.add(f);
    }

    public void remove(File f) {
        f.rmrf();
        content.remove(f);
    }

    @Override
    public void search(String keyword) {
        if (getName().contains(keyword))
            System.out.println(getName());
        content.sort(Comparator.comparing(File::getName));
        for (File f : content) 
            f.search(keyword);
    }

    public File searchFile(String name) throws FileNotFoundException {
        for (File f : content)
            if (f.getName().equals(name))
                return f;
        throw new FileNotFoundException();
    }

    @Override
    public void rmrf() {
        for (File f : content)
            f.rmrf();
        content = new ArrayList<>();
    }

    @Override
    public File chdir() {
        return this;
    }

}
