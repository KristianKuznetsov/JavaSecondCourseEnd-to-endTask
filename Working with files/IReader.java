package com.example.endtask;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface IReader {
    String ReadData(String nameFile) throws IOException, ParserConfigurationException, SAXException;
    void WriteData(String fName, String text);
}
