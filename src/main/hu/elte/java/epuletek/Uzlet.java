package hu.elte.java.epuletek;


import hu.elte.java.emberek.Ember;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Uzlet extends Haz {
    private String [] workerNames;
    private List<Ember> dolgozok = new ArrayList<Ember>();
    private int cassa;

    public Uzlet(int number, int cassa, String[] workers) {
         super(number, Integer.MAX_VALUE);
         this.cassa = cassa;
         this.workerNames = workers;
    }

    public boolean fizetes(int amount) {
        int salaries = dolgozok.size() * amount;
        if ( salaries > cassa) {
            System.err.println("Nincs elég pénz a kasszában.");
            return false;
        } else {
               for (Ember dolgozo : dolgozok) {
                   dolgozo.addMoney(amount);
                   cassa -= amount;
               }
        }
        return true;
    }


    public String toString() {
        return "Üzlet, Kassza: " + cassa  + ", dolgozók:" + Arrays.asList(workerNames) + " " + super.toString();
    }


    public String[] getWorkerNames() {
        return workerNames;
    }

    public void addWorker(Ember ember) {
        dolgozok.add(ember);
    }

    public boolean hasWorker() {
        List<String> wn = Arrays.asList(workerNames);
        for (Ember ember: dolgozok) {
            if (wn.contains(ember.getName())) {
                return true;
            }
        }
        return false;
    }

    public void vasarlas(int money) {
        cassa += money;
    }

    public String write() {
        String result = "uzlet " + number + " " + cassa + " " + workerNames.length  ;
        for (String wn : workerNames) {
            result += " " + wn;
        }
        return result;
    }
}
