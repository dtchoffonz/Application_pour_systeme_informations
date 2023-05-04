/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author smalard
 */
@Entity
public class Spirite extends Medium {
    
    private String support;

    public Spirite(String denomination, String genre, String support,  String Presentation,int nbconsultations) {
        super(denomination, genre, Presentation,nbconsultations);
        this.support = support;
    }

    @Override
    public String toString() {
        return super.toString()+" type : Spirite{" + "support=" + support + '}';
    }

    public Spirite() {
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
    
    
    
}
