package ru.job4j.synchronization;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user.getId()) != null;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User from = users.get(fromId);
        User to = users.get(toId);

        if (from != null && to != null) {
            if (from.getAmount() >= amount) {
                update(new User(fromId, from.getAmount() - amount));
                update(new User(toId, to.getAmount() + amount));
            }
        }
    }

}
