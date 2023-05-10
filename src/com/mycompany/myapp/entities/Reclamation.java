/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class Reclamation implements Serializable {
   private int id ; 
   private String type,commentaire,etat ; 
   private int id_user; 
   private Date date_creation ; 
   private String image ; 

    public Reclamation() {
    }

    public Reclamation(int id, String type, String commentaire, String etat, int id_user, String image) {
        this.id = id;
        this.type = type;
        this.commentaire = commentaire;
        this.etat = etat;
        this.id_user = id_user;
    
        this.image = image;
    }

    public Reclamation(String type, String commentaire, String etat, String image) {
        this.type = type;
        this.commentaire = commentaire;
        this.etat = etat;
  

        this.image = image;
    }

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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type=" + type + ", commentaire=" + commentaire + ", etat=" + etat + ", id_user=" + id_user + ", date_creation=" + date_creation + ", image=" + image + '}';
    }
   
   
    
 
}
