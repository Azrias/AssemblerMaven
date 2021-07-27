package defaultPc;

import java.util.Arrays;
import java.util.HashMap;

public final class Code {

    private static final Code CODE = new Code();
    private CommandType type;

    private HashMap<String,String> mapCmd, mapJump, mapMem;

    public static Code getInstance() {
        return CODE;
    }

    public String getBin(String currentCommand) {
        setType(currentCommand);
        if (type == CommandType.C_COMMAND) {
            return getBinForCCommand(currentCommand);
        }
        if (type == CommandType.A_COMMAND) {
            return getBinForACommand(currentCommand);
        }
        return null;
    }

    private String getBinForACommand(String currentCommand) {
        String cmd = currentCommand.substring(1);
        int parseInt = Integer.parseInt(cmd);

        String num = Integer.toString(parseInt, 2);
        int fill = 16 - num.length();

        char[] chars = new char[fill];
        Arrays.fill(chars, '0');

        return String.copyValueOf(chars) + num;
    }

    public CommandType getType(String currentCommand){
        if (currentCommand.charAt(0) == '@') {
            return CommandType.A_COMMAND;
        } else if (currentCommand.charAt(0) == '(') {
            return CommandType.S_COMMAND;
        } else {
            return CommandType.C_COMMAND;
        }
    }

    private void setType(String currentCommand) {
        if (currentCommand.charAt(0) == '@') {
            type = CommandType.A_COMMAND;
        } else {
            type = CommandType.C_COMMAND;
        }
    }

    private String getBinForCCommand(String currentCommand) {
        String[] parts = splitCurrentCommand(currentCommand);
        String binMem = mapMem.get(parts[0]);
        String binCmd = mapCmd.get(parts[1]);
        String binJump = mapJump.get(parts[2]);
        return "111" + binCmd + binMem + binJump;
    }

    private Code() {
        createMapCmd();
        mapPutJump();
        mapPutDestination();
    }

    private String[] splitCurrentCommand(String currentCommand) {
        if (!currentCommand.contains("=")) {
            currentCommand = '=' + currentCommand;
        }

        if (!currentCommand.contains(";")) {
            currentCommand = currentCommand + ';';
        }

        return currentCommand.split("[=;]", -1);
    }

    //TODO make external file
    private void createMapCmd() {
        mapCmd = new HashMap<>();
        mapCmd.put("0", "0101010");
        mapCmd.put("1", "0111111");
        mapCmd.put("-1", "0111010");
        mapCmd.put("D", "0001100");
        mapCmd.put("A", "0110000");
        mapCmd.put("M", "1110000");
        mapCmd.put("!D", "0001101");
        mapCmd.put("!A", "0110001");
        mapCmd.put("!M", "1110001");
        mapCmd.put("-D", "0001111");
        mapCmd.put("-A", "0110011");
        mapCmd.put("-M", "1110011");
        mapCmd.put("D+1", "0011111");
        mapCmd.put("A+1", "0110111");
        mapCmd.put("M+1", "1110111");
        mapCmd.put("D-1", "0001110");
        mapCmd.put("A-1", "0110010");
        mapCmd.put("M-1", "1110010");
        mapCmd.put("D+A", "0000010");
        mapCmd.put("D+M", "1000010");
        mapCmd.put("D-A", "0010011");
        mapCmd.put("D-M", "1010011");
        mapCmd.put("A-D", "0000111");
        mapCmd.put("M-D", "1000111");
        mapCmd.put("D&A", "0000000");
        mapCmd.put("D&M", "1000000");
        mapCmd.put("D|A", "0010101");
        mapCmd.put("D|M", "1010101");
    }

    private void mapPutJump() {
        mapJump = new HashMap<>();
        mapJump.put("", "000");
        mapJump.put("JGT", "001");
        mapJump.put("JEQ", "010");
        mapJump.put("JGE", "011");
        mapJump.put("JLT", "100");
        mapJump.put("JNE", "101");
        mapJump.put("JLE", "110");
        mapJump.put("JMP", "111");
    }

    private void mapPutDestination() {
        mapMem = new HashMap<>();
        mapMem.put("", "000");
        mapMem.put("M", "001");
        mapMem.put("D", "010");
        mapMem.put("MD", "011");
        mapMem.put("A", "100");
        mapMem.put("AM", "101");
        mapMem.put("AD", "110");
        mapMem.put("AMD", "111");
    }
}
