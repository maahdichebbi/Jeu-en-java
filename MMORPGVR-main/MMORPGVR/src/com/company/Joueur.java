package com.company;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Joueur implements Serializable {

    int force;
    int adresse;
    int resistance;
    int xp = 0;
    int initiative;
    int attaque;
    int defense;
    int esquive;
    int degats;
    int Pa = 30;
    int blessure = 70;
    boolean slot1EstVide = true;// slot d'armure
    boolean slot2EstVide = true;// slot d'arme
    ArrayList<equipement> equipement = new ArrayList<>();

    ArrayList sac = new ArrayList();


    public Joueur() {

        while ((force + adresse + resistance) != 18) {
            System.out.println("veuillez  attribuer 18 degrés au totale  ");
            Scanner scan = new Scanner(System.in);
            System.out.println("attribuer  la force");
            this.force = scan.nextInt();
            System.out.println("attribuer  la adresse");
            this.adresse = scan.nextInt();
            System.out.println("attribuer  la resistance");
            this.resistance = scan.nextInt();
        }

        System.out.println("*** personnage  créer *** ");
    }
public void stats(){
    this.initiative = this.adresse - this.equipement.get(0).encombrement;
    this.attaque = this.adresse + this.equipement.get(1).manibilité;
    this.esquive = this.initiative;
    this.defense = this.resistance + this.equipement.get(0).solidite;
    this.degats = this.force + this.equipement.get(1).impact;
}

    public void pointAction() {
        this.Pa += 1;
    }


    public int degCalc(int nb) { //pour calculer les degrées
        int som = 0;
        for (int i = nb; i > 0; i--) {
            som += ThreadLocalRandom.current().nextInt(1, 6 + 1);
        }
        return som;

    }

    /*public void get_force (){
        System.out.println(force);
    }
    */


    public static int xpRajoutRatio(Joueur j, Pnj mob) {
        int max = Math.max(j.xp, mob.getXp());
        int min = Math.min(j.xp, mob.getXp());
        int ratio = max / min;
        return ratio;

    }



    //-----------------------------------------------------------------------------------------------------------------------
    public void deplacer(ArrayList plat) {
        Scanner input = new Scanner(System.in);
        boolean test = false;
        String choix;
        if (this.Pa >= 2) {
            while (test == false) {


                System.out.println();
                System.out.println("pour deplacer vers le haut  'H'");
                System.out.println("pour deplacer vers la gauche  'G'");
                System.out.println("pour deplacer a droit 'D'");
                System.out.println("pour deplacer en bas  'B'");
                choix = input.nextLine();
                switch (choix.toUpperCase()) {
                    case "H":
                        if (plat.get(Plateau.findJoueur(plat) - 20).getClass().toString().equals("class com.company.equipement")) {
                            Scanner scan = new Scanner(System.in);
                            equipement e = (com.company.equipement) plat.get(Plateau.findJoueur(plat) - 20);
                            plat.set(Plateau.findJoueur(plat) - 20, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat) + 20, " ");
                            System.out.println(e.nom + " trouver voulais vous l'equiper? O/N");
                            String choix2 = scan.nextLine().toUpperCase();
                            if (choix2.equals("O") && (e.encombrement != 0) && (slot1EstVide)) {
                                this.equipement.set(0, e);
                                slot1EstVide = false;
                            } else if (choix2.equals("O") && (e.impact != 0) && (slot2EstVide)) {
                                this.equipement.set(1, e);
                                slot2EstVide = false;
                            } else if (!slot1EstVide && choix2.equals("O") && (e.encombrement != 0)) {
                                this.sac.add(this.equipement.get(0));
                                this.equipement.set(0, e);
                            } else if (!slot2EstVide && choix2.equals("O") && (e.impact != 0)) {
                                this.sac.add(this.equipement.get(1));
                                this.equipement.set(1, e);
                            } else {
                                this.sac.add(e);

                            }
                            test = true;
                        } else if (plat.get(Plateau.findJoueur(plat) - 20).getClass().toString().equals("class com.company.Objets")) {
                            Objets obj = (com.company.Objets) plat.get(Plateau.findJoueur(plat) - 20);
                            plat.set(Plateau.findJoueur(plat) - 20, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat) + 20, " ");
                            System.out.println(obj.nom + " trouver ");
                            this.sac.add(obj);
                            Scanner in = new Scanner(System.in) ;
                            in.nextLine();

                            test = true;
                        } else if (plat.get(Plateau.findJoueur(plat) - 20) != "#" && !(plat.get(Plateau.findJoueur(plat) - 20).getClass().toString().equals("class com.company.Pnj"))) {
                            plat.set(Plateau.findJoueur(plat) - 20, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat) + 20, " ");
                            test = true;
                        }
                        break;


                    case "D":
                        if (plat.get(Plateau.findJoueur(plat) + 1).getClass().toString().equals("class com.company.equipement")) {
                            Scanner scan = new Scanner(System.in);
                            equipement e = (com.company.equipement) plat.get(Plateau.findJoueur(plat) + 1);
                            plat.set(Plateau.findJoueur(plat) + 1, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat), " ");
                            System.out.println(e.nom + " trouver voulais vous l'equiper? O/N");
                            String choix2 = scan.nextLine().toUpperCase();
                            if (choix2.equals("O") && (e.encombrement != 0) && (slot1EstVide)) {
                                this.equipement.set(0, e);
                                slot1EstVide = false;
                            } else if (choix2.equals("O") && (e.impact != 0) && (slot2EstVide)) {
                                this.equipement.set(1, e);
                                slot2EstVide = false;
                            } else if (!slot1EstVide && choix2.equals("O") && (e.encombrement != 0)) {
                                this.sac.add(this.equipement.get(0));
                                this.equipement.set(0, e);
                            } else if (!slot2EstVide && choix2.equals("O") && (e.impact != 0)) {
                                this.sac.add(this.equipement.get(1));
                                this.equipement.set(1, e);
                            } else {
                                this.sac.add(e);

                            }
                            test = true;
                        } else if (plat.get(Plateau.findJoueur(plat) + 1).getClass().toString().equals("class com.company.Objets")) {
                            Objets obj = (com.company.Objets) plat.get(Plateau.findJoueur(plat) + 1);
                            plat.set(Plateau.findJoueur(plat) + 1, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat) + 1, " ");
                            System.out.println(obj.nom + " trouver");
                            this.sac.add(obj);
                            Scanner in = new Scanner(System.in) ;
                            in.nextLine();
                            test = true;
                        } else if (plat.get(Plateau.findJoueur(plat) + 1) != "#"&& !(plat.get(Plateau.findJoueur(plat) + 1).getClass().toString().equals("class com.company.Pnj"))) {
                            plat.set(Plateau.findJoueur(plat) + 1, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat), " ");
                            test = true;
                        }
                        break;
                    case "G":

                        if (plat.get(Plateau.findJoueur(plat) - 1).getClass().toString().equals("class com.company.equipement")) {
                            Scanner scan = new Scanner(System.in);
                            equipement e = (com.company.equipement) plat.get(Plateau.findJoueur(plat) - 1);
                            plat.set(Plateau.findJoueur(plat) - 1, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat) + 1, " ");
                            System.out.println(e.nom + " trouver voulais vous l'equiper? O/N");
                            String choix2 = scan.nextLine().toUpperCase();
                            if (choix2.equals("O") && (e.encombrement != 0) && (slot1EstVide)) {
                                this.equipement.set(0, e);
                                slot1EstVide = false;
                            } else if (choix2.equals("O") && (e.impact != 0) && (slot2EstVide)) {
                                this.equipement.set(1, e);
                                slot2EstVide = false;
                            } else if (!slot1EstVide && choix2.equals("O") && (e.encombrement != 0)) {
                                this.sac.add(this.equipement.get(0));
                                this.equipement.set(0, e);
                            } else if (!slot2EstVide && choix2.equals("O") && (e.impact != 0)) {
                                this.sac.add(this.equipement.get(1));
                                this.equipement.set(1, e);
                            } else {
                                this.sac.add(e);

                            }
                            test = true;
                        } else if (plat.get(Plateau.findJoueur(plat) - 1).getClass().toString().equals("class com.company.Objets")) {
                            Objets obj = (com.company.Objets) plat.get(Plateau.findJoueur(plat) - 1);
                            plat.set(Plateau.findJoueur(plat) - 1, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat) + 1, " ");
                            System.out.println(obj.nom + " trouver");
                            this.sac.add(obj);
                            Scanner in = new Scanner(System.in) ;
                            in.nextLine();
                            test = true;
                        } else if (plat.get(Plateau.findJoueur(plat) - 1) != "#" && !(plat.get(Plateau.findJoueur(plat) - 1).getClass().toString().equals("class com.company.Pnj"))) {
                            plat.set(Plateau.findJoueur(plat) - 1, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat) + 1, " ");
                            test = true;
                        }
                        break;


                    case "B":

                        if (plat.get(Plateau.findJoueur(plat) + 20).getClass().toString().equals("class com.company.equipement")) {
                            Scanner scan = new Scanner(System.in);
                            equipement e = (com.company.equipement) plat.get(Plateau.findJoueur(plat) + 20);
                            plat.set(Plateau.findJoueur(plat) + 20, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat), " ");
                            System.out.println(e.nom + " trouver voulais vous l'equiper? O/N");
                            String choix2 = scan.nextLine().toUpperCase();
                            if (choix2.equals("O") && (e.encombrement != 0) && (slot1EstVide)) {
                                this.equipement.set(0, e);
                                slot1EstVide = false;
                            } else if (choix2.equals("O") && (e.impact != 0) && (slot2EstVide)) {
                                this.equipement.set(1, e);
                                slot2EstVide = false;
                            } else if (!slot1EstVide && choix2.equals("O") && (e.encombrement != 0)) {
                                this.sac.add(this.equipement.get(0));
                                this.equipement.set(0, e);
                            } else if (!slot2EstVide && choix2.equals("O") && (e.impact != 0)) {
                                this.sac.add(this.equipement.get(1));
                                this.equipement.set(1, e);
                            } else {
                                this.sac.add(e);

                            }
                            test = true;
                        } else if (plat.get(Plateau.findJoueur(plat) + 20).getClass().toString().equals("class com.company.Objets")) {
                            Objets obj = (com.company.Objets) plat.get(Plateau.findJoueur(plat) + 20);
                            plat.set(Plateau.findJoueur(plat) + 20, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat), " ");
                            this.sac.add(obj);
                            System.out.println(obj.nom + " trouver ");
                            Scanner in = new Scanner(System.in) ;
                            in.nextLine();

                            test = true;
                        } else if (plat.get(Plateau.findJoueur(plat) + 20) != "#" && !(plat.get(Plateau.findJoueur(plat) +20).getClass().toString().equals("class com.company.Pnj"))) {
                            plat.set(Plateau.findJoueur(plat) + 20, plat.get(Plateau.findJoueur(plat)));
                            plat.set(Plateau.findJoueur(plat), " ");
                            test = true;

                        }
                        break;
                }
            }
            this.Pa -= 2;

        } else {
            Scanner in = new Scanner(System.in) ;
            System.out.println("pas assez points d'action ");
            in.nextLine();
        }


    }



    //--------------------------------------------------------------------------------------------------

    public void utiliserUnObjet(ArrayList plat) {
        Scanner scan = new Scanner(System.in);
        System.out.println("taper le numero d l'objets que vous souhaiter l'utiliser ou l'equiper ");

        int choix = scan.nextInt();

        if (this.sac.get(choix - 1).toString().equals("Potion de soin")) {

            Objets obj = (Objets) this.sac.get(choix - 1);
            this.blessure += obj.getPuissance();
            sac.remove(choix - 1);

            this.Pa-= 1  ;
        } else if (this.sac.get(choix - 1).toString().equals("tome de connaissance")) {

            Objets obj = (Objets) this.sac.get(choix - 1);
            this.xp += obj.getPuissance();
            sac.remove(choix - 1);

            this.Pa-= 2 ;
        }
        else if (this.sac.get(choix - 1).toString().equals("Potion d'energie")) {

            Objets obj = (Objets) this.sac.get(choix - 1);
            this.Pa += obj.getPuissance();
            sac.remove(choix - 1);


        }
        else if (this.sac.get(choix - 1).toString().equals("molotov")) {

            Objets obj = (Objets) this.sac.get(choix - 1);
            System.out.println(" choisier quel case voulez vous l'attaquer ");
            Scanner in = new Scanner(System.in) ;
            String choice = in.nextLine();

            switch (choice.toUpperCase()) {
                case "H":
                    if (plat.get(Plateau.findJoueur(plat) - 20).getClass().toString().equals("class com.company.Pnj")) {
                      Pnj mob = (Pnj)plat.get(Plateau.findJoueur(plat) - 20);
                      mob.pv -= 5 ;
                        plat.set((Plateau.findJoueur(plat) - 20),mob) ;
                        System.out.println(mob.nom + "a reçu 5 degats ");
                        in.nextLine();

                        this.Pa-= 3 ;
                    }else {
                        System.out.println("pas de cible ");
                        scan.nextLine();
                    }

                    break;

                case "G":
                    if (plat.get(Plateau.findJoueur(plat) - 1).getClass().toString().equals("class com.company.Pnj")) {
                        Pnj mob = (Pnj)plat.get(Plateau.findJoueur(plat) - 1);
                        mob.pv -= 5 ;
                        plat.set((Plateau.findJoueur(plat) - 1),mob) ;
                        System.out.println(mob.nom + "a reçu 5 degats ");
                        in.nextLine();

                        this.Pa-= 3 ;

                    }else {
                        System.out.println("pas de cible ");
                        scan.nextLine();
                    }

                    break;

                case "D":
                    if (plat.get(Plateau.findJoueur(plat) + 1).getClass().toString().equals("class com.company.Pnj")) {
                        Pnj mob = (Pnj)plat.get(Plateau.findJoueur(plat) + 1);
                        mob.pv -= 5 ;
                        plat.set((Plateau.findJoueur(plat) + 1),mob) ;
                        System.out.println(mob.nom + "a reçu 5 degats ");
                        in.nextLine();
                        this.Pa-= 3 ;
                    }else {
                        System.out.println("pas de cible ");
                        scan.nextLine();
                    }
                    break;

                case "B":

                    if (plat.get(Plateau.findJoueur(plat) + 20).getClass().toString().equals("class com.company.Pnj")) {
                        Pnj mob = (Pnj)plat.get(Plateau.findJoueur(plat) + 20);
                        mob.pv -= 5 ;
                        plat.set((Plateau.findJoueur(plat) + 20),mob) ;
                        System.out.println(mob.nom + "a reçu 5 degats ");
                        in.nextLine();

                        this.Pa-= 3 ;
                    }else {
                        System.out.println("pas de cible ");
                        scan.nextLine();
                    }
                    break;
            }

            sac.remove(choix - 1);


        }


        else if (this.sac.get(choix - 1).getClass().toString().equals("class com.company.equipement")) {

            equipement e = (com.company.equipement) this.sac.get(choix - 1);
            if ((e.encombrement != 0) && (slot1EstVide)) {
                this.equipement.set(0, e);
                sac.remove(choix - 1);
                slot1EstVide = false;
            } else if ((e.impact != 0) && (slot2EstVide)) {
                sac.remove(choix - 1);
                this.equipement.set(1, e);
                slot2EstVide = false;
            } else if (!slot1EstVide && (e.encombrement != 0)) {
                sac.remove(choix - 1);
                this.sac.add(this.equipement.get(0));
                this.equipement.set(0, e);
            } else if (!slot2EstVide && (e.impact != 0)) {
                this.sac.add(this.equipement.get(1));
                sac.remove(choix - 1);
                this.equipement.set(1, e);
            }
        }

    }




    public static void reset(boolean[] tab) {
        for (int i = 0; i < 10; i++) {
            tab[i] = false;
        }


    }


    public static void remplir(boolean[] tab, int valeur) {
        for (int i = 0; i < valeur; i++) {
            tab[i] = true;
        }

    }

    public String nivBlessure() {
        String s;

        if (this.blessure < 5) {
            s = " Inconscient ";

        } else if (this.blessure < 20) {
            s = " Gravement blessé ";

        } else if (this.blessure < 30) {
            s = " Blessé ";

        } else if (this.blessure < 40) {
            s = "  Légèrement blessé";

        } else if (this.blessure < 55) {
            s = " Légèrement blessé";

        }  else {
            s = " en forme ";
        }
        return s;
    }


    // COMBAT

    public static void combat(Joueur j, Pnj monstre) {
        Scanner in = new Scanner(System.in);
        boolean[] rng = {false, false, false, false, false, false, false, false, false, false};
        j.initiative = j.adresse - j.equipement.get(0).encombrement;
        j.attaque = j.adresse + j.equipement.get(1).manibilité;
        j.esquive = j.initiative;
        j.defense = j.resistance + j.equipement.get(0).solidite;
        j.degats = j.force + j.equipement.get(1).impact;
        int  c = 1 ;
        while (j.blessure > 0 && monstre.pv > 0) {

            // intiative
            for (int i = 10; i > 0; i--) {
                if (i == j.initiative) {
                    //attaque
                    if (j.attaque <= 10)
                        remplir(rng, j.attaque);
                    else {
                        remplir(rng, 10);
                    }
                    if (rng[ThreadLocalRandom.current().nextInt(0, 10)]) {
                        //l'esquive
                        reset(rng);
                        remplir(rng, monstre.initiative);
                        if (rng[ThreadLocalRandom.current().nextInt(0, 10)]) {
                            monstre.pv -= (j.degats - monstre.defense);
                            j.xp += 2;
                            System.out.println(monstre.nom + " niv " + monstre.niv +  " a recu " + Integer.toString(j.degats - monstre.defense) + " degats ");
                        } else {
                            System.out.println(monstre.nom + " niv " + monstre.niv + " a esquiver ton attaque ");
                        }


                    }


                }
                if (i == monstre.initiative) {
                    //attaque
                    if (j.attaque <= 10)
                        remplir(rng, monstre.attaque);
                    else {
                        remplir(rng, 10);
                    }
                    if (rng[ThreadLocalRandom.current().nextInt(0, 10)]) {
                        //l'esquive
                        reset(rng);
                        remplir(rng, j.initiative);
                        if (rng[ThreadLocalRandom.current().nextInt(0, 10)]) {
                            j.blessure -= (monstre.degat - j.defense);
                            j.xp += 2;
                            System.out.println(monstre.nom + " niv " + monstre.niv + " vous attaquer avec " + Integer.toString(monstre.degat - j.defense) + " degats ");
                        } else {
                            System.out.println(" vous avez esquiver un attaque ");
                        }



                    }
                }

            }
            System.out.println(monstre.nom + " niv " + monstre.niv + " points de vie restants " + Integer.toString(monstre.pv));
            System.out.println(" votre niveau de blessure : " + j.nivBlessure());
            System.out.println("-------------------------------------------------------");
            System.out.println(" tapez entrée pour contunier");
            in.nextLine();

            c++;
        }


    }



    public static void rajout(Joueur j){
        Timer time = new Timer() ;
        time.schedule(new TimerTask() {
            @Override
            public void run() {

                j.Pa+= 1;
               // System.out.println("Pa rajouter ");
                if (j.blessure < 60){
                    j.blessure+= 1 ;
                   // System.out.println(" nniveau blessure augmenter ");
                }


            }
        },10000 ,40000);

    }

}






