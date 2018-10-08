package com.mediasoft.qrpay.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "t_utilisateur",schema = "sc_metier")
public class Utilisateur implements Serializable {
    //@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_i", nullable = false, unique = true)
    private int id;


    @Column(name = "r_nom",length=30)
    @Valid
    @NotNull(message = "Veuillez entrer saisir votre nom")
    @NotEmpty(message = "Veuillez entrer saisir votre nom")
    private String nom;



    @Column(name = "r_prenoms",length=30)
    @Valid
    @NotNull(message = "Veuillez entrer saisir le prénom")
    @NotEmpty(message = "Veuillez entrer saisir le  prénom")
    private String prenoms;


    @Column(name = "r_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date date;



    @Column(name = "r_from",length=10)
    private String from;

    @Column(name = "r_from_id",length=30)
    private String fromid;

    @JsonIgnore
    @Column(name = "r_status")
    private int status;

    @Column(name = "r_sexe",length = 1)
    private String sexe;

    @Column(name = "r_pic")
    private String pic;

    @Column(name = "r_last_connect")
    private Date last_connect;


    @OneToMany(mappedBy = "utilisateur")
    private List<Contact> contacts = new ArrayList<Contact>();




    public void setStatus(int status) {
        this.status = status;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getLast_connect() {
        return last_connect;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setLast_connect(Date last_connect) {
        this.last_connect = last_connect;
    }


}
