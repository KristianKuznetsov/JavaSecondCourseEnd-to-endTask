package com.example.endtask;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZIPArchiving {
    public String UnArchive(String fileName) {
        String newFileName = fileName.substring(0, fileName.length() - 4);
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(fileName))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                newFileName = entry.getName();
                FileOutputStream fout = new FileOutputStream(newFileName);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return newFileName;
    }

    public String Archive(String fileName) {
        String newFileName = fileName + ".zip";
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(newFileName));
             FileInputStream fis = new FileInputStream(fileName);) {
            ZipEntry entry = new ZipEntry(fileName);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return newFileName;
    }
}
