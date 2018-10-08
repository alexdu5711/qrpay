package com.mediasoft.qrpay.forms;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AjoutFacture {
    @NotNull(message = "Veuillez entrer le libelle de la facture")
    @Valid
    @NotEmpty(message = "Veuillez entrer le libelle de la facture")
    private String libelle;

    @NotNull(message = "Veuillez entrer le montant de la facture")
    @Valid
    @NotEmpty(message = "Veuillez entrer le montant de la facture")
    private int montant;

    @NotNull(message = "Veuillez entrer le beneficiaire de la facture")
    @Valid
    @NotEmpty(message = "Veuillez entrer le beneficiaire de la facture")
    private String beneficiaire;

    @NotNull(message = "Veuillez entrer le expediteur de la facture")
    @Valid
    @NotEmpty(message = "Veuillez entrer le expediteur de la facture")
    private String expediteur;


    private  String details;

    private  String img;

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

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
