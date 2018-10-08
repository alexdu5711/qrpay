package com.mediasoft.qrpay.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_registercode",schema = "sc_systeme")
public class RegisterCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_i", nullable = false, unique = true)
    public int id;

    @Column(name = "r_code",length = 20)
    private String code;

    @JsonIgnore
    @Column(name = "r_status")
    private int status;

    @Column(name = "r_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date date;

    @Column(name = "r_last_maj")
    private Date last_connect;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn( name = "r_iutilisateur" )
    private Utilisateur utilisateur;


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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getLast_connect() {
        return last_connect;
    }

    public void setLast_connect(Date last_connect) {
        this.last_connect = last_connect;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
