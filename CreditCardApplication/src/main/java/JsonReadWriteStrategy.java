import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReadWriteStrategy implements ReadWriteStrategy {
    // Source : https://stackabuse.com/reading-and-writing-json-in-java/
    @Override
    public List<InputRecord> readFile(String inputFileName) throws IOException {
        try (FileReader fileReader = new FileReader(inputFileName)) {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(fileReader);
            List<InputRecord> result = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                result.add(buildInputRecord(array.get(i)));
            }
            return result;
            // return (List<InputRecord>) array.stream().map(this::makeStringArray).collect(Collectors.toList());
        } catch (ParseException e) {
            System.out.println("Unable to parse the give json file. Please make sure the input is a valid json.");
            return new ArrayList<>();
        }
    }


    private InputRecord buildInputRecord(Object a) {
        JSONObject j = (JSONObject) a;
        return new InputRecord(j.get("CardNumber") == null ? null : String.valueOf(j.get("CardNumber")), String.valueOf(j.get("ExpirationDate")), String.valueOf(j.get("NameOfCardholder")));
    }

    @Override
    public void writeToFile(String outputFileName, List<OutputRecord> outputRecords) throws IOException {
        JSONArray array = new JSONArray();
        for (OutputRecord record : outputRecords) {
            JSONObject object = new JSONObject();
            object.put("CardNumber", record.getCardNumber());
            object.put("CardType", record.getCardType());
            object.put("Error", record.getError());
            array.add(object);
        }
        FileWriter fw = new FileWriter(outputFileName);
        array.writeJSONString(fw);
        fw.close();
    }
}
