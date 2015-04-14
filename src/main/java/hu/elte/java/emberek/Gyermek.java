package hu.elte.java.emberek;


public class Gyermek extends Ember {
    private String mother;
    private String father;

    public Gyermek(String name, String sex, int age, int number, int money, String mother, String father) {
        super(name, sex, age, number, money);
        this.mother = mother;
        this.father = father;
    }

    public String toString() {
        return "Gyermek, neve: " + name + ", életkora: " + age + ", pénze: " + money;
    }

    public String getFather() {
        return father;
    }

    public String getMother() {
        return mother;
    }

    private boolean atSameLocation(Ember ember) {
        return ember != null && ember.getHouse().getNumber() == getHouse().getNumber();
    }

    public boolean canBuy(Ember mother, Ember father) {
        return atSameLocation(mother) || atSameLocation(father);
    }

    public String getParent() {
        return mother == null ? father : mother;
    }

    public String write() {
        //gyermek Anna  no    10 1 20  Bela Eva
        return "gyermek " + name + " " + nem.toString() + " " + age + " " + number + " " + money + " " + father + " " + mother;
    }
}
