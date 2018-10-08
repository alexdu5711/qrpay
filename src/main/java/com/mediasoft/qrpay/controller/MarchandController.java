package com.mediasoft.qrpay.controller;

import com.mediasoft.qrpay.entities.Contact;
import com.mediasoft.qrpay.entities.Marchand;
import com.mediasoft.qrpay.entities.RegisterCode;
import com.mediasoft.qrpay.entities.Utilisateur;
import com.mediasoft.qrpay.forms.Retour;
import com.mediasoft.qrpay.service.MarchandRepository;
import com.mediasoft.qrpay.systeme.SysMailler;
import com.mediasoft.qrpay.systeme.Sysdoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("qrpay/marchand")
public class MarchandController {
    @Autowired
    MarchandRepository marchandRepository;

    @GetMapping("/{id}")
    public ResponseEntity postResponseController(@PathVariable String id)
    {
        Retour reponse = new Retour();

        Marchand marchand = marchandRepository.findByCode(id);
        if ( marchand == null) {
            return reponse.Out(reponse.SetRetour("Marchand introuvable ou innexistant", 400), HttpStatus.BAD_REQUEST);
        }

        if(marchand.getStatus()!=1)
        {
            return reponse.Out(reponse.SetRetour("Marchand désactivé", 400), HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(marchand,HttpStatus.OK);
    }
}
