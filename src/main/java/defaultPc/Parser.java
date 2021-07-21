package defaultPc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    private final BufferedReader bufferedReader;
    private String currentCommand;

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
}
