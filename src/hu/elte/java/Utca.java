package hu.elte.java;

import hu.elte.java.epuletek.Haz;
import hu.elte.java.epuletek.Lakohaz;
import hu.elte.java.epuletek.Uzlet;
import hu.elte.java.emberek.Ember;
import hu.elte.java.emberek.Gyermek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Utca {
    private Map<Integer, Haz> houses = new TreeMap<Integer, Haz>();
    private Map<String, Ember> persons = new TreeMap<String, Ember>();

    public void addHouse(Haz haz) {
        if (haz == null) {
            return;
        }
        int number = haz.getNumber();
        if (!houses.containsKey(number)) {
            houses.put(number, haz);
        }
    }

    public void atmegy(String name, int number) {
        if (houses.containsKey(number)) {
            Haz haz = houses.get(number);
            if (haz.isFull()) {
                System.err.println("Nem fér több személy ebbe a házba: " + number);
            } else {
                if (persons.containsKey(name)) {
                    Ember ember = persons.get(name);
                    Haz regihaz = ember.getHouse();
                    if (regihaz != null) {
                        regihaz.remove(ember);
                    }
                    haz.add(persons.get(name));
                } else {
                       System.err.println("Nincs ilyen nevű személy: " + name);
                }
            }
        } else {
            System.err.println("Nincs ilyen hazszamú ház: " + number);
        }
    }

    private void vasarol(Ember ember, Uzlet uzlet, int amount) {
        if (ember.getMoney() >= amount) {
            ember.levon(amount);
            uzlet.vasarlas(amount);
        } else {
            System.err.println("Nincs elegendő pénze a vásárlónak: " + ember.getName() + ", " + ember.getMoney());
        }
    }

    public void buy(String name, int amount) {
        if (persons.containsKey(name)) {
            Ember ember = persons.get(name);
            Haz haz = ember.getHouse();
            if (haz != null && haz instanceof Uzlet) {
                Uzlet uzlet = (Uzlet)haz;
                if (uzlet.hasWorker()) {
                    if (ember instanceof Gyermek) {
                        Gyermek gyermek = (Gyermek) ember;
                        if (gyermek.canBuy(persons.get(gyermek.getMother()), persons.get(gyermek.getFather()))) {
                            vasarol(gyermek, uzlet, amount);
                        } else {
                            //  behiv szulo
                            System.err.println("Nincs szülő az üzletben.");
                            Ember parent = persons.get(gyermek.getParent());
                            System.err.println("Behivom: " + parent.getName());
                            atmegy(parent.getName(), uzlet.getNumber());
                            buy(gyermek.getName(), amount);
                        }
                    } else {
                        vasarol(ember, uzlet, amount);

                    }

                } else {
                    // behiv elado
                    System.err.println("Nincs eladó az üzletben.");
                    System.err.println("Behivom: " + uzlet.getWorkerNames()[0]);
                    atmegy(uzlet.getWorkerNames()[0], uzlet.getNumber());
                    buy(name, amount);
                }
            } else {
                System.err.println("A személy nem üzletben tartózkodik.");
            }
        } else {
            System.err.println("Nincs ilyen nevű személy: " + name);
        }
    }

    public void pay(int number, int amount) {
        if (!houses.containsKey(number)) {
            System.err.println("Ismeretlen házszám: " + number);
            return;
        }
        Haz business = houses.get(number);
        if (business instanceof Uzlet) {
            Uzlet uzlet1 = (Uzlet)business;
            uzlet1.fizetes(amount);
        } else {
            System.err.println("Az ház nem üzlet: " + number);
        }


    }

    public void addPerson(Ember ember) {
        if (!persons.containsKey(ember.getName()))   {
            persons.put(ember.getName(), ember);
        }
    }

    public void kiir() {
        for (Haz haz : houses.values()){
            System.out.println(haz.toString());
        }
    }

    public void save(String s) {
        try {
            PrintWriter pw = new PrintWriter(new File(s));
            for (Haz haz: houses.values()) {
                if (haz instanceof Lakohaz) {
                    pw.println(haz.write());
                }
            }
            List<Gyermek> gyermekek = new ArrayList<Gyermek>();
            for (Ember ember : persons.values()) {
                if (ember instanceof Gyermek) {
                       gyermekek.add((Gyermek)ember);
                } else {
                    pw.println(ember.write());                }
            }
            for (Gyermek gyermek : gyermekek) {
                pw.println(gyermek.write()) ;
            }
            for (Haz haz: houses.values()) {
                if (haz instanceof Uzlet) {
                    pw.println(haz.write());
                }
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.err.println("A fajl nem talalhato: " + s);
        }

    }

    public void alaphelyzet() {
        for (Ember ember : this.persons.values()) {
            int hazszam = ember.getNumber();
            if (houses.containsKey(hazszam)) {
                houses.get(hazszam).add(ember);
            }
        }
        for (Haz haz: houses.values()) {
            if (haz instanceof Uzlet) {
                String[] nevek = ((Uzlet)haz).getWorkerNames();
                for (String nev: nevek) {
                    if (persons.containsKey(nev)) {
                        atmegy(nev, haz.getNumber());
                        ((Uzlet)haz).addWorker(persons.get(nev));
                    }
                }
            }
        }
    }
}
