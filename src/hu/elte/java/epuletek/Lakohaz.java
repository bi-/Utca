package hu.elte.java.epuletek;


public class Lakohaz extends Haz {

    public Lakohaz(int number, int max) {
        super(number, max);
    }

    public String toString() {
        return "Lakóház, " + super.toString();
    }
}
