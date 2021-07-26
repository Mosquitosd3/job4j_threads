package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class Content implements Parser {
    private final File file;

    public Content(File file) {
        this.file = file;
    }

    @Override
    public String content(Predicate<Character> filter) {
        String output = "";
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while((data = in.read()) > 0) {
                if (filter.test((char) data))
                output += (char) data;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
