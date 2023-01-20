package com.example.endtask;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class TestClass {

    //Block of testing the function of checking the sequence of brackets
    /////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void trueVoidTestBracket() throws Exception{
        Boolean res = ExpressionEvaluation.ParenthesesCheck("(");
        Assert.assertEquals(res, false);
    }

    @Test
    public void trueSimpleTestBracket() throws Exception{
        Boolean res = ExpressionEvaluation.ParenthesesCheck("()");
        Assert.assertEquals(res, true);
    }

    @Test
    public void falseSimpleTestRightBracket() throws Exception{
        Boolean res = ExpressionEvaluation.ParenthesesCheck(")()");
        Assert.assertEquals(res, false);
    }

    @Test
    public void falseSimpleTestLeftBracket() throws Exception{
        Boolean res = ExpressionEvaluation.ParenthesesCheck("()(");
        Assert.assertEquals(res, false);
    }

    @Test
    public void bracketFileTest() throws Exception{
        FileReader fileReader = new FileReader("D:\\Pattern\\FinalTask\\src\\BracketTestFile");
        Scanner sc = new Scanner(fileReader);

        while (sc.hasNextLine()) {
            String testStr = sc.nextLine();
            int ind = testStr.indexOf("->");
            String sT = testStr.substring(0, ind - 1);
            String sB = testStr.substring(ind + 3);
            Boolean res = ExpressionEvaluation.ParenthesesCheck(sT);
            Boolean expect = (sB.equals("true")) ? true : false;
            Assert.assertEquals(res, expect);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////

    //String parsing test block
    /////////////////////////////////////////////////////////////////////////////////////////

    public static void readJSON(ArrayList<testNode> arrayList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBookArray = "";
        Scanner sc = new Scanner(new FileReader("D:\\Pattern\\FinalTask\\src\\ParsingTestFile.json"));
        while (sc.hasNextLine()){
            jsonBookArray += sc.nextLine();
        }
        System.out.println(jsonBookArray);

        List<testNode> list = objectMapper.readValue(jsonBookArray, new TypeReference<List<testNode>>(){});
        arrayList = new ArrayList<testNode>(list);
    }

    public static Boolean ALSCompare(ArrayList<String> firstList, ArrayList<String> secondList){
        if(firstList.size() != secondList.size()) return false;
        for (int i = 0; i < firstList.size(); i++){
            if(!firstList.get(i).equals(secondList.get(i))) return false;
        }
        return true;
    }

    @Test
    public void parsingFileTest() throws Exception{
        ArrayList<testNode> arrayList = new ArrayList<testNode>();
        TestWriter testWriter = new TestWriter();
        arrayList = testWriter.arrayList;

        for (testNode el : arrayList) {
            ExpressionEvaluation t = new ExpressionEvaluation(el.getInData());
            Boolean res = TestClass.ALSCompare(t.getStringArrayList(), el.getArrayList());

        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    //Error block testing
    /////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testBracketError() throws Exception{
        Assert.assertEquals(ErrorClass.bracketError("((55+44)"),
                "[ Bracket validation error in expression { ((55+44) } ]");
    }

    @Test
    public void testOverflowError() throws Exception{
        Assert.assertEquals(ErrorClass.overflowError("9999999999999*999999999999"),
                "[ Overflow error in expression { 9999999999999*999999999999 } ]");
    }

    @Test
    public void testDivideByZeroError() throws Exception{
        Assert.assertEquals(ErrorClass.divideByZeroError("10/0"),
                "[ Division by zero error in expression { 10/0 } ]");
    }

    /////////////////////////////////////////////////////////////////////////////////////////


    //Calculation block testing

    //ArithmeticEvaluatorTest
    /////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void ArithmeticEvaluatorTest() throws Exception{
        Assert.assertEquals("22.0", ArithmeticEvaluator.evaluate("4*5+2"));
        Assert.assertEquals("4.0", ArithmeticEvaluator.evaluate(" 2 * sqrt(4)"));
        Assert.assertEquals("1137.0", ArithmeticEvaluator.evaluate("32 * 4 + 1 + 6 * 34 * 8 - 12 * 52"));
        Assert.assertEquals("404.0", ArithmeticEvaluator.evaluate("304 / 16 + 11 * (16 + 19)"));
        Assert.assertEquals("1764.0", ArithmeticEvaluator.evaluate("32 * 4 / (2 + 6) * 113 - 8 * 12 + 52"));
        Assert.assertEquals("42.0",ArithmeticEvaluator.evaluate("sqrt(32 * 4 / (2 + 6) * 113 - 8 * 12 + 52)"));
        Assert.assertEquals("-30.072164948453608", ArithmeticEvaluator.evaluate("15/(7-(1+1))*3-(2+(1+1))*15/(7-(200+1))*3-(2+(1+1))*(15/(7-(1+1))*3-(2+(1+1))+15/(7-(1+1))*3-(2+(1+1)))"));
    }
    /////////////////////////////////////////////////////////////////////////////////////////

    //RdpCalculatorTest
    /////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void RdpCalculatorTest() throws Exception{
        Assert.assertEquals("22.0", RdpCalculator.evaluate("4*5+2"));
        Assert.assertEquals("4.0", RdpCalculator.evaluate(" 2 * sqrt(4)"));
        Assert.assertEquals("1137.0", RdpCalculator.evaluate("32 * 4 + 1 + 6 * 34 * 8 - 12 * 52"));
        Assert.assertEquals("404.0", RdpCalculator.evaluate("304 / 16 + 11 * (16 + 19)"));
        Assert.assertEquals("1764.0", RdpCalculator.evaluate("32 * 4 / (2 + 6) * 113 - 8 * 12 + 52"));
        Assert.assertEquals("42.0",RdpCalculator.evaluate("sqrt(32 * 4 / (2 + 6) * 113 - 8 * 12 + 52)"));
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void ArchiveTest() throws Exception{
        ZIPArchiving zipArchiving = new ZIPArchiving();
        String zipFile = zipArchiving.Archive("D:\\Pattern\\EndTask\\testArhiving.txt");
        File file = new File(zipFile);
        Assert.assertNotNull(file);
    }

    @Test
    public void UnArchiveTest() throws Exception{
        ZIPArchiving zipArchiving = new ZIPArchiving();
        String UnZipFile = zipArchiving.UnArchive("D:\\Pattern\\EndTask\\testArhiving.txt.zip");
        File file = new File(UnZipFile);
        Assert.assertNotNull(file);
    }

    @Test
    public void EncryptionTest() throws Exception{
        Encryption encryption = new Encryption();
        String encryptFile = encryption.Encode("D:\\Pattern\\EndTask\\testArhiving.txt");
        File file = new File(encryptFile);
        Assert.assertNotNull(file);
    }

    @Test
    public void ReadTxtTest() throws Exception{
        ReaderTXT readerTXT = new ReaderTXT();
        String sTest =  readerTXT.ReadData("D:\\Pattern\\EndTask\\testArhiving.txt");
        Assert.assertEquals("rrrr 67 rrrr END_TEXT",sTest);
    }
}