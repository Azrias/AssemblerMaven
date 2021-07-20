import Commands.CCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CCommandTest {

    @Test
    @DisplayName("D=D+1")
    public void testCmd(){
        CCommand cmd = new CCommand("D=D+1");
        assertEquals("0011111010000",cmd.getCommand());

    }

    @Test
    @DisplayName("0;JMP")
    public void testCmds(){
        CCommand cmd = new CCommand("0;JMP");
        assertEquals("0101010000111",cmd.getCommand());

    }

    @Test
    @DisplayName("0")
    public void testsCmds(){
        CCommand cmd = new CCommand("0");
        assertEquals("0101010000000",cmd.getCommand());

    }


}
