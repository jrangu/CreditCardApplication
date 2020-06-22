import java.io.IOException;
import java.util.List;

public class InputHandler {
    private ReadWriteStrategy strategy;

    public InputHandler(String inputFileExtension) {
        if (inputFileExtension.equals("csv")) {
            this.strategy = new CsvReadWriteStrategy();
        } else if (inputFileExtension.equals("xml")) {
            this.strategy = new XmlReadWriteStrategy();
        } else if (inputFileExtension.equals("json")) {
            this.strategy = new JsonReadWriteStrategy();
        } else {
            System.out.println("Can't process this file. Input file " +
                    "should contain one of these extension - [.csv, .xml, .json]");
        }
    }

    public List<InputRecord> getRecordsFromInput(String inputFileName) throws IOException {
        return strategy.readFile(inputFileName);
    }

    public void writeOutputRecords(String outputFileName, List<OutputRecord> outputRecords) throws IOException {
        strategy.writeToFile(outputFileName, outputRecords);
    }
}
