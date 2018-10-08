package com.mediasoft.qrpay.controller;

import com.mediasoft.qrpay.entities.Banque;
import com.mediasoft.qrpay.service.BanqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("qrpay/banque/")
public class BanqueController {

    @Autowired
    BanqueRepository banqueRepository;

    @GetMapping("/")
    public ResponseEntity postResponseController()
    {
        //List<Banque> banqueList = banqueRepository.findAll(Sort.by("libelle"));
        List<Banque> banqueList = banqueRepository.findByStatus(1);
        return new ResponseEntity<>(banqueList,HttpStatus.OK);
    }
}
