package ru.jurden.calcs.table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.model.MortalityTable;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component // Добавлена аннотация @Component для управления Spring-ом
public class MortalityTableParser {

    public static final String DATA_NODE = "data";
    public static final String X_ATTR = "x";
    public static final String LX_ATTR = "lx";
    public static final String DX_ATTR = "dx";

    public MortalityTable parse(InputStream xmlStream, MortalityTableType tableType) {
        if (Objects.isNull(xmlStream)) {
            log.error("InputStream is null for table type: {}", tableType);
            return null;
        }

        Map<Integer, Integer> lx = new HashMap<>();
        Map<Integer, Integer> dx = new HashMap<>();

        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);

        try {
            XMLStreamReader reader = factory.createXMLStreamReader(xmlStream);

            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLStreamConstants.START_ELEMENT && DATA_NODE.equals(reader.getLocalName())) {
                    String xAttr = reader.getAttributeValue(null, X_ATTR);
                    String lxAttr = reader.getAttributeValue(null, LX_ATTR);
                    String dxAttr = reader.getAttributeValue(null, DX_ATTR);

                    if (Objects.nonNull(xAttr) && Objects.nonNull(lxAttr) && Objects.nonNull(dxAttr)) {
                        int x = Integer.parseInt(xAttr);
                        lx.put(x, Integer.parseInt(lxAttr));
                        dx.put(x, Integer.parseInt(dxAttr));
                    }
                }
            }
        } catch (XMLStreamException | NumberFormatException e) {
            log.error("Error parsing XML for {}: {}", tableType, e.getMessage());
            return null;
        }

        if (lx.isEmpty()) {
            log.error("No data found in XML for {}", tableType);
            return null;
        }

        return new MortalityTable(tableType, lx, dx);
    }

}