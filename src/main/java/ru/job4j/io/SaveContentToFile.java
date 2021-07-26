package ru.job4j.io;

import java.io.*;

public class SaveContentToFile implements Save {
    private final File file;

    public SaveContentToFile(File file) {
        this.file = file;
    }

    @Override
    public void saveContent(String content) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                out.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
