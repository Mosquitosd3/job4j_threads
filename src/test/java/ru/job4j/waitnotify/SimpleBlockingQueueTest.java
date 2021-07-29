package ru.job4j.waitnotify;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
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

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();

        Thread consumer = new Thread(() -> {
            while (! queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    buffer.add(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}