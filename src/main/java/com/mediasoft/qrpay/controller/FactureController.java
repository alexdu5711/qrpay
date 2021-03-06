package com.mediasoft.qrpay.controller;

import com.mediasoft.qrpay.entities.*;
import com.mediasoft.qrpay.forms.AjoutFacture;
import com.mediasoft.qrpay.forms.Retour;
import com.mediasoft.qrpay.service.ContactRepository;
import com.mediasoft.qrpay.service.FactureRepository;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("qrpay/facture/")
public class FactureController {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    FactureRepository factureRepository;


    @PutMapping("/")
    @ResponseBody
    public ResponseEntity postResponseController(
            @RequestBody AjoutFacture ajoutFacture) {
        Retour reponse = new Retour();

        System.out.println("-------Expediteur:"+ajoutFacture.getExpediteur()+"----Destinataire : "+ajoutFacture.getBeneficiaire());



        Contact contactexp = contactRepository.findByValeur(ajoutFacture.getExpediteur());
        if(contactexp ==null)
        {
            return reponse.Out(reponse.SetRetour("Expediteur introuvable", 404), HttpStatus.NOT_FOUND);
        }


        Contact contactbenf = contactRepository.findByValeur(ajoutFacture.getBeneficiaire());
        if(contactbenf ==null)
        {
            return reponse.Out(reponse.SetRetour("Bénéficiaire introuvable", 404), HttpStatus.NOT_FOUND);
        }


        if(contactexp.getUtilisateur().getStatus() != 1)
        {
            return reponse.Out(reponse.SetRetour("Expediteur non autorisé", 404), HttpStatus.NOT_FOUND);

        }

        if(contactbenf.getUtilisateur().getStatus() != 1)
        {
            return reponse.Out(reponse.SetRetour("Beneficiaire non autorisé", 404), HttpStatus.NOT_FOUND);

        }


        Facture facture = new Facture();

        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code1 = RandomStringUtils.random( 6, characters );
        String ref = code1+'-'+contactexp.getId();
        facture.setCode(ref.toUpperCase());
        facture.setDestinataire(contactbenf.getValeur());
        facture.setDestinatairei(contactbenf.getUtilisateur().getId());
        facture.setEmetteur(contactexp.getValeur());
        facture.setEmetteuri(contactexp.getUtilisateur().getId());
        facture.setImg(ajoutFacture.getImg());
        facture.setMontant(ajoutFacture.getMontant());
        facture.setLibelle(ajoutFacture.getLibelle());
        facture.setEtat("ENCOURS");
        try {
            factureRepository.save(facture);
        } catch (Exception e) {
            reponse.SetRetour("Une erreur est survenue durant l'enregistrement de la facture "+e.toString(), 500);
            return reponse.Out(reponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return reponse.Out(reponse.SetRetour("Votre facture référence: "+ref.toUpperCase()+" montant: "+
                ajoutFacture.getMontant()+" est en attente de validation ", 200), HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity postResponseController(@PathVariable String id)
    {

       // System.out.println(id);
        Retour reponse = new Retour();

        List<Facture> factureListDest =  factureRepository.findByDestinataire(id);
        List<Facture> factureListEmet =  factureRepository.findByEmetteur(id);

        List<Facture> factureListd = factureListDest;
        List<Facture> factureListe = factureListEmet;

        ListFacture listFacture = new ListFacture();

        listFacture.setFactureListE(factureListe);
        listFacture.setFactureListR(factureListd);


        return new ResponseEntity<>(listFacture,HttpStatus.OK);
    }

    public class ListFacture {

        public  List<Facture> factureListR;
        public  List<Facture> factureListE;

        public List<Facture> getFactureListR() {
            return factureListR;
        }

        public void setFactureListR(List<Facture> factureListR) {
            this.factureListR = factureListR;
        }

        public List<Facture> getFactureListE() {
            return factureListE;
        }

        public void setFactureListE(List<Facture> factureListE) {
            this.factureListE = factureListE;
        }
    }



}
