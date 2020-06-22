import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Map<CreditCardType, CreditCardFactory> factoryMap = new HashMap<CreditCardType, CreditCardFactory>() {{
        put(CreditCardType.CREDIT_CARD_TYPE_VISA, new VisaCreditCardFactory());
        put(CreditCardType.CREDIT_CARD_TYPE_AMEX, new AmexCreditCardFactory());
        put(CreditCardType.CREDIT_CARD_TYPE_MASTER_CARD, new MasterCreditCardFactory());
        put(CreditCardType.CREDIT_CARD_TYPE_DISCOVER, new DiscoverCreditCardFactory());
    }};

    private final static Set<String> validExtensions = new HashSet<>(Arrays.asList("json", "xml", "csv"));


    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("You should give both input and output files");
            return;
        }
        String inputFileName = args[0];
        String outputFileName = args[1];
        try (FileReader fileReader = new FileReader(inputFileName)) {
        } catch (IOException e) {
            System.out.println("Can't find a file with the name : " + inputFileName);
            return;
        }
        if (validateFileExtensions(getFileExtension(inputFileName), getFileExtension(outputFileName))) {
            InputHandler handler = new InputHandler(getFileExtension(inputFileName));
            try {
                List<InputRecord> inputRecords = handler.getRecordsFromInput(inputFileName);
                List<OutputRecord> outputRecords = inputRecords.stream().map(Main::processRecord).collect(Collectors.toList());
                handler.writeOutputRecords(outputFileName, outputRecords);
            } catch (IOException e) {
                System.out.println("Encountered an exception trying to read/write the file. Please check the input.");
            }
        }
    }

    private static boolean validateFileExtensions(String inExt, String outExt) {
        if (inExt == null || outExt == null || !validExtensions.contains(inExt) || !validExtensions.contains(outExt)) {
            System.out.println("Both input and output files should have one of the extensions - [\".json\", \".csv\", \".xml\"]");
            return false;
        }
        if (!inExt.equals(outExt)) {
            System.out.println("Input and output files should be of the same extension type.");
            return false;
        }
        return true;
    }

    private static String getFileExtension(String fileName) {
        String[] splitArray = fileName.split("\\.");
        return splitArray[splitArray.length - 1].toLowerCase();
    }

    private static OutputRecord processRecord(InputRecord record) {
        if (!record.isValid()) {
            return new OutputRecord(record.getCardNumber().orElseGet(() -> null), "Invalid", "InvalidCardNumber");
        }
        CreditCardType identifiedType = TypeChainManager.getType(record.getCardNumber().get());
        CreditCardFactory factory = factoryMap.get(identifiedType);
        if (factory == null) {
            return new OutputRecord(record.getCardNumber().get(), "Invalid", "InvalidCardNumber");
        }
        CreditCard card = factory.getCreditCard(record);
        return new OutputRecord(card.getNumber(), card.getType().toString(), "None");
    }
}
