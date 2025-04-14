package ru.jurden.calcs.tables;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.models.MortalityTable;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MortalityTableParser {

    public MortalityTable parse(InputStream xmlStream, MortalityTableType tableType) {
        Map<Integer, Integer> lx = new HashMap<>();
        Map<Integer, Integer> dx = new HashMap<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlStream);

            NodeList dataNodes = doc.getElementsByTagName("data");

            for (int i = 0; i < dataNodes.getLength(); i++) {
                Element dataElement = (Element) dataNodes.item(i);
                int x = Integer.parseInt(dataElement.getAttribute("x"));
                lx.put(x, Integer.parseInt(dataElement.getAttribute("lx")));
                dx.put(x, Integer.parseInt(dataElement.getAttribute("dx")));
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error("Error parsing XML: {}", e.getMessage());
        }

        return new MortalityTable(tableType, lx, dx);
    }

}