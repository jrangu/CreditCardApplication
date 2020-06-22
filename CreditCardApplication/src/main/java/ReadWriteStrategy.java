import java.io.IOException;
import java.util.List;

public interface ReadWriteStrategy {
    List<InputRecord> readFile(String inputFileName) throws IOException;

    void writeToFile(String outputFileName, List<OutputRecord> cards) throws IOException;
}
