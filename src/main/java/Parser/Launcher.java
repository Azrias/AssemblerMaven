package Parser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.launch("C:\\Users\\Sirius\\IdeaProjects\\Assembler\\src\\javacode\\q.txt");
    }

    private void launch(String arg) {
        Parser parser = null;
        try (FileReader fileReader = new FileReader(arg)) {
            parser = new Parser(fileReader);
            try (FileWriter fileWriter = new FileWriter("file.hack")){
                while (parser.hasMoreCommands()) {
                    parser.advance();
                    String currentCommand = parser.getBinOfCurrentCommand();
                    fileWriter.write(currentCommand + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
