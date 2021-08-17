import java.io.FileNotFoundException;

public class SymbolicLink extends File{
    private final String content;
    private final File real;
    private final String realname;
    
    public SymbolicLink(String s, String buf, File link, File parent) {
        super(s, parent);
        content = buf;
        real = link;
        realname = real.getName();
    }

    @Override
    public void cat() {
        System.out.println(content);
    }

    @Override
    public void ls() {
        real.ls();
    }

    @Override
    public void search(String keyword) {
        if (getName().equals(keyword))
            System.out.println(getName());
    }

    @Override
    public File chdir() throws FileNotFoundException {
        return ((Directory)getParent()).searchFile(realname);
    }
    
}
