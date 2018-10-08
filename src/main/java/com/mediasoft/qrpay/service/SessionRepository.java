package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {

}
