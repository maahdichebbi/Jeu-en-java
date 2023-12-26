package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class equipement implements Serializable {
    String nom ;
    int encombrement ;
    int solidite ;
    int manibilité ;
    int impact ;


    public equipement(String nom , int encombrement , int solidite,int manibilité , int impact){
         this.nom = nom ;
         this.encombrement = encombrement;
         this.solidite= solidite ;
         this.manibilité= manibilité;
         this.impact= impact ;

    }

    public int getEncombrement() {
        return encombrement;
    }

    public String toString(){
        String s ;
        s = new String("equipement :  "+ nom ) ;
        return s;
    }


    public equipement() {

    }
   /* public static void equiper(Joueur J , equipement e){
        J.equipement.add(e) ;

    }
*/


}
