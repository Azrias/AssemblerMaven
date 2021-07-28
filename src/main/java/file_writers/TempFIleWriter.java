package file_writers;

import defaultPc.Code;
import defaultPc.CommandType;
import defaultPc.Parser;
import defaultPc.SymbolTable;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TempFIleWriter {

    String input;

    public TempFIleWriter(String input) {
        this.input = input;
    }

    public File createAndFillTmpFile() {
        SymbolTable table = createTable(input);

        File file = createTmpFile();

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

    private File createTmpFile() {
        //try {
        //return File.createTempFile("temp", "asm");
        return new File("D:\\tem.txt");
        //} catch (IOException e) {
        //    throw new RuntimeException("creteTmpFIle went wrong");
        //}
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
            currentCommand = currentCommand.replaceAll("\\s", "");

            if (code.getType(currentCommand) == CommandType.S_COMMAND){
                continue;
            }
            if (code.getType(currentCommand) == CommandType.A_COMMAND) {

                table.contains(currentCommand)) {
                String cmd = table.getAddress(currentCommand);
                tmpFileWriter.append("@").append(cmd).append("\n");

            } else {
                tmpFileWriter.append(currentCommand).append("\n");
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

}
