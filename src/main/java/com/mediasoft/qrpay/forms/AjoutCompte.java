package com.mediasoft.qrpay.forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AjoutCompte {


    @NotNull(message = "Veuillez entrer le code banque")
    @Valid
    private String banque;

    @NotNull(message = "Veuillez entrer le code agence")
    @Valid
    private String agence;

    @NotNull(message = "Veuillez entrer le compte")
    @Valid
    private String compte;

    @NotNull(message = "Veuillez entrer la cle_rib")
    @Valid
    private String cle_rib;


    @NotNull(message = "Veuillez entrer l'utilisateur")
    @Valid
    @NotNull
    private int user;

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

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
