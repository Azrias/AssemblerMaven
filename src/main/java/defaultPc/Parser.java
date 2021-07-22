package defaultPc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    private final BufferedReader bufferedReader;

    public String getCurrentCommand() {
        return currentCommand;
    }

    private String currentCommand;

    int counter = 0;

    public String getBinOfCurrentCommand(){
        Code code = Code.getInstance();
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

    public void putToTableIfIsSymbolCommand(SymbolTable table){
        if (isSymbolCommand()){
            table.addEntry(currentCommand.substring(1),counter);
            return;
        }
        counter++;
    }

    private boolean isSymbolCommand(){
        if (currentCommand.charAt(0) == '(') {
            return true;
        }
        if (!(currentCommand.charAt(0) == '@')) {
            return false;
        }
        String subStr = currentCommand.substring(1);
        try {
            Integer.parseInt(subStr);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
