package Commands;

import java.util.Arrays;

public class ACommand  implements Command{

    private final String binCmd;

    public ACommand(String currentCommand) {
        String cmd = currentCommand.substring(1);
        int parseInt = Integer.parseInt(cmd);

        String num = Integer.toString(parseInt, 2);
        int fill = 16 - num.length();

        char[] chars = new char[fill];
        Arrays.fill(chars,'0');

        binCmd = String.copyValueOf(chars) + num;
    }

    @Override
    public String getCommand() {
        return binCmd;
    }
}
