/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import metier.modele.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author smalard
 */
public class DAOClient {

    public DAOClient() {
    }

     public void create(Client client){
        JpaUtil.obtenirContextePersistance().persist(client);
    }
    public void update(Client client){
        JpaUtil.obtenirContextePersistance().merge(client);
    }
    
    public Client findbyMail(String mail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.mail =:mail", Client.class);
        query.setParameter("mail", mail); // correspond au paramètre ":login" dans la requête
        List<Client> clients = query.getResultList();
        Client result = null;
        if (!clients.isEmpty()) {
            result = clients.get(0); // premier de la liste
        }
        return result;
    }
}
