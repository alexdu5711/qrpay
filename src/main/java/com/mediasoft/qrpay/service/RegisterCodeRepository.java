package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.RegisterCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterCodeRepository extends JpaRepository<RegisterCode,Long> {

    RegisterCode findByUtilisateur(Integer user);
    RegisterCode findByCode(String code);
}
