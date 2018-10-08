package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Contact;
import com.mediasoft.qrpay.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    Contact findByValeur(String contact);
    Contact findByUtilisateurAndAndType(Utilisateur cli, String contact);
    boolean existsByValeur(String contact);
    List<Contact> findByUtilisateur(Utilisateur cli);
}
