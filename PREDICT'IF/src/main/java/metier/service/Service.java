/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;
import metier.modele.Employe;
import metier.modele.Spirite;
import metier.modele.Cartomancien;
import metier.modele.Astrologue;
import metier.modele.Client;
import dao.DAOMedium;
import dao.DAOEmploye;
import dao.DAOClient;
import dao.JpaUtil;
import Util.AstroNetApi;
import Util.Message;
import dao.DAOConsultation;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import metier.modele.Consultation;
import metier.modele.Medium;

/**
 *
 * @author smalard
 */
public class Service {

    public Service() {
    }
          
    
    public void initialiserEmployes() {
        
        DAOEmploye employeDao=new DAOEmploye();
        try{
            JpaUtil.creerContextePersistance();

            Employe e1=new Employe("Malard", "Sarah", "06 98 89 80 80", "sarah.malard@insa-lyon.fr", "smalard", "motdepasseDEsarah", "F",true);
            Employe e2=new Employe("Tchoffo Nzumegue", "Diland","07 67 94 99 56", "diland.tchoffo-nzumegue@insa-lyon.fr", "dtchoffonz", "dilan01", "H",true);
            Employe e3=new Employe("Battaglia", "Jules", "06 67 54 30 03", "jules.battaglia@insa-lyon.fr", "jbattaglia", "jules74", "H",true);
            Employe e4=new Employe("Raugel", "Tiphaine", "06 55 44 77 88", "tiphaine.raugel@insa-lyon.fr", "traugel", "motpass", "F",true);
            Employe e5=new Employe("Brice", "Chloe", "07 68 60 51 19", "chloe.brice@insa-lyon.fr", "bchloe", "mdp", "F",true);
            JpaUtil.ouvrirTransaction();
            employeDao.create(e1);
            employeDao.create(e2);
            employeDao.create(e3);
            employeDao.create(e4);
            employeDao.create(e5);
            JpaUtil.validerTransaction();
        }
        catch(Exception ex){
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public void initialiserMediums() {
        
                    
        DAOMedium MediumDao=new DAOMedium();
        try{
            JpaUtil.creerContextePersistance();

            Spirite s1=new Spirite("Gwenaelle", "F", "Boule de cristal" , "Spécialiste des grandes conversations au-delà de TOUTES les frontières",0);
            Spirite s2=new Spirite( "Professeur Tran", "H", "Marc de café, boule de cristal, oreilles de lapin","Votre avenir est devant vous: regardons-le ensemble!",0);
            Spirite s3=new Spirite("Marie", "F", "oreilles de chat" , "Spécialiste relations animales",0);
            Spirite s4=new Spirite( "Professeur Kevin", "H", "Boule de cristal","Discutons sérieusement !",0);
            Cartomancien c1=new Cartomancien("Mme Irma", "F","Comprenez votre entourage grace à mes cartes! Résultats rapides.",0);
            Cartomancien c2=new Cartomancien("Endora", "F","Mes cartes répondent à toutes vos questions personnelles",0);
            Cartomancien c3=new Cartomancien("Mme Simpson", "F","En quelques secondes, votre avenir entre vos mains.",0);
            Astrologue a1=new Astrologue( "Serena", "F","Ecole Normale Supérieure d'Astrologie(ENS-Astro)",2016,"Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé",0);
            Astrologue a2=new Astrologue( "Mr M", "H","Institut des Nouveaux Savoirs Astrologiques",2010,"Avenir, avenir, que nous réserves-tu? N'attendez plus, demandez à me consulter!",0);
            Astrologue a3=new Astrologue( "Grand marabouk", "H","Institut des Nouveaux Savoirs Astrologiques",2009,"Consultez pour mieux gérer !",0);
            JpaUtil.ouvrirTransaction();
            MediumDao.create(s1);
            MediumDao.create(s2);
            MediumDao.create(s3);
            MediumDao.create(s4);
            MediumDao.create(c1);
            MediumDao.create(c2);
            MediumDao.create(c3);
            MediumDao.create(a1);
            MediumDao.create(a2);
            MediumDao.create(a3);
            JpaUtil.validerTransaction();
        }
        catch(Exception ex){
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public Medium trouverMediumParId(Long MediumId){
        
        DAOMedium MediumDao=new DAOMedium();
        Medium medium=null;
        try{
            JpaUtil.creerContextePersistance();
            medium=MediumDao.findById(MediumId);
        }
        catch (Exception ex){
            ex.printStackTrace();
            medium=null;
        }
        finally{
            JpaUtil.fermerContextePersistance();  
        }
        return medium;
    }
    
    public Client inscriptionClient(Client client) {
        
        Message messsage=new Message();
        DAOClient clientDao=new DAOClient();
        AstroNetApi astroApi = new AstroNetApi();
        
        try{
            JpaUtil.creerContextePersistance();

            List<String> profil = astroApi.getProfil(client.getPrenom(), client.getDateNaissance());
            client.setSigneZodiaque(profil.get(0));
            client.setSigneChinois(profil.get(1));
            client.setCouleur(profil.get(2));
            client.setAnimal(profil.get(3));

            JpaUtil.ouvrirTransaction();
            clientDao.create(client);
            JpaUtil.validerTransaction();
            messsage.envoyerMail("contact@predict.if", client.getMail(), "Bienvenue chez PREDICT'IF", "Bonjour "+client.getPrenom()+", nous vous confirmons votre inscription au service PREDICT'IF. Rendez-vous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums");
        }
        catch(Exception ex){
            ex.printStackTrace();
            messsage.envoyerMail("contact@predict.if", client.getMail(), "Echec de l'inscription chez PREDICT'IF", "Bonjour "+client.getPrenom()+", votre inscription au service PREDICT'IF a malencontreusement échoué... Merci de recommencer ultérieurement");
            JpaUtil.annulerTransaction();
            client=null;
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    return client;
    }
    
    
    public Client authentifierClient(String mail, String motDePasse) {
        
        Client client = null;
        DAOClient clientDao= new DAOClient();
        
        try {
            JpaUtil.creerContextePersistance();
            Client c = clientDao.findbyMail(mail);
            if (c != null) {
                if (c.getMotDePasse().equals(motDePasse)) {
                    client = c;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(); 
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    return client;
    }
    
    public Employe authentifierEmploye(String login, String motDePasse) {
        Employe employe = null;
        DAOEmploye employeDao= new DAOEmploye();
        JpaUtil.creerContextePersistance();
        try {
            Employe e = employeDao.findbyLogin(login);
            if (e != null) {
                if (e.getMotDePasse().equals(motDePasse)) {
                    employe = e;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(); 
            employe = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    return employe;
    }
    
    
    public List<Medium> listerMediums() {
        
       List <Medium> listeDesMediums=new ArrayList<Medium>();
       DAOMedium mediumDao=new DAOMedium();
        try{
            JpaUtil.creerContextePersistance();
            listeDesMediums=mediumDao.listeMedium();
        }
        catch(Exception ex){
            ex.printStackTrace();
            listeDesMediums=null;
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    return listeDesMediums;
    }
    
    public List<String> consulterProfilAstral(Client client){

        List <String> profilAstral= new ArrayList<String>();
        profilAstral.add(client.getSigneZodiaque());
        profilAstral.add(client.getSigneChinois());
        profilAstral.add(client.getCouleur());
        profilAstral.add(client.getAnimal());
    return profilAstral; 
    }
    
    
    public Employe demanderConsultation(Client client,Medium medium) {
        
       
        Message message=new Message();
        DAOEmploye employeDao=new DAOEmploye();
        DAOConsultation consultationDao=new DAOConsultation();
        Employe employe;
        try{
            JpaUtil.creerContextePersistance();
            
            employe = employeDao.employeaSelectionner(medium.getGenre());//on choisi l'employé qui fera la consultation
            employe.setDisponible(false);                                // Cet employé n'est plus disponible
            
            Date maintenant = new Date();
            Consultation consultationactuelle= new Consultation(maintenant,client,medium); //créer une nouvelle consultation avec le client et le medium à incarner
            employe.setConsultationactuelle(consultationactuelle);                          //l'employe est associé à la consultation créée
            
            JpaUtil.ouvrirTransaction();
            consultationDao.create(consultationactuelle);
            employeDao.update(employe);
            JpaUtil.validerTransaction();
            
            message.envoyerNotification(employe.getTelephone(), "Bonjour "+employe.getPrenom()+". Consultation requise pour "+ client.getPrenom()+" "+ client.getNom()+" .Médium à incarner: "+ medium.getDenomination());
        }
        catch(Exception ex){
            ex.printStackTrace();
            message.envoyerNotification(client.getTelephone(), "Bonjour "+client.getPrenom()+"Désolé tous nos mediums sont actuellement occupé, nous ne pouvons donc satisfaire votre demande. Nous vous prions de renouveller votre demande plutard");
            JpaUtil.annulerTransaction();
            employe=null;
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    return employe;
    }
    
    public void jeSuisPret(Employe employe) {
        
        Message message=new Message();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy à HH:mm:ss");
        try{
            message.envoyerNotification(employe.getConsultationactuelle().getClient().getTelephone(), "Bonjour "+employe.getConsultationactuelle().getClient().getPrenom()+". J'ai bien recu votre demande de consultation du "+dateFormat.format(employe.getConsultationactuelle().getDate())+". Vous pouvez des à présent me contacter au "+employe.getTelephone()+". A tout de suite ! Médiumiquement votre,"+employe.getConsultationactuelle().getMediumaincarner().getDenomination());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
        }
    }

    
    public List<String> demanderAide(Client client, int niveauAmour, int niveauSante, int niveauTravail){
           
        AstroNetApi astroApi = new AstroNetApi();
        List<String> predictions= new ArrayList<String>() ;

        try {
            predictions = astroApi.getPredictions(client.getCouleur(), client.getAnimal(), niveauAmour, niveauSante, niveauTravail);
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    return predictions; 
    }

       
    public Consultation confirmerFinConsultation(Employe employe) {
        
        
        DAOClient clientDao= new DAOClient();
        DAOEmploye employeDao=new DAOEmploye();
        DAOMedium mediumDao=new DAOMedium();
        Consultation consultation;
        try{
        
            JpaUtil.creerContextePersistance();
            consultation=employe.getConsultationactuelle();
            
            //On incrémente le nombre de consultations du médium qui a été incarné
            Medium medium=consultation.getMediumaincarner();
            medium.setNbconsultations(medium.getNbconsultations()+1);
            
            //on ajoute la consultation actuelle dans l'historique des consultations du client qui a été consulté
            Client client = consultation.getClient();
            client.addHistoriqueConsultation(consultation);
            
            //On ajoute la consultation actuelle à l'historique des consultations effectuees par l'employé
            employe.addHistoriqueConsultation(consultation); 
            employe.setConsultationactuelle(null);      //On met la consultation actuelle à null
            employe.setDisponible(true);                //L'employe est maintenant disponible

            JpaUtil.ouvrirTransaction();
            mediumDao.update(medium);
            clientDao.update(client);
            employeDao.update(employe);
            JpaUtil.validerTransaction();
        }
        catch(Exception ex){
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            consultation=null;
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    return consultation;
    }
    
    
    public Consultation laisserCommentaire(Employe employe, String commentaire) {
        
        Consultation consultationactuelle;
        DAOConsultation consultationDao=new DAOConsultation();
        try{
            JpaUtil.creerContextePersistance();
            
            //On prend la derniere consultation, c'est à dire celle qui vient de se terminer
            consultationactuelle=employe.getConsultationseffectuees().get(employe.getConsultationseffectuees().size() - 1);
            consultationactuelle.setCommentaire(commentaire);
            
            JpaUtil.ouvrirTransaction();
            consultationDao.update(consultationactuelle);
            JpaUtil.validerTransaction();
        }
        catch(Exception ex){
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            consultationactuelle=null;
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    return consultationactuelle;
    }
    
   
    
   public  List<Pair<Integer, String>> statsConsultationsParMedium() {
        
        DAOMedium mediumDao=new DAOMedium();
        List<Pair<Integer, String>> consultationsParMedium=new ArrayList();
        try{
            JpaUtil.creerContextePersistance();

            List<Medium> listeMedium= mediumDao.listeMedium();
                for (Medium medium : listeMedium) {
                    Pair<Integer, String> pair = new Pair<>(medium.getNbconsultations(), medium.getDenomination());
                    consultationsParMedium.add(pair);

                }

            }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    return consultationsParMedium;
    }
   
   
    public  List<String> statsTop5Mediums() {
              
        DAOMedium mediumDao = new DAOMedium();
        List<String> topmediums = new ArrayList<String>();
        List<Pair<Integer, String>> consultationsParMedium=new ArrayList();
        
        try{
            JpaUtil.creerContextePersistance();
            List<Medium> listeMedium = mediumDao.listeMedium(); //liste des mediums
            for (Medium medium : listeMedium) {
                Pair<Integer, String> pair = new Pair<>(medium.getNbconsultations(), medium.getDenomination());
                consultationsParMedium.add(pair);//mediums avec leur nombre de consultations
            }

            for (int i = 0; i < 5; i++) {//5 itérations pour prendre le top 5
                int max = -1;//max initialisé à -1
                String mediumaAjouter = "";
                Iterator<Pair<Integer, String>> iter = consultationsParMedium.iterator();//itérateur sur la liste des mediums et leur nombre de consultations
                while (iter.hasNext()) { //Avec cette boucle, on récupère le max
                    Pair<Integer, String> pair = iter.next();
                    if (pair.getKey() > max) {
                        max = pair.getKey();
                        mediumaAjouter = pair.getValue();
                    }
                }
                iter = consultationsParMedium.iterator();
                while (iter.hasNext()) { ////Avec cette boucle on enlève le max, pour prendre son suivant à la prochaine itération 
                    Pair<Integer, String> pair = iter.next();
                    if (mediumaAjouter.equals(pair.getValue())) {
                        iter.remove();//On enlève le max
                    }
                }
                topmediums.add(mediumaAjouter);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            topmediums=null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    return topmediums;
    }
      
        /*try{
            JpaUtil.creerContextePersistance();
            topmediums=mediumDao.top5();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    return topmediums;
    }*/
    
      public  List<Pair<String, List<String>>>statsRepartitionClientsParEmploye() {
       
       DAOEmploye employeDao=new DAOEmploye();   
       List<Pair<String, List<String>>> employeClients=new ArrayList();
        try{
            JpaUtil.creerContextePersistance();

            List<Employe> listeEmploye= employeDao.listeEmployes();
            for (Employe employe : listeEmploye) {
                    List<String> listeClients=new ArrayList<String>();
                    List<Consultation> consultations=employe.getConsultationseffectuees();//Toutes les consultations de l'employé               
                        for (Consultation consultation : consultations) {
                        String nomclient=consultation.getClient().getNom();//on récupère le nom du client
                        if(!listeClients.contains(nomclient)) //S'il n'est pas déjà contenu, on l'ajoute
                                listeClients.add(nomclient);
                        }
                    Pair<String,List<String>> pair=new Pair<>(employe.getPrenom(), listeClients);//Un employe et sa liste de clients
                    employeClients.add(pair);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    return employeClients;
    }
      
    public Integer statsNbConsultationsenCours(){
         
        DAOEmploye employeDao=new DAOEmploye();
        Integer nbConsultations;
        try{
            JpaUtil.creerContextePersistance();
            nbConsultations=employeDao.nbNonDisponible();
            }
        catch(Exception ex){
            ex.printStackTrace();
            nbConsultations=null;
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    return nbConsultations;
     
    }
    
    
    public Integer statsNbConsultations() {
              
        DAOEmploye employeDao=new DAOEmploye();
        Integer nbConsultations=0;
        try{
            JpaUtil.creerContextePersistance();
            List<Employe> liste=employeDao.listeEmployes();//liste des employes
            for (Employe employe : liste) {
                nbConsultations+=employe.getConsultationseffectuees().size();//Je somme le nombre de consultations effectuées par tous les employés
            }
            }
        catch(Exception ex){
            ex.printStackTrace();
            nbConsultations=null;
        }
        finally{
            JpaUtil.fermerContextePersistance();
        }
    return nbConsultations;
    }
        
}
