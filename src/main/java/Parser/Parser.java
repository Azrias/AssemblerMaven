package Parser;

import Commands.ACommand;
import Commands.CCommand;
import Commands.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    private BufferedReader bufferedReader;
    private String currentCommand;

    public Command getCurrentCommand() {
        Command cmd;
        if (currentCommand.charAt(0) == '@'){
            cmd = new ACommand(currentCommand);
        } else {
            cmd = new CCommand(currentCommand);
        }
        return cmd;
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
