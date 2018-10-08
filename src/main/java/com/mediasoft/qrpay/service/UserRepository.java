package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findById(Integer user);
    Utilisateur findByFromidAndFrom(String fromid,String from);
}
