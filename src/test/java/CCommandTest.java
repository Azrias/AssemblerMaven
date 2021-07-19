import Commands.CCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CCommandTest {

    @Test
    @DisplayName("D=D+1")
    public void testCmd(){
        CCommand cmd = new CCommand("D=D+1");
        assertEquals("D",cmd.getMemAloc());
        assertEquals("D+1",cmd.getCmd());
    }

    @Test
    @DisplayName("D+1")
    public void testCmfd(){
        CCommand cmd = new CCommand("D+1");
        assertEquals("",cmd.getMemAloc());
        assertEquals("D+1",cmd.getCmd());
    }

    @Test
    @DisplayName("D=D+1;JMP")
    public void testCmdw(){
        CCommand cmd = new CCommand("D=D+1;JMP");
        assertEquals("D",cmd.getMemAloc());
        assertEquals("D+1",cmd.getCmd());
        assertEquals("JMP",cmd.getJump());
    }

    @Test
    @DisplayName("0;JMP")
    public void testCmdwd(){
        CCommand cmd = new CCommand("0;JMP");
        assertEquals("JMP",cmd.getJump());
    }


    @Test
    @DisplayName("D=D+1")
    public void testCasdsmd(){
        CCommand cmd = new CCommand("D=D+A");
        assertEquals("000010",cmd.getCommand());
    }

    @Test
    @DisplayName("D=D|A")
    public void testCadasdsmd(){
        CCommand cmd = new CCommand("D=D|A");
        assertEquals("010101",cmd.getCommand());
    }

    @Test
    @DisplayName("D=A-D")
    public void testCadaasadsdsmd(){
        CCommand cmd = new CCommand("D=A-D");
        assertEquals("000111",cmd.getCommand());
    }

    @Test
    @DisplayName("D=D-A")
    public void teasdsasstCadaasadsdsmd(){
        CCommand cmd = new CCommand("D=D-A");
        assertEquals("010011",cmd.getCommand());
    }
}
