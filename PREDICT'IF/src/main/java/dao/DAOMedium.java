/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import metier.modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author smalard
 */
public class DAOMedium {

    public DAOMedium() {
    }

    public void create(Medium medium) {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }

    public void update(Medium medium) {
        JpaUtil.obtenirContextePersistance().merge(medium);
    }
        
    public Medium findById(Long MediumId) {
    return JpaUtil.obtenirContextePersistance().find(Medium.class, MediumId);
    }
    
    public List<Medium> listeMedium() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> requete = em.createQuery("SELECT m FROM Medium m", Medium.class);
    return requete.getResultList();
    }

}
