import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReadWriteStrategy implements ReadWriteStrategy {
    @Override
    public List<InputRecord> readFile(String inputFileName) throws IOException {
        CSVReader csvReader = null;
        try (FileReader fileReader = new FileReader(inputFileName)) {
            csvReader =
                    new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            List<String[]> lines = csvReader.readAll();
            List<InputRecord> result = new ArrayList<>();
            for (String[] line : lines) {
                String cardNumber = line.length > 0 ? line[0] : null;
                String name = line.length > 1 ? line[1] : null;
                String expiry = line.length > 2 ? line[2] : null;
                result.add(new InputRecord(cardNumber, name, expiry));
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // (TODO) Close the reader
            csvReader.close();
        }
    }

    @Override
    public void writeToFile(String outputFileName, List<OutputRecord> outputRecords) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(outputFileName));
        csvWriter.writeNext(new String[]{"CardNumber", "CardType", "Error"}, false);
        for (OutputRecord record : outputRecords) {
            csvWriter.writeNext(new String[]{record.getCardNumber(), record.getCardType(), record.getError()}, false);
        }
        csvWriter.close();
    }
}
