package defaultPc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    private final BufferedReader bufferedReader;

    private String currentCommand;
    Code code = new Code();

    public String getCurrentCommand() {
        return currentCommand;
    }

    int counterForSymbols = 0;
    int counterForCommands = 14;

    public String getBinOfCurrentCommand(){
        return code.getBin(currentCommand);
    }

    public Parser(FileReader in) {
        this.bufferedReader = new BufferedReader(in);
    }

    public boolean hasMoreCommands(){
        try {
            return bufferedReader.ready();
        } catch (IOException e) {
            throw new RuntimeException("IO has more commands");
        }
    }

    public void advance(){
        try {
            currentCommand = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putToTableIfSymbolCommand(SymbolTable table){
        if (isSymbolCommand() && !table.contains(currentCommand)){
            currentCommand = currentCommand.replaceAll("[@()]","");
            table.addEntry(currentCommand, counterForSymbols);
            return;
        }
        counterForSymbols++;
    }

    public void putToTableIfCommand(SymbolTable table){
        if (isSymbolicACommand() && !table.contains(currentCommand)){
            currentCommand = currentCommand.replaceAll("[@()]","");
            table.addEntry(currentCommand, ++counterForCommands);
        }
    }

    private boolean isSymbolCommand(){
        return currentCommand.charAt(0) == '(';
    }

    private boolean isSymbolicACommand(){
        if (currentCommand.charAt(0) == '@') {
            String subStr = currentCommand.substring(1);
            try {
                Integer.parseInt(subStr);
                return false;
            } catch (NumberFormatException e) {
                return true;
            }
        }
        return false;
    }
}
