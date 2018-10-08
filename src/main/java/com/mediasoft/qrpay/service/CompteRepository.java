package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Compte;
import com.mediasoft.qrpay.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte,Long> {

   // Compte findByR_ribAndUtilisateur(String rib, Utilisateur utilisateur);
    Compte findByRibAndUtilisateur(String rib, Utilisateur utilisateur);
    List<Compte> findByUtilisateurAndStatus(Utilisateur utilisateur,int status);
    List<Compte> findByUtilisateur(Utilisateur utilisateur);

    Compte findByUtilisateurAndBanque(Utilisateur utilisateur,String banque);

}
