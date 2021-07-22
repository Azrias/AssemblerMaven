package defaultPc;

import file_writers.OutputFileWriter;
import file_writers.TempFIleWriter;

import java.io.*;

public class Launcher {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        if (args.length != 2){
            throw new RuntimeException("The number of arguments should be 2");
        }
        launcher.launch(args[0],args[1]);
    }

    private void launch(String input, String output) {
        TempFIleWriter tempFIleWriter = new TempFIleWriter(input);
        File tmp = tempFIleWriter.createAndFillTmpFile();

        OutputFileWriter outputFileWriter = new OutputFileWriter(tmp, output);
        outputFileWriter.createHackFile(tmp, output);
    }
}
