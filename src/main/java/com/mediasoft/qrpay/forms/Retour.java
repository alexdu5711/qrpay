package com.mediasoft.qrpay.forms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Retour {
    private String libelle;
    private int status;
    @JsonIgnore
    private HttpStatus code;


    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }




    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public Retour() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Retour SetRetour(String libelle, int status) {
        this.libelle = libelle;
        this.status = status;
        return this;
    }

    public ResponseEntity Out(Retour retour, HttpStatus code)
    {
        return new ResponseEntity<>(retour,code);
    }


}
