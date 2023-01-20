package com.example.endtask;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final String[] inFileName = {""};
        final String[] outFileName = {"outStandard"};
        final String[] resultText = {""};
        final Boolean[] flag = {false};
        final int textSize = 15;
        final int weightSize = 55;
        final int highSize = 28;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("Данные");
        Menu menu2 = new Menu("Дополнительно");

        MenuItem inFile = new MenuItem("Выбрать файл");
        MenuItem outFile = new MenuItem("Записать файл");
        MenuItem archiveFile = new MenuItem("Заархивировать файл");
        MenuItem encryptFile = new MenuItem("Закодировать файл");
        MenuItem instruction = new MenuItem("Инструкция");
        MenuItem clearArea = new MenuItem("Очистить поле");

        menu1.getItems().add(inFile);
        menu1.getItems().add(outFile);
        menu1.getItems().add(archiveFile);
        menu1.getItems().add(encryptFile);
        menu2.getItems().add(instruction);
        menu2.getItems().add(clearArea);

        menuBar.getMenus().add(menu1);
        menuBar.getMenus().add(menu2);

        Label leftLabel = new Label("Исходный текст");
        Label rightLabel = new Label("Преобразованный текст");
        Label voidLabel = new Label();
        TextField textField = new TextField();
        leftLabel.setFont(new Font(20));
        rightLabel.setFont(new Font(20));
        TextArea RightTextArea = new TextArea("");
        //RightTextArea.setFont(new Font(textSize));
        TextArea LeftTextArea = new TextArea("");
        //LeftTextArea.setFont(new Font(textSize));
        RightTextArea.setPrefColumnCount(weightSize);
        RightTextArea.setPrefRowCount(highSize);
        LeftTextArea.setPrefColumnCount(weightSize);
        LeftTextArea.setPrefRowCount(highSize);
        VBox RVBox = new VBox(rightLabel, RightTextArea);
        VBox LVBox = new VBox(leftLabel, LeftTextArea);
        Separator separator = new Separator(Orientation.HORIZONTAL);
        HBox hBox = new HBox( LVBox, separator, RVBox);
        VBox mainVBox = new VBox(menuBar, hBox, voidLabel, textField);

       inFile.setOnAction((new EventHandler<ActionEvent>() {
           public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    inFileName[0] = file.toPath().toString();
                    Reader reader = new Reader();
                    try {
                        String text = reader.ReadData(inFileName[0]);
                        LeftTextArea.setText(text);
                        ExpressionEvaluation expressionEvaluation = new ExpressionEvaluation(text);
                        resultText[0] = TextBuilder.generateTextWithWidth(expressionEvaluation.getStringArrayList(), 80);
                        RightTextArea.setText(resultText[0]);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ParserConfigurationException e) {
                        throw new RuntimeException(e);
                    } catch (SAXException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }));

        outFile.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String temp = textField.getText();
                textField.setText("");
                if(!temp.equals("")) outFileName[0] = textField.getText();
                Reader reader = new Reader();
                reader.WriteData("D:\\Pattern\\EndTask\\src\\main\\java\\com\\example\\endtask\\" + outFileName[0], resultText[0]);
                flag[0] = true;
            }
        }));

        archiveFile.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(flag[0].equals(true)){
                    ZIPArchiving zipArchiving = new ZIPArchiving();
                    outFileName[0] = zipArchiving.Archive(outFileName[0]);
                }
            }
        }));

        encryptFile.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(flag[0].equals(true)){
                    Encryption encryption = new Encryption();
                    outFileName[0] = encryption.Encode(outFileName[0]);
                }
            }
        }));

        instruction.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                StringBuilder text = new StringBuilder();
                try (FileReader fileReader = new FileReader("D:\\Pattern\\EndTask\\src\\main\\java\\com\\example\\endtask\\tutorial.txt")) {
                    Scanner sc = new Scanner(fileReader);
                    while (sc.hasNextLine()) {
                        text.append(sc.nextLine());
                        text.append('\n');
                    }
                    LeftTextArea.setFont(new Font(16));
                    LeftTextArea.setText(text.toString());

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }));

        clearArea.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                RightTextArea.setText("");
                LeftTextArea.setText("");
                textField.setText("");
            }
        }));

        Group group = new Group();
        group.getChildren().add(mainVBox);
        Scene scene = new Scene(group, 1300, 600);

        scene.setFill(new LinearGradient(
                0, 0, 1, 1, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#81c483")),
                new Stop(1, Color.web("#fcc200")))
        );

        stage.setTitle("Калькулятор");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}