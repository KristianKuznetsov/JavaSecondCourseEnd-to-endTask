package com.example.endtask;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReaderJSON implements IReader{
    public String ReadData(String nameFile){
        String jsonBookArray = "";
        try( FileReader reader = new FileReader(nameFile)) {
            Scanner sc = new Scanner(new FileReader(nameFile));
            while (sc.hasNextLine()){
                jsonBookArray += sc.nextLine();
            }
            /*
            ТУТ обычно по тегам идет превращение в колекцию примерно так
            ObjectMapper objectMapper = new ObjectMapper();
            List<Class> list = objectMapper.readValue(jsonBookArray, new TypeReference<List<Class>>(){});
            но я не придумал какие теге нужно делать для обычного текста, так что оставиом вот такой вариант исполнения
            с xml файлами примерно также

            с записью файла соответственнор без тегов тоже не очень красиво
             */
        }catch(Exception e) {
            e.printStackTrace();
        }
        return jsonBookArray;
    }

    public void WriteData(String fileName, String text){
        try {
            FileWriter fileWriter = new FileWriter(fileName + ".json", false);
            fileWriter.write(text);
            fileWriter.flush();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}