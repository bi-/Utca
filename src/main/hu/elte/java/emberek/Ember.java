package hu.elte.java.emberek;

import hu.elte.java.epuletek.Haz;

public class Ember {
    protected Nem nem;
    protected int age;
    protected String name;
    protected int money;
    protected int number;
    protected Haz house;

    public int getNumber() {
        return number;
    }



    public Ember(String name, String sex, int age, int number, int money) {
        this.name = name;
        this.nem = Nem.valueOf(sex);
        this.age = age;
        this.number = number;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Személy, neve: " + name + ", életkora: " + age + ", pénze: " + money;
    }

    public boolean equals(Object other) {
       if (other == null) {
           return false;
       }
       if (other instanceof Ember) {
           return name.equals(((Ember) other).getName());
       }
       return false;
    }

    public void setHouse(Haz house) {
        this.house = house;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public Haz getHouse() {
        return house;
    }

    public void levon(int amount) {
        money -= amount;
    }

    public int getMoney() {
        return money;
    }


    public String write() {
        return "ember " + name + " " + nem.toString()  + " " + age + " " + number + " " + money;
    }
}
