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
@Table(name = "t_operation",schema = "sc_metier")
public class Operation  implements Serializable {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_i", nullable = false, unique = true)
    public int id;


    @Column(name = "r_ref",length=20)
    @Valid
    private String code;


    @Column(name = "r_sens",length=10)
    @Valid
    private String sens;

    @Column(name = "r_libelle",length=100)
    @Valid
    @NotNull(message = "Veuillez entrer saisir libelle")
    @NotEmpty(message = "Veuillez entrer saisir libelle")
    private String libelle;


    @Column(name = "r_montant")
    @Valid
    @NotNull()
    private int montant;

    @Column(name = "r_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date date;


    @Column(name = "r_client",length=25)
    @Valid
    private String client;

}
