package com.example.endtask;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluation {
    private ArrayList<String> stringArrayList;
    private ArrayList<Boolean> checkArrayList;

    public ArrayList<String> getStringArrayList() {
        return stringArrayList;
    }

    public ArrayList<Boolean> getCheckArrayList() {
        return checkArrayList;
    }

    public static boolean ParenthesesCheck(String startStr)  {
        String newStr = "";
        for(int i = 0; i < startStr.length(); i++){
            if(startStr.substring(i, i+1).equals("(")) newStr += "(";
            if(startStr.substring(i, i+1).equals(")")) newStr += ")";
        }

        int checkBracket = 0;
        for (int i = 0; i < newStr.length(); i++) {
            String oneSymbol = newStr.substring(i, i + 1);
            if (oneSymbol.equals("(")) checkBracket++;
            else checkBracket--;

            if (checkBracket < 0) return false;
        }
        return checkBracket==0;
    }

    public ExpressionEvaluation(String str) throws Exception {
        stringArrayList = new ArrayList<String>();
        checkArrayList = new ArrayList<Boolean>();

        ArrayList<String> tempArrayList = new ArrayList<String>();
        String[] words = str.split("\\s");
        Collections.addAll(tempArrayList, words);

        Boolean flag = false;
        StringBuilder tempStr = new StringBuilder();

        Pattern pattern = Pattern.compile("(\\+|-|\\*|\\/|\\(|\\)|\\d|,|\\.|PI|E)+");
        Pattern patternRepeat = Pattern.compile("((\\.{2,})|(,{2,})|(\\+{2,})|(-{2,})|(\\*{2,})|(\\/{2,}))");
        Pattern patternDoubleOperators = Pattern.compile("((\\+-)|(-\\+)|(\\+\\*)|(\\*\\+)|(\\+\\/)|(\\/\\+)|(-\\*)|(\\*-)|(-\\/)|(\\/-)|(\\*\\/)|(\\/\\*))");

        for (int i = 0; i < tempArrayList.size(); i++) {
            Matcher matcher = pattern.matcher(tempArrayList.get(i));
            Matcher matcherRepeat = patternRepeat.matcher(tempArrayList.get(i));
            Matcher matcherDoubleOperators = patternDoubleOperators.matcher(tempArrayList.get(i));

            if(matcher.matches() && !matcherRepeat.find() && !matcherDoubleOperators.find()) {
                tempStr.append(tempArrayList.get(i));
                flag = true;
            }
            else {
                if(flag) {
                    stringArrayList.add(tempStr.toString());
                    tempStr.setLength(0);
                    flag = false;
                    stringArrayList.add(tempArrayList.get(i));
                }
                else stringArrayList.add(tempArrayList.get(i));
            }
        }

        ArrayList<Integer> removeIndex = new ArrayList<Integer>();
        for (int j = 0; j < stringArrayList.size(); j++){
            if(stringArrayList.get(j).equals("")) removeIndex.add(j);
        }

        for (int j = removeIndex.size() - 1; j >= 0; j--){
            int ind = removeIndex.get(j);
            stringArrayList.remove(ind);
        }

        for(int j = 0; j < stringArrayList.size(); j++){
            Matcher matcher = pattern.matcher(stringArrayList.get(j));
            Matcher matcherRepeat = patternRepeat.matcher(stringArrayList.get(j));
            Matcher matcherDoubleOperators = patternDoubleOperators.matcher(stringArrayList.get(j));

            if(matcher.matches() && !matcherRepeat.find() && !matcherDoubleOperators.find()) checkArrayList.add(true);
            else checkArrayList.add(false);
        }

        for (int j = 0; j < stringArrayList.size(); j++){
            if(stringArrayList.get(j).equals("(") || stringArrayList.get(j).equals(")")) checkArrayList.set(j, false);

            if(checkArrayList.get(j).equals(true)){
                Boolean bracket = ExpressionEvaluation.ParenthesesCheck(stringArrayList.get(j));
                if(bracket.equals(false)) {
                    checkArrayList.set(j, false);
                    stringArrayList.set(j, ErrorClass.bracketError(stringArrayList.get(j)));
                }
            }
        }


        for (int j = 0; j < checkArrayList.size(); j++){
            if(checkArrayList.get(j).equals(true)){
                String expressionString = stringArrayList.get(j);
                expressionString = expressionString.replaceAll(",", "\\.");
                String result = "";
                try {
                    result = ArithmeticEvaluator.evaluate(expressionString);
                } catch (Exception e){
                    if(e.getMessage().equals("Divide by 0")) {
                        result = ErrorClass.divideByZeroError(expressionString);
                    } else{
                        result = ErrorClass.unknownError(expressionString);
                    }
                }

                stringArrayList.set(j, result);
            }
        }

    }
}
