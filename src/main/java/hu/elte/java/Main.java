package hu.elte.java;

import hu.elte.java.emberek.Ember;
import hu.elte.java.emberek.Gyermek;
import hu.elte.java.epuletek.Lakohaz;
import hu.elte.java.epuletek.Uzlet;

import java.io.*;

/*
    lakohaz 1 4
	lakohaz 9 3
	lakohaz 7 1

	ember   Bela  ferfi 40 1 200
	ember   Eva   no    30 1 300
	ember   Janos ferfi 20 7 400

	gyermek Anna  no    10 1 20  Bela Eva

	uzlet 4 400 1 Bela
	uzlet 6 100 2 Eva Janos
 */
public class Main {

    public static void main(String[] args) {
        Utca utca = new Utca();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
            String line;
            ObjectFactory factory = new ObjectFactory();
            while ((line = reader.readLine()) != null) {
                Class clazz = factory.getLineType(line);
                if (clazz == Uzlet.class) {
                    Uzlet uzlet = factory.getBusiness(line);
                    utca.addHouse(uzlet);
                } else if (clazz == Lakohaz.class) {
                    Lakohaz lhouse = factory.getLivingHouse(line);
                    utca.addHouse(lhouse);
                } else if (clazz == Ember.class) {
                    Ember ember = factory.getPerson(line);
                    utca.addPerson(ember);
                } else if (clazz == Gyermek.class) {
                    Ember ember = factory.getChild(line);
                    utca.addPerson(ember);
                }
            }
            utca.alaphelyzet();
        } catch (FileNotFoundException e) {
            System.err.println("A fájl nem található: " + args[0]);
        } catch (IOException e) {
            System.err.println("Ki/bemeneti hiba.");
        }

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String command = null;
            try {
                command = bufferRead.readLine();
            } catch (IOException e) {
                continue;
            }
            String[] splitted = command.split(" ");
            String parancs = splitted[0];
            if ("kilep".equals(parancs)) {
                System.exit(0);
            } else if ("kiir".equals(parancs)) {
                utca.kiir();
            } else if ("elment".equals(parancs)) {
                utca.save(splitted[1]);
            } else if ("atmegy".equals(parancs)) {
                try {
                    int hazszam = Integer.parseInt(splitted[2]);
                    utca.atmegy(splitted[1], hazszam);
                } catch (NumberFormatException nfe) {
                    hiba(parancs);
                }
            } else if ("vasarol".equals(parancs)) {
                try {
                    int osszeg = Integer.parseInt(splitted[2]);
                    utca.buy(splitted[1], osszeg);
                } catch (NumberFormatException nfe) {
                    hiba(parancs);
                }
            } else if ("fizetes".equals(parancs)) {
                try {
                    int hazszam = Integer.parseInt(splitted[1]);
                    int osszeg = Integer.parseInt(splitted[2]);
                    utca.pay(hazszam, osszeg);
                } catch (NumberFormatException nfe) {
                    hiba(parancs);
                }
            } else {
                hiba(parancs);
            }
        }
    }

    private static void hiba(String parancs) {
        System.err.println("Ismeretlen parancs: " + parancs);
        System.err.print("Lehetséges parancsok:");
        System.err.print(" kilep");
        System.err.print(", kiir");
        System.err.print(", elment <fájlnév>");
        System.err.print(", atmegy <név> <házszám>");
        System.err.print(", vasarol <név> <összeg>");
        System.err.println(", fizetes <házszám> <összeg>");
    }

}
