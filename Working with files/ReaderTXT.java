package com.example.endtask;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReaderTXT implements IReader{
    public String ReadData(String nameFile) throws FileNotFoundException {
        StringBuilder text = new StringBuilder();
        try (FileReader fileReader = new FileReader(nameFile)) {
            Scanner sc = new Scanner(fileReader);
            while (sc.hasNextLine()) {
                text.append(sc.nextLine());
            }
            text.append(" END_TEXT");

        } catch (IOException ex) {
            return "";
        }
        return text.toString();
    }

    public void WriteData(String nameFile, String text){
        try {
            FileWriter fileWriter = new FileWriter(nameFile + ".txt", false);
            fileWriter.write(text);
            fileWriter.flush();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}