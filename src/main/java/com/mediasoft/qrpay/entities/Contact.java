package com.mediasoft.qrpay.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_contact",schema = "sc_metier")
public class Contact implements Serializable {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_i", nullable = false, unique = true)
    public int id;

    @Column(name = "r_valeur")
    private String valeur;

    @Column(name = "r_type")
    private String type;

    @Column(name = "r_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date date;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn( name = "r_iutilisateurs" )
    private Utilisateur utilisateur;

    @JsonIgnore
    @Column(name = "r_status")
    private int status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }




}
