package com.example.endtask;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Reader {
    private String fileName;
    public Reader(){
        this.fileName = null;
    }

    public String ReadData() throws IOException,  ParserConfigurationException, SAXException {
        return "";
    }

    public String ReadData(String nameFile) throws IOException, ParserConfigurationException, SAXException {
        String text = "";
        while(nameFile.endsWith(".enc") || nameFile.endsWith(".zip")){
            if(nameFile.endsWith(".zip")) {
                ZIPArchiving archiving = new ZIPArchiving();
                nameFile = archiving.UnArchive(nameFile);
            }
            if(nameFile.endsWith(".enc")){
                Encryption enc = new Encryption();
                nameFile = enc.Decode(nameFile);
            }
        }
        if(nameFile.endsWith("txt")){
            ReaderTXT reader = new ReaderTXT();
            text = reader.ReadData(nameFile);
        }
        else if(nameFile.endsWith("json")) {
            ReaderJSON reader = new ReaderJSON();
            text = reader.ReadData(nameFile);
        }
        else if(nameFile.endsWith("xml")) {
            ReaderXML reader = new ReaderXML();
            text = reader.ReadData(nameFile);
        }
        return text;
    }

    public void WriteData(String fName, String text){
        if(fName.endsWith(".json")){
            ReaderJSON readerJSON = new ReaderJSON();
            readerJSON.WriteData(fName.substring(0, fName.lastIndexOf(".json")), text);
        }
        else if(fName.endsWith(".xml")) {
            ReaderXML readerXML = new ReaderXML();
            readerXML.WriteData(fName.substring(0, fName.lastIndexOf(".xml")), text);
        }
        else if(fName.endsWith(".txt")) {
            ReaderTXT readerTXT = new ReaderTXT();
            readerTXT.WriteData(fName.substring(0, fName.lastIndexOf(".txt")), text);
        }
        else {
            ReaderTXT readerTXT = new ReaderTXT();
            readerTXT.WriteData(fName, text);
        }
    }
}
