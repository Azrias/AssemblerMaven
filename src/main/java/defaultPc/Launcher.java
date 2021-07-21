package defaultPc;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        if (args.length != 2){
            throw new RuntimeException("The number of arguments should be 2");
        }
        launcher.launch(args[0],args[1]);
    }

    private void launch(String input, String output) {
        try (FileReader fileReader = new FileReader(input)) {
            Parser parser = new Parser(fileReader);

            SymbolTable table = createSymbolTable(parser);

            try (FileWriter fileWriter = new FileWriter(File.createTempFile("tmp","asm"))){
                execute(parser, fileWriter);
                parser = new Parser(new FileReader(File.createTempFile("tmp","asm")))
            }
            try (FileWriter fileWriter = new FileWriter(output)){
                execute(parser, fileWriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SymbolTable createSymbolTable(Parser parser) {
        SymbolTable table = new SymbolTable();
        while (parser.hasMoreCommands()) {
            parser.advance();
            parser.putToTableIfIsSymbolCommand(table);
        }
    }

    private void execute(Parser parser, FileWriter fileWriter) throws IOException {
        while (parser.hasMoreCommands()) {
            parser.advance();
            String currentCommand = parser.getBinOfCurrentCommand();
            fileWriter.write(currentCommand + "\n");
        }
    }
}
