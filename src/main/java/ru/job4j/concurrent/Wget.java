package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(getNameFile())) {
            byte[] dataBuffer = new byte[1024];
            int byteRead;
            long startTime = System.currentTimeMillis();
            while ((byteRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, byteRead);
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                if (speed < time) {
                    Thread.sleep(time - speed);
                }
                startTime = System.currentTimeMillis();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getNameFile() throws MalformedURLException {
        URL address = new URL(url);
        String name = address.getFile();
        return name.substring(name.lastIndexOf("/") + 1);

    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Enter url and speed");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
