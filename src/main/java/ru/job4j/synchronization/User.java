package ru.job4j.synchronization;

public class User {
    private int amount;
    private int id;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }


    public int getId() {
        return id;
    }
}
