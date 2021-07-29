package ru.job4j.waitnotify;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void createProducerAndConsumer() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(4);

        Thread producer = new Thread(() -> {
            queue.offer(1);
            queue.offer(2);
            queue.offer(3);
            queue.offer(4);
        });

        Thread consumer = new Thread(() -> {
            queue.poll();
            queue.poll();
            queue.poll();
            queue.poll();
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