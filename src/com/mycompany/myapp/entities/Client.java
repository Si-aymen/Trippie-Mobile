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
public class Client {
    int id_client,gsm;
    String email,password,img;
    Role id_role;

    public Client(int id_client, int gsm, String email, String password, String img) {
        this.id_client = id_client;
        this.gsm = gsm;
        this.email = email;
        this.password = password;
        this.img = img;
    }

    public Client(int id_client) {
        this.id_client = id_client;
    }

    public Client() {
    }

    public Client(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Client(int gsm, String email, String password, String img) {
        this.gsm = gsm;
        this.email = email;
        this.password = password;
        this.img = img;
    }

    public Client(int id_client, int gsm, String email, String password, String img, Role id_role) {
        this.id_client = id_client;
        this.gsm = gsm;
        this.email = email;
        this.password = password;
        this.img = img;
        this.id_role = id_role;
    }

    public Client(int gsm, String email, String password, String img, Role id_role) {
        this.gsm = gsm;
        this.email = email;
        this.password = password;
        this.img = img;
        this.id_role = id_role;
    }
    

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getGsm() {
        return gsm;
    }

    public void setGsm(int gsm) {
        this.gsm = gsm;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Role getId_role() {
        return id_role;
    }

    public void setId_role(Role id_role) {
        this.id_role = id_role;
    }
  
    
    
    
    
}
