package com.mediasoft.qrpay.forms;

import com.mediasoft.qrpay.entities.Utilisateur;

public class Retour_login {

    private int status;
    private String session ;

    private Utilisateur users ;


    public Utilisateur getUsers() {
        return users;
    }

    public void setUsers(Utilisateur users) {
        this.users = users;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
