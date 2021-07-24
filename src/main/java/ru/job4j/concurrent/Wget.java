package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int index = 0; index <= 100; index++) {
                    System.out.print("\nLoading : " + index +"%");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
