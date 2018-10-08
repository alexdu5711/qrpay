package com.mediasoft.qrpay.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_malling",schema = "sc_systeme")
public class Mailling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_i", nullable = false, unique = true)
    public int id;

    @Column(name = "r_destinataire")
    private String destinataire;

    @Column(name = "r_sujet")
    private String sujet;


    @Column(name = "r_message",columnDefinition = "text")
    private String message;


    @Column(name = "r_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date date;


    @Column(name = "r_type")
    private String type;


    @Column(name = "r_last_maj")
    private Date last_maj;

    @Column(name = "r_status")
    private int status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLast_maj() {
        return last_maj;
    }

    public void setLast_maj(Date last_maj) {
        this.last_maj = last_maj;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



}
