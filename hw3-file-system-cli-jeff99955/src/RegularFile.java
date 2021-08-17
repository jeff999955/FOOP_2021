public class RegularFile extends File{
    private final String content;
    public RegularFile(String s, String buf, File parent) {
        super(s, parent);
        content = buf;
    }

    @Override
    public void cat() {
        System.out.println(content);
    }

    @Override
    public void ls() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void search(String keyword) {
        if (getName().contains(keyword))
            System.out.println(getName());
    }

    @Override
    public File chdir() {
        throw new UnsupportedOperationException();
    }
}
