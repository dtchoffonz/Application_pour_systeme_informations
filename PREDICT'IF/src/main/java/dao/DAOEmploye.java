/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import metier.modele.Employe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author smalard
 */
public class DAOEmploye {

    public DAOEmploye() {
    }
    
    public void create(Employe employe){
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
        public void update(Employe employe){
        JpaUtil.obtenirContextePersistance().merge(employe);
    }
     
    public Employe findById(Long EmployeId){
    return JpaUtil.obtenirContextePersistance().find(Employe.class, EmployeId);
    }

    public List<Employe> listeEmployes() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> requete = em.createQuery("SELECT e FROM Employe e", Employe.class);
    return requete.getResultList();
    }
    
    
    public Employe employeaSelectionner(String genre) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        
        TypedQuery<Employe> requete = em.createQuery("SELECT e FROM Employe e WHERE e.disponible=true and e.genre=:genre", Employe.class);
        requete.setParameter("genre", genre);
        List<Employe> employes = requete.getResultList();
        Employe employeBon=null;
        if(!employes.isEmpty()){
            int min=employes.get(0).getConsultationseffectuees().size();
            employeBon= employes.get(0);

            for (int i = 1; i < employes.size(); i++) {
                if((employes.get(i)).getConsultationseffectuees().size()<min){
                    employeBon=employes.get(i);
                }
            }
        }    
    return employeBon;
    }
    
    public Employe findbyLogin(String login) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.login = :login", Employe.class);
        query.setParameter("login", login); // correspond au paramètre ":login" dans la requête
        List<Employe> employes = query.getResultList();
        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
    return result;
    }
    
    public int nbNonDisponible() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Long> requete = em.createQuery("SELECT count(e) FROM Employe e WHERE e.disponible=false", Long.class);
        List<Long> resultat = requete.getResultList();
    return resultat.get(0).intValue();
    }
}
