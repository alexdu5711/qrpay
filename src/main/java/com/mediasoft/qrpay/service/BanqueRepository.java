package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Banque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BanqueRepository  extends JpaRepository<Banque,Long> {
    List<Banque> findByStatus(int status);
}
