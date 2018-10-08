package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture,Long> {

    List<Facture> findByDestinataire(String dest);
    List<Facture> findByEmetteur(String emet);
    Facture findByCode(String code);
}
