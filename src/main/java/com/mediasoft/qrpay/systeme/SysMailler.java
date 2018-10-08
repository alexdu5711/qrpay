package com.mediasoft.qrpay.systeme;
import com.mediasoft.qrpay.entities.Mailling;
import com.mediasoft.qrpay.service.MaillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
@Repository
public class SysMailler {
    @Autowired
    MaillingRepository maillingRepository;


        private String sujet;
        private String destinbataire;
        private String message;
        private String type;



    public MaillingRepository getMaillingRepository() {
        return maillingRepository;
    }

    public void setMaillingRepository(MaillingRepository maillingRepository) {
        this.maillingRepository = maillingRepository;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDestinbataire() {
        return destinbataire;
    }

    public void setDestinbataire(String destinbataire) {
        this.destinbataire = destinbataire;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean EnvoiMail(String destinbataire, String sujet, String message)
    {

        this.destinbataire = destinbataire;
        this.message = message;
        this.sujet = sujet;
        this.type = "email";


        Mailling mailling = new Mailling();
        mailling.setDestinataire(destinbataire);
        mailling.setType("email");
        mailling.setSujet(sujet);
        mailling.setMessage(message);



        try {
           maillingRepository.save(mailling);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }




}
