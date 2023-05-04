/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import metier.modele.Employe;
import metier.modele.Client;
import dao.JpaUtil;
import Util.Saisie;
import metier.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Main {

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JpaUtil.creerFabriquePersistance();
        //testerInitialiserEmployes();
        //testerInitialiserMedium();
            System.out.println("------- POUR LE CLIENT -------\n"
                    + "1-Inscription Client\n"
                    + "2-Authentifier Client\n"
                    + "3-Consulter profil Astral\n"
                    + "4-Consulter historiques des consultations\n"
                    + "5-Afficher les médiums\n"
                    + "6-Selectionner Medium\n"
                    + "\n"
                    + "------- POUR L'EMPLOYE -------\n"
                    + "7-Authentifier Employé\n"
                    + "8-Consulter Historique du Client\n"
                    + "9-Je suis pret(pour l'employe)\n"
                    + "10-Demander des prédictions\n"
                    + "11-Confirmer fin de la consultation\n"
                    + "12-Laisser un Commentaires\n"
                    + "13-Statistiques");
            Saisie saisie= new Saisie();
            Integer choix=saisie.lireInteger("Veuillez renseigner votre choix");
            switch(choix){

                case 1 :    testerInscriptionClient();
                            break;

                case 2 :    testerAuthentifierClient();
                            break;

                case 3 :    testerConsulterProfilAstral();
                            break;

                case 4 :    testerHistoriqueConsultationsCoteClient();
                            break;

                case 5 :    testerListerMediums();
                            break;

                case 6 :    testerDemanderConsultation();
                            break;
                            
                case 7 :    testerAuthentifierEmploye();
                            break;

                case 8 :    testerHistoriqueConsultationsCoteEmploye();
                            break;

                case 9 :    testerJeSuisPret();
                            break;

                case 10 :    testerDemanderAide();
                            break;


                case 11 :   testerConfirmerFinConsultation();
                            break;

                case 12 :   testerLaissercommentaire();
                            break;

                case 13:    testerStats();
                            break;

            }


        JpaUtil.fermerFabriquePersistance();
}
    
    
    static void testerInitialiserEmployes(){
        
        Service service =new Service();
        service.initialiserEmployes(); 
    }
         
     
    static void testerInitialiserMedium(){
        
        Service service =new Service();
        service.initialiserMediums();
      
    }
    
    static void testerInscriptionClient(){
        
        Service service = new Service();
        Saisie saisie = new Saisie();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date naissance=null;
        String nom=saisie.lireChaine("Veuillez renseigner votre nom :");
        String prenom=saisie.lireChaine("Veuillez renseigner votre prénom :");
        String dateaparse=saisie.lireChaine("Veuillez renseigner votre date de naissance (format: dd/MM/yyyy) :");
        String numerotel=saisie.lireChaine("Veuillez renseigner votre numero de telephone");
        String adressepostale=saisie.lireChaine("Veuillez renseigner votre adresse postale :");
        String mail=saisie.lireChaine("Veuillez rentrer votre adresse mail :");
        String motdepasse=saisie.lireChaine("Veuillez rentrer un mot de passe :");
        
        try {
            naissance = dateFormat.parse(dateaparse);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        Client client = new Client(nom, prenom, naissance, numerotel,adressepostale, motdepasse, mail);
        client=service.inscriptionClient(client);
        
        if(client == null){
            System.out.println("L'inscription a échoué");
        }else{
            System.out.println("Inscription réussie!");   
            System.out.println(client);
        }
    }
     
     
    static void testerAuthentifierClient(){
        
        Service service = new Service();
        Client client = new Client();
        Saisie saisie = new Saisie();
   
        String mail = saisie.lireChaine("Veuillez renseigner votre adresse mail");
        String motDePasse = saisie.lireChaine("Veuillez renseigner votre mot de passe");
        client= service.authentifierClient(mail,motDePasse);
        if(client==null){
            System.out.println("Adresse mail ou mot de passe invalide ");
        }else{
            System.out.println("Vous êtes identifiés en tant que :");
            System.out.println(client);
        }
    }
    
    static void testerAuthentifierEmploye(){
        
        Service service = new Service();
        Employe employe = new Employe();
        Saisie saisie = new Saisie();
   
        String login = saisie.lireChaine("Veuillez renseigner votre login");
        String motDePasse = saisie.lireChaine("Veuillez renseigner votre mot de passe");
        employe= service.authentifierEmploye(login,motDePasse);
        if(employe==null){
            System.out.println("Login ou mot de passe invalide ");
        }else{
            System.out.println("Vous êtes identifiés en tant que :");
            System.out.println(employe);
        }
    }
     
    static void testerListerMediums(){
        
        Service service=new Service();
        List<Medium> listedesmediums=service.listerMediums();
        for (Medium medium : listedesmediums) {
            System.out.println(medium);
        }
   
    }
    
    static void testerConsulterProfilAstral(){
        
        
        Service service = new Service();
        Saisie saisie = new Saisie();
        String mail = saisie.lireChaine("Veuillez renseigner votre adresse mail");
        String motDePasse = saisie.lireChaine("Veuillez renseigner votre mot de passe");
        Client client=service.authentifierClient(mail, motDePasse);
        if(client==null){
            System.out.println("Adresse mail ou mot de passe invalide ");
        }else{
            System.out.println("Vous êtes identifiés en tant que :");
            System.out.println(client);
        }
        List <String> profilAstral=service.consulterProfilAstral(client);
      
        System.out.println("votre signe du zodiaque est:  "+ profilAstral.get(0)+"\n"
               +"votre signe chinois est:  "+ profilAstral.get(1)+"\n"
                +"votre couleur porte-bonheur:  "+ profilAstral.get(2)+"\n"
                +"animal totem:  "+ profilAstral.get(3));
    }
    
    static void testerDemanderConsultation(){
        
        Service service=new Service();
        Saisie saisie = new Saisie();
        String mail = saisie.lireChaine("Veuillez renseigner votre adresse mail");
        String motDePasse = saisie.lireChaine("Veuillez renseigner votre mot de passe");
        Client client=service.authentifierClient(mail, motDePasse);
        if(client==null){
            System.out.println("Adresse mail ou mot de passe invalide ");
        }else{
            System.out.println("Vous êtes identifiés en tant que :");
            System.out.println(client);
        }
        long mediumId=saisie.lireInteger("Veuillez renseigner l'ID du medium que vous avez choisi :");
        Medium medium=service.trouverMediumParId(mediumId);
        
        Employe employe=service.demanderConsultation(client,medium);    
        System.out.println("Employé qui fera la consultation: "+employe);
    }
     
    
    static void testerJeSuisPret(){
        
        Service service=new Service();
        Saisie saisie = new Saisie();
   
        String login = saisie.lireChaine("Veuillez renseigner votre login");
        String motDePasse = saisie.lireChaine("Veuillez renseigner votre mot de passe");
        Employe employe= service.authentifierEmploye(login,motDePasse);
        if(employe==null){
            System.out.println("Login ou mot de passe invalide ");
        }else{
            System.out.println("Vous êtes identifiés en tant que :");
            System.out.println(employe);
        }
        service.jeSuisPret(employe);
    }
    
    static void testerDemanderAide(){
         
        Service service=new Service();
        Saisie saisie = new Saisie();
        String login = saisie.lireChaine("Veuillez renseigner votre login");
        String motDePasse = saisie.lireChaine("Veuillez renseigner votre mot de passe");
        Employe employe= service.authentifierEmploye(login,motDePasse);
        if(employe==null){
            System.out.println("Login ou mot de passe invalide ");
        }else{
            System.out.println("Vous êtes identifiés en tant que :");
            System.out.println(employe);
        }
        if (employe.getConsultationactuelle()==null){
            System.out.println("Aucune consultation en cours");
        }else{
            Integer amour=saisie.lireInteger("Veuillez renseigner le niveau d'amour :");
            Integer sante=saisie.lireInteger("Veuillez renseigner le niveau de sante :");
            Integer travail=saisie.lireInteger("Veuillez renseigner le niveau de travail :");
            List<String> predictions=service.demanderAide(employe.getConsultationactuelle().getClient(),amour,sante,travail);
            System.out.println("Amour: " + predictions.get(0)+"\n Santé: " + predictions.get(1)+"\n Travail: " + predictions.get(2));
        }
        // prediction en Amour = predictions.get(0);
        // prediction en Sante = predictions.get(1);
        // prediction en Travail = predictions.get(2);
    }

    
    static void testerConfirmerFinConsultation(){
        
        Service service=new Service();
        Saisie saisie = new Saisie();
        String login = saisie.lireChaine("Veuillez renseigner votre login");
        String motDePasse = saisie.lireChaine("Veuillez renseigner votre mot de passe");
        Employe employe= service.authentifierEmploye(login,motDePasse);
        if(employe==null){
            System.out.println("Login ou mot de passe invalide ");
        }else{
            System.out.println("Vous êtes identifiés en tant que :");
            System.out.println(employe);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        Consultation consultation=service.confirmerFinConsultation(employe);
        if (consultation==null){
            System.out.println("une erreur est survenue");
        }else{
            System.out.println("Vous avez bien mis fin à la consultation du "+dateFormat.format(consultation.getDate())+" avec le client "+consultation.getClient() );
            System.out.println("Vos informations et celles du médium que vous avez incarné ont été bien mises à jour");
            System.out.println(employe);
            System.out.println(consultation.getMediumaincarner()+"nombre de consultations="+consultation.getMediumaincarner().getNbconsultations());
        }
       
    }

    
    
     static void testerLaissercommentaire(){
        
        Service service=new Service();
        Saisie saisie=new Saisie();
        String login = saisie.lireChaine("Veuillez renseigner votre login");
        String motDePasse = saisie.lireChaine("Veuillez renseigner votre mot de passe");
        Employe employe= service.authentifierEmploye(login,motDePasse);
        if(employe==null){
            System.out.println("Login ou mot de passe invalide ");
        }else{
            System.out.println("Vous êtes identifiés en tant que :");
            System.out.println(employe);
        }
        String commentaire = saisie.lireChaine("Veuillez renseigner votre avis sur le déroulement de la consultation");        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        Consultation consultation=service.laisserCommentaire(employe, commentaire);
        
        if (consultation==null){
            System.out.println("une erreur est survenue");
        }else{
            System.out.println("Vous avez bien mis à jour la consultation du "+dateFormat.format(consultation.getDate())+"avec le client "+consultation.getClient() );
            System.out.println("commentaire ajouté: "+consultation.getCommentaire());
        }
       
    }
     
    static void testerHistoriqueConsultationsCoteEmploye(){ 
        
        Service service=new Service();
        Saisie saisie = new Saisie();
        String login = saisie.lireChaine("Veuillez renseigner votre login");
        String motDePasse = saisie.lireChaine("Veuillez renseigner votre mot de passe");
        Employe employe= service.authentifierEmploye(login,motDePasse);
        if(employe==null){
            System.out.println("Login ou mot de passe invalide ");
        }else{
            System.out.println("Vous êtes identifiés en tant que :");
            System.out.println(employe);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //Un employe consulte l'historique d'un client dont il est actuellement en charge
        if(employe.getConsultationactuelle()==null){
            System.out.println("Vous n'etes en charge d'aucun client actuellement");
        }else{
            List<Consultation> consultationClient=employe.getConsultationactuelle().getClient().getConsultations();
            for (Consultation consultation : consultationClient) {
                System.out.println(consultation.getMediumaincarner().getDenomination()+"             "+dateFormat.format(consultation.getDate())+"\n"
                +consultation.getCommentaire());
                }
        }
    }
    
          
    static void testerHistoriqueConsultationsCoteClient(){ 
        
        Service service=new Service();
        Saisie saisie = new Saisie();
        String mail = saisie.lireChaine("Veuillez renseigner votre adresse mail");
        String motDePasse = saisie.lireChaine("Veuillez renseigner votre mot de passe");
        Client client=service.authentifierClient(mail, motDePasse);
        if(client==null){
            System.out.println("Adresse mail ou mot de passe invalide ");
        }else{
            System.out.println("Vous êtes identifiés en tant que :");
            System.out.println(client);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        List<Consultation> ConsultationClient=client.getConsultations();
        for (Consultation consultation : ConsultationClient) {
            System.out.println(consultation.getMediumaincarner().getDenomination()+"             "+dateFormat.format(consultation.getDate())+"\n");
        }
    }
     
     
    static void testerStats(){ 
        Service service=new Service();
        Saisie saisie = new Saisie();
        String login = saisie.lireChaine("Veuillez renseigner votre login");
        String motDePasse = saisie.lireChaine("Veuillez renseigner votre mot de passe");
        Employe employe= service.authentifierEmploye(login,motDePasse);
        if(employe==null){
            System.out.println("Login ou mot de passe invalide ");
        }else{
            System.out.println("Vous êtes identifiés en tant que :");
            System.out.println(employe);
        
        List<Pair<Integer, String>> consultationsparmedium=service.statsConsultationsParMedium();
        System.out.println("------- Nombre de Consultations par Médiums  ---------");
         for (Pair<Integer, String> pair : consultationsparmedium) {
            System.out.println(pair.getValue()+" : "+pair.getKey());
         }
         
        List<String> top5Medium=service.statsTop5Mediums();
        System.out.println("------- Top 5 des Médiums choisis par les Clients ---------");
        for (String mediumNom : top5Medium) {
            System.out.println(mediumNom);
        }
        
        List<Pair<String, List<String>>> listeRepartition= service.statsRepartitionClientsParEmploye();
        System.out.println("------- Répartition des Clients par Employés ---------");
        for (Pair<String, List<String>> pair : listeRepartition) {
            if(pair.getValue().isEmpty()){
                System.out.println("L'employé "+pair.getKey()+" n'a aucun client.");
            }else{
                System.out.print("Voici la liste des clients de "+pair.getKey()+" : ");
           
                for (String client : pair.getValue()) {
                    System.out.print(client + ", ");
                }
            System.out.print("\n");    
            }
        }
        
        Integer nbConsultation=service.statsNbConsultations();
        System.out.println("------- Nombre total de consultations réailsées par PREDICT'IF---------");
        System.out.println(nbConsultation);
        
        Integer nbConsultationenCours=service.statsNbConsultationsenCours();
        System.out.println("------- Nombre de consultations actuellement en cours ---------");
        System.out.println(nbConsultationenCours);
        }
    }

     
}
    

