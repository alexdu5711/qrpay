package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Annonces;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnonceRepository  extends JpaRepository<Annonces,Long> {
    List<Annonces> findByStatus(int status,Pageable pageable);

}
