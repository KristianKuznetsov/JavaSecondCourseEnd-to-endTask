package com.example.endtask;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.File;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;




public class ReaderXML {
    public String ReadData(String nameFile) throws IOException, SAXException, ParserConfigurationException, ParserConfigurationException, IOException, SAXException {
        StringBuilder text = new StringBuilder();
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(nameFile);
        Node root = document.getDocumentElement();
        NodeList examples = root.getChildNodes();
        for (int i = 0; i < examples.getLength(); i++) {
            Node example = examples.item(i);
            if (example.getNodeType() != Node.TEXT_NODE) {
                text.append(example.getChildNodes().item(0).getTextContent());
            }
        }
        return text.toString();
    }

    public void WriteData(String nameFile, String text) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("Users");
            Element user = doc.createElement("user");
            user.setTextContent(text);
            user.setAttribute ("text", " ");
            root.appendChild(user);
            doc.appendChild(root);

            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //печатаем в файл
            StreamResult file = new StreamResult(new File(nameFile + ".xml"));
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}