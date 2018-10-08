package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture,Long> {

}
