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
 * @author mtirn
 */
public class cartefidelite  implements Serializable {
    private int id_cf ;
    private String pointMerci;
    private Date dateExpiration ;
    private int idA;

    public cartefidelite() {
    }

    public cartefidelite(int idA) {
        this.idA = idA;
    }

    public cartefidelite(String pointMerci) {
        this.pointMerci = pointMerci;
    }
    

    public cartefidelite(int id_cf, String pointMerci, Date dateExpiration, int idA) {
        this.id_cf = id_cf;
        this.pointMerci = pointMerci;
        this.dateExpiration = dateExpiration;
        this.idA = idA;
    }

    public cartefidelite(String pointMerci, Date dateExpiration, int idA) {
        this.pointMerci = pointMerci;
        this.dateExpiration = dateExpiration;
        this.idA = idA;
    }

    public cartefidelite(String pointMerci, Date dateExpiration) {
        this.pointMerci = pointMerci;
        this.dateExpiration = dateExpiration;
    }

    public int getId_cf() {
        return id_cf;
    }

    public String getPointMerci() {
        return pointMerci;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public int getIdA() {
        return idA;
    }

    public void setId_cf(int id_cf) {
        this.id_cf = id_cf;
    }

    public void setPointMerci(String pointMerci) {
        this.pointMerci = pointMerci;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    @Override
    public String toString() {
        return "cartefidelite{" + "id_cf=" + id_cf + ", pointMerci=" + pointMerci + ", dateExpiration=" + dateExpiration + ", idA=" + idA + '}';
    }

   

    
    
   
   
    
}


