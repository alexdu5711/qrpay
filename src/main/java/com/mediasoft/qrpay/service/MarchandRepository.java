package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Marchand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarchandRepository extends JpaRepository<Marchand,Long> {
    Marchand findByCode(String code);
}
