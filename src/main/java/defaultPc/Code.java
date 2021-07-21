package defaultPc;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

public final class Code {

    private static final Code CODE = new Code();
    private CommandType type;

    private HashMap<String,String> mapcmd;
    private HashMap<String,String> mapjump;
    private HashMap<String,String> mapmem;
    private String memAloc;
    private String cmd;
    private String jump;

    public static Code getInstance() {
        return CODE;
    }

    public String getBin(String currentCommand) {
        setType(currentCommand);
        if (type == CommandType.C_COMMAND){
            initFields(currentCommand);
            return buildBin();
        }
        if (type == CommandType.A_COMMAND) {
            return initFieldForA(currentCommand);
        }
        return null;
    }

    private String initFieldForA(String currentCommand) {
        String cmd = currentCommand.substring(1);
        int parseInt = Integer.parseInt(cmd);

        String num = Integer.toString(parseInt, 2);
        int fill = 16 - num.length();

        char[] chars = new char[fill];
        Arrays.fill(chars,'0');

        return String.copyValueOf(chars) + num;
    }

    private void setType(String currentCommand) {
        if (currentCommand.charAt(0) == '@'){
            type = CommandType.A_COMMAND;
        } else {
            type = CommandType.C_COMMAND;
        }
    }

    @NotNull
    private String buildBin() {
        String binMem = mapmem.get(memAloc);
        String binCmd = mapcmd.get(cmd);
        String binJump = mapjump.get(jump);
        return "111" + binCmd + binMem + binJump;
    }

    private Code() {
        createMapCmd();
        mapPutJump();
        mapPutDestination();
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
        if (memAloc.equals("")){
            memAloc = null;
        }
        cmd = splitEnsure[1];
        jump = splitEnsure[2];
    }

    private void createMapCmd() {
        mapcmd = new HashMap<>();
        mapcmd.put("0", "0101010");
        mapcmd.put("1", "0111111");
        mapcmd.put("-1", "0111010");
        mapcmd.put("D", "0001100");
        mapcmd.put("A", "0110000");
        mapcmd.put("M", "1110000");
        mapcmd.put("!D", "0001101");
        mapcmd.put("!A", "0110001");
        mapcmd.put("!M", "1110001");
        mapcmd.put("-D", "0001111");
        mapcmd.put("-A", "0110011");
        mapcmd.put("-M", "1110011");
        mapcmd.put("D+1", "0011111");
        mapcmd.put("A+1", "0110111");
        mapcmd.put("M+1", "1110111");
        mapcmd.put("D-1", "0001110");
        mapcmd.put("A-1", "0110010");
        mapcmd.put("M-1", "1110010");
        mapcmd.put("D+A", "0000010");
        mapcmd.put("D+M", "1000010");
        mapcmd.put("D-A", "0010011");
        mapcmd.put("D-M", "1010011");
        mapcmd.put("A-D", "0000111");
        mapcmd.put("M-D", "1000111");
        mapcmd.put("D&A", "0000000");
        mapcmd.put("D&M", "1000000");
        mapcmd.put("D|A", "0010101");
        mapcmd.put("D|M", "1010101");
    }

    private void mapPutJump() {
        mapjump = new HashMap<>();
        mapjump.put(null, "000");
        mapjump.put("JGT", "001");
        mapjump.put("JEQ", "010");
        mapjump.put("JGE", "011");
        mapjump.put("JLT", "100");
        mapjump.put("JNE", "101");
        mapjump.put("JLE", "110");
        mapjump.put("JMP", "111");
    }

    private void mapPutDestination() {
        mapmem = new HashMap<>();
        mapmem.put(null, "000");
        mapmem.put("M", "001");
        mapmem.put("D", "010");
        mapmem.put("MD", "011");
        mapmem.put("A", "100");
        mapmem.put("AM", "101");
        mapmem.put("AD", "110");
        mapmem.put("AMD", "111");
    }
}
