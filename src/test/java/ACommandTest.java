import Commands.CCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


public class ACommandTest {
    CCommand cmd;

    @Test
    @DisplayName("Simple multiplication should work")
    public void setup(){
        String command = "D=D+1";
        cmd = new CCommand(command);
        assumeTrue(5>1);
    }
}
