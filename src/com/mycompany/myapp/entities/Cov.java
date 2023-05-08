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
public class Cov implements Serializable {

    private int id;
    private String depart, destination;
    private int nbre_place;

    public Cov() {
    }

    public Cov(int id, String depart, String destination, int nbre_place) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
        this.nbre_place = nbre_place;

    }

    public Cov(String depart, String destination, int nbre_place) {

        this.depart = depart;
        this.destination = destination;
        this.nbre_place = nbre_place;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNbre_place() {
        return nbre_place;
    }

    public void setNbre_place(int nbre_place) {
        this.nbre_place = nbre_place;
    }
    

    @Override
    public String toString() {
        return "Cov{" + "id=" + id + ", depart=" + depart + ", destination=" + destination + ", nbre_place=" + nbre_place + '}';
    }

 

}
