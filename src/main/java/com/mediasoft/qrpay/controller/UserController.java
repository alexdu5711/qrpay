package com.mediasoft.qrpay.controller;

import com.mediasoft.qrpay.entities.*;
import com.mediasoft.qrpay.forms.LoginForm;
import com.mediasoft.qrpay.forms.Register;
import com.mediasoft.qrpay.forms.Retour;
import com.mediasoft.qrpay.forms.Retour_login;
import com.mediasoft.qrpay.service.*;
import com.mediasoft.qrpay.systeme.SysMailler;
import com.mediasoft.qrpay.systeme.Sysdoc;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("qrpay/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    PasswordRepository passwordRepository;
    @Autowired
    RegisterCodeRepository registerCodeRepository;
    @Autowired
    MaillingRepository maillingRepository;

    @Autowired
    SessionRepository sessionRepository;




    private String app = "";

    private String link = "";

    public Boolean EnvoiMail(String destinbataire, String sujet, String message,String type) {


        Mailling mailling = new Mailling();
        mailling.setDestinataire(destinbataire);
        mailling.setType(type);
        mailling.setSujet(sujet);
        mailling.setMessage(message);
        try {
            maillingRepository.save(mailling);
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    @PutMapping("/register")
    @ResponseBody
    public ResponseEntity postResponseController(
            @RequestBody Register register) {
        Retour reponse = new Retour();
        SysMailler sysMailler = new SysMailler();


        if(register.getFrom() !="WEB")
        {

            Utilisateur utilisateur = userRepository.findByFromidAndFrom(register.getFromid(),register.getFrom());

            if(utilisateur !=null)
            {

                utilisateur.setLast_connect(new Date());
                try {
                    userRepository.save(utilisateur);
                } catch (Exception e) {
                 //   System.out.println("-----------------"+e.toString());
                    reponse.SetRetour("Une erreur est survenue durant la connexion ", 500);
                    return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }

                String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                String code = RandomStringUtils.random( 15, characters );

                Session session = new Session();
                session.setCode(code);
                session.setUtilisateur(utilisateur);
                session.setBulk(register.getDevice());
                session.setDate(new Date());
                try {
                    sessionRepository.save(session);
                } catch (Exception e) {
                    //   System.out.println("-----------------"+e.toString());
                    reponse.SetRetour("Une erreur est survenue durant la session ", 500);
                    return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }


                Retour_login retour_login = new Retour_login();

                retour_login.setStatus(2);
                retour_login.setSession(code);
                retour_login.setUsers(utilisateur);
               // System.out.println(utilisateur.toString());


                // System.out.println(cli.getNom());
                return new ResponseEntity<>(retour_login,HttpStatus.OK);


            }

        }





        Contact ct[] = register.getContact();
        for (Contact entity : ct) {
            if (contactRepository.existsByValeur(entity.getValeur())) {

                reponse.SetRetour("Les informations entrées existent déjà veuillez vous connecter ", 400);
                return reponse.Out(reponse, HttpStatus.BAD_REQUEST);
            }
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(register.getNom());
        utilisateur.setPrenoms(register.getPrenom());
        utilisateur.setSexe(register.getSexe());
        utilisateur.setFrom(register.getFrom());
        utilisateur.setFromid(register.getFromid());
        utilisateur.setPic(register.getPicture());

        try {
            userRepository.save(utilisateur);
        } catch (Exception e) {

            reponse.SetRetour("Une erreur est survenue durant l'enregistrement de l'utilisateur veuillez rééssayer "+e.toString(), 500);
            return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        Pwd pwd = new Pwd(register.getPwd(),utilisateur);
        try {
            passwordRepository.save(pwd);
        } catch (Exception e) {
            System.out.println(e.toString());
            reponse.SetRetour("Une erreur est survenue durant l'enregistrement du pwd veuillez rééssayer ", 500);
            return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        String characters = "0123456789";
        String code = RandomStringUtils.random( 5, characters );
        RegisterCode registerCode = new RegisterCode();
        registerCode.setCode(code);
        registerCode.setUtilisateur(utilisateur);

        try {
            registerCodeRepository.save(registerCode);
        } catch (Exception e) {
            reponse.SetRetour("Une erreur est survenue durant l'enregistrement du code de validation ", 500);
            return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }





        Contact cta[] = register.getContact();

        for (Contact entity : cta) {
            entity.setUtilisateur(utilisateur);
            entity.setStatus(0);
            try {
                contactRepository.save(entity);
            } catch (Exception e) {
                System.out.println(e.toString());
                reponse.SetRetour("Une erreur est survenue durant l'enregistrement du contact veuillez rééssayer ", 500);
                return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }

                if(!entity.getType().equals("phone") )
                {

                    String message = "Bienvenue sur "+app+" \n Afin de confirmer votre inscription veuillez cliquer sur le lien suivant \n" +
                            link+"/user/confirme/"+code+
                            "\n L'équipe de "+app;

                    if(!EnvoiMail(entity.getValeur(),"Bienvenue sur "+app,message,"email"))
                    {
                        reponse.SetRetour("Une erreur est survenue durant l'enregistrement du mail veuillez rééssayer ", 500);
                        return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                }
                else
                {
                    String message = "Veuillez saisir le code suivant afin de confirmer votre numéro de téléphone "+code;

                    if(!EnvoiMail(entity.getValeur(),"Bienvenue sur "+app,message,"sms"))
                    {
                        reponse.SetRetour("Une erreur est survenue durant l'enregistrement du sms veuillez rééssayer ", 500);
                        return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }


        }




        String characters1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code1 = RandomStringUtils.random( 15, characters1 );


        Session session = new Session();
        session.setCode(code1);
        session.setUtilisateur(utilisateur);
        session.setBulk(register.getDevice());
        session.setDate(new Date());
        try {
            sessionRepository.save(session);
        } catch (Exception e) {
            //   System.out.println("-----------------"+e.toString());
            reponse.SetRetour("Une erreur est survenue durant la session ", 500);
            return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Retour_login retour_login = new Retour_login();
        retour_login.setStatus(1);
        retour_login.setSession(code);
        retour_login.setUsers(utilisateur);



        return new ResponseEntity<>(retour_login,HttpStatus.OK);


    }


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity postResponseController(
            @RequestBody LoginForm loginForm) {
        Retour reponse = new Retour();

        Contact ct = contactRepository.findByValeur(loginForm.getLogin());
        if (ct == null) {
            return reponse.Out(reponse.SetRetour("Login ou Mot de passe incorrect", 404), HttpStatus.NOT_FOUND);
        }

        Pwd pass = passwordRepository.findByUtilisateur(ct.getUtilisateur());

        if(!new BCryptPasswordEncoder().matches(loginForm.getPass() + pass.getSalt(),pass.getMdp()))
        {
            return reponse.Out(reponse.SetRetour("Login ou Mot de passe incorrect", 400), HttpStatus.BAD_REQUEST);
        }




        Utilisateur cli = userRepository.findById(pass.getUtilisateur().getId());

        if(cli.getStatus() !=1 )
        {
            return reponse.Out(reponse.SetRetour("Votre compte n'est pas activé veuillez l'activer via le lien reçu par mail ou contacter l'administrateur", 400), HttpStatus.BAD_REQUEST);
        }




        cli.setLast_connect(new Date());
        try {
            userRepository.save(cli);
        } catch (Exception e) {
            System.out.println("-----------------"+e.toString());
            reponse.SetRetour("Une erreur est survenue durant la connexion ", 500);
            return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


       // System.out.println(cli.getNom());
        return new ResponseEntity<>(cli,HttpStatus.OK);
    }


    @GetMapping("/confirme/{id}")
    public ResponseEntity postResponseController(@PathVariable String id)
           {


               System.out.println(app);
               Retour reponse = new Retour();
               RegisterCode registerCode = registerCodeRepository.findByCode(id);
               if ( registerCode == null) {
                   return reponse.Out(reponse.SetRetour("Code de validation expiré ou introvable", 400), HttpStatus.BAD_REQUEST);
               }

               if(registerCode.getStatus()!=0)
               {
                   return reponse.Out(reponse.SetRetour("Code de validation expiré ou introvable", 400), HttpStatus.BAD_REQUEST);
               }


               registerCode.setStatus(1);
               registerCode.setLast_connect(new Date());
               try {
                   registerCodeRepository.save(registerCode);
               } catch (Exception e) {

                   reponse.SetRetour("Une erreur est survenue durant la validation de votre compte ", 500);
                   return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
               }


               Utilisateur utilisateur = userRepository.findById(registerCode.getUtilisateur().getId());
                if(utilisateur ==null)
                {
                    reponse.SetRetour("Utilisateur introuvable  ", 500);
                    return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }


                Contact contact = contactRepository.findByUtilisateurAndAndType(utilisateur,"email");
               if(contact ==null)
               {
                   reponse.SetRetour("Contact utilisateur introuvable introuvable  ", 500);
                   return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
               }




               String message = "Nous sommes très heureux de vous confirmer votre inscription sur EWOH.\n" +
                       "Toute l’équipe EWOH vous remercie de votre confiance et vous souhaite la bienvenue.";

               SysMailler sysMailler = new SysMailler();
               if(!EnvoiMail(contact.getValeur(),"Bienvenue sur EWOH",message,"email"))
               {
                   reponse.SetRetour("Une erreur est survenue durant l'enregistrement du mail veuillez rééssayer ", 500);
                   return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
               }


               utilisateur.setStatus(1);

               try {
                   userRepository.save(utilisateur);
               } catch (Exception e) {
                   reponse.SetRetour("Une erreur est survenue durant la validation de votre compte ", 500);
                   return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
               }


               Sysdoc sysdoc = new Sysdoc();

               sysdoc.setUser(utilisateur.getId());
               sysdoc.setUserPhotoDir();
               sysdoc.setUserPieceDir();
               sysdoc.setUserVehiculeDir();
               sysdoc.CreatUserDir(utilisateur.getId());

               return reponse.Out(reponse.SetRetour("Valider avec succès", 200), HttpStatus.OK);
            }


}
