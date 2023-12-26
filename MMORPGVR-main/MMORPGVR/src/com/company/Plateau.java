package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Plateau extends Joueur implements Serializable {




    public    Plateau() {
    }
    public static ArrayList generate() {
        ArrayList plat = new ArrayList();
        // tous les types d'equpement dns le jeu ;
        ArrayList<equipement> equipements  = new ArrayList<equipement>();
        equipements.add(0,new equipement("Longue épée",0,0,6,6));
        equipements.add(1,new equipement("armure de chevalier",4,8,0,0));
        equipements.add(2,new equipement("épée cassée",0,0,1,2));
        equipements.add(3,new equipement("vêtements de cuir",4,4,0,0));
        equipements.add(4,new equipement("Épée bâtarde",0,0,4,8));
        equipements.add(5,new equipement("vêtements déchirer",3,1,0,0));
        equipements.add(6,new equipement("lance",0,0,8,5));
        equipements.add(7,new equipement("armure legendaire",1,10,0,0));
        equipements.add(8,new equipement("epée legendaire",0,0,15,15));


        // tous les types d'objets dns le jeu ;
        ArrayList<Objets> objets= new ArrayList<>();
        objets.add(0,new Objets("Potion de soin", 10));
        objets.add(1,new Objets("molotov", 4));
        objets.add(2,new Objets("tome de connaissance", 40));
        objets.add(3,new Objets("Potion d'energie", 5));

        // les PNJ
        ArrayList<Pnj> monstres = new ArrayList<>( );
        monstres.add(0,new Pnj("loup garou",7,6,2, ThreadLocalRandom.current().nextInt(1, 5)));
        monstres.add(1,new Pnj("troll",8,8,4, ThreadLocalRandom.current().nextInt(1, 5)));
        monstres.add(2,new Pnj("Skeleton",7,6,2, ThreadLocalRandom.current().nextInt(2, 5)));




        String blocks[] = {" ", " ", "x", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "x", " "};
        String mur[] = {"#"};
        int numsV[] = {42, 66, 72,133,141,193,180,220,232,252,280,300,340};
        int numsH[] = { 66,  135,210,290};
        for (int i = 1; i <= 400; i++) {
            if (i < 21) {
                plat.add(mur[0]);

            } else if (i > 380) {
                plat.add(mur[0]);
            } else if (i % 20 == 1) {
                plat.add(mur[0]);
            } else if (i % 20 == 2) {
                plat.add(mur[0]);
            } else {
                plat.add(blocks[ThreadLocalRandom.current().nextInt(0, blocks.length)]);
            }

        }
        while (plat.indexOf("x") != -1) {  // emplacement d'objets
            int r = ThreadLocalRandom.current().nextInt(1, 4+1);
            if ((plat.get(plat.indexOf("x") - 1) == "x") || (plat.get(plat.indexOf("x") + 1) == "x")) {
                plat.set(plat.indexOf("x"), " ");
            } else {
                if (r == 1) {
                    int alea = ThreadLocalRandom.current().nextInt(0,equipements.size());
                    plat.set(plat.indexOf("x"), equipements.get(alea));

                } else if (r == 2) {
                    int alea = ThreadLocalRandom.current().nextInt(0,monstres.size());
                    plat.set(plat.indexOf("x"), monstres.get(alea));
                } else if (r == 3) {
                    int alea = ThreadLocalRandom.current().nextInt(0,objets.size());
                    plat.set(plat.indexOf("x"), objets.get(alea));
                } else if (r == 4) {
                    int alea = ThreadLocalRandom.current().nextInt(0,objets.size());
                    plat.set(plat.indexOf("x"), objets.get(alea));
                }
            }
        }
        int c = ThreadLocalRandom.current().nextInt(4, 7 + 1);
        //System.out.println("murs:"+c);
        while (c > 0) {
            if (c % 2 == 0) {
                int pointInitiale = numsV[ThreadLocalRandom.current().nextInt(0, numsV.length)];

                for (int i = 0; i <= 7; i++) {
                    plat.set(pointInitiale + i, "#");


                }
            } else if (c % 2 == 1) {
                int pointInitiale = numsH[ThreadLocalRandom.current().nextInt(0, numsH.length)];
                int i = 20;
                for (int k = 0; k <= 4; k++) {
                    plat.set(pointInitiale + i , "#");
                    i+= 20;
                }

            }
            c--;
        }



        return plat;
    }

public static Joueur setJoueur(ArrayList plat){
    Joueur j1 = new Joueur();
    plat.set(190,j1);
    j1.equipement.add(new equipement("vêtements déchirer",3,1,2,1));
    j1.equipement.add(new equipement("Baton",0,0,2,1));
    j1.slot1EstVide= false;
    j1.slot2EstVide= false;
    return j1 ;
}
public static int findJoueur(ArrayList plat){
        int i = 20 ;
    while (!(plat.get(i).getClass().toString().equals("class com.company.Joueur"))){
        i++;
    }
    return i ;
}


public  static void afficher(ArrayList plat) {

        for (int i = 1; i < plat.size(); i++) {

           if (plat.get(i).getClass().toString().equals("class com.company.Joueur")) {
                System.out.print("\uD83D\uDEB6");
            }else if (plat.get(i).getClass().toString().equals("class com.company.Pnj")){
               System.out.print("M");
           }else if (plat.get(i).getClass().toString().equals("class com.company.Objets")){
               System.out.print("O");
           }
           else if ((plat.get(i).getClass().toString().equals("class com.company.equipement"))){
                System.out.print("E");
           }
           else {
                System.out.print(plat.get(i));
            }
           if (i % 20 == 0) {
               System.out.println();
           }
        }
    }

    public static void save(ArrayList plat) throws IOException {
        File file = new File("mmorpg.txt");
        FileOutputStream fo = new FileOutputStream(file);
        ObjectOutputStream read = new ObjectOutputStream(fo);
        for (int i = 0 ; i < plat.size( ); i++  ){
            read.writeObject(plat.get(i));
        }
        read.close();
        fo.close();

    }

    public static void load(ArrayList plat ) throws IOException, ClassNotFoundException {
       // BufferedReader file = new BufferedReader(new FileReader("mmorpg"));
        FileInputStream fi = new FileInputStream("C:\\Users\\medam\\Desktop\\IdeaProjects\\MMORPGVR\\mmorpg.txt");
        ObjectInputStream in = new ObjectInputStream(fi);
        for (int i = 1; i <= 400; i++ ){
            plat.add(in.readObject());
        }
        in.close();
        fi.close();

    }



    }





