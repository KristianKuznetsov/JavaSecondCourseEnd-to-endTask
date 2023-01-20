package com.example.endtask;

public class ErrorClass {
    public static String bracketError(String str) {
        return "[ Bracket validation error in expression { " + str + " } ]";
    }

    public static String overflowError(String str){
        return "[ Overflow error in expression { " + str + " } ]";
    }

    public static String divideByZeroError(String str){
        return "[ Division by zero error in expression { " + str + " } ]";
    }

    public static String unknownError(String str){
        return "[ Undefined error in expression { " + str + " } ]";
    }
}
