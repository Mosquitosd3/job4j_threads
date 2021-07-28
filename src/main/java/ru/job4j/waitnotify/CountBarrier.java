package ru.job4j.waitnotify;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(int total) {
        this.total = total;
    }

    public void count() {
        count++;
        monitor.notifyAll();
    }

    private void await() throws InterruptedException {
        while (count < total) {
            monitor.wait();
        }
    }
}
