package com.example.endtask;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticParser {
    private static final Pattern tokenPattern = Pattern.compile("\\s*([0-9]*\\.?[0-9]+E(\\+|-)?[0-9]+|[A-Za-z]+|[0-9]*\\.?[0-9]+|\\S)\\s*");
    private static final Pattern operanPattern = Pattern.compile("^[0-9]*\\.?[0-9]+|[A-Za-z]|π");//[0-9]+E(\\+{0,1}|-)[0-9]+
    private static final Pattern opPattern = Pattern.compile("^\\+|-|\\*|/|\\(|\\)|\\^|\\$|%|!$");
    private static final Pattern funcPattern = Pattern.compile("^sqrt$");


    static final int BINARY  = 0;
    static final int UNARY = 1;

    public static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
    static
    {
        // Map<       "token" , []{Precedence, Arity} >
        OPERATORS.put("+"     , new int[] { 1, BINARY });
        OPERATORS.put("-"     , new int[] { 1, BINARY });
        OPERATORS.put("*"     , new int[] { 2, BINARY });
        OPERATORS.put("/"     , new int[] { 2, BINARY });
        OPERATORS.put("sqrt"  , new int[] { 3, UNARY });
        OPERATORS.put("%"     , new int[] { 3, UNARY });
        OPERATORS.put("$"     , new int[] { 4, UNARY });//unary negation {-a,-(a),(-a)}
        OPERATORS.put("("     , new int[] { 5, BINARY });


    }

    static int isBinary(String s) throws Exception{
        return OPERATORS.get(s)[1];
    }

    private static String[] toPostFix(String[] tokens){
        ArrayList<String> post = new ArrayList<>();//Result
        Stack<String> stack = new Stack<>();
        for(String s : tokens){
            if(isFunction(s)){
                stack.push(s);
            }
            else if(isOperator(s)) {
                if(s.equals("(")){
                    stack.push(s);
                    continue;
                }
                if (s.equals(")")) {
                    while (!stack.isEmpty()) {
                        String a = stack.peek();
                        if(!a.equals("("))
                            post.add(stack.pop());
                        else{
                            stack.pop();
                            if(!stack.isEmpty() && isFunction(stack.peek())){
                                post.add(stack.pop());
                            }
                            break;
                        }
                    }
                } else {
                    try {
                        while (!stack.isEmpty() && cmpPrecedence(s, stack.peek()) <= 0) {
                            if (!stack.peek().equals("(")) {
                                post.add(stack.pop());
                            }else {
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    stack.push(s);
                }
            }
            else if(isOperand(s)){
                post.add(s);
            }
        }
        while(!stack.isEmpty()){
            String s = stack.pop();
            if(!s.equals("("))
                post.add(s);
        }
        String[] output = new String[post.size()];
        return post.toArray(output);
    }

    public static String[] toPostFix(String code){
        String[] tokens = tokenizer(code);
        return toPostFix(expressionCleaner(tokens));
    }
    public static String[] tokenizer(String code){
        ArrayList<String> result = new ArrayList<>();
        Matcher matcher = tokenPattern.matcher(code);

        while(matcher.find()){
            result.add(matcher.group(1));
        }
        String[] output = new String[result.size()];
        return result.toArray(output);
    }

    private static String[] expressionCleaner(String[] tokens){
        ArrayList<String> result = new ArrayList<>();

        for(int i = 0; i < tokens.length ;i++){

            switch (tokens[i]) {
                case "-":
                    if (i == 0 || result.get(i - 1).equals("("))
                        tokens[i] = "$";
                    break;
            }

            if(i < tokens.length-1){
                if(tokens[i].equals(")") && ArithmeticParser.isOperand(tokens[i+1])) {
                    result.add(")");
                    result.add("*");
                    i++;
                }

            }
            result.add(tokens[i]);
        }

        String[] output = new String[result.size()];
        return result.toArray(output);

    }

    public static boolean isOperand(String token){
        return operanPattern.matcher(token).matches();
    }
    public static boolean isOperator(String token){
        return opPattern.matcher(token).matches();
    }
    public static boolean isFunction(String token){
        return funcPattern.matcher(token).matches();
    }

    public static int cmpPrecedence(String t1,String t2) throws Exception{
        return OPERATORS.get(t1)[0]-OPERATORS.get(t2)[0];
    }

    public static String toInt(String number){

        if(number.endsWith(".0")){
            return number.substring(0,number.length() - 2);
        }

        return number;
    }
}
