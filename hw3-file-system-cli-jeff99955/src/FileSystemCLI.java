import static utils.Inputs.in;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

public class FileSystemCLI {
    private File root;
    private File currentDir;
    private String cwd;

    public FileSystemCLI() {
        root = new Directory("");
        root.setParent(root);
        currentDir = root;
        cwd = "/";
    }

    public void start() {
        while (true) {
            printCurrentPath();
            String command = in.nextLine();
            if (command.equals("exit")) {
                break;
            }
            executeCommand(command);
        }
    }

    private void printCurrentPath() {
        System.out.println("Current path: " + cwd);
    }

    public void executeCommand(String command) {
        String[] segments = command.split("\\s");

        try {
            String commandName = segments[0];
            performCommand(commandName, segments);
        } catch (Exception err) {
            System.err.println("(Debug) Error: " + err.getMessage());
            System.out.println("Illegal command.");
        }
    }

    private void performCommand(String commandName, String[] segments) throws FileNotFoundException, FileAlreadyExistsException {
        switch (commandName) {
            case "cd":
                changeDirectory(segments[1]);
                break;
            case "mkdir":
                makeDirectory(segments[1]);
                break;
            case "touch":
                touch(segments[1], segments[2]);
                break;
            case "rm":
                remove(segments[1]);
                break;
            case "cat":
                concatenate(segments[1]);
                break;
            case "ls":
                list();
                break;
            case "search":
                search(segments[1]);
                break;
            case "ln":
                link(segments[1], segments[2]);
                break;
        }
    }

    private void changeDirectory(String directoryName) throws FileNotFoundException, UnsupportedOperationException{
        if (directoryName.equals("..")){
            currentDir = currentDir.getParent();
            String[] buf = cwd.split("/");
            cwd = "";
            for (int i = 1; i < buf.length - 1; i++)
                cwd += "/" + buf[i];
            if (cwd.length() == 0) cwd = "/";
        }
        else {
            currentDir = ((Directory)currentDir).searchFile(directoryName).chdir();
            cwd += (cwd.equals("/")) ? currentDir.getName() : ("/" + currentDir.getName());
        }

    }

    private void makeDirectory(String directoryName) throws FileAlreadyExistsException {
        try {
            File f = ((Directory)currentDir).searchFile(directoryName);
            throw new FileAlreadyExistsException(directoryName);
        } catch (FileNotFoundException e) {
            Directory dir = new Directory(directoryName, currentDir);
            ((Directory)currentDir).creat(dir);
        }
    }

    private void touch(String fileName, String content) {
        RegularFile tmp = new RegularFile(fileName, content, currentDir);        
        ((Directory)currentDir).creat(tmp);
    }

    private void remove(String childName) throws FileNotFoundException{
        File f = ((Directory)currentDir).searchFile(childName);
        ((Directory)currentDir).remove(f);
    }

    private void concatenate(String fileName) throws UnsupportedOperationException, FileNotFoundException {
        File f = ((Directory)currentDir).searchFile(fileName);
        f.cat();
    }

    private void list() throws UnsupportedOperationException {
        currentDir.ls();
    }

    private void link(String targetName, String linkName) throws FileAlreadyExistsException, FileNotFoundException {
        try {
            File f = ((Directory)currentDir).searchFile(linkName);
            throw new FileAlreadyExistsException(linkName);
        } catch (FileNotFoundException e) {
            File tf = ((Directory)currentDir).searchFile(targetName);
            File tmp = tf.chdir();
            String ts = (cwd.equals("/")) ? (cwd+targetName) : (cwd+"/"+targetName);
            SymbolicLink symLink = new SymbolicLink(linkName, ts, tf, currentDir);
            ((Directory)currentDir).creat(symLink);
        }
    }

    private void search(String keyword) {
        currentDir.search(keyword);
    }

}
