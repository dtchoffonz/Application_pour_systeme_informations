/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author smalard
 */
@Entity
public class Employe {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
     private String mail;
    private String login;
    private String motDePasse;
    private String genre;
    private boolean disponible;
    @OneToMany
    private List<Consultation> consultationseffectuees=new ArrayList();
    @OneToOne
    private Consultation consultationactuelle;


    public Employe(String nom, String prenom, String telephone, String mail, String login, String motDePasse, String genre, boolean disponible) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
        this.login = login;
        this.motDePasse = motDePasse;
        this.genre = genre;
        this.disponible = disponible;
    }
    

    public Employe() {
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getLogin() {
        return login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    
    public String getMail() {
        return mail;
    }


    public String getGenre() {
        return genre;
    }

    public boolean isDisponible() {
        return disponible;
    }
    
    public List<Consultation> getConsultationseffectuees() {
        return consultationseffectuees;
    }

    public Consultation getConsultationactuelle() {
        return consultationactuelle;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephonePro) {
        this.telephone = telephonePro;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public void setConsultationseffectuees(List<Consultation> consultationseffectuees) {
        this.consultationseffectuees = consultationseffectuees;
    }

    public void setConsultationactuelle(Consultation consultationactuelle) {
        this.consultationactuelle = consultationactuelle;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public void setConsultations(List<Consultation> consultations) {
        this.consultationseffectuees = consultations;
    }

    public void addHistoriqueConsultation(Consultation consultaion){
        this.consultationseffectuees.add(consultaion); 
    }
        
    @Override
    public String toString() {
        return "Employe{" + "nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", mail=" + mail + ", login=" + login + ", motDePasse=" + motDePasse + ", genre=" + genre + ", disponible=" + disponible + '}';
    }

    
  
    
    
    
    
}
