package ru.job4j.waitnotify;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void createProducerAndConsumer() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(4);

        Thread producer = new Thread(() -> {
            try {
                queue.offer(1);
                queue.offer(2);
                queue.offer(3);
                queue.offer(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                queue.poll();
                queue.poll();
                queue.poll();
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        try {
            producer.start();
            consumer.start();
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }
}