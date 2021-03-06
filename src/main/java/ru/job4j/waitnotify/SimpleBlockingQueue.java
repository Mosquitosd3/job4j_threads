package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() > size) {
                wait();
            }
            queue.offer(value);
            notify();
            System.out.println("offer " + value);
        }
    }

    public T poll() throws InterruptedException {
        T rsl = null;
        synchronized (this) {
            while (queue.isEmpty()) {
                wait();
            }
            rsl = queue.poll();
            System.out.println("poll: " + rsl);
            notify();
        }
        return rsl;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
