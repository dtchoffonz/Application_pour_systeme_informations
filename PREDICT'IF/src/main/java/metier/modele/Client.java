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
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author smalard
 */
@Entity
public class Client {
    
      @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
     private String telephone;
    private String adressePostale;
    private String motDePasse;
    @Column(unique=true)
    private String mail;
    private String signeZodiaque;
    private String signeChinois;
    private String couleur;
    private String animal;
    @OneToMany
    private List<Consultation> consultationsterminees;
   

    public Client(String nom, String prenom, Date dateNaissance,String telephone, String adressePostale, String motDePasse, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone=telephone;
        this.dateNaissance = dateNaissance;
        this.adressePostale = adressePostale;
        this.motDePasse = motDePasse;
        this.mail = mail;
    
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public Client() {
    }

    public List<Consultation> getConsultations() {
        return consultationsterminees;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getMail() {
        return mail;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setSigneZodiaque(String signe_zodiaque) {
        this.signeZodiaque = signe_zodiaque;
    }

    public void setSigneChinois(String signe_chinois) {
        this.signeChinois = signe_chinois;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public void setConsultations(List<Consultation> consultation) {
        this.consultationsterminees = consultation;
    }
    
    public void addHistoriqueConsultation(Consultation consultaion){
        this.consultationsterminees.add(consultaion); 
    }


    
    @Override
    public String toString() {
        return "Client{" + "nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", adressePostale=" + adressePostale + ", motDePasse=" + motDePasse + ", mail=" + mail + ", signe_zodiaque=" + signeZodiaque + ", signe_chinois=" + signeChinois + ", couleur=" + couleur + ", animal=" + animal + '}';
    }
    
    

    
    
}
