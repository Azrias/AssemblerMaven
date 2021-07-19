package Parser;

import Commands.Command;

import java.io.FileReader;
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
            while (parser.hasMoreCommands()) {
                parser.advance();
                Command cmd = parser.getCurrentCommand();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
