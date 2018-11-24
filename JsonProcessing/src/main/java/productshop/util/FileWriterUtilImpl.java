package productshop.util;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtilImpl implements FileWriterUtil {
    @Override
    public void writeFile(String str,String folder) throws IOException {

        FileWriter fileWriter = new FileWriter("src/main/resources/files/output/"+folder+".json");

        fileWriter.write(str);

        fileWriter.close();

    }
}
