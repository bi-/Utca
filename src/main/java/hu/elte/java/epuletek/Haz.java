package hu.elte.java.epuletek;

import hu.elte.java.emberek.Ember;

import java.util.ArrayList;
import java.util.List;

public abstract class Haz {
    protected int number;
    protected int max;
    protected List<Ember> emberek;

    public Haz(int number, int max) {
        this.number = number;
        this.max = max;
        this.emberek = new ArrayList<Ember>();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Házszám: " + number + ", Lakók max száma: " + max + ", Lakók jelenlegi száma: " + emberek.size());
        if (emberek.size() > 0) {
            sb.append("\n A házban vannak:");
            for (Ember ember : emberek) {
                sb.append("\n");
                sb.append("  " + ember.toString());
            }
        }
        return sb.toString();
    }

    public int getNumber() {
        return number;
    }

    public boolean isFull() {
        return emberek.size() >= max;
    }

    public void add(Ember ember) {
        if (!emberek.contains(ember)) {
            emberek.add(ember);
            ember.setHouse(this);
        }
    }

    public void remove(Ember ember) {
        if (emberek.contains(ember)) {
            emberek.remove(ember);
        }
    }

    public String write() {
        return "lakohaz " + number + " " + max;
    }

}
