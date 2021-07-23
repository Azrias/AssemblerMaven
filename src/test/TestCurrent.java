import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCurrent {

    @Test
    public void testCurrent(){
        String currentCommand = "@df";
        currentCommand = currentCommand.replaceAll("[@()]","");
        System.out.println(currentCommand);
        assertEquals("df",currentCommand);

        currentCommand = "(START)";
        currentCommand = currentCommand.replaceAll("[@()]","");
        assertEquals("START",currentCommand);
    }

}
