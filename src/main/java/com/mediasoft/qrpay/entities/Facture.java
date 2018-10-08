package com.mediasoft.qrpay.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "t_facture",schema = "sc_metier")
public class Facture implements Serializable {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_i", nullable = false, unique = true)
    public int id;

    @Column(name = "r_ref",length=20)
    @Valid
    private String code;



    @Column(name = "r_libelle",length=100)
    @Valid
    @NotNull(message = "Veuillez entrer saisir libelle")
    @NotEmpty(message = "Veuillez entrer saisir libelle")
    private String libelle;

    @Column(name = "r_montant")
    @Valid
    @NotNull()

    private int montant;


    @Column(name = "r_img")
    private String img;


    @Column(name = "r_emetteur",length=25)
    @Valid
    @NotNull(message = "Veuillez entrer saisir emetteur")
    @NotEmpty(message = "Veuillez entrer saisir emetteur")
    private String emetteur;

    @Column(name = "r_emetteuri")
    @Valid
    @NotNull(message = "Veuillez entrer saisir r_emetteuri")
    @JsonIgnore
    private int emetteuri;


    @Column(name = "r_destinataire",length=25)
    @Valid
    @NotNull(message = "Veuillez entrer saisir r_destinataire")
    @NotEmpty(message = "Veuillez entrer saisir r_destinataire")
    private String destinataire;

    @Column(name = "r_destinatairei")
    @JsonIgnore
    @Valid
    @NotNull(message = "Veuillez entrer saisir r_destinatairei")

    private int destinatairei;


    @Column(name = "r_etat",length=25)
    @Valid
    @NotNull(message = "Veuillez entrer saisir r_etat")
    @NotEmpty(message = "Veuillez entrer saisir r_etat")
    private String etat;

    @Column(name = "r_details",columnDefinition = "text")
    private String details;

    @Column(name = "r_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date date;

    @Column(name = "r_last_maj")
    private Date last_maj;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(String emetteur) {
        this.emetteur = emetteur;
    }

    public int getEmetteuri() {
        return emetteuri;
    }

    public void setEmetteuri(int emetteuri) {
        this.emetteuri = emetteuri;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public int getDestinatairei() {
        return destinatairei;
    }

    public void setDestinatairei(int destinatairei) {
        this.destinatairei = destinatairei;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getLast_maj() {
        return last_maj;
    }

    public void setLast_maj(Date last_maj) {
        this.last_maj = last_maj;
    }
}
