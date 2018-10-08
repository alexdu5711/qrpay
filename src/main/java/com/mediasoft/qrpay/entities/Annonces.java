package com.mediasoft.qrpay.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_annonces",schema = "sc_metier")
public class Annonces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "r_i", nullable = false, unique = true)
    public int id;


    @Column(name = "r_reference")
    private String reference;

    @Column(name = "r_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)

    private Date date;

    @Column(name = "r_libelle")
    private String libelle;

    @Column(name = "r_prix")
    private String prix;

    @Column(name = "r_image")
    private String image;

    @JsonIgnore
    @Column(name = "r_status")
    private int status;





    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "r_imarchand")
    private Marchand marchand;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Marchand getMarchand() {
        return marchand;
    }

    public void setMarchand(Marchand marchand) {
        this.marchand = marchand;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
