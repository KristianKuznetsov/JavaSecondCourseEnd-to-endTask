package com.example.endtask;
import java.util.ArrayList;

public class testNode {
    String inData;
    ArrayList<String> arrayList;

    public testNode(String inStr, String answerStr ) {
        this.inData = inStr;
        this.arrayList = new ArrayList<String>();

        if(answerStr.contains("|")) {
            ArrayList<Integer> index = new ArrayList<Integer>();
            index.add(-1);
            for (int i = 0; i < answerStr.length(); i++)
                if (answerStr.substring(i, i + 1).equals("|")) index.add(i);

            for (int i = 0; i < index.size() - 1; i++) {
                int indStart = index.get(i) + 1;
                int indEnd = index.get(i + 1);
                arrayList.add(answerStr.substring(indStart, indEnd));
            }
            int indLast = index.get(index.size() - 1) + 1;
            arrayList.add(answerStr.substring(indLast));
        } else{
            arrayList.add(answerStr);
        }
    }

    public String getInData() {
        return inData;
    }

    public void setInData(String inData) {
        this.inData = inData;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "testNode{" +
                "inData='" + inData + '\'' +
                ", arrayList=" + arrayList +
                '}';
    }
}