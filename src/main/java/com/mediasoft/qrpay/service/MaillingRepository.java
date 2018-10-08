package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Mailling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaillingRepository extends JpaRepository<Mailling,Long> {
    Mailling findById(int Id);
}
