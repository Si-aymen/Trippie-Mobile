/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Voiture;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceVoiture {

    public ArrayList<Voiture> voitures;

    public static ServiceVoiture instance = null;
    public boolean resultOK;
    private ConnectionRequest req, cr;
    public int resultCode;

    private ServiceVoiture() {
        req = new ConnectionRequest();
    }

    public static ServiceVoiture getInstance() {
        if (instance == null) {
            instance = new ServiceVoiture();
        }
        return instance;
    }

    public boolean addTask(Voiture v) {
        String url = Statics.BASE_URL + "/addVoitureJSON/new?matricule=" + v.getMatricule()
                + "&marque=" + v.getMarque() + "&puissance=" + v.getPuissance()
                + "&prix_jours=" + v.getPrix_jours() + "&picture=" + v.getPicture()
                + "&energie=" + v.getEnergie() + "&etat=" + v.getEtat()
                + "&id_locateur=" + v.getIdlocateur();

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Voiture> getAll() {
        voitures = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/voituremobile/list");
        System.out.println("url = " + cr);
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    voitures = (ArrayList<Voiture>) getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return voitures;
    }

    private ArrayList<Voiture> getList() {
        JSONParser jsonp;
        jsonp = new JSONParser();

        try {
            Map<String, Object> parsedJson = jsonp.parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");
            for (Map<String, Object> obj : list) {
                Voiture r = new Voiture();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setMatricule((String) obj.get("matricule").toString());
                r.setMarque((String) obj.get("marque").toString());
                r.setPuissance((String) obj.get("puissance").toString());
                //      r.setPrix_jours((String) obj.get("prix_jours").toString());
                //    r.setPicture((String) obj.get("picture").toString());
                // r.setEnergie((String) obj.get("energie").toString());
                // r.setEtat((String) obj.get("etat").toString());

                r.setId((int) id);

                voitures.add(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return voitures;
    }

    public int delete(int id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/deleteVoitureJSON/" + id);
        System.out.println("url delete : " + cr);
        cr.setHttpMethod("POST");
        // cr.addArgument("id", String.valueOf(id));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }

    public int edit(Voiture v) {
        return manage(v);
    }

    public boolean modifier(Voiture v) {

        String url = Statics.BASE_URL + "/updateVoitureJSON/" + v.getId() + "?matricule=" + v.getMatricule() + "&marque=" + v.getMarque() + "&puissance=" + v.getPuissance() + "&prix_jours="
                + v.getPrix_jours() + "&picture=" + v.getPicture() + "&energie=" + v.getEnergie() + "&etat="
                + "non reservé" + "&id_locateur=" + v.getIdlocateur() + "";

        req.setUrl(url);

        req.setHttpMethod("POST");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public int manage(Voiture v) {

        cr = new ConnectionRequest();

        cr.setHttpMethod("GET");
        cr.setUrl(Statics.BASE_URL + "/updateVoitureJSON/?id=" + (int) v.getId() + "&matricule=" + v.getMatricule() + "&marque=" + v.getMarque() + "&puissance=" + v.getPuissance()
                + "&prix_jours=" + v.getPrix_jours() + "&picture=" + v.getPicture()
                + "&energie=" + v.getEnergie() + "&etat=" + v.getEtat()
                + "&id_locateur=" + v.getIdlocateur());

        cr.addArgument("id", String.valueOf(v.getId()));
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

}
