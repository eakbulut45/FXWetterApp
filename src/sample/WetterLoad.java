package sample;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WetterLoad {

    private static WetterLoad instance;

    public WetterLoad() {
    }

    public static WetterLoad getInstance() {
        if (instance == null) {
            instance = new WetterLoad();
        }
        return instance;
    }

    public WetterInfo[] loads(String city) throws Exception {
        String uri = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&mode=xml&appid=53b2f8cb3a8e502f9dc9ee08b6c09fb0";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(uri);
        NodeList times = document.getElementsByTagName("time");
        WetterInfo[] wetterInfos = new WetterInfo[times.getLength()];

        for (int x = 0; x < times.getLength(); x++) {
            Node time = times.item(x);
            NamedNodeMap timeAttributes = time.getAttributes();
            String timestamp = timeAttributes.getNamedItem("from").getNodeValue();
            NodeList children = time.getChildNodes();

            for (int y = 0; y < children.getLength(); y++) {
                Node child = children.item(y);
                if (child.getNodeName().equals("temperature")) {
                    Node child1 = children.item(y);
                    String temperature = child1.getAttributes().getNamedItem("value").getNodeValue();
                    wetterInfos[x] = new WetterInfo(timestamp, temperature, null, null);
                }
            }
            for (int y = 0; y < children.getLength(); y++) {
                Node child = children.item(y);
                if (child.getNodeName().equals("windSpeed")) {
                    String windSpeed = child.getAttributes().getNamedItem("mps").getNodeValue();
                    wetterInfos[x] = new WetterInfo(timestamp, wetterInfos[x].getTemparature(), windSpeed, null);
                }
            }
            for (int y = 0; y < children.getLength(); y++) {
                Node child = children.item(y);
                if (child.getNodeName().equals("humidity")) {

                    String humidity = child.getAttributes().getNamedItem("value").getNodeValue();
                    wetterInfos[x] = new WetterInfo(timestamp, wetterInfos[x].getTemparature(), wetterInfos[x].getSpeed(), humidity);
                }
            }
        }
        return wetterInfos;
    }
}