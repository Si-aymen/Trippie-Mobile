/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author aymen
 */
public class Locateur {
    int id_loc,gsm;
    Role id_role;
    String nom_agence,email,password,img;

    public Locateur() {
    }

    public Locateur(int id_loc, int gsm, Role id_role, String nom_agence, String email, String password) {
        this.id_loc = id_loc;
        this.gsm = gsm;
        this.id_role = id_role;
        this.nom_agence = nom_agence;
        this.email = email;
        this.password = password;
    }

    public Locateur(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Locateur(int gsm, Role id_role, String nom_agence, String email, String password) {
        this.gsm = gsm;
        this.id_role = id_role;
        this.nom_agence = nom_agence;
        this.email = email;
        this.password = password;
    }

    public int getId_loc() {
        return id_loc;
    }

    public void setId_loc(int id_loc) {
        this.id_loc = id_loc;
    }

    public String getNom_agence() {
        return nom_agence;
    }

    public void setNom_agence(String nom_agence) {
        this.nom_agence = nom_agence;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Locateur(int gsm, String nom_agence, String email, String password, String img) {
        this.gsm = gsm;
        this.nom_agence = nom_agence;
        this.email = email;
        this.password = password;
        this.img = img;
    }

    public int getId_ch() {
        return id_loc;
    }

    public void setId_ch(int id_loc) {
        this.id_loc = id_loc;
    }

    public int getGsm() {
        return gsm;
    }

    public void setGsm(int gsm) {
        this.gsm = gsm;
    }

    public Role getId_role() {
        return id_role;
    }

    public void setId_role(Role id_role) {
        this.id_role = id_role;
    }

  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    @Override
    public String toString() {
        return "Locateur{" + "id_loc=" + id_loc + ", gsm=" + gsm + ", id_role=" + id_role + ", nom_agence=" + nom_agence + ", email=" + email + ", password=" + password + '}';
    }
    
    
}
