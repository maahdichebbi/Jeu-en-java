package com.company;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Pnj  implements Serializable {
    String nom ;
    int initiative ;
    int attaque ;
    int defense ;
    int degat ;
    int pv ;
   private int xp ;
    int niv ;
    String objets ;

    public Pnj(String nom ,int initiative , int attaque ,int defense,  int niv){
    this.initiative = initiative ;
    this.nom = nom ;
    this.attaque= attaque ;
    this.defense = defense ;
    this.niv = niv ;
    this.xp =  ThreadLocalRandom.current().nextInt(8, 30 + 1);
    switch (niv){
        case 1 :
            this.pv = ThreadLocalRandom.current().nextInt(4, 10);
            this.degat = ThreadLocalRandom.current().nextInt(5, 9);
            this.xp =  ThreadLocalRandom.current().nextInt(10, 31);
            break ;
        case 2 :
            this.pv = ThreadLocalRandom.current().nextInt(10, 21);
            this.degat = ThreadLocalRandom.current().nextInt(10, 14);
            this.xp =  ThreadLocalRandom.current().nextInt(30, 51);
            break ;
        case 3 :
            this.pv = ThreadLocalRandom.current().nextInt(21, 31);
            this.degat = ThreadLocalRandom.current().nextInt(14, 20);
            this.xp = ThreadLocalRandom.current().nextInt(50, 71);
            break ;
        case 4 :
            this.pv = ThreadLocalRandom.current().nextInt(36, 49);
            this.degat = ThreadLocalRandom.current().nextInt(20, 30);
            this.xp =  ThreadLocalRandom.current().nextInt(80, 120);

            break ;
    }

    }

    public int getPv() {
        return pv;
    }

    public String getNom() {
        return nom;
    }

    public int getXp() {
        return xp;
    }


}
