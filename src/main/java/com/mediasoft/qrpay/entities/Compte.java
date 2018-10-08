package com.mediasoft.qrpay.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "t_compte",schema = "sc_metier")
public class Compte {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_i", nullable = false, unique = true)
    public int id;

    @Column(name = "r_code_banque",length=8)
    @Valid
    @NotNull(message = "Veuillez entrer saisir code banque")
    @NotEmpty(message = "Veuillez entrer saisir code banque")
    private String banque;

    @Column(name = "r_agence",length=8)
    @Valid
    @NotNull(message = "Veuillez entrer saisir code agence")
    @NotEmpty(message = "Veuillez entrer saisir code agence")
    private String agence;

    @Column(name = "r_compte",length=15)
    @Valid
    @NotNull(message = "Veuillez entrer saisir compte")
    @NotEmpty(message = "Veuillez entrer saisir compte")
    private String compte;

    @Column(name = "r_cle_rib",length=3)
    @Valid
    @NotNull(message = "Veuillez entrer saisir cle_rib")
    @NotEmpty(message = "Veuillez entrer saisir cle_rib")
    private String cle_rib;


    @Column(name = "r_rib")
    private String rib;



    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn( name = "r_iutilisateurs" )
    private Utilisateur utilisateur;


    @Column(name = "r_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date date;


    @JsonIgnore
    @Column(name = "r_status")
    private int status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBanque() {
        return banque;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getCle_rib() {
        return cle_rib;
    }

    public void setCle_rib(String cle_rib) {
        this.cle_rib = cle_rib;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
