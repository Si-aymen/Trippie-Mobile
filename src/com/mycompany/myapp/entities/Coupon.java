/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Coupon {
      private int id;
    private String date_debut;
    private String date_expiratio;
    private int taux;
    private String code_coupon;
    private int nbr_utilisation;
    private String type;
    

    public Coupon() {
    }

    public Coupon(int id, String date_debut, String date_expiratio, int taux, String code_coupon, int nbr_utilisation, String type) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_expiratio = date_expiratio;
        this.taux = taux;
        this.code_coupon = code_coupon;
        this.nbr_utilisation = nbr_utilisation;
        this.type = type;
    }

    public Coupon(String date_debut, String date_expiratio, int taux, String code_coupon, int nbr_utilisation, String type) {
        this.date_debut = date_debut;
        this.date_expiratio = date_expiratio;
        this.taux = taux;
        this.code_coupon = code_coupon;
        this.nbr_utilisation = nbr_utilisation;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_expiratio() {
        return date_expiratio;
    }

    public void setDate_expiratio(String date_expiratio) {
        this.date_expiratio = date_expiratio;
    }

    public int getTaux() {
        return taux;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }

    public String getCode_coupon() {
        return code_coupon;
    }

    public void setCode_coupon(String code_coupon) {
        this.code_coupon = code_coupon;
    }

    public int getNbr_utilisation() {
        return nbr_utilisation;
    }

    public void setNbr_utilisation(int nbr_utilisation) {
        this.nbr_utilisation = nbr_utilisation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Coupon{" + "id=" + id + ", date_debut=" + date_debut + ", date_expiratio=" + date_expiratio + ", taux=" + taux + ", code_coupon=" + code_coupon + ", nbr_utilisation=" + nbr_utilisation + ", type=" + type + '}';
    }
     
}

