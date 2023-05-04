/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author smalard
 */
@Entity
public class Consultation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private String Commentaire;
    

    @OneToOne
    private Client client;
    @OneToOne
    private Medium mediumaincarner;

    public Consultation(Date date,Client client, Medium medium) {

        this.date = date;
        this.client=client;
        this.mediumaincarner=medium;
    }

    public Consultation() {
    }

    public Date getDate() {
        return date;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMediumaincarner(Medium mediumaincarner) {
        this.mediumaincarner = mediumaincarner;
    }

    public Client getClient() {
        return client;
    }

    public Medium getMediumaincarner() {
        return mediumaincarner;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
