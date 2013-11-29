package hu.elte.java;


import hu.elte.java.epuletek.Lakohaz;
import hu.elte.java.epuletek.Uzlet;
import hu.elte.java.emberek.Ember;
import hu.elte.java.emberek.Gyermek;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectFactory {
    public Class getLineType(String line) {
        if (line == null || line.matches("^\\s*$")) {
            return null;
        } else if (line.matches("^\\s*lakohaz.*$")) {
            return Lakohaz.class;
        } else if (line.matches("^\\s*ember.*$")) {
            return Ember.class;
        } else if (line.matches("^\\s*gyermek.*$")) {
            return Gyermek.class;
        } else if (line.matches("^\\s*uzlet.*$")) {
            return Uzlet.class;
        }   else {
            return null;
        }

    }

    public Lakohaz getLivingHouse(String line) {
        if (getLineType(line) == Lakohaz.class) {
            Pattern p = Pattern.compile("^\\s*lakohaz (\\d+) (\\d+)");
            Matcher m = p.matcher(line);
            System.out.println(m);
            if (m.matches() && m.groupCount() == 2) {
                System.out.println(m.group()) ;
                int number = Integer.parseInt(m.group(1));
                int flats = Integer.parseInt(m.group(2));
                return new Lakohaz(number, flats);
            }
        }
        return null;
    }

    public Ember getPerson(String line) {
        if (getLineType(line) == Ember.class) {
            Pattern p = Pattern.compile("^\\s*ember\\s+(\\S+)\\s+(\\S+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)");
            Matcher m = p.matcher(line);
            if (m.matches() && m.groupCount() == 5) {
                String name = m.group(1);
                String sex = m.group(2);
                int age = Integer.parseInt(m.group(3));
                int number = Integer.parseInt(m.group(4));
                int money = Integer.parseInt(m.group(5));
                return new Ember(name, sex, age, number, money);
            }
        }
        return null;
    }

    public Gyermek getChild(String line) {
        if (getLineType(line) == Gyermek.class) {
            Pattern p = Pattern.compile("^\\s*gyermek\\s+(\\S+)\\s+(\\S+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\S+)\\s+(\\S+)");
            Matcher m = p.matcher(line);
            if (m.matches() && m.groupCount() == 7) {
                String name = m.group(1);
                String sex = m.group(2);
                int age = Integer.parseInt(m.group(3));
                int number = Integer.parseInt(m.group(4));
                int money = Integer.parseInt(m.group(5));
                String mother = m.group(6);
                String father = m.group(7);
                return new Gyermek(name, sex, age, number, money, father, mother);
            }
        }
        return null;

    }

    public Uzlet getBusiness(String line) {
        if (getLineType(line) == Uzlet.class) {
            Pattern p = Pattern.compile("^\\s*uzlet\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(.*)$");
            Matcher m = p.matcher(line);
            if (m.matches() && m.groupCount() == 4) {
                int number = Integer.parseInt(m.group(1));
                int cassa = Integer.parseInt(m.group(2));
                //int workers = Integer.parseInt(m.group(3));
                String workerNames = m.group(4);
                String [] names = workerNames.split(" ");
                return new Uzlet(number, cassa, names);
            }
        }
        return null;
    }
}
