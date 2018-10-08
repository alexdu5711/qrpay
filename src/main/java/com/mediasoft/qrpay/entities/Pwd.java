package com.mediasoft.qrpay.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_pwd",schema = "sc_securite")
public class Pwd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_i", nullable = false, unique = true)
    public int id;

    @Column(name = "r_mdp")
    private String mdp;

    @Column(name = "r_salt")
    private String salt;


    @Column(name = "r_old_mdp")
    private String old_mdp;

    @Column(name = "r_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date date;


    @Column(name = "r_init")
    private int init;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_iuser")
    @JsonIgnore
    private Utilisateur utilisateur;




    public Pwd() {}

    public Pwd(String value,Utilisateur utilisateur) {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        this.salt = RandomStringUtils.random( 15, characters );

        this.mdp = new BCryptPasswordEncoder().encode(value + this.salt);
        this.utilisateur = utilisateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getOld_mdp() {
        return old_mdp;
    }

    public void setOld_mdp(String old_mdp) {
        this.old_mdp = old_mdp;
    }

    public int getInit() {
        return init;
    }

    public void setInit(int init) {
        this.init = init;
    }
}
