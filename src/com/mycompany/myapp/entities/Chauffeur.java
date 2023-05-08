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
public class Chauffeur {
    int id_ch,gsm,id_role;
    String num_permis,img,email,password;

    public Chauffeur() {
    }

    public Chauffeur(int id_ch, int gsm, int id_role, String num_permis, String email, String password) {
        this.id_ch = id_ch;
        this.gsm = gsm;
        this.id_role = id_role;
        this.num_permis = num_permis;
        this.email = email;
        this.password = password;
    }

    public Chauffeur(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Chauffeur(int gsm, int id_role, String num_permis, String email, String password) {
        this.gsm = gsm;
        this.id_role = id_role;
        this.num_permis = num_permis;
        this.email = email;
        this.password = password;
    }

    public Chauffeur(int gsm, String num_permis, String email, String password) {
        this.gsm = gsm;
        this.num_permis = num_permis;
        this.email = email;
        this.password = password;
    }

    public Chauffeur(int gsm, String num_permis, String img, String email, String password) {
        this.gsm = gsm;
        this.num_permis = num_permis;
        this.img = img;
        this.email = email;
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    

    public int getId_ch() {
        return id_ch;
    }

    public void setId_ch(int id_ch) {
        this.id_ch = id_ch;
    }

    public int getGsm() {
        return gsm;
    }

    public void setGsm(int gsm) {
        this.gsm = gsm;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getNum_permis() {
        return num_permis;
    }

    public void setNum_permis(String num_permis) {
        this.num_permis = num_permis;
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
        return "Chauffeur{" + "id_ch=" + id_ch + ", gsm=" + gsm + ", id_role=" + id_role + ", num_permis=" + num_permis + ", email=" + email + ", password=" + password + '}';
    }
    
    
}
