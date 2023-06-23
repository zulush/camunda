package fr.deloitte.services;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import fr.deloitte.models.DmnTable;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
@Component
public class DMNParserService {

    public DmnTable parse(String filePath) throws ParserConfigurationException, IOException, SAXException {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Extract inputs
            List<String> inputs = new ArrayList<>();
            NodeList inputList = doc.getElementsByTagName("inputEntry");
            for (int i = 0; i < inputList.getLength(); i++) {
                Node inputNode = inputList.item(i);
                if (inputNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element inputElement = (Element) inputNode;
                    inputs.add(inputElement.getTextContent().replaceAll("[\"\\n\\s]+", ""));
                }
            }

            // Extract outputs
            List<String> outputs = new ArrayList<>();
            NodeList outputList = doc.getElementsByTagName("outputEntry");
            for (int i = 0; i < outputList.getLength(); i++) {
                Node outputNode = outputList.item(i);
                if (outputNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element outputElement = (Element) outputNode;
                    outputs.add(outputElement.getTextContent().replaceAll("[\"\\n\\s]+", ""));
                }
            }

        int rulesNumber = countOccurrences(filePath, "</rule>");
            int inputNumber = countOccurrences(filePath, "</inputEntry>") / rulesNumber;
            int outputNumber = countOccurrences(filePath, "</outputEntry>") / rulesNumber;


            return new DmnTable(inputNumber, outputNumber, inputs, outputs);
    }

    private int countOccurrences(String filePath, String target) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                count += (line.split(target, -1).length - 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
