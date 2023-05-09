/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.io.Serializable;

/**
 *
 * @author bhk
 */
public class Voiture implements Serializable {

    private int id;
    String matricule, marque, puissance, prix_jours, picture, energie, etat;
    int idlocateur;

    public Voiture() {
    }

    public Voiture(int id, String matricule, String marque, String puissance) {
        this.id = id;
        this.matricule = matricule;
        this.marque = marque;
        this.puissance = puissance;
      
    
    }

    public Voiture(String matricule, String marque, String puissance, String prix_jours, String picture, String energie, String etat, int idlocateur) {
        this.matricule = matricule;
        this.marque = marque;
        this.puissance = puissance;
        this.prix_jours = prix_jours;
        this.picture = picture;
        this.energie = energie;
        this.etat = etat;
        this.idlocateur = idlocateur;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getPuissance() {
        return puissance;
    }

    public void setPuissance(String puissance) {
        this.puissance = puissance;
    }

    public String getPrix_jours() {
        return prix_jours;
    }

    public void setPrix_jours(String prix_jours) {
        this.prix_jours = prix_jours;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEnergie() {
        return energie;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdlocateur() {
        return idlocateur;
    }

    public void setIdlocateur(int idlocateur) {
        this.idlocateur = idlocateur;
    }

    @Override
    public String toString() {
        return "Voiture{" + "id=" + id + ", matricule=" + matricule + ", marque=" + marque + ", puissance=" + puissance + ", prix_jours=" + prix_jours + ", picture=" + picture + ", energie=" + energie + ", etat=" + etat + ", idlocateur=" + idlocateur + '}';
    }

}
