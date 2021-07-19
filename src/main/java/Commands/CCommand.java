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
            bin = createBin(cmd.charAt(0),cmd.charAt(1),cmd.charAt(2),3);
        } else if (cmd.length() == 2){
            bin = createBin(cmd.charAt(0),cmd.charAt(1),'\0',2);
        } else if (cmd.length() == 1){
            bin = createBin(cmd.charAt(0),'\0','\0', 1);
        } else {
            throw new RuntimeException("Syntax error");
        }

        return bin;
    }

    private String createBin(char firstChar,char operation,char secondChar,int length){
        int zx = 0;
        int zy = 0;
        int nx = 0;
        int ny = 0;
        int f= 0;
        int ng = 0;
        if (firstChar == '\0') {
            zx = 0; //TODO D должна идти первой
        }
        if (secondChar == '\0') {
            zy = 0; //TODO D должна идти первой
        }
        if (operation == '+') {
            f = 1;
        } else if (operation == '|') {
            return  "010101";
        } else if (operation == '-') {
            f = 1;
            ng = 1;
            if (length == 2) {
                if (firstChar == 'D'){
                    nx = 0;
                } else {
                    ny = 1;
                }
            } else {
                if (firstChar == 'D'){
                    ny = 1;
                } else {
                    nx = 0;
                }
            }
        }
        return String.valueOf(zx) + zy + nx + ny + f + ng;
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

        String[] split = currentCommand.split("=|;");
        String[] splitEnsure = Arrays.copyOf(split,3);

        memAloc = splitEnsure[0];
        cmd = splitEnsure[1];
        jump = splitEnsure[2];
    }

}
