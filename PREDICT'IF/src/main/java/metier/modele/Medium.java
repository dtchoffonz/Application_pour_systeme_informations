/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
/**
 *
 * @author smalard
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Medium {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String denomination;
    private String genre;
    private String Presentation;
    private int nbconsultations;


    public int getNbconsultations() {
        return nbconsultations;
    }

    public void setNbconsultations(int nbconsultations) {
        this.nbconsultations = nbconsultations;
    }

    public Medium(String denomination, String genre, String Presentation,int nbconsultations) {
        this.denomination = denomination;
        this.genre = genre;
        this.Presentation = Presentation;
        this.nbconsultations=nbconsultations;
    }

    @Override
    public String toString() {
        return "Medium{" + "denomination=" + denomination + ", genre=" + genre + ", Presentation=" + Presentation + '}';
    }

    public Medium() {
    }
    
    public String getDenomination() {
        return denomination;
    }

    public String getGenre() {
        return genre;
    }

    public String getPresentation() {
        return Presentation;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPresentation(String Presentation) {
        this.Presentation = Presentation;
    }
    
}
