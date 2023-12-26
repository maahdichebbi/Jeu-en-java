package com.company;

import java.io.IOException;
import java.util.*;

public class Menu {
    String choix ;
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void  Ui (Joueur j1 , ArrayList plat) throws IOException {
        j1.stats();
        int i = 1 ;
        Scanner scan = new Scanner(System.in);


        while (i > 0) {
            Plateau.afficher(plat);

            System.out.print("#");
            System.out.println();
            System.out.println("☬ Vos objets équipés ☬ ");
            if (j1.equipement.isEmpty() == false) {
                for (int c = 0 ; c < j1.equipement.size(); c++){
                    System.out.println(j1.equipement.get(c).nom);
                }

            }

            System.out.println("\uD83D\uDC5C Votre sac \uD83D\uDC5C ");
            if (j1.sac.isEmpty() == false) {



                for (int c = 0 ; c < j1.sac.size(); c++){

                    System.out.println(j1.sac.get(c).toString() + " (" + Objets.Cout(j1.sac.get(c).toString()) +") " );
                }

            }

            System.out.println("ᚻ Vos caractéristiques ᛗ ");
            System.out.println("- force : " + j1.force);
            System.out.println("- adresse : " + j1.adresse);
            System.out.println("- endurance : "+j1.resistance);
            System.out.println("ᛃ initiative : " + j1.initiative);
            System.out.println("ᛃ attaque :  " + j1.attaque);
            System.out.println("ᛃ défense : " + j1.defense);
            System.out.println("ᛃ dégats : " + j1.degats);
            System.out.println("votre niveaux de blessure : " + j1.nivBlessure());
            System.out.println("vos points d'experience " + j1.xp);
            System.out.println("Vos points d'action : " + j1.Pa);
            System.out.println("vous pouvez:");
            System.out.println("1☽ vous déplacer \uD83D\uDC5F (2PA)");
            System.out.println("2☽ attaquer ⚔ (3PA)");
            System.out.println("3☽ utiliser un objet ◌ (Variable)");
            System.out.println("4☽ finir et garder les PA restants ❦");
            System.out.print(" ♔ votre choix ♔ : ");
            int choix = scan.nextInt();
            switch (choix) {
                case 1:
                    if (j1.nivBlessure().equals(" Inconscient ")){
                        System.out.println("vous etes  Inconscient attendez jusqu'a vius regenere ou boire un Postion de soin ");
                        scan.nextLine();
                    }else {
                        j1.deplacer(plat);
                        j1.stats();
                    }
                    break;
                case 2:
                    if (j1.Pa >= 3){


                        System.out.println(" choisier quel case voulez vous l'attaquer ");
                        Scanner in = new Scanner(System.in) ;
                        String choice = in.nextLine();

                        switch (choice.toUpperCase()) {
                            case "H":
                                if (plat.get(Plateau.findJoueur(plat) - 20).getClass().toString().equals("class com.company.Pnj")) {
                                    Joueur.combat(j1,(Pnj) plat.get(Plateau.findJoueur(plat) - 20));
                                    if ( ((Pnj) plat.get(Plateau.findJoueur(plat) - 20)).getPv() <= 0 ){
                                        System.out.println("vous avez vincu "+  ((Pnj) plat.get(Plateau.findJoueur(plat) - 20)).getNom() );
                                        j1.xp+=  (((Pnj) plat.get(Plateau.findJoueur(plat) - 20)).getXp())* Joueur.xpRajoutRatio(j1,(Pnj) plat.get(Plateau.findJoueur(plat) - 20));
                                        plat.set( Plateau.findJoueur(plat) - 20,Objets.Loot());

                                    }else if (j1.blessure <=0){
                                        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
                                        System.out.println("vous etes mort ");
                                        choice = in.nextLine();

                                        System.exit(0);
                                    }
                                    j1.Pa-= 3 ;
                                }else {
                                    System.out.println("pas de cible ");
                                    scan.nextLine();
                                }

                                break;

                            case "G":
                                if (plat.get(Plateau.findJoueur(plat) - 1).getClass().toString().equals("class com.company.Pnj")) {
                                    Joueur.combat(j1,(Pnj) plat.get(Plateau.findJoueur(plat) - 1));
                                    if ( ((Pnj) plat.get(Plateau.findJoueur(plat) - 1)).getPv() <= 0 ){
                                        System.out.println("vous avez vincu "+  ((Pnj) plat.get(Plateau.findJoueur(plat) -1)).getNom() );
                                        j1.xp+=  (((Pnj) plat.get(Plateau.findJoueur(plat) - 1)).getXp())* Joueur.xpRajoutRatio(j1,(Pnj) plat.get(Plateau.findJoueur(plat) - 1));
                                        plat.set( Plateau.findJoueur(plat) -1,Objets.Loot());

                                    }else if (j1.blessure <=0){
                                        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
                                        System.out.println("vous etes mort ");
                                        choice = in.nextLine();

                                        System.exit(0);
                                    }
                                    j1.Pa-= 3 ;
                                }else {
                                    System.out.println("pas de cible ");
                                    scan.nextLine();
                                }

                                break;

                            case "D":
                                if (plat.get(Plateau.findJoueur(plat) + 1).getClass().toString().equals("class com.company.Pnj")) {
                                    Joueur.combat(j1,(Pnj) plat.get(Plateau.findJoueur(plat) + 1));
                                    if ( ((Pnj) plat.get(Plateau.findJoueur(plat) + 1)).getPv() <= 0 ){
                                        System.out.println("vous avez vincu "+  ((Pnj) plat.get(Plateau.findJoueur(plat) + 1)).getNom() );
                                        j1.xp+=  (((Pnj) plat.get(Plateau.findJoueur(plat) + 1)).getXp())* Joueur.xpRajoutRatio(j1,(Pnj) plat.get(Plateau.findJoueur(plat) + 1));
                                        plat.set( Plateau.findJoueur(plat) + 1,Objets.Loot());

                                    }else if (j1.blessure <=0){
                                        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
                                        System.out.println("vous etes mort ");
                                        choice = in.nextLine();

                                        System.exit(0);
                                    }
                                    j1.Pa -= 3 ;
                                }else {
                                    System.out.println("pas de cible ");
                                    scan.nextLine();
                                }
                                break;

                            case "B":

                                if (plat.get(Plateau.findJoueur(plat) + 20).getClass().toString().equals("class com.company.Pnj")) {
                                    Joueur.combat(j1,(Pnj) plat.get(Plateau.findJoueur(plat) + 20));
                                    if ( ((Pnj) plat.get(Plateau.findJoueur(plat) + 20)).getPv() <= 0 ){
                                        System.out.println("vous avez vincu "+  ((Pnj) plat.get(Plateau.findJoueur(plat) + 20)).getNom() );
                                        j1.xp+=  (((Pnj) plat.get(Plateau.findJoueur(plat) + 20)).getXp()) * Joueur.xpRajoutRatio(j1,(Pnj) plat.get(Plateau.findJoueur(plat) + 20));
                                        plat.set( Plateau.findJoueur(plat) + 20,Objets.Loot());

                                    }else if (j1.blessure <=0){
                                        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
                                        System.out.println("vous etes mort ");
                                        choice = in.nextLine();

                                        System.exit(0);
                                    }
                                    j1.Pa -= 3 ;
                                }else {
                                    System.out.println("pas de cible ");
                                    scan.nextLine();
                                }
                                break;
                        }
                    }
                    break;
                case 3:
                    j1.utiliserUnObjet(plat);
                    j1.stats();
                    break;

                case 4:
                    Plateau.save(plat);
                    System.out.println(new String(new char[50]).replace("\0", "\r\n"));
                    i--;

                    break;

            }


        }


    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean ok = true ;
        while (ok ) {

            System.out.println("ᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞ");
            System.out.println("ᛝᛝ        ELEHLPTMMMORPGSVR        ᛝᛝ");
            System.out.println("ᛝᛝ---------------------------------ᛝᛝ");
            System.out.println("ᛝᛝ        Ⅰ nouvelle partie        ᛝᛝ");
            System.out.println("ᛝᛝ        Ⅱ reprendre partie        ᛝᛝ");
            System.out.println("ᛝᛝ                                  ᛝᛝ");
            System.out.println("ᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞᛞ");

            Scanner scan = new Scanner(System.in);


            if (scan.nextInt() == 1) {
                ArrayList plat = Plateau.generate();
                Joueur j1 = Plateau.setJoueur(plat);
                Joueur.rajout(j1);
                Ui(j1,plat);


            }else {
                int i = 1;
                ArrayList plat = new ArrayList();
                Plateau.load(plat);
                Joueur j1 = (Joueur) plat.get(Plateau.findJoueur(plat));
                Joueur.rajout(j1);
                Ui(j1,plat);

            }






        }

    }


    }
