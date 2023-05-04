package metier.modele;

import javax.persistence.Entity;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smalard
 */
@Entity
public class Astrologue extends Medium{
    
    private int promotion;
    private String formation;

    public Astrologue(String denomination, String genre, String formation, int promotion, String Presentation,int nbconsultations) {
        super(denomination, genre, Presentation,nbconsultations);
        this.promotion = promotion;
        this.formation = formation;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    @Override
    public String toString() {
        return super.toString()+" type : Astrologue{" + "promotion=" + promotion + ", formation=" + formation + '}';
    }
    


    public Astrologue() {
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }
    
    
    
}
