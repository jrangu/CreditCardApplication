import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlReadWriteStrategy implements ReadWriteStrategy {
    @Override
    public List<InputRecord> readFile(String inputFileName) throws IOException {
        try (FileInputStream fileReader = new FileInputStream(inputFileName)) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            Document document = documentBuilderFactory.newDocumentBuilder().parse(fileReader);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("row");
            List<InputRecord> result = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                result.add(new InputRecord(
                        getValue(element, "CardNumber"),
                        getValue(element, "ExpirationDate"),
                        getValue(element, "NameOfCardholder")
                ));
            }
            return result;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            //throw e;
            return new ArrayList<>();
        }
    }

    @Override
    public void writeToFile(String outputFileName, List<OutputRecord> outputRecords) throws IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //add elements to Document
            Element rootElement =
                    doc.createElement("root");
            //append root element to document
            doc.appendChild(rootElement);

            for (OutputRecord record : outputRecords) {
                rootElement.appendChild(getChildNode(doc, record));
            }
            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to file
            StreamResult file = new StreamResult(new File(outputFileName));

            //write data
            transformer.transform(source, file);

        } catch (Exception e) {

        }
    }

    private static Node getChildNode(Document doc, OutputRecord record) {
        Element row = doc.createElement("row");
        row.appendChild(getChild(doc, row, "CardNumber", record.getCardNumber()));
        row.appendChild(getChild(doc, row, "CardType", record.getCardType()));
        row.appendChild(getChild(doc, row, "Error", record.getError()));
        return row;
    }

    private static Node getChild(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private String getValue(Element element, String tag) {
        if (element.getElementsByTagName(tag).getLength() > 0) {
            if (element.getElementsByTagName(tag).item(0).getChildNodes().getLength() > 0) {
                return element.getElementsByTagName(tag).item(0).getChildNodes().item(0).getNodeValue();
            }
        }
        return null;
    }
}
