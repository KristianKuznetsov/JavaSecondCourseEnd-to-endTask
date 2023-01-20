package com.example.endtask;
import java.io.*;
import java.util.Base64;

public class Encryption {
    private String fileName;

    public Encryption() {
    }

    public String Encode(String name) {
        try (FileInputStream fis = new FileInputStream(name)) {
            Base64.Encoder enc = Base64.getEncoder();
            OutputStream os = enc.wrap(new FileOutputStream(name + ".enc"));
            int _byte;
            while ((_byte = fis.read()) != -1) {
                os.write(_byte);
            }
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name + ".enc";
    }
    public String Decode(String name) throws IOException {
        String newName = name.substring(0, name.length() - 4);
        FileOutputStream fos = new FileOutputStream(newName);
        Base64.Decoder decoder = Base64.getDecoder();
        InputStream is = decoder.wrap(new FileInputStream(name));
        int _byte;
        while ((_byte = is.read()) != -1)
            fos.write(_byte);
        return newName;
    }
}
