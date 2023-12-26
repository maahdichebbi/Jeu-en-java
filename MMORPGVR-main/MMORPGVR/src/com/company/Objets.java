package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Objets implements Serializable {
    String nom  ;
    int puissance ;

    public Objets(String nom , int puissance){

        this.nom = nom ;
        this.puissance = puissance;


    }



    public int getPuissance(){
        return this.puissance;
    }

    public String toString(){
        String s ;
        s = new String(nom) ;
        return s;
    }

    public static String Cout(String nom){

        String s = " "  ;
        switch (nom) {
            case "Potion de soin ":
                s = " 1 PA " ;
                break;
            case "tome de connaissance":
                s = " 2 PA " ;
                break;

            case "molotov":
                s = " 3 PA " ;
                break;

            default:
                s = "0 PA";
        }
        return  s ;
    }

        public static Objets Loot () {
            ArrayList<Objets> objets= new ArrayList<>();
            objets.add(0,new Objets("Potion de soin", 10));
            objets.add(1,new Objets("molotov", 4));
            objets.add(2,new Objets("tome de connaissance", 20));
            objets.add(3,new Objets("Potion d'energie", 5));

            return objets.get(ThreadLocalRandom.current().nextInt(0, objets.size()));


        }




}
