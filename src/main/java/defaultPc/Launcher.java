package defaultPc;

import java.io.*;

public class Launcher {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        if (args.length != 2){
            throw new RuntimeException("The number of arguments should be 2");
        }
        launcher.launch(args[0],args[1]);
    }

    private void launch(String input, String output) {
        File tmp = createTmpFile(input);
        createHackFile(tmp, output);
    }

    private File createTmpFile(String input) {
        SymbolTable table = createTable(input);

        File file = createTmpFIle();

        try (FileWriter tmpFileWriter = new FileWriter(file)) {
            try (FileReader fileReader = new FileReader(input)) {
                Parser parser = new Parser(fileReader);
                writeToTmp(table, tmpFileWriter, parser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private File createTmpFIle() {
        try {
            return File.createTempFile("temp", "asm");
        } catch (IOException e) {
            throw new RuntimeException("creteTmpFIle went wrong");
        }
    }

    private void createHackFile(File input, String output) {
        try (FileReader fileReader = new FileReader(input)) {
            Parser parser = new Parser(fileReader);
            try (FileWriter fileWriter = new FileWriter(output)){
                execute(parser, fileWriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SymbolTable createTable(String input) {
        SymbolTable table = null;
        try (FileReader fileReader = new FileReader(input)) {
            Parser parser = new Parser(fileReader);
            table = createSymbolTable(parser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }

    private void writeToTmp(SymbolTable table, FileWriter tmpFileWriter, Parser parser) throws IOException {
        while (parser.hasMoreCommands()) {
            Code code = Code.getInstance();
            parser.advance();
            String currentCommand = parser.getCurrentCommand();
            if (code.getType(currentCommand) == CommandType.A_COMMAND){
                if (table.contains(currentCommand.substring(1))){
                    String cmd = table.getAddress(currentCommand.substring(1));
                    tmpFileWriter.write(cmd);
                }
            } else {
                tmpFileWriter.write(currentCommand);
            }
        }
    }


    private SymbolTable createSymbolTable(Parser parser) {
        SymbolTable table = new SymbolTable();
        while (parser.hasMoreCommands()) {
            parser.advance();
            parser.putToTableIfIsSymbolCommand(table);
        }
        return table;
    }

    private void execute(Parser parser, FileWriter fileWriter) throws IOException {
        while (parser.hasMoreCommands()) {
            parser.advance();
            String currentCommand = parser.getBinOfCurrentCommand();
            fileWriter.write(currentCommand + "\n");
        }
    }
}
