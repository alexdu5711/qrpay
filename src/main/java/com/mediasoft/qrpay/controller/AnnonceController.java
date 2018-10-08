package com.mediasoft.qrpay.controller;


import com.mediasoft.qrpay.entities.*;
import com.mediasoft.qrpay.forms.Retour;
import com.mediasoft.qrpay.service.AnnonceRepository;
import com.mediasoft.qrpay.service.BanqueRepository;
import com.mediasoft.qrpay.systeme.SysMailler;
import com.mediasoft.qrpay.systeme.Sysdoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("qrpay/annonce/")
public class AnnonceController {

    @Autowired
    AnnonceRepository annonceRepository;

    @GetMapping("/")
    public ResponseEntity postResponseController()
    {
        //List<Banque> banqueList = banqueRepository.findAll(Sort.by("libelle"));
        List<Annonces> annoncesList = annonceRepository.findByStatus(1,PageRequest.of(0, 5));
        return new ResponseEntity<>(annoncesList,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity postResponseController(@PathVariable int id)
    {
        List<Annonces> annoncesList = annonceRepository.findByStatus(1,PageRequest.of(id, 10));
        return new ResponseEntity<>(annoncesList,HttpStatus.OK);
    }




}


