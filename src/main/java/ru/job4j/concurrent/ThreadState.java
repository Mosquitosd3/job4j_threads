package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        Thread second = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        System.out.println(first.getState());
        System.out.println(second.getState());

        first.start();
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Работа завершена");
    }
}
