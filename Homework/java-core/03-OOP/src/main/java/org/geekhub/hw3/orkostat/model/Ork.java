package org.geekhub.hw3.orkostat.model;

public class Ork {

    private int price;

    public Ork() {
        this.price = 10_000;
    }
    public Ork(int price) {
        this.price = price;
    }

    public String scream() {
        return "Aaaaaa!";
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
