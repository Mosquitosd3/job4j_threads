package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private final File file;
    private final Parser parser;

    public ParseFile(File file) {
        this.file = file;
        this.parser = new Content(file);
    }

    public String getContent() throws IOException {
        return parser.content(x -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return parser.content(x -> x < 0x80);
    }
}
