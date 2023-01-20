package com.example.endtask;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestWriter {
    ArrayList<testNode> arrayList;

    public TestWriter() {
        this.arrayList = new ArrayList<testNode>();
        arrayList.add(new testNode("15 + 74 asd 90", "15+74|asd|90"));
        arrayList.add(new testNode("15 + 74 asd 90", "15+74|asd|90"));
        arrayList.add(new testNode("15.74 + 44,8 asd 90", "15.74+44,8|asd|90"));
        arrayList.add(new testNode("151+7,4 asd 9.0", "151+7,4|asd|9.0"));
        arrayList.add(new testNode("16 / 4 * (3 + 1) play 34 * 18 uuu 44.72 - 71", "16/4*(3+1)|play|34*18|uuu|44.72-71"));
        arrayList.add(new testNode("99 / 45", "99/45"));
        arrayList.add(new testNode("2*3 fff 43+ 7", "2*3|fff|43+7"));
        arrayList.add(new testNode("aaa15 2+ 74 asd 90 uuu", "aaa15|2+74|asd|90|uuu"));
        arrayList.add(new testNode("15/ ( 7-(1+ 1))* 3-(2 +(1+1))*15 /(7 -(200 +1 )) 3-(2+(1+1) )(15/(7-(1+1 ))*3- (2+(1+1 ))+15/(7-(1+1))*3-(2+(1+1)))",
                "15/(7-(1+1))*3-(2+(1+1))*15/(7-(200+1))3-(2+(1+1))(15/(7-(1+1))*3-(2+(1+1))+15/(7-(1+1))*3-(2+(1+1)))"));

    }

    public void writeJSON() throws IOException {
        FileWriter fr = new FileWriter("D:\\Pattern\\FinalTask\\src\\ParsingTestFile.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jString = objectMapper.writeValueAsString(this.arrayList);
        fr.write(jString);
        fr.close();
    }

}