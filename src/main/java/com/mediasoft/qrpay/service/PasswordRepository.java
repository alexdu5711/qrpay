package com.mediasoft.qrpay.service;

import com.mediasoft.qrpay.entities.Pwd;
import com.mediasoft.qrpay.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Pwd,Long> {
  Pwd findByUtilisateur(Utilisateur utilisateurId);
}
