package com.mediasoft.qrpay.controller;

import com.mediasoft.qrpay.entities.*;
import com.mediasoft.qrpay.forms.AjoutCompte;
import com.mediasoft.qrpay.forms.Retour;
import com.mediasoft.qrpay.service.CompteRepository;
import com.mediasoft.qrpay.service.MaillingRepository;
import com.mediasoft.qrpay.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("qrpay/compte")
public class CompteController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompteRepository compteRepository;

    @Autowired
    MaillingRepository maillingRepository;



    @PutMapping("/add")
    @ResponseBody
    public ResponseEntity postResponseController(
            @RequestBody AjoutCompte ajoutCompte) {
        Retour reponse = new Retour();

        String rib = ajoutCompte.getBanque()+ajoutCompte.getAgence()+ajoutCompte.getCompte()+ajoutCompte.getCle_rib();

        Utilisateur utilisateur = userRepository.findById(ajoutCompte.getUser());

        if (utilisateur == null) {
            return reponse.Out(reponse.SetRetour("Utilisateur introuvable", 404), HttpStatus.NOT_FOUND);
        }


       Compte compte=  compteRepository.findByRibAndUtilisateur(rib,utilisateur);

        if (compte != null) {
            return reponse.Out(reponse.SetRetour("Ce compte existe déjà dans votre liste de compte ", 404), HttpStatus.NOT_FOUND);
        }


        Compte ct1 = compteRepository.findByUtilisateurAndBanque(utilisateur,ajoutCompte.getBanque());

        if(ct1 != null)
        {
            return reponse.Out(reponse.SetRetour("Vous disposez déjà d'un compte actif pour cette banque "+ajoutCompte.getBanque(), 404), HttpStatus.NOT_FOUND);

        }


        Compte cp = new Compte();

        cp.setBanque(ajoutCompte.getBanque());
        cp.setAgence(ajoutCompte.getAgence());
        cp.setCompte(ajoutCompte.getCompte());
        cp.setCle_rib(ajoutCompte.getCle_rib());
        cp.setRib(rib);
        cp.setStatus(0);
        cp.setUtilisateur(utilisateur);

        try {
           // compteRepository.save(cp);
        } catch (Exception e) {

            reponse.SetRetour("Une erreur est survenue durant l'ajout  ", 500);
            return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return reponse.Out(reponse.SetRetour("Enregistré avec succès", 200), HttpStatus.OK);
    }


    @GetMapping("list/{id}/all")
    public ResponseEntity postResponseController(@PathVariable Integer id
                                                 //@RequestHeader("Authorization") String httpHeaders,@RequestHeader() HttpHeaders httpHeader
     )
    {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getRemoteAddr();
        System.out.println("-----------------"+ip);
        Retour reponse = new Retour();

        Utilisateur utilisateur = userRepository.findById(id);
        if ( utilisateur == null) {
            return reponse.Out(reponse.SetRetour("Utilisateur introuvable introvable", 400), HttpStatus.BAD_REQUEST);
        }

        List<Compte> compteList = compteRepository.findByUtilisateur(utilisateur);
        return new ResponseEntity<>(compteList,HttpStatus.OK);

    }

    @GetMapping("list/{id}/all/{stat}")
    public ResponseEntity postResponseController(@PathVariable Integer id,@PathVariable Integer Stat)
    {
        Retour reponse = new Retour();

        Utilisateur utilisateur = userRepository.findById(id);

        if ( utilisateur == null) {

            return reponse.Out(reponse.SetRetour("Utilisateur introuvable non trouvé", 400), HttpStatus.BAD_REQUEST);
        }

        List<Compte> compteList = compteRepository.findByUtilisateurAndStatus(utilisateur,Stat);
        //  List<Com> banqueList = banqueRepository.findByStatus(1);
        return new ResponseEntity<>(compteList,HttpStatus.OK);

    }

}
