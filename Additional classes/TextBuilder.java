package com.example.endtask;
import java.util.ArrayList;

public class TextBuilder {
    public static String generateTextWithWidth(ArrayList<String> list, int width){
        StringBuilder newText = new StringBuilder();
        int tempSize = 0;

        for (String el : list) {
            if(tempSize + 1 + el.length() <= width){
                tempSize += 1;
                tempSize += el.length();
                newText.append(el + " ");
            } else{
                newText.append("\n" + el + " ");
                tempSize = el.length() + 1;
            }
        }
        return newText.toString();
    }
}