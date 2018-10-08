package com.mediasoft.qrpay.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "t_banque",schema = "sc_metier")
public class Banque implements Serializable {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_i", nullable = false, unique = true)
    public int id;


    @Column(name = "r_libelle",length=30)
    @Valid
    @NotNull(message = "Veuillez entrer saisir libelle")
    @NotEmpty(message = "Veuillez entrer saisir libelle")
    private String libelle;


    @Column(name = "r_code",length=15)
    @Valid
    @NotNull(message = "Veuillez entrer saisir r_code")
    @NotEmpty(message = "Veuillez entrer saisir r_code")
    private String code;

    @Column(name = "r_logo",length=15)
    @Valid
    @NotNull(message = "Veuillez entrer saisir r_logo")
    @NotEmpty(message = "Veuillez entrer saisir r_logo")
    private String logo;

    @JsonIgnore
    @Column(name = "r_status")
    private int status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
