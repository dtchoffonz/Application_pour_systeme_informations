/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import metier.modele.Consultation;

/**
 *
 * @author smalard
 */
public class DAOConsultation {

    public DAOConsultation() {
    }

     public void create(Consultation consultation){
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
     
    public void update(Consultation consultation){
        JpaUtil.obtenirContextePersistance().merge(consultation);
    }
    
    public Consultation findById(Long ConsultationId){
    return JpaUtil.obtenirContextePersistance().find(Consultation.class, ConsultationId);
    }
    
}
