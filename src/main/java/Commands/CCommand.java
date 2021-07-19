package Commands;

import java.util.Arrays;

public class CCommand implements Command{
    private String memAloc;
    private String cmd;
    private String jump;

    public String getJump() {
        return jump;
    }

    public String getMemAloc() {
        return memAloc;
    }

    public String getCmd() {
        return cmd;
    }

    @Override
    public String getCommand() {
        String bin;
        if (cmd.length() == 3) {
            bin = createBin('\0',cmd.charAt(0),cmd.charAt(1),cmd.charAt(2));
        } else if (cmd.length() == 2){
            bin = createBin(cmd.charAt(0),cmd.charAt(1));
        } else if (cmd.length() == 1){
            bin = createBin(cmd.charAt(0));
        } else {
            throw new RuntimeException("Syntax error");
        }

        return bin;
    }

    private String createBin(char firstChar) {
        if (firstChar == 'D'){
            return "001100";
        } else if (firstChar == 'A'){
            return "110000";
        } else if (firstChar == '1'){
            return "111111";
        } else {
            return "101010";
        }
    }

    private String createBin(char firstSign, char firstChar) {
        int zx, zy, nx, ny, f, ng;
        if (firstChar == 'D'){
            zy = 1;
            zx = 0;
        } else {
            zy = 0;
            zx = 1;
        }
        if (firstSign == '-'){
            if (firstChar == 'D'){
                return "001111";
            } else {
                return "110011";
            }
        } else if (firstSign == '!'){
            if (firstChar == 'D'){
                return "001101";
            } else {
                return "110001";
            }
        } else {
            return "111010";
        }
    }

    private String createBin(char firstSign, char firstChar, char secondSign, char secondChar){
        int zx, zy, nx, ny, f, ng;

        if(secondChar == 'D' || firstChar == 'A') {
            char temp = firstSign;
            firstSign = secondSign;
            secondSign = temp;
            temp = firstChar;
            firstChar = secondChar;
            secondChar = temp;
        }

        if (secondSign == '&'){
            return "000000";
        }

        if (secondSign == '|'){
            return "010101";
        }

        if (firstChar == '\0' || firstChar == '1') {
            zx = 1;
        } else {
            zx = 0;
        }
        if (secondChar == '\0' || secondChar == '1') {
            zy = 1;
        } else {
            zy = 0;
        }

        f=1;
        if (firstSign == '+' && secondSign == '+'){
            nx = 0;
            ny = 0;
            ng = 0;
        } else  if (firstSign == '+' && secondSign == '-') {
            nx = 1;
            ny = 0;
            ng = 1;
        } else  if (firstSign == '-' && secondSign == '+') {
            nx = 0;
            ny = 1;
            ng = 1;
        } else {
            throw new RuntimeException("Syntax error");
        }

        return String.valueOf(zx) + nx + zy + ny + f + ng;
    }

    public CCommand( String currentCommand) {
        initFields(currentCommand);
    }

    private void initFields(String currentCommand) {
        if (!currentCommand.contains("=")) {
            currentCommand = '=' + currentCommand;
        }

        if (!currentCommand.contains(";")) {
            currentCommand = currentCommand + ';';
        }

        String[] split = currentCommand.split("[=;]");
        String[] splitEnsure = Arrays.copyOf(split,3);

        memAloc = splitEnsure[0];
        cmd = splitEnsure[1];
        jump = splitEnsure[2];
    }

}
